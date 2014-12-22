/*
 * Dog - Addons - Mqtt
 * 
 * Copyright (c) 2013-2014 Dario Bonino
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
package it.polito.elite.dog.addons.mqtt.bridge.translators;

import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.StateValue;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.measure.Measure;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.osgi.service.log.LogService;

/**
 * A simple implementation of {@link StateTranslator} supporting basic
 * {@link HashMap} representation of device status contents and the relative
 * serialization in JSON.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 *
 */
public class SimpleStateTranslator implements StateTranslator
{

	private ObjectMapper mapper;

	/**
	 * 
	 */
	public SimpleStateTranslator()
	{
		// initialize the instance-wide object mapper
		this.mapper = new ObjectMapper();
		// set the mapper pretty printing
		this.mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		// avoid empty arrays and null values
		this.mapper.configure(
				SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, false);
		this.mapper.setSerializationInclusion(Inclusion.NON_NULL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.addons.mqtt.bridge.translators.StateTranslator#
	 * translateState(it.polito.elite.dog.core.library.model.DeviceStatus)
	 */
	@Override
	public byte[] translateState(DeviceStatus state)
	{
		return this.translateState(state, null);
	}

	public byte[] translateState(DeviceStatus state, LogHelper logger)
	{
		// the state content to be translated in json
		Map<String, Object[]> stateContent = new HashMap<String, Object[]>();

		// the states composing a single device status
		Map<String, State> allStates = null;

		if (state != null)
		{
			allStates = state.getStates();

			// check if the device state is available, i.e., not null
			if (allStates != null)
			{
				// iterate over all states
				for (String stateKey : allStates.keySet())
				{
					// get the current state
					State currentState = allStates.get(stateKey);

					// get the values associate to the current state
					StateValue currentStateValues[] = currentState
							.getCurrentStateValue();

					// create the json-ready state values
					Object responseBodyStateValues[] = new Object[currentStateValues.length];

					// iterate over the state values
					for (int j = 0; j < currentStateValues.length; j++)
					{
						// get state value features
						HashMap<String, Object> features = currentStateValues[j]
								.getFeatures();

						// prepare the map to store in the response
						// body
						HashMap<String, Object> responseBodyFeatures = new HashMap<String, Object>();

						// iterate over the features
						for (String featureKey : features.keySet())
						{
							// check the "value" feature and, if it
							// is an instance of measure, serialize
							// it as a String
							if (featureKey.contains("Value"))
							{
								if (features.get(featureKey) instanceof Measure<?, ?>)
									responseBodyFeatures.put("value", features
											.get(featureKey).toString());
								else
									responseBodyFeatures.put("value",
											features.get(featureKey));

							}
							else
							{
								Object value = features.get(featureKey);

								if ((!(value instanceof String))
										|| ((value instanceof String) && (!((String) value)
												.isEmpty())))
									responseBodyFeatures.put(featureKey,
											features.get(featureKey));
							}

						}

						// store the current state value
						responseBodyStateValues[j] = responseBodyFeatures;
					}

					// store the state
					stateContent.put(currentState.getClass().getSimpleName(),
							responseBodyStateValues);
				}
			}
		}

		// the final json translation
		String translatedState = null;
		
		// check that everything is in place
		if ((stateContent != null) && (!stateContent.isEmpty()))
		{
			try
			{
				translatedState = this.mapper.writeValueAsString(stateContent);
			}
			catch (IOException e)
			{
				// the other notification fields
				if (logger != null)
					logger.log(
							LogService.LOG_WARNING,
							"Ops! Something went wrong in transforming a notification... skip!",
							e);
				else
					e.printStackTrace(System.err);
			}
			
		}
		
		return (translatedState!=null)?translatedState.getBytes():new byte[0];
	}

}
