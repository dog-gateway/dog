/**
 * TemplateManager_CfgFormatString.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_CfgFormatString  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTformatDescription;

    private java.lang.String UCPTformatSpecifier;

    public TemplateManager_CfgFormatString() {
    }

    public TemplateManager_CfgFormatString(
           java.lang.String UCPTformatDescription,
           java.lang.String UCPTformatSpecifier) {
           this.UCPTformatDescription = UCPTformatDescription;
           this.UCPTformatSpecifier = UCPTformatSpecifier;
    }


    /**
     * Gets the UCPTformatDescription value for this TemplateManager_CfgFormatString.
     * 
     * @return UCPTformatDescription
     */
    public java.lang.String getUCPTformatDescription() {
        return UCPTformatDescription;
    }


    /**
     * Sets the UCPTformatDescription value for this TemplateManager_CfgFormatString.
     * 
     * @param UCPTformatDescription
     */
    public void setUCPTformatDescription(java.lang.String UCPTformatDescription) {
        this.UCPTformatDescription = UCPTformatDescription;
    }


    /**
     * Gets the UCPTformatSpecifier value for this TemplateManager_CfgFormatString.
     * 
     * @return UCPTformatSpecifier
     */
    public java.lang.String getUCPTformatSpecifier() {
        return UCPTformatSpecifier;
    }


    /**
     * Sets the UCPTformatSpecifier value for this TemplateManager_CfgFormatString.
     * 
     * @param UCPTformatSpecifier
     */
    public void setUCPTformatSpecifier(java.lang.String UCPTformatSpecifier) {
        this.UCPTformatSpecifier = UCPTformatSpecifier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_CfgFormatString)) return false;
        TemplateManager_CfgFormatString other = (TemplateManager_CfgFormatString) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTformatDescription==null && other.getUCPTformatDescription()==null) || 
             (this.UCPTformatDescription!=null &&
              this.UCPTformatDescription.equals(other.getUCPTformatDescription()))) &&
            ((this.UCPTformatSpecifier==null && other.getUCPTformatSpecifier()==null) || 
             (this.UCPTformatSpecifier!=null &&
              this.UCPTformatSpecifier.equals(other.getUCPTformatSpecifier())));
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
        if (getUCPTformatDescription() != null) {
            _hashCode += getUCPTformatDescription().hashCode();
        }
        if (getUCPTformatSpecifier() != null) {
            _hashCode += getUCPTformatSpecifier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateManager_CfgFormatString.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgFormatString"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTformatDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTformatDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTformatSpecifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTformatSpecifier"));
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
