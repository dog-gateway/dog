/**
 * E_Dummy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public abstract class E_Dummy  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_eType dummyA;

    public E_Dummy() {
    }

    public E_Dummy(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_eType dummyA) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus);
        this.dummyA = dummyA;
    }


    /**
     * Gets the dummyA value for this E_Dummy.
     * 
     * @return dummyA
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_eType getDummyA() {
        return dummyA;
    }


    /**
     * Sets the dummyA value for this E_Dummy.
     * 
     * @param dummyA
     */
    public void setDummyA(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_eType dummyA) {
        this.dummyA = dummyA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof E_Dummy)) return false;
        E_Dummy other = (E_Dummy) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.dummyA==null && other.getDummyA()==null) || 
             (this.dummyA!=null &&
              this.dummyA.equals(other.getDummyA())));
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
        if (getDummyA() != null) {
            _hashCode += getDummyA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(E_Dummy.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Dummy"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dummyA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "DummyA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_eType"));
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
