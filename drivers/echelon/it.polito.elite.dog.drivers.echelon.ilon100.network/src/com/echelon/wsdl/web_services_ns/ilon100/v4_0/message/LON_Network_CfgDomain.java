/**
 * LON_Network_CfgDomain.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class LON_Network_CfgDomain  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int UCPTdomainIndex;

    private int UCPTdomainLength;

    private byte[] UCPTdomainKey;

    public LON_Network_CfgDomain() {
    }

    public LON_Network_CfgDomain(
           int UCPTdomainIndex,
           int UCPTdomainLength,
           byte[] UCPTdomainKey) {
           this.UCPTdomainIndex = UCPTdomainIndex;
           this.UCPTdomainLength = UCPTdomainLength;
           this.UCPTdomainKey = UCPTdomainKey;
    }


    /**
     * Gets the UCPTdomainIndex value for this LON_Network_CfgDomain.
     * 
     * @return UCPTdomainIndex
     */
    public int getUCPTdomainIndex() {
        return UCPTdomainIndex;
    }


    /**
     * Sets the UCPTdomainIndex value for this LON_Network_CfgDomain.
     * 
     * @param UCPTdomainIndex
     */
    public void setUCPTdomainIndex(int UCPTdomainIndex) {
        this.UCPTdomainIndex = UCPTdomainIndex;
    }


    /**
     * Gets the UCPTdomainLength value for this LON_Network_CfgDomain.
     * 
     * @return UCPTdomainLength
     */
    public int getUCPTdomainLength() {
        return UCPTdomainLength;
    }


    /**
     * Sets the UCPTdomainLength value for this LON_Network_CfgDomain.
     * 
     * @param UCPTdomainLength
     */
    public void setUCPTdomainLength(int UCPTdomainLength) {
        this.UCPTdomainLength = UCPTdomainLength;
    }


    /**
     * Gets the UCPTdomainKey value for this LON_Network_CfgDomain.
     * 
     * @return UCPTdomainKey
     */
    public byte[] getUCPTdomainKey() {
        return UCPTdomainKey;
    }


    /**
     * Sets the UCPTdomainKey value for this LON_Network_CfgDomain.
     * 
     * @param UCPTdomainKey
     */
    public void setUCPTdomainKey(byte[] UCPTdomainKey) {
        this.UCPTdomainKey = UCPTdomainKey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Network_CfgDomain)) return false;
        LON_Network_CfgDomain other = (LON_Network_CfgDomain) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.UCPTdomainIndex == other.getUCPTdomainIndex() &&
            this.UCPTdomainLength == other.getUCPTdomainLength() &&
            ((this.UCPTdomainKey==null && other.getUCPTdomainKey()==null) || 
             (this.UCPTdomainKey!=null &&
              java.util.Arrays.equals(this.UCPTdomainKey, other.getUCPTdomainKey())));
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
        _hashCode += getUCPTdomainIndex();
        _hashCode += getUCPTdomainLength();
        if (getUCPTdomainKey() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTdomainKey());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTdomainKey(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Network_CfgDomain.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Network_Cfg>Domain"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdomainIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdomainIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdomainLength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdomainLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdomainKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdomainKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"));
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
