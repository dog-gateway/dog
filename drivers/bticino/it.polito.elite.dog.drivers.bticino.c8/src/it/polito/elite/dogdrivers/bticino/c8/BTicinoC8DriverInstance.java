/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2010-2014 Emiliano Castellina and Luigi De Russis
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
package it.polito.elite.dogdrivers.bticino.c8;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.SnapshotCamera;
import it.polito.elite.dog.core.library.model.state.OnOffState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.OffStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OnStateValue;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoNetworkDriver;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoSpecificDriver;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.bticino.core.OpenWebNet;

/**
 * 
 * @author <a href="mailto:castellina.emi@gmail.com">Emiliano Castellina</a>
 *         (original version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class BTicinoC8DriverInstance implements BTicinoSpecificDriver, SnapshotCamera
{
	
	BTicinoNetworkDriver network;
	ControllableDevice device;
	String address;
	
	private BTicinoC8Driver driver;
	private final String protocol = "https://";
	private final String cameraPage = "/telecamera.php";
	
	private final String type = "7";
	private String where;
	private final String on = "0";
	private final String off = "8";
	private final String zoom_in = "120";
	private final String zoom_out = "121";
	private final String increaseLuminosity = "150";
	private final String decreaseLuminosity = "151";
	private final String increaseContrast = "160";
	private final String decreaseContrast = "161";
	private final String increaseQuality = "180";
	private final String decreaseQuality = "181";
	private final String increaseColor = "170";
	private final String decreaseColor = "171";
	private final String panUp = "141";
	private final String panDown = "140";
	private final String panLeft = "131";
	private final String panRight = "130";
	private URL cameraURL;
	private DeviceStatus deviceState;
	private TrustManager[] tm;
	private HostnameVerifier hv;
	
	public BTicinoC8DriverInstance(ControllableDevice device, BTicinoC8Driver driver, BTicinoNetworkDriver network)
	{
		this.device = device;
		this.network = network;
		this.driver = driver;
		this.deviceState = new DeviceStatus(this.device.getDeviceId());
		this.deviceState.setState(OnOffState.class.getSimpleName(), new OnOffState(new OffStateValue()));
		
		Set<String> whereList = (Set<String>) device.getDeviceDescriptor().getSimpleConfigurationParams()
				.get(BTicinoNetworkDriver.ADDRESS);
		
		if (whereList != null && !whereList.isEmpty())
		{
			this.where = whereList.iterator().next();
			this.network.bind(this, this.where);
			
			this.readStatus();
			this.device.setDriver(this);
			this.createURL(protocol + this.network.getIpAddress() + cameraPage);
			this.configHttps();
			
		}
		
	}
	
	private void configHttps()
	{
		this.tm = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers()
			{
				return null;
			}
			
			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
			{
				// intentionally left empty
				
			}
			
			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
			{
				// intentionally left empty
				
			}
		} };
		
		this.hv = new HostnameVerifier() {
			
			@Override
			public boolean verify(String hostname, SSLSession session)
			{
				// intentionally left empty
				return true;
			}
		};
		
	}
	
	private void readStatus()
	{
		OpenWebNet message = new OpenWebNet();
		message.CreateStateMsgOpen(type, where);
		this.network.sendMyOpenMessage(message, 50);
	}
	
	private void createURL(String urlStr)
	{
		if (urlStr != null)
		{
			try
			{
				this.cameraURL = new URL(urlStr);
			}
			catch (MalformedURLException e)
			{
				
				e.printStackTrace();
				this.cameraURL = null;
			}
		}
	}
	
	public void unSetDriver()
	{
		if (this.device != null && this.driver != null)
			this.device.unSetDriver(this.driver);
	}
	
	public String getAddress()
	{
		return this.where;
	}
	
	@Override
	public void recieveLowLevelMessage(OpenWebNet message)
	{
		
		String what = message.getCosa();
		if (what != null)
		{
			OnOffState onOffState = null;
			if (what.equals(on))
			{
				onOffState = new OnOffState(new OnStateValue());
			}
			else if (what.equals(off))
			{
				onOffState = new OnOffState(new OffStateValue());
			}
			
			if (onOffState != null)
			{
				// modify the state of the device
				this.setInnerState(onOffState);
			}
		}
	}
	
	/**
	 * Set the inner OnOffState and notify the change of States
	 * 
	 * @param onOffState
	 *            new state.
	 */
	private void setInnerState(OnOffState onOffState)
	{
		this.deviceState.setState(onOffState.getStateName(), onOffState);
		((SnapshotCamera) this.device).notifyStateChanged(onOffState);
		
	}
	
	private void sendLowLevelMessage(String messageStr, String address)
	{
		OpenWebNet message = new OpenWebNet();
		message.createMsgOpen("*" + type + "*" + messageStr + "*" + address + "*##");
		network.sendMyOpenMessage(message, 50);
	}
	
	@Override
	public void decreaseColor()
	{
		this.sendLowLevelMessage(decreaseColor, "");
	}
	
	@Override
	public void decreaseContrast()
	{
		this.sendLowLevelMessage(decreaseContrast, "");
	}
	
	@Override
	public void decreaseLuminosity()
	{
		
		this.sendLowLevelMessage(decreaseLuminosity, "");
	}
	
	@Override
	public void decreaseQuality()
	{
		
		this.sendLowLevelMessage(decreaseQuality, "");
	}
	
	@Override
	public Image grabPicture()
	{
		BufferedImage bimg = null;
		
		if (this.deviceState != null
				&& this.deviceState.getState(OnOffState.class.getSimpleName()).getCurrentStateValue()[0].getValue()
						.equals(OnOffState.ON))
		{
			try
			{
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, this.tm, new java.security.SecureRandom());
				
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				HttpsURLConnection.setDefaultHostnameVerifier(this.hv);
				
				URLConnection connection = this.cameraURL.openConnection();
				connection.setUseCaches(false);
				if (connection instanceof HttpsURLConnection)
				{
					ImageInputStream iis = ImageIO.createImageInputStream(connection.getInputStream());
					Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
					
					ImageReader reader = null;
					if (readers.hasNext())
					{
						reader = readers.next();
						reader.setInput(iis);
						bimg = reader.read(0);
						reader.dispose();
						iis.close();
						
						return bimg.getScaledInstance(bimg.getWidth(), bimg.getHeight(), BufferedImage.SCALE_DEFAULT);
					}
				}
			}
			catch (IOException e)
			{
				
				e.printStackTrace();
			}
			catch (NoSuchAlgorithmException e)
			{
				
				e.printStackTrace();
			}
			catch (KeyManagementException e)
			{
				
				e.printStackTrace();
			}
		}
		
		// if device is off or image could not be retrieved
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("images/noimg.jpg");
		try
		{
			bimg = ImageIO.read(in);
		}
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		
		if (bimg != null)
			return bimg.getScaledInstance(bimg.getWidth(), bimg.getHeight(), BufferedImage.SCALE_DEFAULT);
		return null;
	}
	
	@Override
	public void increaseColor()
	{
		// risoluzione telecamera bticino f452v
		this.sendLowLevelMessage(increaseColor, "");
	}
	
	@Override
	public void increaseContrast()
	{
		
		this.sendLowLevelMessage(increaseContrast, "");
	}
	
	@Override
	public void increaseLuminosity()
	{
		
		this.sendLowLevelMessage(increaseLuminosity, "");
	}
	
	@Override
	public void increaseQuality()
	{
		
		this.sendLowLevelMessage(increaseQuality, "");
	}
	
	@Override
	public void off()
	{
		
		this.sendLowLevelMessage(off, "");
		OnOffState onOffState = new OnOffState(new OffStateValue());
		this.setInnerState(onOffState);
	}
	
	@Override
	public void on()
	{
		
		this.off();
		this.sendLowLevelMessage(on, where);
		this.readStatus();
	}
	
	@Override
	public void panDown()
	{
		
		this.sendLowLevelMessage(panDown, "");
	}
	
	@Override
	public void panLeft()
	{
		
		this.sendLowLevelMessage(panLeft, "");
	}
	
	@Override
	public void panRight()
	{
		
		this.sendLowLevelMessage(panRight, "");
	}
	
	@Override
	public void panUp()
	{
		
		this.sendLowLevelMessage(panUp, "");
	}
	
	@Override
	public void zoomIn()
	{
		
		this.sendLowLevelMessage(zoom_in, "");
	}
	
	@Override
	public void zoomOut()
	{
		
		this.sendLowLevelMessage(zoom_out, "");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.model.devicecategory.SnapshotCamera#getState()
	 */
	@Override
	public DeviceStatus getState()
	{
		
		return this.deviceState;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.domotics.model.devicecategory.SnapshotCamera#
	 * notifyStateChanged(it.polito.elite.domotics.model.state.State)
	 */
	@Override
	public void notifyStateChanged(State newState)
	{
		// intentionally left empty
		
	}
	
}
