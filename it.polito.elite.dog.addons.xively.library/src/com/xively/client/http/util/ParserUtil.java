// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;

import com.xively.client.http.util.exception.ParseFromObjectException;
import com.xively.client.http.util.exception.ParseToObjectException;
import com.xively.client.model.ApiKey;
import com.xively.client.model.DomainObject;
import com.xively.client.model.Datapoint;
import com.xively.client.model.Datastream;
import com.xively.client.model.Feed;
import com.xively.client.model.Trigger;


/**
 * Helper class for parsing to and from json for API calls
 * 
 * @author s0pau
 */
public class ParserUtil
{
	private static Logger log = Logger.getLogger(ParserUtil.class);

	private static ObjectMapper objectMapper;

	// TODO use annotations to metaprogram the parsing behaviour instead
	enum ParseArg
	{
		feed(Feed.class, null, false, null, false, "id"),
		datastream(Datastream.class, null, true, "datastreams", true, "id"),
		datapoint(Datapoint.class, null, false, "datapoints", true, "at"),
		trigger(Trigger.class, null, false, null, false, "id"),
		apikey(ApiKey.class, "key", false, "key", false, "api_key");

		@SuppressWarnings("rawtypes")
		private Class objClass;
		private String updateRoot;
		private boolean isUpdateHasId;
		private String createRoot;
		private boolean isCreateHasId;
		private String idName;

		ParseArg(@SuppressWarnings("rawtypes") Class objClass, String updateRoot, boolean isUpdateHasId, String createRoot, boolean isCreateHasId, String idName)
		{
			this.objClass = objClass;
			this.updateRoot = updateRoot;
			this.isUpdateHasId = isUpdateHasId;
			this.createRoot = createRoot;
			this.isCreateHasId = isCreateHasId;
			this.idName = idName;
		}

		@SuppressWarnings("rawtypes")
		public Class getObjClass()
		{
			return objClass;
		}

		public String getUpdateRoot()
		{
			return updateRoot;
		}

		public boolean isUpdateHasId()
		{
			return isUpdateHasId;
		}

		public String getCreateRoot()
		{
			return createRoot;
		}

		public boolean isCreateHasId()
		{
			return isCreateHasId;
		}

		public String getIdName()
		{
			return idName;
		}

		public static ParseArg valueOf(DomainObject obj)
		{
			for (ParseArg arg : ParseArg.values())
			{
				if (obj.getClass() == arg.objClass)
				{
					return arg;
				}
			}

			return null;
		}

		public static ParseArg valueOf(@SuppressWarnings("rawtypes") Class objClass)
		{
			for (ParseArg arg : ParseArg.values())
			{
				if (objClass == arg.objClass)
				{
					return arg;
				}
			}

			return null;
		}
	}

	/**
	 * Get the list of model objects and create json as expected by the API.
	 * 
	 * @param isUpdate
	 *            parse object to body suitable for updates if true; for
	 *            creates, otherwise
	 * @param models
	 *            models to be parsed to body
	 * 
	 * @return json string suitable for Xively API consumption
	 * @throws ParseToObjectException
	 *             if unable to completely parse from model to json or if models
	 *             is empty or null
	 */
	public static <T extends DomainObject> String toJson(boolean isUpdate, T... models)
	{
		log.debug("Parsing models to json");

		if (models == null || models.length == 0)
		{
			throw new ParseToObjectException("No model to parse to object");
		}

		if (models.length > 1 && (models[0] instanceof Feed) && (models[0] instanceof Trigger))
		{
			throw new UnsupportedOperationException(String.format("Bulk operation is not currently supported for %s", models[0]
					.getClass().getSimpleName()));
		}

		ParseArg arg = ParseArg.valueOf(models[0]);

		// TODO strip nodes that has a null key?
		String json = null;

		try
		{
			// ADD ROOT
			// Setting SerializationConfig.Feature.WRAP_ROOT_VALUE at mapper
			// did not read annotated label properly, use withRootName
			if (isUpdate && arg.updateRoot != null)
			{
				ObjectMapper mapper = getObjectMapper();
				mapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
				mapper.getSerializationConfig().withAnnotationIntrospector( new JacksonAnnotationIntrospector());
				json = mapper.writer().writeValueAsString(models);
			} else if (!isUpdate && arg.createRoot != null)
			{
				ObjectMapper mapper = getObjectMapper();
				mapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
				mapper.getSerializationConfig().withAnnotationIntrospector( new JacksonAnnotationIntrospector());
				json = mapper.writer().writeValueAsString(models);
			} else
			{
				T model = models[0];
				ObjectMapper mapper = getObjectMapper();
				mapper.getSerializationConfig().withAnnotationIntrospector( new JacksonAnnotationIntrospector());
				json = mapper.writeValueAsString(model);
			}

			// ApiKey needs to be wrapped in a root node without the array
			// container, hack the standards!
			if (arg.objClass == ApiKey.class)
			{
				int start = json.indexOf("\"key\":[") + 6;
				int end = json.lastIndexOf("}") - 1;
				StringBuilder sb = new StringBuilder(json);
				sb.setCharAt(start, ' ');
				sb.setCharAt(end, ' ');
				json = sb.toString();
			}
		} catch (IOException e)
		{
			throw new ParseFromObjectException("Cannot parse model to object", e);
		}

		log.debug(String.format("Parsed json from models: %s", json));
		return json;
	}

	public static <T extends DomainObject> Collection<T> toConnectedObjects(String body, @SuppressWarnings("rawtypes") Class elementType)
	{
		log.debug(String.format("Parsing string to objects: %s", body));

		Collection<T> objs;
		CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, elementType);

		try
		{
			JsonNode node = getObjectMapper().reader().readTree(body);
			JsonNode total = node.get("totalResults");
			if (total != null)
			{
				JsonNode results = node.get("results");
				objs = getObjectMapper().readValue(results.toString(), collectionType);
				log.debug(String.format("Parsed string to %s objects", total));
			} else
			{
				objs = getObjectMapper().readValue(body, collectionType);
			}
		} catch (IOException e)
		{
			throw new ParseToObjectException(String.format("Unable to parse [%s] to %s.", body, elementType), e);
		}

		return objs;
	}

	@SuppressWarnings("unchecked")
	public static <T extends DomainObject> T toConnectedObject(String body, @SuppressWarnings("rawtypes") Class elementType)
	{
		log.debug(String.format("Parsing string to object: %s", body));

		T obj;

		if (ApiKey.class.equals(elementType))
		{
			// ApiKey comes wrapped in a root node without the array container,
			// hackily remove the hack so it can be parsed by standard parsers
			int start = body.indexOf("\"key\":") + 6;
			int end = body.lastIndexOf("}");
			body = body.substring(start, end);
		}

		try
		{
			obj = (T) getObjectMapper().readValue(body, elementType);
		} catch (IOException e)
		{
			throw new ParseToObjectException(String.format("Unable to parse [%s] to %s.", body, elementType), e);
		}

		return obj;
	}

	public static ObjectMapper getObjectMapper()
	{
		if (objectMapper == null)
		{
			ObjectMapper retval = new ObjectMapper();
			retval.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			retval.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			//retval.configure(DeserializationConfig.Feature..EAGER_DESERIALIZER_FETCH, false);
			//retval.configure(SerializationConfig.Feature.EAGER_SERIALIZER_FETCH, false);
			objectMapper = retval;
		}
		return objectMapper;
	}
}
