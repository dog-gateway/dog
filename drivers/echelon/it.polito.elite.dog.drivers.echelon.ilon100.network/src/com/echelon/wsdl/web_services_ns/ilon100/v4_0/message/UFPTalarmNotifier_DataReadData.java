/**
 * UFPTalarmNotifier_DataReadData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTalarmNotifier_DataReadData  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTalarmText;

    private int UCPTalarmPriority2;

    private int UCPTalarmGroup;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTalarmType;

    public UFPTalarmNotifier_DataReadData() {
    }

    public UFPTalarmNotifier_DataReadData(
           java.lang.String UCPTalarmText,
           int UCPTalarmPriority2,
           int UCPTalarmGroup,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTalarmType) {
           this.UCPTalarmText = UCPTalarmText;
           this.UCPTalarmPriority2 = UCPTalarmPriority2;
           this.UCPTalarmGroup = UCPTalarmGroup;
           this.UCPTalarmType = UCPTalarmType;
    }


    /**
     * Gets the UCPTalarmText value for this UFPTalarmNotifier_DataReadData.
     * 
     * @return UCPTalarmText
     */
    public java.lang.String getUCPTalarmText() {
        return UCPTalarmText;
    }


    /**
     * Sets the UCPTalarmText value for this UFPTalarmNotifier_DataReadData.
     * 
     * @param UCPTalarmText
     */
    public void setUCPTalarmText(java.lang.String UCPTalarmText) {
        this.UCPTalarmText = UCPTalarmText;
    }


    /**
     * Gets the UCPTalarmPriority2 value for this UFPTalarmNotifier_DataReadData.
     * 
     * @return UCPTalarmPriority2
     */
    public int getUCPTalarmPriority2() {
        return UCPTalarmPriority2;
    }


    /**
     * Sets the UCPTalarmPriority2 value for this UFPTalarmNotifier_DataReadData.
     * 
     * @param UCPTalarmPriority2
     */
    public void setUCPTalarmPriority2(int UCPTalarmPriority2) {
        this.UCPTalarmPriority2 = UCPTalarmPriority2;
    }


    /**
     * Gets the UCPTalarmGroup value for this UFPTalarmNotifier_DataReadData.
     * 
     * @return UCPTalarmGroup
     */
    public int getUCPTalarmGroup() {
        return UCPTalarmGroup;
    }


    /**
     * Sets the UCPTalarmGroup value for this UFPTalarmNotifier_DataReadData.
     * 
     * @param UCPTalarmGroup
     */
    public void setUCPTalarmGroup(int UCPTalarmGroup) {
        this.UCPTalarmGroup = UCPTalarmGroup;
    }


    /**
     * Gets the UCPTalarmType value for this UFPTalarmNotifier_DataReadData.
     * 
     * @return UCPTalarmType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTalarmType() {
        return UCPTalarmType;
    }


    /**
     * Sets the UCPTalarmType value for this UFPTalarmNotifier_DataReadData.
     * 
     * @param UCPTalarmType
     */
    public void setUCPTalarmType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTalarmType) {
        this.UCPTalarmType = UCPTalarmType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTalarmNotifier_DataReadData)) return false;
        UFPTalarmNotifier_DataReadData other = (UFPTalarmNotifier_DataReadData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTalarmText==null && other.getUCPTalarmText()==null) || 
             (this.UCPTalarmText!=null &&
              this.UCPTalarmText.equals(other.getUCPTalarmText()))) &&
            this.UCPTalarmPriority2 == other.getUCPTalarmPriority2() &&
            this.UCPTalarmGroup == other.getUCPTalarmGroup() &&
            ((this.UCPTalarmType==null && other.getUCPTalarmType()==null) || 
             (this.UCPTalarmType!=null &&
              this.UCPTalarmType.equals(other.getUCPTalarmType())));
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
        if (getUCPTalarmText() != null) {
            _hashCode += getUCPTalarmText().hashCode();
        }
        _hashCode += getUCPTalarmPriority2();
        _hashCode += getUCPTalarmGroup();
        if (getUCPTalarmType() != null) {
            _hashCode += getUCPTalarmType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTalarmNotifier_DataReadData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Data>ReadData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmPriority2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmPriority2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmType"));
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
