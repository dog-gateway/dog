/**
 * TemplateManager_ENUM_CfgMember.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_ENUM_CfgMember  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTdescription;

    private java.lang.String UCPTmemberName;

    private int UCPTvalue;

    public TemplateManager_ENUM_CfgMember() {
    }

    public TemplateManager_ENUM_CfgMember(
           java.lang.String UCPTdescription,
           java.lang.String UCPTmemberName,
           int UCPTvalue) {
           this.UCPTdescription = UCPTdescription;
           this.UCPTmemberName = UCPTmemberName;
           this.UCPTvalue = UCPTvalue;
    }


    /**
     * Gets the UCPTdescription value for this TemplateManager_ENUM_CfgMember.
     * 
     * @return UCPTdescription
     */
    public java.lang.String getUCPTdescription() {
        return UCPTdescription;
    }


    /**
     * Sets the UCPTdescription value for this TemplateManager_ENUM_CfgMember.
     * 
     * @param UCPTdescription
     */
    public void setUCPTdescription(java.lang.String UCPTdescription) {
        this.UCPTdescription = UCPTdescription;
    }


    /**
     * Gets the UCPTmemberName value for this TemplateManager_ENUM_CfgMember.
     * 
     * @return UCPTmemberName
     */
    public java.lang.String getUCPTmemberName() {
        return UCPTmemberName;
    }


    /**
     * Sets the UCPTmemberName value for this TemplateManager_ENUM_CfgMember.
     * 
     * @param UCPTmemberName
     */
    public void setUCPTmemberName(java.lang.String UCPTmemberName) {
        this.UCPTmemberName = UCPTmemberName;
    }


    /**
     * Gets the UCPTvalue value for this TemplateManager_ENUM_CfgMember.
     * 
     * @return UCPTvalue
     */
    public int getUCPTvalue() {
        return UCPTvalue;
    }


    /**
     * Sets the UCPTvalue value for this TemplateManager_ENUM_CfgMember.
     * 
     * @param UCPTvalue
     */
    public void setUCPTvalue(int UCPTvalue) {
        this.UCPTvalue = UCPTvalue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_ENUM_CfgMember)) return false;
        TemplateManager_ENUM_CfgMember other = (TemplateManager_ENUM_CfgMember) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTdescription==null && other.getUCPTdescription()==null) || 
             (this.UCPTdescription!=null &&
              this.UCPTdescription.equals(other.getUCPTdescription()))) &&
            ((this.UCPTmemberName==null && other.getUCPTmemberName()==null) || 
             (this.UCPTmemberName!=null &&
              this.UCPTmemberName.equals(other.getUCPTmemberName()))) &&
            this.UCPTvalue == other.getUCPTvalue();
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
        if (getUCPTdescription() != null) {
            _hashCode += getUCPTdescription().hashCode();
        }
        if (getUCPTmemberName() != null) {
            _hashCode += getUCPTmemberName().hashCode();
        }
        _hashCode += getUCPTvalue();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateManager_ENUM_CfgMember.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_ENUM_Cfg>Member"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmemberName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmemberName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTvalue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTvalue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
