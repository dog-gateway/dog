/**
 * UFPTscheduler_CfgDateBasedException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTscheduler_CfgDateBasedException  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTindex;

    private java.lang.String UCPTexceptionName;

    public UFPTscheduler_CfgDateBasedException() {
    }

    public UFPTscheduler_CfgDateBasedException(
           java.lang.Integer UCPTindex,
           java.lang.String UCPTexceptionName) {
           this.UCPTindex = UCPTindex;
           this.UCPTexceptionName = UCPTexceptionName;
    }


    /**
     * Gets the UCPTindex value for this UFPTscheduler_CfgDateBasedException.
     * 
     * @return UCPTindex
     */
    public java.lang.Integer getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this UFPTscheduler_CfgDateBasedException.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(java.lang.Integer UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTexceptionName value for this UFPTscheduler_CfgDateBasedException.
     * 
     * @return UCPTexceptionName
     */
    public java.lang.String getUCPTexceptionName() {
        return UCPTexceptionName;
    }


    /**
     * Sets the UCPTexceptionName value for this UFPTscheduler_CfgDateBasedException.
     * 
     * @param UCPTexceptionName
     */
    public void setUCPTexceptionName(java.lang.String UCPTexceptionName) {
        this.UCPTexceptionName = UCPTexceptionName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTscheduler_CfgDateBasedException)) return false;
        UFPTscheduler_CfgDateBasedException other = (UFPTscheduler_CfgDateBasedException) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTindex==null && other.getUCPTindex()==null) || 
             (this.UCPTindex!=null &&
              this.UCPTindex.equals(other.getUCPTindex()))) &&
            ((this.UCPTexceptionName==null && other.getUCPTexceptionName()==null) || 
             (this.UCPTexceptionName!=null &&
              this.UCPTexceptionName.equals(other.getUCPTexceptionName())));
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
        if (getUCPTindex() != null) {
            _hashCode += getUCPTindex().hashCode();
        }
        if (getUCPTexceptionName() != null) {
            _hashCode += getUCPTexceptionName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTscheduler_CfgDateBasedException.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTscheduler_Cfg>DateBased>Exception"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTexceptionName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTexceptionName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
