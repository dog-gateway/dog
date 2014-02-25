/**
 * LON_Device_eCommand.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unchecked"})
public class LON_Device_eCommand implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LON_Device_eCommand(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Reset = "Reset";
    public static final java.lang.String _ChangeApplicationStatus = "ChangeApplicationStatus";
    public static final java.lang.String _Wink = "Wink";
    public static final java.lang.String _ChangeCommissionStatus = "ChangeCommissionStatus";
    public static final java.lang.String _FetchProgId = "FetchProgId";
    public static final java.lang.String _ImageDownload = "ImageDownload";
    public static final java.lang.String _GetTemplate = "GetTemplate";
    public static final java.lang.String _CpFileTransfer = "CpFileTransfer";
    public static final java.lang.String _QueryStatus = "QueryStatus";
    public static final java.lang.String _ClearStatus = "ClearStatus";
    public static final java.lang.String _RepeatingData = "RepeatingData";
    public static final java.lang.String _PowerlineData = "PowerlineData";
    public static final java.lang.String _SendServicePin = "SendServicePin";
    public static final java.lang.String _ProxyData = "ProxyData";
    public static final java.lang.String _UpdateCpDefaults = "UpdateCpDefaults";
    public static final java.lang.String _FetchNeuronId = "FetchNeuronId";
    public static final LON_Device_eCommand Reset = new LON_Device_eCommand(_Reset);
    public static final LON_Device_eCommand ChangeApplicationStatus = new LON_Device_eCommand(_ChangeApplicationStatus);
    public static final LON_Device_eCommand Wink = new LON_Device_eCommand(_Wink);
    public static final LON_Device_eCommand ChangeCommissionStatus = new LON_Device_eCommand(_ChangeCommissionStatus);
    public static final LON_Device_eCommand FetchProgId = new LON_Device_eCommand(_FetchProgId);
    public static final LON_Device_eCommand ImageDownload = new LON_Device_eCommand(_ImageDownload);
    public static final LON_Device_eCommand GetTemplate = new LON_Device_eCommand(_GetTemplate);
    public static final LON_Device_eCommand CpFileTransfer = new LON_Device_eCommand(_CpFileTransfer);
    public static final LON_Device_eCommand QueryStatus = new LON_Device_eCommand(_QueryStatus);
    public static final LON_Device_eCommand ClearStatus = new LON_Device_eCommand(_ClearStatus);
    public static final LON_Device_eCommand RepeatingData = new LON_Device_eCommand(_RepeatingData);
    public static final LON_Device_eCommand PowerlineData = new LON_Device_eCommand(_PowerlineData);
    public static final LON_Device_eCommand SendServicePin = new LON_Device_eCommand(_SendServicePin);
    public static final LON_Device_eCommand ProxyData = new LON_Device_eCommand(_ProxyData);
    public static final LON_Device_eCommand UpdateCpDefaults = new LON_Device_eCommand(_UpdateCpDefaults);
    public static final LON_Device_eCommand FetchNeuronId = new LON_Device_eCommand(_FetchNeuronId);
    public java.lang.String getValue() { return _value_;}
    public static LON_Device_eCommand fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        LON_Device_eCommand enumeration = (LON_Device_eCommand)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static LON_Device_eCommand fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(LON_Device_eCommand.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_eCommand"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
