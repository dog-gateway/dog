/**
 * TemplateManager_CfgBitfieldScale.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_CfgBitfieldScale  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private float UCPTmultiplier;

    private int UCPTexponent;

    private int UCPToffset;

    private double UCPTresolution;

    public TemplateManager_CfgBitfieldScale() {
    }

    public TemplateManager_CfgBitfieldScale(
           float UCPTmultiplier,
           int UCPTexponent,
           int UCPToffset,
           double UCPTresolution) {
           this.UCPTmultiplier = UCPTmultiplier;
           this.UCPTexponent = UCPTexponent;
           this.UCPToffset = UCPToffset;
           this.UCPTresolution = UCPTresolution;
    }


    /**
     * Gets the UCPTmultiplier value for this TemplateManager_CfgBitfieldScale.
     * 
     * @return UCPTmultiplier
     */
    public float getUCPTmultiplier() {
        return UCPTmultiplier;
    }


    /**
     * Sets the UCPTmultiplier value for this TemplateManager_CfgBitfieldScale.
     * 
     * @param UCPTmultiplier
     */
    public void setUCPTmultiplier(float UCPTmultiplier) {
        this.UCPTmultiplier = UCPTmultiplier;
    }


    /**
     * Gets the UCPTexponent value for this TemplateManager_CfgBitfieldScale.
     * 
     * @return UCPTexponent
     */
    public int getUCPTexponent() {
        return UCPTexponent;
    }


    /**
     * Sets the UCPTexponent value for this TemplateManager_CfgBitfieldScale.
     * 
     * @param UCPTexponent
     */
    public void setUCPTexponent(int UCPTexponent) {
        this.UCPTexponent = UCPTexponent;
    }


    /**
     * Gets the UCPToffset value for this TemplateManager_CfgBitfieldScale.
     * 
     * @return UCPToffset
     */
    public int getUCPToffset() {
        return UCPToffset;
    }


    /**
     * Sets the UCPToffset value for this TemplateManager_CfgBitfieldScale.
     * 
     * @param UCPToffset
     */
    public void setUCPToffset(int UCPToffset) {
        this.UCPToffset = UCPToffset;
    }


    /**
     * Gets the UCPTresolution value for this TemplateManager_CfgBitfieldScale.
     * 
     * @return UCPTresolution
     */
    public double getUCPTresolution() {
        return UCPTresolution;
    }


    /**
     * Sets the UCPTresolution value for this TemplateManager_CfgBitfieldScale.
     * 
     * @param UCPTresolution
     */
    public void setUCPTresolution(double UCPTresolution) {
        this.UCPTresolution = UCPTresolution;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_CfgBitfieldScale)) return false;
        TemplateManager_CfgBitfieldScale other = (TemplateManager_CfgBitfieldScale) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.UCPTmultiplier == other.getUCPTmultiplier() &&
            this.UCPTexponent == other.getUCPTexponent() &&
            this.UCPToffset == other.getUCPToffset() &&
            this.UCPTresolution == other.getUCPTresolution();
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
        _hashCode += new Float(getUCPTmultiplier()).hashCode();
        _hashCode += getUCPTexponent();
        _hashCode += getUCPToffset();
        _hashCode += new Double(getUCPTresolution()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateManager_CfgBitfieldScale.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_CfgBitfield>Scale"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmultiplier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmultiplier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTexponent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTexponent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPToffset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPToffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTresolution");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTresolution"));
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
