/**
 * UFPTanalogFunctionBlock_DpRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTanalogFunctionBlock_DpRef  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTfieldName;

    private java.lang.Double UCPTpollRate;

    public UFPTanalogFunctionBlock_DpRef() {
    }

    public UFPTanalogFunctionBlock_DpRef(
           java.lang.String dpType,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator discrim,
           java.lang.String UCPTname,
           java.lang.String UCPTformatDescription,
           java.lang.String UCPTfieldName,
           java.lang.Double UCPTpollRate) {
    	super(
        		UCPTname,
                UCPTformatDescription,
                dpType,
                discrim
            );
        this.UCPTfieldName = UCPTfieldName;
        this.UCPTpollRate = UCPTpollRate;
    }


    /**
     * Gets the UCPTfieldName value for this UFPTanalogFunctionBlock_DpRef.
     * 
     * @return UCPTfieldName
     */
    public java.lang.String getUCPTfieldName() {
        return UCPTfieldName;
    }


    /**
     * Sets the UCPTfieldName value for this UFPTanalogFunctionBlock_DpRef.
     * 
     * @param UCPTfieldName
     */
    public void setUCPTfieldName(java.lang.String UCPTfieldName) {
        this.UCPTfieldName = UCPTfieldName;
    }


    /**
     * Gets the UCPTpollRate value for this UFPTanalogFunctionBlock_DpRef.
     * 
     * @return UCPTpollRate
     */
    public java.lang.Double getUCPTpollRate() {
        return UCPTpollRate;
    }


    /**
     * Sets the UCPTpollRate value for this UFPTanalogFunctionBlock_DpRef.
     * 
     * @param UCPTpollRate
     */
    public void setUCPTpollRate(java.lang.Double UCPTpollRate) {
        this.UCPTpollRate = UCPTpollRate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTanalogFunctionBlock_DpRef)) return false;
        UFPTanalogFunctionBlock_DpRef other = (UFPTanalogFunctionBlock_DpRef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTfieldName==null && other.getUCPTfieldName()==null) || 
             (this.UCPTfieldName!=null &&
              this.UCPTfieldName.equals(other.getUCPTfieldName()))) &&
            ((this.UCPTpollRate==null && other.getUCPTpollRate()==null) || 
             (this.UCPTpollRate!=null &&
              this.UCPTpollRate.equals(other.getUCPTpollRate())));
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
        if (getUCPTfieldName() != null) {
            _hashCode += getUCPTfieldName().hashCode();
        }
        if (getUCPTpollRate() != null) {
            _hashCode += getUCPTpollRate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTanalogFunctionBlock_DpRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTanalogFunctionBlock_DpRef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfieldName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpollRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpollRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
