/**
 * E_DpRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * A Fb-Item references a used Dp here. 'E_DpRef' may be extended
 * to a special '[Fb]_DpRef' xsd:type.
 * 				ATTENTION: use the 'Dp_eType' enumeration for the 'dpType'
 */
@SuppressWarnings({"rawtypes","unused"})
public class E_DpRef  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTname;

    private java.lang.String UCPTformatDescription;

    private java.lang.String dpType;  // attribute

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator discrim;  // attribute

    public E_DpRef() {
    }

    public E_DpRef(
           java.lang.String UCPTname,
           java.lang.String UCPTformatDescription,
           java.lang.String dpType,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator discrim) {
           this.UCPTname = UCPTname;
           this.UCPTformatDescription = UCPTformatDescription;
           this.dpType = dpType;
           this.discrim = discrim;
    }


    /**
     * Gets the UCPTname value for this E_DpRef.
     * 
     * @return UCPTname
     */
    public java.lang.String getUCPTname() {
        return UCPTname;
    }


    /**
     * Sets the UCPTname value for this E_DpRef.
     * 
     * @param UCPTname
     */
    public void setUCPTname(java.lang.String UCPTname) {
        this.UCPTname = UCPTname;
    }


    /**
     * Gets the UCPTformatDescription value for this E_DpRef.
     * 
     * @return UCPTformatDescription
     */
    public java.lang.String getUCPTformatDescription() {
        return UCPTformatDescription;
    }


    /**
     * Sets the UCPTformatDescription value for this E_DpRef.
     * 
     * @param UCPTformatDescription
     */
    public void setUCPTformatDescription(java.lang.String UCPTformatDescription) {
        this.UCPTformatDescription = UCPTformatDescription;
    }


    /**
     * Gets the dpType value for this E_DpRef.
     * 
     * @return dpType
     */
    public java.lang.String getDpType() {
        return dpType;
    }


    /**
     * Sets the dpType value for this E_DpRef.
     * 
     * @param dpType
     */
    public void setDpType(java.lang.String dpType) {
        this.dpType = dpType;
    }


    /**
     * Gets the discrim value for this E_DpRef.
     * 
     * @return discrim
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator getDiscrim() {
        return discrim;
    }


    /**
     * Sets the discrim value for this E_DpRef.
     * 
     * @param discrim
     */
    public void setDiscrim(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator discrim) {
        this.discrim = discrim;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof E_DpRef)) return false;
        E_DpRef other = (E_DpRef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTname==null && other.getUCPTname()==null) || 
             (this.UCPTname!=null &&
              this.UCPTname.equals(other.getUCPTname()))) &&
            ((this.UCPTformatDescription==null && other.getUCPTformatDescription()==null) || 
             (this.UCPTformatDescription!=null &&
              this.UCPTformatDescription.equals(other.getUCPTformatDescription()))) &&
            ((this.dpType==null && other.getDpType()==null) || 
             (this.dpType!=null &&
              this.dpType.equals(other.getDpType()))) &&
            ((this.discrim==null && other.getDiscrim()==null) || 
             (this.discrim!=null &&
              this.discrim.equals(other.getDiscrim())));
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
        if (getUCPTname() != null) {
            _hashCode += getUCPTname().hashCode();
        }
        if (getUCPTformatDescription() != null) {
            _hashCode += getUCPTformatDescription().hashCode();
        }
        if (getDpType() != null) {
            _hashCode += getDpType().hashCode();
        }
        if (getDiscrim() != null) {
            _hashCode += getDiscrim().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(E_DpRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_DpRef"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("dpType");
        attrField.setXmlName(new javax.xml.namespace.QName("", "dpType"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("discrim");
        attrField.setXmlName(new javax.xml.namespace.QName("", "discrim"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "DpRef_eDiscriminator"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTformatDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTformatDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
