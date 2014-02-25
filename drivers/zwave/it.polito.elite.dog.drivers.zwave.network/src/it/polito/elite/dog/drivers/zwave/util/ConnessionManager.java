/*
 * Dog  - Z-Wave
 * 
 * Copyright 2013 Davide Aimone  and Dario Bonino 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package it.polito.elite.dog.drivers.zwave.util;

import it.polito.elite.dog.drivers.zwave.model.zway.json.ZWaveModelTree;

import java.io.IOException;

//import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ConnessionManager {
	// the log identifier, unique for the class
	public static String LOG_ID = "[ZWaveConnessionManager]: ";

	// the logger
	private LogService logger;

	public static final String DATA_PATH = "/ZWaveAPI/Data";
	public static final String RUN_PATH = "/ZWaveAPI/Run";
	private static ConnessionManager connessionManager = null;

	protected String sURL;
	protected String sUser;
	protected String sPassword;
	protected String authToken;

	protected Client client;
	protected WebTarget service;
	private String sLastError;

	// Tree representing system status
	ZWaveModelTree zWaveModelTree = null;

	// Json tree representig the system status, used for partial update
	private JsonNode zWaveTree;

	// Convert JSON to Java object
	ObjectMapper mapper = new ObjectMapper();

	// last update
	long lastUpdate = 0;

	private ConnessionManager(String sURL, String sUser, String sPassword,
			BundleContext bundleContext) {
		this.sURL = sURL;
		this.sUser = sUser;
		this.sPassword = sPassword;
		this.authToken = this.getAuthToken();

		// SSL manage
		/*
		 * HostnameVerifier hv = new javax.net.ssl.HostnameVerifier() {
		 * 
		 * public boolean verify(String hostname, javax.net.ssl.SSLSession
		 * sslSession) { if (hostname.equals("z-cloud.z-wave.me")) { return
		 * true; } return false; } };
		 */

		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("SSL");
			ctx.init(null, null, null);
		} catch (Exception e1) {
			logger.log(LogService.LOG_ERROR, LOG_ID, e1);
		}

		/*
		 * ClientConfig clientConfig = new DefaultClientConfig();
		 * //clientConfig.
		 * getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
		 * Boolean.TRUE);
		 * //clientConfig.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS
		 * , true); clientConfig.getClasses().add(JacksonJsonProvider.class);
		 * try { clientConfig.getProperties().put(HTTPSProperties.
		 * PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(hv,ctx)); } catch
		 * (Exception e) { logger.log(LogService.LOG_ERROR, LOG_ID, e); }
		 * 
		 * 
		 * client = Client.create(clientConfig);
		 */
		client = ClientBuilder.newBuilder().sslContext(ctx)
				.register(JacksonFeature.class).build();
		service = client.target(sURL);
	}

	/**
	 * Obtain the current ConnessionManager
	 * 
	 * @return ConnessionManager, may be null
	 */
	public static ConnessionManager get() {
		return connessionManager;
	}

	/**
	 * Obtain an instance of ConnessionManager, with the given params. If one of
	 * them changed or if no ConnessionManager already exists, a new one is
	 * created
	 * 
	 * @param sURL
	 * @param sUser
	 * @param sPassword
	 * @return ConnessionManager
	 */
	public static ConnessionManager get(String sURL, String sUser,
			String sPassword, BundleContext bundleContext) {
		// create a new instance if needed, or returns the current one
		if (connessionManager == null
				|| !sURL.equals(connessionManager.getURL())
				|| !sUser.equals(connessionManager.getUser())
				|| !sPassword.equals(connessionManager.getPassword()))
			connessionManager = new ConnessionManager(sURL, sUser, sPassword,
					bundleContext);

		return connessionManager;
	}

	/**
	 * Test the current configuration with a broadcast ping. Call getLastError()
	 * to discover causes of fail
	 * 
	 * @return true if succeeded, false otherwise.
	 */
	public boolean testConnection() {
		// devices[255] is the broadcast
		// return sendCommandBoolean("devices[255].SendNoOperation()");
		return true;
	}

	/**
	 * Query zway-server for an update of the system status since the lSince
	 * param. Implementation of partial update is 2-3 times faster than a full
	 * update.
	 * 
	 * @param lSince
	 *            Unix-timestamp representing the last update, 0 for a full
	 *            query
	 * @return ZWaveModelTree representing the full system
	 * @throws Exception
	 * 
	 */
	public ZWaveModelTree updateDevices(long lSince) throws Exception {
		Response response = service.path(DATA_PATH)
				.path(String.valueOf(lSince))
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", "Basic " + getAuthToken()).post(null); // post
																				// is
																				// mandatory

		if (response.getStatus() == Status.OK.getStatusCode()) {
			String json = response.readEntity(String.class);

			// Convert JSON to Java object
			try {
				// in this case we process the whole data
				if (zWaveModelTree == null || lSince == 0) {
					// System.out.println(json);//use
					// http://jsoneditoronline.org/ for a friendly UI
					zWaveModelTree = mapper.readValue(json,
							ZWaveModelTree.class);
					zWaveTree = mapper.readTree(json);
				} else
				// otherwise we proceed to update the tree
				{
					zWaveModelTree = JsonUpdate.updateModel(mapper, zWaveTree,
							json);// devices.26.instances.0.commandClasses.49.data.1.val
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		} else {
			throw new Exception("Can't read json from Z-Way server: "
					+ response.toString());
		}

		return zWaveModelTree;
	}

	/**
	 * Query zway-server for an update of the system status. It's equivalent to
	 * updateDevices(ZWaveModelTree.getLastUpdate())
	 * 
	 * @return ZWaveModelTree representing the full system
	 * @throws Exception
	 */
	public ZWaveModelTree updateDevices() throws Exception {
		updateDevices(lastUpdate);
		// Update time of the last update
		lastUpdate = zWaveModelTree.getUpdateTime();
		return zWaveModelTree;
	}

	/**
	 * Send a command and returns a boolean
	 * 
	 * @param sCommand
	 * @return if client response status is ok returns true, otherwise false.
	 *         Call getLastError() to discover causes of fail
	 */
	protected boolean sendCommandBoolean(String sCommand) {
		boolean bSuccess = true;

		try {
			sendCommand(sCommand);
		} catch (Exception e) {
			bSuccess = false;
			sLastError = e.getMessage();
		}

		return bSuccess;
	}

	public String sendCommand(String sCommand) throws Exception {
		String jsonResponse = null;

		Response response = service.path(RUN_PATH).path(sCommand)
				//
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", "Basic " + this.authToken).post(null); // post
																				// obbligatoria

		if (response.getStatus() == Status.OK.getStatusCode()) {
			jsonResponse = response.readEntity(String.class);
		} else {
			throw new Exception(
					"Can't read json from Z-Way server: " + response.toString());
		}

		return jsonResponse;
	}

	public String pingDevice(String sNodeId) throws Exception {
		return sendCommand("devices[" + sNodeId + "].SendNoOperation()");
	}

	// TODO: metodo di autenticazione debole. Non esistono alternative?
	private String getAuthToken() {
		byte[] btToken = Base64.encode((this.getUser() + ":" + getPassword())
				.getBytes());
		return new String(btToken);
	}

	public String getURL() {
		return sURL;
	}

	public String getUser() {
		return sUser;
	}

	public String getPassword() {
		return sPassword;
	}

	public String getLastError() {
		return sLastError;
	}
}
