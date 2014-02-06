/**
 * TemplateManager_CfgEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_CfgEnum  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int UCPTindex;

    private java.lang.String UCPTname;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue;

    private int UCPTscope;

    private java.lang.String UCPTreference;

    public TemplateManager_CfgEnum() {
    }

    public TemplateManager_CfgEnum(
           int UCPTindex,
           java.lang.String UCPTname,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue,
           int UCPTscope,
           java.lang.String UCPTreference) {
           this.UCPTindex = UCPTindex;
           this.UCPTname = UCPTname;
           this.UCPTminValue = UCPTminValue;
           this.UCPTmaxValue = UCPTmaxValue;
           this.UCPTscope = UCPTscope;
           this.UCPTreference = UCPTreference;
    }


    /**
     * Gets the UCPTindex value for this TemplateManager_CfgEnum.
     * 
     * @return UCPTindex
     */
    public int getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this TemplateManager_CfgEnum.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(int UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTname value for this TemplateManager_CfgEnum.
     * 
     * @return UCPTname
     */
    public java.lang.String getUCPTname() {
        return UCPTname;
    }


    /**
     * Sets the UCPTname value for this TemplateManager_CfgEnum.
     * 
     * @param UCPTname
     */
    public void setUCPTname(java.lang.String UCPTname) {
        this.UCPTname = UCPTname;
    }


    /**
     * Gets the UCPTminValue value for this TemplateManager_CfgEnum.
     * 
     * @return UCPTminValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTminValue() {
        return UCPTminValue;
    }


    /**
     * Sets the UCPTminValue value for this TemplateManager_CfgEnum.
     * 
     * @param UCPTminValue
     */
    public void setUCPTminValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue) {
        this.UCPTminValue = UCPTminValue;
    }


    /**
     * Gets the UCPTmaxValue value for this TemplateManager_CfgEnum.
     * 
     * @return UCPTmaxValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmaxValue() {
        return UCPTmaxValue;
    }


    /**
     * Sets the UCPTmaxValue value for this TemplateManager_CfgEnum.
     * 
     * @param UCPTmaxValue
     */
    public void setUCPTmaxValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue) {
        this.UCPTmaxValue = UCPTmaxValue;
    }


    /**
     * Gets the UCPTscope value for this TemplateManager_CfgEnum.
     * 
     * @return UCPTscope
     */
    public int getUCPTscope() {
        return UCPTscope;
    }


    /**
     * Sets the UCPTscope value for this TemplateManager_CfgEnum.
     * 
     * @param UCPTscope
     */
    public void setUCPTscope(int UCPTscope) {
        this.UCPTscope = UCPTscope;
    }


    /**
     * Gets the UCPTreference value for this TemplateManager_CfgEnum.
     * 
     * @return UCPTreference
     */
    public java.lang.String getUCPTreference() {
        return UCPTreference;
    }


    /**
     * Sets the UCPTreference value for this TemplateManager_CfgEnum.
     * 
     * @param UCPTreference
     */
    public void setUCPTreference(java.lang.String UCPTreference) {
        this.UCPTreference = UCPTreference;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_CfgEnum)) return false;
        TemplateManager_CfgEnum other = (TemplateManager_CfgEnum) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.UCPTindex == other.getUCPTindex() &&
            ((this.UCPTname==null && other.getUCPTname()==null) || 
             (this.UCPTname!=null &&
              this.UCPTname.equals(other.getUCPTname()))) &&
            ((this.UCPTminValue==null && other.getUCPTminValue()==null) || 
             (this.UCPTminValue!=null &&
              this.UCPTminValue.equals(other.getUCPTminValue()))) &&
            ((this.UCPTmaxValue==null && other.getUCPTmaxValue()==null) || 
             (this.UCPTmaxValue!=null &&
              this.UCPTmaxValue.equals(other.getUCPTmaxValue()))) &&
            this.UCPTscope == other.getUCPTscope() &&
            ((this.UCPTreference==null && other.getUCPTreference()==null) || 
             (this.UCPTreference!=null &&
              this.UCPTreference.equals(other.getUCPTreference())));
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
        _hashCode += getUCPTindex();
        if (getUCPTname() != null) {
            _hashCode += getUCPTname().hashCode();
        }
        if (getUCPTminValue() != null) {
            _hashCode += getUCPTminValue().hashCode();
        }
        if (getUCPTmaxValue() != null) {
            _hashCode += getUCPTmaxValue().hashCode();
        }
        _hashCode += getUCPTscope();
        if (getUCPTreference() != null) {
            _hashCode += getUCPTreference().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateManager_CfgEnum.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgEnum"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("UCPTscope");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTscope"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTreference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTreference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
