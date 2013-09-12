package it.polito.elite.dog.drivers.zwave.model;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class GetSetEntry {
	@JsonProperty("label") private String label;
	@JsonProperty("type") private Map<String, Object> type;
}
