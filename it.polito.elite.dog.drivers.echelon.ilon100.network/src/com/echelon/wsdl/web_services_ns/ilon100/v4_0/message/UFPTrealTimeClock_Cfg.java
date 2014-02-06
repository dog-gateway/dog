/**
 * UFPTrealTimeClock_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized configuration type of the 'UFPTrealTimeClock'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTrealTimeClock_Cfg"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTrealTimeClock_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double UCPTdegLongitude;

    private double UCPTdegLatitude;

    public UFPTrealTimeClock_Cfg() {
    }

    public UFPTrealTimeClock_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef[] dataPoint,
           double UCPTdegLongitude,
           double UCPTdegLatitude) {
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
            dataPoint);
        this.UCPTdegLongitude = UCPTdegLongitude;
        this.UCPTdegLatitude = UCPTdegLatitude;
    }


    /**
     * Gets the UCPTdegLongitude value for this UFPTrealTimeClock_Cfg.
     * 
     * @return UCPTdegLongitude
     */
    public double getUCPTdegLongitude() {
        return UCPTdegLongitude;
    }


    /**
     * Sets the UCPTdegLongitude value for this UFPTrealTimeClock_Cfg.
     * 
     * @param UCPTdegLongitude
     */
    public void setUCPTdegLongitude(double UCPTdegLongitude) {
        this.UCPTdegLongitude = UCPTdegLongitude;
    }


    /**
     * Gets the UCPTdegLatitude value for this UFPTrealTimeClock_Cfg.
     * 
     * @return UCPTdegLatitude
     */
    public double getUCPTdegLatitude() {
        return UCPTdegLatitude;
    }


    /**
     * Sets the UCPTdegLatitude value for this UFPTrealTimeClock_Cfg.
     * 
     * @param UCPTdegLatitude
     */
    public void setUCPTdegLatitude(double UCPTdegLatitude) {
        this.UCPTdegLatitude = UCPTdegLatitude;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTrealTimeClock_Cfg)) return false;
        UFPTrealTimeClock_Cfg other = (UFPTrealTimeClock_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.UCPTdegLongitude == other.getUCPTdegLongitude() &&
            this.UCPTdegLatitude == other.getUCPTdegLatitude();
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
        _hashCode += new Double(getUCPTdegLongitude()).hashCode();
        _hashCode += new Double(getUCPTdegLatitude()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTrealTimeClock_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTrealTimeClock_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdegLongitude");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdegLongitude"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdegLatitude");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdegLatitude"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
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
