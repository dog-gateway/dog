/**
 * UFPTcalendar_CfgExceptionSchedule.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTcalendar_CfgExceptionSchedule  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgESDate startDate;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgESDate endDate;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTschedDay;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTschedMonth;

    public UFPTcalendar_CfgExceptionSchedule() {
    }

    public UFPTcalendar_CfgExceptionSchedule(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgESDate startDate,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgESDate endDate,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTschedDay,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTschedMonth) {
           this.startDate = startDate;
           this.endDate = endDate;
           this.UCPTschedDay = UCPTschedDay;
           this.UCPTschedMonth = UCPTschedMonth;
    }


    /**
     * Gets the startDate value for this UFPTcalendar_CfgExceptionSchedule.
     * 
     * @return startDate
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgESDate getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this UFPTcalendar_CfgExceptionSchedule.
     * 
     * @param startDate
     */
    public void setStartDate(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgESDate startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the endDate value for this UFPTcalendar_CfgExceptionSchedule.
     * 
     * @return endDate
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgESDate getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this UFPTcalendar_CfgExceptionSchedule.
     * 
     * @param endDate
     */
    public void setEndDate(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgESDate endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the UCPTschedDay value for this UFPTcalendar_CfgExceptionSchedule.
     * 
     * @return UCPTschedDay
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTschedDay() {
        return UCPTschedDay;
    }


    /**
     * Sets the UCPTschedDay value for this UFPTcalendar_CfgExceptionSchedule.
     * 
     * @param UCPTschedDay
     */
    public void setUCPTschedDay(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTschedDay) {
        this.UCPTschedDay = UCPTschedDay;
    }


    /**
     * Gets the UCPTschedMonth value for this UFPTcalendar_CfgExceptionSchedule.
     * 
     * @return UCPTschedMonth
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTschedMonth() {
        return UCPTschedMonth;
    }


    /**
     * Sets the UCPTschedMonth value for this UFPTcalendar_CfgExceptionSchedule.
     * 
     * @param UCPTschedMonth
     */
    public void setUCPTschedMonth(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTschedMonth) {
        this.UCPTschedMonth = UCPTschedMonth;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTcalendar_CfgExceptionSchedule)) return false;
        UFPTcalendar_CfgExceptionSchedule other = (UFPTcalendar_CfgExceptionSchedule) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.UCPTschedDay==null && other.getUCPTschedDay()==null) || 
             (this.UCPTschedDay!=null &&
              this.UCPTschedDay.equals(other.getUCPTschedDay()))) &&
            ((this.UCPTschedMonth==null && other.getUCPTschedMonth()==null) || 
             (this.UCPTschedMonth!=null &&
              this.UCPTschedMonth.equals(other.getUCPTschedMonth())));
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
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getUCPTschedDay() != null) {
            _hashCode += getUCPTschedDay().hashCode();
        }
        if (getUCPTschedMonth() != null) {
            _hashCode += getUCPTschedMonth().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTcalendar_CfgExceptionSchedule.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTcalendar_Cfg>Exception>Schedule"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "StartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTcalendar_CfgESDate"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "EndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTcalendar_CfgESDate"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTschedDay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTschedDay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTschedMonth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTschedMonth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
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
