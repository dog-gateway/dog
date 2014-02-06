/**
 * UFPTrealTimeClock_Data_DpRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTrealTimeClock_Data_DpRef  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.util.Calendar[] value;

    public UFPTrealTimeClock_Data_DpRef() {
    }

    public UFPTrealTimeClock_Data_DpRef(
           java.lang.String dpType,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator discrim,
           java.lang.String UCPTname,
           java.lang.String UCPTformatDescription,
           java.util.Calendar[] value) {
    	super(
        		UCPTname,
                UCPTformatDescription,
                dpType,
                discrim
            );
        this.value = value;
    }


    /**
     * Gets the value value for this UFPTrealTimeClock_Data_DpRef.
     * 
     * @return value
     */
    public java.util.Calendar[] getValue() {
        return value;
    }


    /**
     * Sets the value value for this UFPTrealTimeClock_Data_DpRef.
     * 
     * @param value
     */
    public void setValue(java.util.Calendar[] value) {
        this.value = value;
    }

    public java.util.Calendar getValue(int i) {
        return this.value[i];
    }

    public void setValue(int i, java.util.Calendar _value) {
        this.value[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTrealTimeClock_Data_DpRef)) return false;
        UFPTrealTimeClock_Data_DpRef other = (UFPTrealTimeClock_Data_DpRef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              java.util.Arrays.equals(this.value, other.getValue())));
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
        if (getValue() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValue());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValue(), i);
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
        new org.apache.axis.description.TypeDesc(UFPTrealTimeClock_Data_DpRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTrealTimeClock_Data_DpRef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
