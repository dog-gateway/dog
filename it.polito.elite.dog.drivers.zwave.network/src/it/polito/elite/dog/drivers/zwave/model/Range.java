package it.polito.elite.dog.drivers.zwave.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Range {
	@JsonProperty("max") private long max;
	@JsonProperty("min") private long min;
	@JsonProperty("shift") private Integer shift;
}
