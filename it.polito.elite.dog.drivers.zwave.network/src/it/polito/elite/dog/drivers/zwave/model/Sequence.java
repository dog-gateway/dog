package it.polito.elite.dog.drivers.zwave.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Sequence {
	@JsonProperty("updateTime") private Integer updateTime;
	@JsonProperty("name") private String name;
	@JsonProperty("value") private Boolean value;
	@JsonProperty("type") private String type;
	@JsonProperty("invalidateTime") private Integer invalidateTime;
}
