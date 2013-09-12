package it.polito.elite.dog.drivers.zwave.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Controller 
{
	@JsonProperty("data") private ControllerData controllerData;
}
