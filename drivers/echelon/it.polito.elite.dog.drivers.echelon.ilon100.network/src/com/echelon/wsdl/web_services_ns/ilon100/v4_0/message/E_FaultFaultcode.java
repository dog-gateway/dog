/**
 * E_FaultFaultcode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class E_FaultFaultcode  implements java.io.Serializable, org.apache.axis.encoding.SimpleType {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int _value;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fault_eType faultType;  // attribute

    public E_FaultFaultcode() {
    }

    // Simple Types must have a String constructor
    public E_FaultFaultcode(int _value) {
        this._value = _value;
    }
    public E_FaultFaultcode(java.lang.String _value) {
        this._value = new Integer(_value).intValue();
    }

    // Simple Types must have a toString for serializing the value
    public java.lang.String toString() {
        return new Integer(_value).toString();
    }


    /**
     * Gets the _value value for this E_FaultFaultcode.
     * 
     * @return _value
     */
    public int get_value() {
        return _value;
    }


    /**
     * Sets the _value value for this E_FaultFaultcode.
     * 
     * @param _value
     */
    public void set_value(int _value) {
        this._value = _value;
    }


    /**
     * Gets the faultType value for this E_FaultFaultcode.
     * 
     * @return faultType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fault_eType getFaultType() {
        return faultType;
    }


    /**
     * Sets the faultType value for this E_FaultFaultcode.
     * 
     * @param faultType
     */
    public void setFaultType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fault_eType faultType) {
        this.faultType = faultType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof E_FaultFaultcode)) return false;
        E_FaultFaultcode other = (E_FaultFaultcode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this._value == other.get_value() &&
            ((this.faultType==null && other.getFaultType()==null) || 
             (this.faultType!=null &&
              this.faultType.equals(other.getFaultType())));
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
        _hashCode += get_value();
        if (getFaultType() != null) {
            _hashCode += getFaultType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(E_FaultFaultcode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">E_Fault>faultcode"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("faultType");
        attrField.setXmlName(new javax.xml.namespace.QName("", "faultType"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Fault_eType"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "_value"));
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
          new  org.apache.axis.encoding.ser.SimpleSerializer(
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
          new  org.apache.axis.encoding.ser.SimpleDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
