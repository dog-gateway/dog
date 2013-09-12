package it.polito.elite.dog.drivers.zwave.model;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;


public class DeviceData 
{
	//static name properties
	private Integer updateTime;
	private String name;
	private Object value;
	private String type;
	private Integer invalidateTime;
	
	//dynamic name prop are managed through this map
	//and through @JsonAnyGetter and @JsonAnySetter annotation
	private Map<String, DataElemObject> data = new HashMap<String, DataElemObject>(); 
	
	@JsonCreator
    public DeviceData(@JsonProperty("updateTime") Integer updateTime,
    		@JsonProperty("name") String name,
    		@JsonProperty("value") Object value,
    		@JsonProperty("type") String type,
    		@JsonProperty("invalidateTime") Integer invalidateTime
    		)
    {
		this.updateTime = updateTime;
		this.name = name;
		this.value = value;
		this.type = type;
		this.invalidateTime = invalidateTime;
    }
	
	// "any getter" needed for serialization    
    @JsonAnyGetter
    public Map<String,DataElemObject> getAllData() {
        return data;
    }

    @JsonAnySetter
    public void set(String name, DataElemObject value) {
    	data.put(name, value);
    }
    
    /**
     * returns the property defined by name or null
     */
    public DataElemObject get(String name)
    {
    	return data.get(name);
    }
    
	/**
	 * @return the updateTime
	 */
	public Integer getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the invalidateTime
	 */
	public Integer getInvalidateTime() {
		return invalidateTime;
	}

	/**
	 * @param invalidateTime the invalidateTime to set
	 */
	public void setInvalidateTime(Integer invalidateTime) {
		this.invalidateTime = invalidateTime;
	}
}
