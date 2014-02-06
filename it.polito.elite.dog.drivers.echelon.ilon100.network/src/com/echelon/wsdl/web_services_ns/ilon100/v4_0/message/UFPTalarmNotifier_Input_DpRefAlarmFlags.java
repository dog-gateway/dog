/**
 * UFPTalarmNotifier_Input_DpRefAlarmFlags.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTalarmNotifier_Input_DpRefAlarmFlags  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private short UCPTlogEnable;

    private short UCPTinvisible;

    private short UCPTclearRequired;

    private short UCPTackRequired;

    private short UCPTdisabled;

    private short UCPTcovEnabled;

    public UFPTalarmNotifier_Input_DpRefAlarmFlags() {
    }

    public UFPTalarmNotifier_Input_DpRefAlarmFlags(
           short UCPTlogEnable,
           short UCPTinvisible,
           short UCPTclearRequired,
           short UCPTackRequired,
           short UCPTdisabled,
           short UCPTcovEnabled) {
           this.UCPTlogEnable = UCPTlogEnable;
           this.UCPTinvisible = UCPTinvisible;
           this.UCPTclearRequired = UCPTclearRequired;
           this.UCPTackRequired = UCPTackRequired;
           this.UCPTdisabled = UCPTdisabled;
           this.UCPTcovEnabled = UCPTcovEnabled;
    }


    /**
     * Gets the UCPTlogEnable value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @return UCPTlogEnable
     */
    public short getUCPTlogEnable() {
        return UCPTlogEnable;
    }


    /**
     * Sets the UCPTlogEnable value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @param UCPTlogEnable
     */
    public void setUCPTlogEnable(short UCPTlogEnable) {
        this.UCPTlogEnable = UCPTlogEnable;
    }


    /**
     * Gets the UCPTinvisible value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @return UCPTinvisible
     */
    public short getUCPTinvisible() {
        return UCPTinvisible;
    }


    /**
     * Sets the UCPTinvisible value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @param UCPTinvisible
     */
    public void setUCPTinvisible(short UCPTinvisible) {
        this.UCPTinvisible = UCPTinvisible;
    }


    /**
     * Gets the UCPTclearRequired value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @return UCPTclearRequired
     */
    public short getUCPTclearRequired() {
        return UCPTclearRequired;
    }


    /**
     * Sets the UCPTclearRequired value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @param UCPTclearRequired
     */
    public void setUCPTclearRequired(short UCPTclearRequired) {
        this.UCPTclearRequired = UCPTclearRequired;
    }


    /**
     * Gets the UCPTackRequired value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @return UCPTackRequired
     */
    public short getUCPTackRequired() {
        return UCPTackRequired;
    }


    /**
     * Sets the UCPTackRequired value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @param UCPTackRequired
     */
    public void setUCPTackRequired(short UCPTackRequired) {
        this.UCPTackRequired = UCPTackRequired;
    }


    /**
     * Gets the UCPTdisabled value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @return UCPTdisabled
     */
    public short getUCPTdisabled() {
        return UCPTdisabled;
    }


    /**
     * Sets the UCPTdisabled value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @param UCPTdisabled
     */
    public void setUCPTdisabled(short UCPTdisabled) {
        this.UCPTdisabled = UCPTdisabled;
    }


    /**
     * Gets the UCPTcovEnabled value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @return UCPTcovEnabled
     */
    public short getUCPTcovEnabled() {
        return UCPTcovEnabled;
    }


    /**
     * Sets the UCPTcovEnabled value for this UFPTalarmNotifier_Input_DpRefAlarmFlags.
     * 
     * @param UCPTcovEnabled
     */
    public void setUCPTcovEnabled(short UCPTcovEnabled) {
        this.UCPTcovEnabled = UCPTcovEnabled;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTalarmNotifier_Input_DpRefAlarmFlags)) return false;
        UFPTalarmNotifier_Input_DpRefAlarmFlags other = (UFPTalarmNotifier_Input_DpRefAlarmFlags) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.UCPTlogEnable == other.getUCPTlogEnable() &&
            this.UCPTinvisible == other.getUCPTinvisible() &&
            this.UCPTclearRequired == other.getUCPTclearRequired() &&
            this.UCPTackRequired == other.getUCPTackRequired() &&
            this.UCPTdisabled == other.getUCPTdisabled() &&
            this.UCPTcovEnabled == other.getUCPTcovEnabled();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getUCPTlogEnable();
        _hashCode += getUCPTinvisible();
        _hashCode += getUCPTclearRequired();
        _hashCode += getUCPTackRequired();
        _hashCode += getUCPTdisabled();
        _hashCode += getUCPTcovEnabled();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTalarmNotifier_Input_DpRefAlarmFlags.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Input_DpRef>AlarmFlags"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlogEnable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlogEnable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTinvisible");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTinvisible"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTclearRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTclearRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTackRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTackRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdisabled");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdisabled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcovEnabled");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcovEnabled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
