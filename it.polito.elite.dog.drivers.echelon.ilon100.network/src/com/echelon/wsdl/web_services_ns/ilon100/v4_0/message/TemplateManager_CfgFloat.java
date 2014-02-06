/**
 * TemplateManager_CfgFloat.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_CfgFloat  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTbaseType;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue;

    public TemplateManager_CfgFloat() {
    }

    public TemplateManager_CfgFloat(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTbaseType,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue) {
           this.UCPTbaseType = UCPTbaseType;
           this.UCPTminValue = UCPTminValue;
           this.UCPTmaxValue = UCPTmaxValue;
    }


    /**
     * Gets the UCPTbaseType value for this TemplateManager_CfgFloat.
     * 
     * @return UCPTbaseType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTbaseType() {
        return UCPTbaseType;
    }


    /**
     * Sets the UCPTbaseType value for this TemplateManager_CfgFloat.
     * 
     * @param UCPTbaseType
     */
    public void setUCPTbaseType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTbaseType) {
        this.UCPTbaseType = UCPTbaseType;
    }


    /**
     * Gets the UCPTminValue value for this TemplateManager_CfgFloat.
     * 
     * @return UCPTminValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTminValue() {
        return UCPTminValue;
    }


    /**
     * Sets the UCPTminValue value for this TemplateManager_CfgFloat.
     * 
     * @param UCPTminValue
     */
    public void setUCPTminValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue) {
        this.UCPTminValue = UCPTminValue;
    }


    /**
     * Gets the UCPTmaxValue value for this TemplateManager_CfgFloat.
     * 
     * @return UCPTmaxValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmaxValue() {
        return UCPTmaxValue;
    }


    /**
     * Sets the UCPTmaxValue value for this TemplateManager_CfgFloat.
     * 
     * @param UCPTmaxValue
     */
    public void setUCPTmaxValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue) {
        this.UCPTmaxValue = UCPTmaxValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_CfgFloat)) return false;
        TemplateManager_CfgFloat other = (TemplateManager_CfgFloat) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTbaseType==null && other.getUCPTbaseType()==null) || 
             (this.UCPTbaseType!=null &&
              this.UCPTbaseType.equals(other.getUCPTbaseType()))) &&
            ((this.UCPTminValue==null && other.getUCPTminValue()==null) || 
             (this.UCPTminValue!=null &&
              this.UCPTminValue.equals(other.getUCPTminValue()))) &&
            ((this.UCPTmaxValue==null && other.getUCPTmaxValue()==null) || 
             (this.UCPTmaxValue!=null &&
              this.UCPTmaxValue.equals(other.getUCPTmaxValue())));
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
        if (getUCPTbaseType() != null) {
            _hashCode += getUCPTbaseType().hashCode();
        }
        if (getUCPTminValue() != null) {
            _hashCode += getUCPTminValue().hashCode();
        }
        if (getUCPTmaxValue() != null) {
            _hashCode += getUCPTmaxValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateManager_CfgFloat.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgFloat"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTbaseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTbaseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTminValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTminValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxValue"));
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
