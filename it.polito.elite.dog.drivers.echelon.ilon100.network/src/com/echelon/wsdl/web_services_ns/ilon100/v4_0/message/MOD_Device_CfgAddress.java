/**
 * MOD_Device_CfgAddress.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class MOD_Device_CfgAddress  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int UCPTaddress;

    private org.apache.axis.types.URI UCPTipAddress;

    public MOD_Device_CfgAddress() {
    }

    public MOD_Device_CfgAddress(
           int UCPTaddress,
           org.apache.axis.types.URI UCPTipAddress) {
           this.UCPTaddress = UCPTaddress;
           this.UCPTipAddress = UCPTipAddress;
    }


    /**
     * Gets the UCPTaddress value for this MOD_Device_CfgAddress.
     * 
     * @return UCPTaddress
     */
    public int getUCPTaddress() {
        return UCPTaddress;
    }


    /**
     * Sets the UCPTaddress value for this MOD_Device_CfgAddress.
     * 
     * @param UCPTaddress
     */
    public void setUCPTaddress(int UCPTaddress) {
        this.UCPTaddress = UCPTaddress;
    }


    /**
     * Gets the UCPTipAddress value for this MOD_Device_CfgAddress.
     * 
     * @return UCPTipAddress
     */
    public org.apache.axis.types.URI getUCPTipAddress() {
        return UCPTipAddress;
    }


    /**
     * Sets the UCPTipAddress value for this MOD_Device_CfgAddress.
     * 
     * @param UCPTipAddress
     */
    public void setUCPTipAddress(org.apache.axis.types.URI UCPTipAddress) {
        this.UCPTipAddress = UCPTipAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MOD_Device_CfgAddress)) return false;
        MOD_Device_CfgAddress other = (MOD_Device_CfgAddress) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.UCPTaddress == other.getUCPTaddress() &&
            ((this.UCPTipAddress==null && other.getUCPTipAddress()==null) || 
             (this.UCPTipAddress!=null &&
              this.UCPTipAddress.equals(other.getUCPTipAddress())));
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
        _hashCode += getUCPTaddress();
        if (getUCPTipAddress() != null) {
            _hashCode += getUCPTipAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MOD_Device_CfgAddress.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">MOD_Device_Cfg>Address"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTaddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTaddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTipAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTipAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
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
