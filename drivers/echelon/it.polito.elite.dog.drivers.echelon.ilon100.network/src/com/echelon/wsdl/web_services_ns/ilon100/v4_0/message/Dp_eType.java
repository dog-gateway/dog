/**
 * Dp_eType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;
@SuppressWarnings({"rawtypes","unchecked"})
public class Dp_eType implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected Dp_eType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String __Any = "_Any";
    public static final java.lang.String _Input = "Input";
    public static final java.lang.String _Output = "Output";
    public static final java.lang.String _InOut = "InOut";
    public static final java.lang.String _Compare = "Compare";
    public static final java.lang.String _Alarm = "Alarm";
    public static final java.lang.String _Alarm2 = "Alarm2";
    public static final java.lang.String _AlarmDestEnable = "AlarmDestEnable";
    public static final java.lang.String _Elevation = "Elevation";
    public static final java.lang.String _Azimuth = "Azimuth";
    public static final java.lang.String _Sunrise = "Sunrise";
    public static final java.lang.String _Sunset = "Sunset";
    public static final java.lang.String _BaseTime = "BaseTime";
    public static final java.lang.String _Source = "Source";
    public static final java.lang.String _Target = "Target";
    public static final java.lang.String _nviEnable = "nviEnable";
    public static final java.lang.String _nvoDropOut = "nvoDropOut";
    public static final java.lang.String _nvoTimeDate = "nvoTimeDate";
    public static final java.lang.String _nvoSummerTime = "nvoSummerTime";
    public static final java.lang.String _nvoWinterTime = "nvoWinterTime";
    public static final java.lang.String _nviTimeZone = "nviTimeZone";
    public static final java.lang.String _nciMasterSlave = "nciMasterSlave";
    public static final java.lang.String _nciUpdateRate = "nciUpdateRate";
    public static final java.lang.String _nvoDateEvent = "nvoDateEvent";
    public static final java.lang.String _nviDateResync = "nviDateResync";
    public static final java.lang.String _nciInputType = "nciInputType";
    public static final java.lang.String _nciInvert = "nciInvert";
    public static final java.lang.String _nvoSetting = "nvoSetting";
    public static final java.lang.String _nviLatchEnable = "nviLatchEnable";
    public static final java.lang.String _nvoAlarmFlag = "nvoAlarmFlag";
    public static final java.lang.String _nvoLevelAlarm = "nvoLevelAlarm";
    public static final java.lang.String _nviClear = "nviClear";
    public static final java.lang.String _nvoValueDif = "nvoValueDif";
    public static final java.lang.String _nciDeltaTime = "nciDeltaTime";
    public static final java.lang.String _nvoValueFb = "nvoValueFb";
    public static final java.lang.String _nviValue = "nviValue";
    public static final java.lang.String _nviValueFb = "nviValueFb";
    public static final java.lang.String _nvoValue = "nvoValue";
    public static final java.lang.String _nviTrigger = "nviTrigger";
    public static final java.lang.String _nciValueSet = "nciValueSet";
    public static final java.lang.String _nciCorrFactor = "nciCorrFactor";
    public static final java.lang.String _nviRequest = "nviRequest";
    public static final java.lang.String _nvoStatus = "nvoStatus";
    public static final java.lang.String _nviDateEvent = "nviDateEvent";
    public static final java.lang.String _nviTimeSet = "nviTimeSet";
    public static final java.lang.String _nvoDateResync = "nvoDateResync";
    public static final java.lang.String _nvoDeviceAlarm = "nvoDeviceAlarm";
    public static final java.lang.String _nvoIpAddress = "nvoIpAddress";
    public static final java.lang.String _nviFileReq = "nviFileReq";
    public static final java.lang.String _nvoFileStat = "nvoFileStat";
    public static final java.lang.String _nviFilePos = "nviFilePos";
    public static final java.lang.String _nvoFileDirectory = "nvoFileDirectory";
    public static final Dp_eType _Any = new Dp_eType(__Any);
    public static final Dp_eType Input = new Dp_eType(_Input);
    public static final Dp_eType Output = new Dp_eType(_Output);
    public static final Dp_eType InOut = new Dp_eType(_InOut);
    public static final Dp_eType Compare = new Dp_eType(_Compare);
    public static final Dp_eType Alarm = new Dp_eType(_Alarm);
    public static final Dp_eType Alarm2 = new Dp_eType(_Alarm2);
    public static final Dp_eType AlarmDestEnable = new Dp_eType(_AlarmDestEnable);
    public static final Dp_eType Elevation = new Dp_eType(_Elevation);
    public static final Dp_eType Azimuth = new Dp_eType(_Azimuth);
    public static final Dp_eType Sunrise = new Dp_eType(_Sunrise);
    public static final Dp_eType Sunset = new Dp_eType(_Sunset);
    public static final Dp_eType BaseTime = new Dp_eType(_BaseTime);
    public static final Dp_eType Source = new Dp_eType(_Source);
    public static final Dp_eType Target = new Dp_eType(_Target);
    public static final Dp_eType nviEnable = new Dp_eType(_nviEnable);
    public static final Dp_eType nvoDropOut = new Dp_eType(_nvoDropOut);
    public static final Dp_eType nvoTimeDate = new Dp_eType(_nvoTimeDate);
    public static final Dp_eType nvoSummerTime = new Dp_eType(_nvoSummerTime);
    public static final Dp_eType nvoWinterTime = new Dp_eType(_nvoWinterTime);
    public static final Dp_eType nviTimeZone = new Dp_eType(_nviTimeZone);
    public static final Dp_eType nciMasterSlave = new Dp_eType(_nciMasterSlave);
    public static final Dp_eType nciUpdateRate = new Dp_eType(_nciUpdateRate);
    public static final Dp_eType nvoDateEvent = new Dp_eType(_nvoDateEvent);
    public static final Dp_eType nviDateResync = new Dp_eType(_nviDateResync);
    public static final Dp_eType nciInputType = new Dp_eType(_nciInputType);
    public static final Dp_eType nciInvert = new Dp_eType(_nciInvert);
    public static final Dp_eType nvoSetting = new Dp_eType(_nvoSetting);
    public static final Dp_eType nviLatchEnable = new Dp_eType(_nviLatchEnable);
    public static final Dp_eType nvoAlarmFlag = new Dp_eType(_nvoAlarmFlag);
    public static final Dp_eType nvoLevelAlarm = new Dp_eType(_nvoLevelAlarm);
    public static final Dp_eType nviClear = new Dp_eType(_nviClear);
    public static final Dp_eType nvoValueDif = new Dp_eType(_nvoValueDif);
    public static final Dp_eType nciDeltaTime = new Dp_eType(_nciDeltaTime);
    public static final Dp_eType nvoValueFb = new Dp_eType(_nvoValueFb);
    public static final Dp_eType nviValue = new Dp_eType(_nviValue);
    public static final Dp_eType nviValueFb = new Dp_eType(_nviValueFb);
    public static final Dp_eType nvoValue = new Dp_eType(_nvoValue);
    public static final Dp_eType nviTrigger = new Dp_eType(_nviTrigger);
    public static final Dp_eType nciValueSet = new Dp_eType(_nciValueSet);
    public static final Dp_eType nciCorrFactor = new Dp_eType(_nciCorrFactor);
    public static final Dp_eType nviRequest = new Dp_eType(_nviRequest);
    public static final Dp_eType nvoStatus = new Dp_eType(_nvoStatus);
    public static final Dp_eType nviDateEvent = new Dp_eType(_nviDateEvent);
    public static final Dp_eType nviTimeSet = new Dp_eType(_nviTimeSet);
    public static final Dp_eType nvoDateResync = new Dp_eType(_nvoDateResync);
    public static final Dp_eType nvoDeviceAlarm = new Dp_eType(_nvoDeviceAlarm);
    public static final Dp_eType nvoIpAddress = new Dp_eType(_nvoIpAddress);
    public static final Dp_eType nviFileReq = new Dp_eType(_nviFileReq);
    public static final Dp_eType nvoFileStat = new Dp_eType(_nvoFileStat);
    public static final Dp_eType nviFilePos = new Dp_eType(_nviFilePos);
    public static final Dp_eType nvoFileDirectory = new Dp_eType(_nvoFileDirectory);
    public java.lang.String getValue() { return _value_;}
    public static Dp_eType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Dp_eType enumeration = (Dp_eType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static Dp_eType fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dp_eType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_eType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
