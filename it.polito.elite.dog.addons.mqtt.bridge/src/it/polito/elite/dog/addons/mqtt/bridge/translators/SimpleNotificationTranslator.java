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

import it.polito.elite.dog.core.library.model.notification.Notification;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.measure.Measure;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.osgi.service.log.LogService;

/**
 * A simple implementation of {@link NotificationTranslator} supporting basic
 * {@link HashMap} representation of notification contents and relative
 * serialization in JSON.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 *
 */
public class SimpleNotificationTranslator implements NotificationTranslator
{
	private ObjectMapper mapper;

	public SimpleNotificationTranslator()
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

	@Override
	public byte[] translateNotification(Notification notification)
	{
		return this.translateNotification(notification, null);
	}

	@Override
	public byte[] translateNotification(Notification notification,
			LogHelper logger)
	{

		HashMap<String, String> notificationContent = new HashMap<String, String>();

		if (notification != null)
		{
			// transparently translate the notification fields into a key-value
			// representation, as this is the baseline transformer a more
			// "complex" and "purposeful" approach is expected in "specific"
			// translator implementations.
			for (Field notificationField : notification.getClass()
					.getDeclaredFields())
			{
				// override access protection
				notificationField.setAccessible(true);

				// get the field name
				String notificationFieldName = notificationField.getName();

				// get the field value (only Strings or Measures)
				Object notificationFieldValue = null;
				try
				{
					String notificationFieldValueFinal = "";
					notificationFieldValue = notificationField
							.get(notification);
					// the content could be a measure or a string only
					if (notificationFieldValue instanceof Measure<?, ?>)
						notificationFieldValueFinal = notificationFieldValue
								.toString();
					else if (notificationFieldValue instanceof String)
						notificationFieldValueFinal = (String) notificationFieldValue;

					// extract the notification topic (without osgi-specific
					// information)
					if (notificationFieldName.equals("notificationTopic"))
					{
						String[] fieldValueFinalParts = notificationFieldValueFinal
								.split("/");
						notificationFieldValueFinal = fieldValueFinalParts[fieldValueFinalParts.length - 1];
					}

					// insert the acquired information in the map
					notificationContent.put(notificationFieldName,
							notificationFieldValueFinal);
				}
				catch (Exception e)
				{
					// if something went wrong we want to continue with
					// the other notification fields
					if (logger != null)
						logger.log(
								LogService.LOG_WARNING,
								"Ops! Something goes wrong in parsing a notification... skip!",
								e);
					else
						e.printStackTrace(System.err);
				}
			}
		}

		// the resulting map shall be wrapped into a json string
		String translatedNotification = null;
		try
		{
			translatedNotification = this.mapper
					.writeValueAsString(notificationContent);
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

		return (translatedNotification != null) ? translatedNotification
				.getBytes() : new byte[0];
	}

}
