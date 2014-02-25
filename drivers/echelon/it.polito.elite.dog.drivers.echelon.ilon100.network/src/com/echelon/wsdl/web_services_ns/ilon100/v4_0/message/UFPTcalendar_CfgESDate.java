/**
 * UFPTcalendar_CfgESDate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTcalendar_CfgESDate  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.util.Date UCPTdate;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTyearMask;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmonthMask;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdayMask;

    public UFPTcalendar_CfgESDate() {
    }

    public UFPTcalendar_CfgESDate(
           java.util.Date UCPTdate,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTyearMask,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmonthMask,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdayMask) {
           this.UCPTdate = UCPTdate;
           this.UCPTyearMask = UCPTyearMask;
           this.UCPTmonthMask = UCPTmonthMask;
           this.UCPTdayMask = UCPTdayMask;
    }


    /**
     * Gets the UCPTdate value for this UFPTcalendar_CfgESDate.
     * 
     * @return UCPTdate
     */
    public java.util.Date getUCPTdate() {
        return UCPTdate;
    }


    /**
     * Sets the UCPTdate value for this UFPTcalendar_CfgESDate.
     * 
     * @param UCPTdate
     */
    public void setUCPTdate(java.util.Date UCPTdate) {
        this.UCPTdate = UCPTdate;
    }


    /**
     * Gets the UCPTyearMask value for this UFPTcalendar_CfgESDate.
     * 
     * @return UCPTyearMask
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTyearMask() {
        return UCPTyearMask;
    }


    /**
     * Sets the UCPTyearMask value for this UFPTcalendar_CfgESDate.
     * 
     * @param UCPTyearMask
     */
    public void setUCPTyearMask(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTyearMask) {
        this.UCPTyearMask = UCPTyearMask;
    }


    /**
     * Gets the UCPTmonthMask value for this UFPTcalendar_CfgESDate.
     * 
     * @return UCPTmonthMask
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmonthMask() {
        return UCPTmonthMask;
    }


    /**
     * Sets the UCPTmonthMask value for this UFPTcalendar_CfgESDate.
     * 
     * @param UCPTmonthMask
     */
    public void setUCPTmonthMask(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmonthMask) {
        this.UCPTmonthMask = UCPTmonthMask;
    }


    /**
     * Gets the UCPTdayMask value for this UFPTcalendar_CfgESDate.
     * 
     * @return UCPTdayMask
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTdayMask() {
        return UCPTdayMask;
    }


    /**
     * Sets the UCPTdayMask value for this UFPTcalendar_CfgESDate.
     * 
     * @param UCPTdayMask
     */
    public void setUCPTdayMask(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdayMask) {
        this.UCPTdayMask = UCPTdayMask;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTcalendar_CfgESDate)) return false;
        UFPTcalendar_CfgESDate other = (UFPTcalendar_CfgESDate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTdate==null && other.getUCPTdate()==null) || 
             (this.UCPTdate!=null &&
              this.UCPTdate.equals(other.getUCPTdate()))) &&
            ((this.UCPTyearMask==null && other.getUCPTyearMask()==null) || 
             (this.UCPTyearMask!=null &&
              this.UCPTyearMask.equals(other.getUCPTyearMask()))) &&
            ((this.UCPTmonthMask==null && other.getUCPTmonthMask()==null) || 
             (this.UCPTmonthMask!=null &&
              this.UCPTmonthMask.equals(other.getUCPTmonthMask()))) &&
            ((this.UCPTdayMask==null && other.getUCPTdayMask()==null) || 
             (this.UCPTdayMask!=null &&
              this.UCPTdayMask.equals(other.getUCPTdayMask())));
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
        if (getUCPTdate() != null) {
            _hashCode += getUCPTdate().hashCode();
        }
        if (getUCPTyearMask() != null) {
            _hashCode += getUCPTyearMask().hashCode();
        }
        if (getUCPTmonthMask() != null) {
            _hashCode += getUCPTmonthMask().hashCode();
        }
        if (getUCPTdayMask() != null) {
            _hashCode += getUCPTdayMask().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTcalendar_CfgESDate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTcalendar_CfgESDate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTyearMask");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTyearMask"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmonthMask");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmonthMask"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdayMask");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdayMask"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
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
