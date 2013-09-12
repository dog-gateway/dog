package it.polito.elite.dog.drivers.zwave.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Climate {
	@JsonProperty("updateTime") private Integer updateTime;
	@JsonProperty("name") private String name;
	@JsonProperty("value") private Object value;
	@JsonProperty("type") private String type;
	@JsonProperty("shift") private Scene shift;
	@JsonProperty("invalidateTime") private Integer invalidateTime;
}
