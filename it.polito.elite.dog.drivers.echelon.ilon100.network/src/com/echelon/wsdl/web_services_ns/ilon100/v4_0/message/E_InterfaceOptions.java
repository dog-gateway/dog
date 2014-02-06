/**
 * E_InterfaceOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class E_InterfaceOptions  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTspeed;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTsize;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTparity;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTstopBits;

    public E_InterfaceOptions() {
    }

    public E_InterfaceOptions(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTspeed,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTsize,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTparity,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTstopBits) {
           this.UCPTspeed = UCPTspeed;
           this.UCPTsize = UCPTsize;
           this.UCPTparity = UCPTparity;
           this.UCPTstopBits = UCPTstopBits;
    }


    /**
     * Gets the UCPTspeed value for this E_InterfaceOptions.
     * 
     * @return UCPTspeed
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTspeed() {
        return UCPTspeed;
    }


    /**
     * Sets the UCPTspeed value for this E_InterfaceOptions.
     * 
     * @param UCPTspeed
     */
    public void setUCPTspeed(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTspeed) {
        this.UCPTspeed = UCPTspeed;
    }


    /**
     * Gets the UCPTsize value for this E_InterfaceOptions.
     * 
     * @return UCPTsize
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTsize() {
        return UCPTsize;
    }


    /**
     * Sets the UCPTsize value for this E_InterfaceOptions.
     * 
     * @param UCPTsize
     */
    public void setUCPTsize(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTsize) {
        this.UCPTsize = UCPTsize;
    }


    /**
     * Gets the UCPTparity value for this E_InterfaceOptions.
     * 
     * @return UCPTparity
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTparity() {
        return UCPTparity;
    }


    /**
     * Sets the UCPTparity value for this E_InterfaceOptions.
     * 
     * @param UCPTparity
     */
    public void setUCPTparity(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTparity) {
        this.UCPTparity = UCPTparity;
    }


    /**
     * Gets the UCPTstopBits value for this E_InterfaceOptions.
     * 
     * @return UCPTstopBits
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTstopBits() {
        return UCPTstopBits;
    }


    /**
     * Sets the UCPTstopBits value for this E_InterfaceOptions.
     * 
     * @param UCPTstopBits
     */
    public void setUCPTstopBits(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTstopBits) {
        this.UCPTstopBits = UCPTstopBits;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof E_InterfaceOptions)) return false;
        E_InterfaceOptions other = (E_InterfaceOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTspeed==null && other.getUCPTspeed()==null) || 
             (this.UCPTspeed!=null &&
              this.UCPTspeed.equals(other.getUCPTspeed()))) &&
            ((this.UCPTsize==null && other.getUCPTsize()==null) || 
             (this.UCPTsize!=null &&
              this.UCPTsize.equals(other.getUCPTsize()))) &&
            ((this.UCPTparity==null && other.getUCPTparity()==null) || 
             (this.UCPTparity!=null &&
              this.UCPTparity.equals(other.getUCPTparity()))) &&
            ((this.UCPTstopBits==null && other.getUCPTstopBits()==null) || 
             (this.UCPTstopBits!=null &&
              this.UCPTstopBits.equals(other.getUCPTstopBits())));
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
        if (getUCPTspeed() != null) {
            _hashCode += getUCPTspeed().hashCode();
        }
        if (getUCPTsize() != null) {
            _hashCode += getUCPTsize().hashCode();
        }
        if (getUCPTparity() != null) {
            _hashCode += getUCPTparity().hashCode();
        }
        if (getUCPTstopBits() != null) {
            _hashCode += getUCPTstopBits().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(E_InterfaceOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_InterfaceOptions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTspeed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTspeed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTparity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTparity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstopBits");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstopBits"));
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
