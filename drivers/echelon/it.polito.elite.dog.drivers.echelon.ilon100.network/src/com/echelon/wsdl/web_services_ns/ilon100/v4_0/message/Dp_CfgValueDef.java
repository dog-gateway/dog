/**
 * Dp_CfgValueDef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class Dp_CfgValueDef  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTindex;

    private java.lang.String UCPTname;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTvalue;

    public Dp_CfgValueDef() {
    }

    public Dp_CfgValueDef(
           java.lang.Integer UCPTindex,
           java.lang.String UCPTname,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTvalue) {
           this.UCPTindex = UCPTindex;
           this.UCPTname = UCPTname;
           this.UCPTvalue = UCPTvalue;
    }


    /**
     * Gets the UCPTindex value for this Dp_CfgValueDef.
     * 
     * @return UCPTindex
     */
    public java.lang.Integer getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this Dp_CfgValueDef.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(java.lang.Integer UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTname value for this Dp_CfgValueDef.
     * 
     * @return UCPTname
     */
    public java.lang.String getUCPTname() {
        return UCPTname;
    }


    /**
     * Sets the UCPTname value for this Dp_CfgValueDef.
     * 
     * @param UCPTname
     */
    public void setUCPTname(java.lang.String UCPTname) {
        this.UCPTname = UCPTname;
    }


    /**
     * Gets the UCPTvalue value for this Dp_CfgValueDef.
     * 
     * @return UCPTvalue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTvalue() {
        return UCPTvalue;
    }


    /**
     * Sets the UCPTvalue value for this Dp_CfgValueDef.
     * 
     * @param UCPTvalue
     */
    public void setUCPTvalue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTvalue) {
        this.UCPTvalue = UCPTvalue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dp_CfgValueDef)) return false;
        Dp_CfgValueDef other = (Dp_CfgValueDef) obj;
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
            ((this.UCPTname==null && other.getUCPTname()==null) || 
             (this.UCPTname!=null &&
              this.UCPTname.equals(other.getUCPTname()))) &&
            ((this.UCPTvalue==null && other.getUCPTvalue()==null) || 
             (this.UCPTvalue!=null &&
              this.UCPTvalue.equals(other.getUCPTvalue())));
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
        if (getUCPTname() != null) {
            _hashCode += getUCPTname().hashCode();
        }
        if (getUCPTvalue() != null) {
            _hashCode += getUCPTvalue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dp_CfgValueDef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_CfgValueDef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTvalue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTvalue"));
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
