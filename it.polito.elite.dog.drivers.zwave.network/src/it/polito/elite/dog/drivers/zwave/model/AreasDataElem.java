package it.polito.elite.dog.drivers.zwave.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class AreasDataElem {
	@JsonProperty("updateTime") private Integer updateTime;
	@JsonProperty("name") private Integer name;
	@JsonProperty("value") private String value;
	@JsonProperty("scene") private Scene scene;
	@JsonProperty("type") private String type;
	@JsonProperty("climate") private Climate climate;
	@JsonProperty("invalidateTime") private Integer invalidateTime;
}
