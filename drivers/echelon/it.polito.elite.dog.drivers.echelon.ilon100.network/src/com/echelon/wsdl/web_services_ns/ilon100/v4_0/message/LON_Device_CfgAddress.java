/**
 * LON_Device_CfgAddress.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class LON_Device_CfgAddress  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTdomainIndex;

    private int UCPTdomainLength;

    private byte[] UCPTdomainKey;

    private java.lang.Integer UCPTsubnet;

    private java.lang.Integer UCPTnodeId;

    public LON_Device_CfgAddress() {
    }

    public LON_Device_CfgAddress(
           java.lang.Integer UCPTdomainIndex,
           int UCPTdomainLength,
           byte[] UCPTdomainKey,
           java.lang.Integer UCPTsubnet,
           java.lang.Integer UCPTnodeId) {
           this.UCPTdomainIndex = UCPTdomainIndex;
           this.UCPTdomainLength = UCPTdomainLength;
           this.UCPTdomainKey = UCPTdomainKey;
           this.UCPTsubnet = UCPTsubnet;
           this.UCPTnodeId = UCPTnodeId;
    }


    /**
     * Gets the UCPTdomainIndex value for this LON_Device_CfgAddress.
     * 
     * @return UCPTdomainIndex
     */
    public java.lang.Integer getUCPTdomainIndex() {
        return UCPTdomainIndex;
    }


    /**
     * Sets the UCPTdomainIndex value for this LON_Device_CfgAddress.
     * 
     * @param UCPTdomainIndex
     */
    public void setUCPTdomainIndex(java.lang.Integer UCPTdomainIndex) {
        this.UCPTdomainIndex = UCPTdomainIndex;
    }


    /**
     * Gets the UCPTdomainLength value for this LON_Device_CfgAddress.
     * 
     * @return UCPTdomainLength
     */
    public int getUCPTdomainLength() {
        return UCPTdomainLength;
    }


    /**
     * Sets the UCPTdomainLength value for this LON_Device_CfgAddress.
     * 
     * @param UCPTdomainLength
     */
    public void setUCPTdomainLength(int UCPTdomainLength) {
        this.UCPTdomainLength = UCPTdomainLength;
    }


    /**
     * Gets the UCPTdomainKey value for this LON_Device_CfgAddress.
     * 
     * @return UCPTdomainKey
     */
    public byte[] getUCPTdomainKey() {
        return UCPTdomainKey;
    }


    /**
     * Sets the UCPTdomainKey value for this LON_Device_CfgAddress.
     * 
     * @param UCPTdomainKey
     */
    public void setUCPTdomainKey(byte[] UCPTdomainKey) {
        this.UCPTdomainKey = UCPTdomainKey;
    }


    /**
     * Gets the UCPTsubnet value for this LON_Device_CfgAddress.
     * 
     * @return UCPTsubnet
     */
    public java.lang.Integer getUCPTsubnet() {
        return UCPTsubnet;
    }


    /**
     * Sets the UCPTsubnet value for this LON_Device_CfgAddress.
     * 
     * @param UCPTsubnet
     */
    public void setUCPTsubnet(java.lang.Integer UCPTsubnet) {
        this.UCPTsubnet = UCPTsubnet;
    }


    /**
     * Gets the UCPTnodeId value for this LON_Device_CfgAddress.
     * 
     * @return UCPTnodeId
     */
    public java.lang.Integer getUCPTnodeId() {
        return UCPTnodeId;
    }


    /**
     * Sets the UCPTnodeId value for this LON_Device_CfgAddress.
     * 
     * @param UCPTnodeId
     */
    public void setUCPTnodeId(java.lang.Integer UCPTnodeId) {
        this.UCPTnodeId = UCPTnodeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Device_CfgAddress)) return false;
        LON_Device_CfgAddress other = (LON_Device_CfgAddress) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTdomainIndex==null && other.getUCPTdomainIndex()==null) || 
             (this.UCPTdomainIndex!=null &&
              this.UCPTdomainIndex.equals(other.getUCPTdomainIndex()))) &&
            this.UCPTdomainLength == other.getUCPTdomainLength() &&
            ((this.UCPTdomainKey==null && other.getUCPTdomainKey()==null) || 
             (this.UCPTdomainKey!=null &&
              java.util.Arrays.equals(this.UCPTdomainKey, other.getUCPTdomainKey()))) &&
            ((this.UCPTsubnet==null && other.getUCPTsubnet()==null) || 
             (this.UCPTsubnet!=null &&
              this.UCPTsubnet.equals(other.getUCPTsubnet()))) &&
            ((this.UCPTnodeId==null && other.getUCPTnodeId()==null) || 
             (this.UCPTnodeId!=null &&
              this.UCPTnodeId.equals(other.getUCPTnodeId())));
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
        if (getUCPTdomainIndex() != null) {
            _hashCode += getUCPTdomainIndex().hashCode();
        }
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
        if (getUCPTsubnet() != null) {
            _hashCode += getUCPTsubnet().hashCode();
        }
        if (getUCPTnodeId() != null) {
            _hashCode += getUCPTnodeId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Device_CfgAddress.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_Cfg>Address"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdomainIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdomainIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsubnet");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsubnet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTnodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTnodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
