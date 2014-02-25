/**
 * UFPTdataLogger_Data.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized data type of the 'UFPTdataLogger'.
 * 					Example:   xSelect="//Item[@xsi:type="UFPTdataLogger_Data"][UCPTlastUpdate>="2007-01-15T15:30:21Z"][position()<50]"
 * 					Example:   xSelect="//Item[@xsi:type="UFPTdataLogger_Data"][UCPTpointName="Net/LON/BAS/Data
 * Logger 1/nviInput"][UCPTlastUpdate>="2007-01-15T15:30:21Z"][position()<50]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTdataLogger_Data  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UFPTdataLogger_Data() {
    }

    public UFPTdataLogger_Data(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTvalue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTpointStatus,
           java.lang.Integer UCPTpriority,
           java.lang.Float UCPTmaxAge,
           java.lang.Short UCPTpropagate,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTmetaDataPath) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus,
            UCPTlastUpdate,
            UCPTdescription,
            UCPTuri,
            UCPTvalue,
            UCPTpointStatus,
            UCPTpriority,
            UCPTmaxAge,
            UCPTpropagate,
            UCPTmetaDataPath);
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTdataLogger_Data)) return false;
        UFPTdataLogger_Data other = (UFPTdataLogger_Data) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj);
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTdataLogger_Data.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdataLogger_Data"));
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
