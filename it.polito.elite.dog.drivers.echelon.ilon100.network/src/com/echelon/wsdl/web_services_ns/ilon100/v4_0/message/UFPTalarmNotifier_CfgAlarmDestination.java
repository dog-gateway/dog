/**
 * UFPTalarmNotifier_CfgAlarmDestination.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTalarmNotifier_CfgAlarmDestination  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTmailPath;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTdataPointPath;

    private java.lang.String UCPTpointValue;

    private int UCPTminLevel;

    private int UCPTmaxLevel;

    private java.lang.Integer UCPTnackDelay;

    public UFPTalarmNotifier_CfgAlarmDestination() {
    }

    public UFPTalarmNotifier_CfgAlarmDestination(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTmailPath,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTdataPointPath,
           java.lang.String UCPTpointValue,
           int UCPTminLevel,
           int UCPTmaxLevel,
           java.lang.Integer UCPTnackDelay) {
           this.UCPTmailPath = UCPTmailPath;
           this.UCPTdataPointPath = UCPTdataPointPath;
           this.UCPTpointValue = UCPTpointValue;
           this.UCPTminLevel = UCPTminLevel;
           this.UCPTmaxLevel = UCPTmaxLevel;
           this.UCPTnackDelay = UCPTnackDelay;
    }


    /**
     * Gets the UCPTmailPath value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @return UCPTmailPath
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path getUCPTmailPath() {
        return UCPTmailPath;
    }


    /**
     * Sets the UCPTmailPath value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @param UCPTmailPath
     */
    public void setUCPTmailPath(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTmailPath) {
        this.UCPTmailPath = UCPTmailPath;
    }


    /**
     * Gets the UCPTdataPointPath value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @return UCPTdataPointPath
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path getUCPTdataPointPath() {
        return UCPTdataPointPath;
    }


    /**
     * Sets the UCPTdataPointPath value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @param UCPTdataPointPath
     */
    public void setUCPTdataPointPath(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTdataPointPath) {
        this.UCPTdataPointPath = UCPTdataPointPath;
    }


    /**
     * Gets the UCPTpointValue value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @return UCPTpointValue
     */
    public java.lang.String getUCPTpointValue() {
        return UCPTpointValue;
    }


    /**
     * Sets the UCPTpointValue value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @param UCPTpointValue
     */
    public void setUCPTpointValue(java.lang.String UCPTpointValue) {
        this.UCPTpointValue = UCPTpointValue;
    }


    /**
     * Gets the UCPTminLevel value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @return UCPTminLevel
     */
    public int getUCPTminLevel() {
        return UCPTminLevel;
    }


    /**
     * Sets the UCPTminLevel value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @param UCPTminLevel
     */
    public void setUCPTminLevel(int UCPTminLevel) {
        this.UCPTminLevel = UCPTminLevel;
    }


    /**
     * Gets the UCPTmaxLevel value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @return UCPTmaxLevel
     */
    public int getUCPTmaxLevel() {
        return UCPTmaxLevel;
    }


    /**
     * Sets the UCPTmaxLevel value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @param UCPTmaxLevel
     */
    public void setUCPTmaxLevel(int UCPTmaxLevel) {
        this.UCPTmaxLevel = UCPTmaxLevel;
    }


    /**
     * Gets the UCPTnackDelay value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @return UCPTnackDelay
     */
    public java.lang.Integer getUCPTnackDelay() {
        return UCPTnackDelay;
    }


    /**
     * Sets the UCPTnackDelay value for this UFPTalarmNotifier_CfgAlarmDestination.
     * 
     * @param UCPTnackDelay
     */
    public void setUCPTnackDelay(java.lang.Integer UCPTnackDelay) {
        this.UCPTnackDelay = UCPTnackDelay;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTalarmNotifier_CfgAlarmDestination)) return false;
        UFPTalarmNotifier_CfgAlarmDestination other = (UFPTalarmNotifier_CfgAlarmDestination) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTmailPath==null && other.getUCPTmailPath()==null) || 
             (this.UCPTmailPath!=null &&
              this.UCPTmailPath.equals(other.getUCPTmailPath()))) &&
            ((this.UCPTdataPointPath==null && other.getUCPTdataPointPath()==null) || 
             (this.UCPTdataPointPath!=null &&
              this.UCPTdataPointPath.equals(other.getUCPTdataPointPath()))) &&
            ((this.UCPTpointValue==null && other.getUCPTpointValue()==null) || 
             (this.UCPTpointValue!=null &&
              this.UCPTpointValue.equals(other.getUCPTpointValue()))) &&
            this.UCPTminLevel == other.getUCPTminLevel() &&
            this.UCPTmaxLevel == other.getUCPTmaxLevel() &&
            ((this.UCPTnackDelay==null && other.getUCPTnackDelay()==null) || 
             (this.UCPTnackDelay!=null &&
              this.UCPTnackDelay.equals(other.getUCPTnackDelay())));
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
        if (getUCPTmailPath() != null) {
            _hashCode += getUCPTmailPath().hashCode();
        }
        if (getUCPTdataPointPath() != null) {
            _hashCode += getUCPTdataPointPath().hashCode();
        }
        if (getUCPTpointValue() != null) {
            _hashCode += getUCPTpointValue().hashCode();
        }
        _hashCode += getUCPTminLevel();
        _hashCode += getUCPTmaxLevel();
        if (getUCPTnackDelay() != null) {
            _hashCode += getUCPTnackDelay().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTalarmNotifier_CfgAlarmDestination.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_CfgAlarmDestination"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmailPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmailPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Path"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdataPointPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdataPointPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Path"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpointValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpointValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTminLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTminLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTnackDelay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTnackDelay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
