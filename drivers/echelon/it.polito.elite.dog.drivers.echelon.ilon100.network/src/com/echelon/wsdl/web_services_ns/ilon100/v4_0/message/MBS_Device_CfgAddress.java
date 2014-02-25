/**
 * MBS_Device_CfgAddress.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class MBS_Device_CfgAddress  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmbusAddressTyp;

    private java.lang.String UCPTmbusPrimaryAddress;

    private java.lang.String UCPTmbusSecondaryAddress;

    public MBS_Device_CfgAddress() {
    }

    public MBS_Device_CfgAddress(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmbusAddressTyp,
           java.lang.String UCPTmbusPrimaryAddress,
           java.lang.String UCPTmbusSecondaryAddress) {
           this.UCPTmbusAddressTyp = UCPTmbusAddressTyp;
           this.UCPTmbusPrimaryAddress = UCPTmbusPrimaryAddress;
           this.UCPTmbusSecondaryAddress = UCPTmbusSecondaryAddress;
    }


    /**
     * Gets the UCPTmbusAddressTyp value for this MBS_Device_CfgAddress.
     * 
     * @return UCPTmbusAddressTyp
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmbusAddressTyp() {
        return UCPTmbusAddressTyp;
    }


    /**
     * Sets the UCPTmbusAddressTyp value for this MBS_Device_CfgAddress.
     * 
     * @param UCPTmbusAddressTyp
     */
    public void setUCPTmbusAddressTyp(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmbusAddressTyp) {
        this.UCPTmbusAddressTyp = UCPTmbusAddressTyp;
    }


    /**
     * Gets the UCPTmbusPrimaryAddress value for this MBS_Device_CfgAddress.
     * 
     * @return UCPTmbusPrimaryAddress
     */
    public java.lang.String getUCPTmbusPrimaryAddress() {
        return UCPTmbusPrimaryAddress;
    }


    /**
     * Sets the UCPTmbusPrimaryAddress value for this MBS_Device_CfgAddress.
     * 
     * @param UCPTmbusPrimaryAddress
     */
    public void setUCPTmbusPrimaryAddress(java.lang.String UCPTmbusPrimaryAddress) {
        this.UCPTmbusPrimaryAddress = UCPTmbusPrimaryAddress;
    }


    /**
     * Gets the UCPTmbusSecondaryAddress value for this MBS_Device_CfgAddress.
     * 
     * @return UCPTmbusSecondaryAddress
     */
    public java.lang.String getUCPTmbusSecondaryAddress() {
        return UCPTmbusSecondaryAddress;
    }


    /**
     * Sets the UCPTmbusSecondaryAddress value for this MBS_Device_CfgAddress.
     * 
     * @param UCPTmbusSecondaryAddress
     */
    public void setUCPTmbusSecondaryAddress(java.lang.String UCPTmbusSecondaryAddress) {
        this.UCPTmbusSecondaryAddress = UCPTmbusSecondaryAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MBS_Device_CfgAddress)) return false;
        MBS_Device_CfgAddress other = (MBS_Device_CfgAddress) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTmbusAddressTyp==null && other.getUCPTmbusAddressTyp()==null) || 
             (this.UCPTmbusAddressTyp!=null &&
              this.UCPTmbusAddressTyp.equals(other.getUCPTmbusAddressTyp()))) &&
            ((this.UCPTmbusPrimaryAddress==null && other.getUCPTmbusPrimaryAddress()==null) || 
             (this.UCPTmbusPrimaryAddress!=null &&
              this.UCPTmbusPrimaryAddress.equals(other.getUCPTmbusPrimaryAddress()))) &&
            ((this.UCPTmbusSecondaryAddress==null && other.getUCPTmbusSecondaryAddress()==null) || 
             (this.UCPTmbusSecondaryAddress!=null &&
              this.UCPTmbusSecondaryAddress.equals(other.getUCPTmbusSecondaryAddress())));
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
        if (getUCPTmbusAddressTyp() != null) {
            _hashCode += getUCPTmbusAddressTyp().hashCode();
        }
        if (getUCPTmbusPrimaryAddress() != null) {
            _hashCode += getUCPTmbusPrimaryAddress().hashCode();
        }
        if (getUCPTmbusSecondaryAddress() != null) {
            _hashCode += getUCPTmbusSecondaryAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MBS_Device_CfgAddress.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">MBS_Device_Cfg>Address"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmbusAddressTyp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmbusAddressTyp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmbusPrimaryAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmbusPrimaryAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmbusSecondaryAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmbusSecondaryAddress"));
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
