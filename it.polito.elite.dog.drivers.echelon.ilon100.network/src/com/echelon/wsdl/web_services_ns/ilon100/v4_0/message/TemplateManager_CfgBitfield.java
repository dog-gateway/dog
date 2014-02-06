/**
 * TemplateManager_CfgBitfield.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_CfgBitfield  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private short UCPTsignedFlag;

    private int UCPTwidth;

    private int UCPToffset;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfieldScale scale;

    public TemplateManager_CfgBitfield() {
    }

    public TemplateManager_CfgBitfield(
           short UCPTsignedFlag,
           int UCPTwidth,
           int UCPToffset,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfieldScale scale) {
           this.UCPTsignedFlag = UCPTsignedFlag;
           this.UCPTwidth = UCPTwidth;
           this.UCPToffset = UCPToffset;
           this.UCPTminValue = UCPTminValue;
           this.UCPTmaxValue = UCPTmaxValue;
           this.scale = scale;
    }


    /**
     * Gets the UCPTsignedFlag value for this TemplateManager_CfgBitfield.
     * 
     * @return UCPTsignedFlag
     */
    public short getUCPTsignedFlag() {
        return UCPTsignedFlag;
    }


    /**
     * Sets the UCPTsignedFlag value for this TemplateManager_CfgBitfield.
     * 
     * @param UCPTsignedFlag
     */
    public void setUCPTsignedFlag(short UCPTsignedFlag) {
        this.UCPTsignedFlag = UCPTsignedFlag;
    }


    /**
     * Gets the UCPTwidth value for this TemplateManager_CfgBitfield.
     * 
     * @return UCPTwidth
     */
    public int getUCPTwidth() {
        return UCPTwidth;
    }


    /**
     * Sets the UCPTwidth value for this TemplateManager_CfgBitfield.
     * 
     * @param UCPTwidth
     */
    public void setUCPTwidth(int UCPTwidth) {
        this.UCPTwidth = UCPTwidth;
    }


    /**
     * Gets the UCPToffset value for this TemplateManager_CfgBitfield.
     * 
     * @return UCPToffset
     */
    public int getUCPToffset() {
        return UCPToffset;
    }


    /**
     * Sets the UCPToffset value for this TemplateManager_CfgBitfield.
     * 
     * @param UCPToffset
     */
    public void setUCPToffset(int UCPToffset) {
        this.UCPToffset = UCPToffset;
    }


    /**
     * Gets the UCPTminValue value for this TemplateManager_CfgBitfield.
     * 
     * @return UCPTminValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTminValue() {
        return UCPTminValue;
    }


    /**
     * Sets the UCPTminValue value for this TemplateManager_CfgBitfield.
     * 
     * @param UCPTminValue
     */
    public void setUCPTminValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue) {
        this.UCPTminValue = UCPTminValue;
    }


    /**
     * Gets the UCPTmaxValue value for this TemplateManager_CfgBitfield.
     * 
     * @return UCPTmaxValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmaxValue() {
        return UCPTmaxValue;
    }


    /**
     * Sets the UCPTmaxValue value for this TemplateManager_CfgBitfield.
     * 
     * @param UCPTmaxValue
     */
    public void setUCPTmaxValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue) {
        this.UCPTmaxValue = UCPTmaxValue;
    }


    /**
     * Gets the scale value for this TemplateManager_CfgBitfield.
     * 
     * @return scale
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfieldScale getScale() {
        return scale;
    }


    /**
     * Sets the scale value for this TemplateManager_CfgBitfield.
     * 
     * @param scale
     */
    public void setScale(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfieldScale scale) {
        this.scale = scale;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_CfgBitfield)) return false;
        TemplateManager_CfgBitfield other = (TemplateManager_CfgBitfield) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.UCPTsignedFlag == other.getUCPTsignedFlag() &&
            this.UCPTwidth == other.getUCPTwidth() &&
            this.UCPToffset == other.getUCPToffset() &&
            ((this.UCPTminValue==null && other.getUCPTminValue()==null) || 
             (this.UCPTminValue!=null &&
              this.UCPTminValue.equals(other.getUCPTminValue()))) &&
            ((this.UCPTmaxValue==null && other.getUCPTmaxValue()==null) || 
             (this.UCPTmaxValue!=null &&
              this.UCPTmaxValue.equals(other.getUCPTmaxValue()))) &&
            ((this.scale==null && other.getScale()==null) || 
             (this.scale!=null &&
              this.scale.equals(other.getScale())));
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
        _hashCode += getUCPTsignedFlag();
        _hashCode += getUCPTwidth();
        _hashCode += getUCPToffset();
        if (getUCPTminValue() != null) {
            _hashCode += getUCPTminValue().hashCode();
        }
        if (getUCPTmaxValue() != null) {
            _hashCode += getUCPTmaxValue().hashCode();
        }
        if (getScale() != null) {
            _hashCode += getScale().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateManager_CfgBitfield.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgBitfield"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsignedFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsignedFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTwidth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTwidth"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Scale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_CfgBitfield>Scale"));
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
