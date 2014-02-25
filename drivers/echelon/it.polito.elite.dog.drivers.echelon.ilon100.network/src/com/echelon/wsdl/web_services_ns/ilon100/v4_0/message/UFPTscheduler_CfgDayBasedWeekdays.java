/**
 * UFPTscheduler_CfgDayBasedWeekdays.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTscheduler_CfgDayBasedWeekdays  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private short UCPTsunday;

    private short UCPTmonday;

    private short UCPTtuesday;

    private short UCPTwednesday;

    private short UCPTthursday;

    private short UCPTfriday;

    private short UCPTsaturday;

    public UFPTscheduler_CfgDayBasedWeekdays() {
    }

    public UFPTscheduler_CfgDayBasedWeekdays(
           short UCPTsunday,
           short UCPTmonday,
           short UCPTtuesday,
           short UCPTwednesday,
           short UCPTthursday,
           short UCPTfriday,
           short UCPTsaturday) {
           this.UCPTsunday = UCPTsunday;
           this.UCPTmonday = UCPTmonday;
           this.UCPTtuesday = UCPTtuesday;
           this.UCPTwednesday = UCPTwednesday;
           this.UCPTthursday = UCPTthursday;
           this.UCPTfriday = UCPTfriday;
           this.UCPTsaturday = UCPTsaturday;
    }


    /**
     * Gets the UCPTsunday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @return UCPTsunday
     */
    public short getUCPTsunday() {
        return UCPTsunday;
    }


    /**
     * Sets the UCPTsunday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @param UCPTsunday
     */
    public void setUCPTsunday(short UCPTsunday) {
        this.UCPTsunday = UCPTsunday;
    }


    /**
     * Gets the UCPTmonday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @return UCPTmonday
     */
    public short getUCPTmonday() {
        return UCPTmonday;
    }


    /**
     * Sets the UCPTmonday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @param UCPTmonday
     */
    public void setUCPTmonday(short UCPTmonday) {
        this.UCPTmonday = UCPTmonday;
    }


    /**
     * Gets the UCPTtuesday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @return UCPTtuesday
     */
    public short getUCPTtuesday() {
        return UCPTtuesday;
    }


    /**
     * Sets the UCPTtuesday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @param UCPTtuesday
     */
    public void setUCPTtuesday(short UCPTtuesday) {
        this.UCPTtuesday = UCPTtuesday;
    }


    /**
     * Gets the UCPTwednesday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @return UCPTwednesday
     */
    public short getUCPTwednesday() {
        return UCPTwednesday;
    }


    /**
     * Sets the UCPTwednesday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @param UCPTwednesday
     */
    public void setUCPTwednesday(short UCPTwednesday) {
        this.UCPTwednesday = UCPTwednesday;
    }


    /**
     * Gets the UCPTthursday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @return UCPTthursday
     */
    public short getUCPTthursday() {
        return UCPTthursday;
    }


    /**
     * Sets the UCPTthursday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @param UCPTthursday
     */
    public void setUCPTthursday(short UCPTthursday) {
        this.UCPTthursday = UCPTthursday;
    }


    /**
     * Gets the UCPTfriday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @return UCPTfriday
     */
    public short getUCPTfriday() {
        return UCPTfriday;
    }


    /**
     * Sets the UCPTfriday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @param UCPTfriday
     */
    public void setUCPTfriday(short UCPTfriday) {
        this.UCPTfriday = UCPTfriday;
    }


    /**
     * Gets the UCPTsaturday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @return UCPTsaturday
     */
    public short getUCPTsaturday() {
        return UCPTsaturday;
    }


    /**
     * Sets the UCPTsaturday value for this UFPTscheduler_CfgDayBasedWeekdays.
     * 
     * @param UCPTsaturday
     */
    public void setUCPTsaturday(short UCPTsaturday) {
        this.UCPTsaturday = UCPTsaturday;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTscheduler_CfgDayBasedWeekdays)) return false;
        UFPTscheduler_CfgDayBasedWeekdays other = (UFPTscheduler_CfgDayBasedWeekdays) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.UCPTsunday == other.getUCPTsunday() &&
            this.UCPTmonday == other.getUCPTmonday() &&
            this.UCPTtuesday == other.getUCPTtuesday() &&
            this.UCPTwednesday == other.getUCPTwednesday() &&
            this.UCPTthursday == other.getUCPTthursday() &&
            this.UCPTfriday == other.getUCPTfriday() &&
            this.UCPTsaturday == other.getUCPTsaturday();
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
        _hashCode += getUCPTsunday();
        _hashCode += getUCPTmonday();
        _hashCode += getUCPTtuesday();
        _hashCode += getUCPTwednesday();
        _hashCode += getUCPTthursday();
        _hashCode += getUCPTfriday();
        _hashCode += getUCPTsaturday();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTscheduler_CfgDayBasedWeekdays.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTscheduler_Cfg>DayBased>Weekdays"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsunday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsunday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmonday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmonday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTtuesday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtuesday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTwednesday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTwednesday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTthursday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTthursday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfriday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfriday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsaturday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsaturday"));
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
