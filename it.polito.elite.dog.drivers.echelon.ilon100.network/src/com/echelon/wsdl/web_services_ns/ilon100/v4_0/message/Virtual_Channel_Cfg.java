/**
 * Virtual_Channel_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Specialized channel configuration type.
 * 					Example:  xSelect="//Item[@xsi:type="Virtual_Channel_Cfg"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class Virtual_Channel_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle;

    private java.lang.String UCPTchannelType;

    public Virtual_Channel_Cfg() {
    }

    public Virtual_Channel_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle,
           java.lang.String UCPTchannelType) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus,
            UCPTlastUpdate,
            UCPTdescription,
            UCPTuri);
        this.UCPThandle = UCPThandle;
        this.UCPTchannelType = UCPTchannelType;
    }


    /**
     * Gets the UCPThandle value for this Virtual_Channel_Cfg.
     * 
     * @return UCPThandle
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPThandle() {
        return UCPThandle;
    }


    /**
     * Sets the UCPThandle value for this Virtual_Channel_Cfg.
     * 
     * @param UCPThandle
     */
    public void setUCPThandle(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle) {
        this.UCPThandle = UCPThandle;
    }


    /**
     * Gets the UCPTchannelType value for this Virtual_Channel_Cfg.
     * 
     * @return UCPTchannelType
     */
    public java.lang.String getUCPTchannelType() {
        return UCPTchannelType;
    }


    /**
     * Sets the UCPTchannelType value for this Virtual_Channel_Cfg.
     * 
     * @param UCPTchannelType
     */
    public void setUCPTchannelType(java.lang.String UCPTchannelType) {
        this.UCPTchannelType = UCPTchannelType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Virtual_Channel_Cfg)) return false;
        Virtual_Channel_Cfg other = (Virtual_Channel_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPThandle==null && other.getUCPThandle()==null) || 
             (this.UCPThandle!=null &&
              this.UCPThandle.equals(other.getUCPThandle()))) &&
            ((this.UCPTchannelType==null && other.getUCPTchannelType()==null) || 
             (this.UCPTchannelType!=null &&
              this.UCPTchannelType.equals(other.getUCPTchannelType())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getUCPThandle() != null) {
            _hashCode += getUCPThandle().hashCode();
        }
        if (getUCPTchannelType() != null) {
            _hashCode += getUCPTchannelType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Virtual_Channel_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Virtual_Channel_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThandle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThandle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_UniqueKey"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTchannelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTchannelType"));
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
