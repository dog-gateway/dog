package it.polito.elite.dog.drivers.zwave.model;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class Type {
	@JsonProperty("fix") private Map<String, Integer> fix;
	@JsonProperty("enumof") private List<GetSetEntry> enumof;
	@JsonProperty("range") private Range range;
	@JsonProperty("string") private Object string;
	@JsonProperty("node") private Object node;
}
