/**
 * UFPTtypeTranslator_Rule_CfgDataPointFormat.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTtypeTranslator_Rule_CfgDataPointFormat  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTnickName;

    private java.lang.String UCPTformatDescription;

    public UFPTtypeTranslator_Rule_CfgDataPointFormat() {
    }

    public UFPTtypeTranslator_Rule_CfgDataPointFormat(
           java.lang.String UCPTnickName,
           java.lang.String UCPTformatDescription) {
           this.UCPTnickName = UCPTnickName;
           this.UCPTformatDescription = UCPTformatDescription;
    }


    /**
     * Gets the UCPTnickName value for this UFPTtypeTranslator_Rule_CfgDataPointFormat.
     * 
     * @return UCPTnickName
     */
    public java.lang.String getUCPTnickName() {
        return UCPTnickName;
    }


    /**
     * Sets the UCPTnickName value for this UFPTtypeTranslator_Rule_CfgDataPointFormat.
     * 
     * @param UCPTnickName
     */
    public void setUCPTnickName(java.lang.String UCPTnickName) {
        this.UCPTnickName = UCPTnickName;
    }


    /**
     * Gets the UCPTformatDescription value for this UFPTtypeTranslator_Rule_CfgDataPointFormat.
     * 
     * @return UCPTformatDescription
     */
    public java.lang.String getUCPTformatDescription() {
        return UCPTformatDescription;
    }


    /**
     * Sets the UCPTformatDescription value for this UFPTtypeTranslator_Rule_CfgDataPointFormat.
     * 
     * @param UCPTformatDescription
     */
    public void setUCPTformatDescription(java.lang.String UCPTformatDescription) {
        this.UCPTformatDescription = UCPTformatDescription;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTtypeTranslator_Rule_CfgDataPointFormat)) return false;
        UFPTtypeTranslator_Rule_CfgDataPointFormat other = (UFPTtypeTranslator_Rule_CfgDataPointFormat) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTnickName==null && other.getUCPTnickName()==null) || 
             (this.UCPTnickName!=null &&
              this.UCPTnickName.equals(other.getUCPTnickName()))) &&
            ((this.UCPTformatDescription==null && other.getUCPTformatDescription()==null) || 
             (this.UCPTformatDescription!=null &&
              this.UCPTformatDescription.equals(other.getUCPTformatDescription())));
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
        if (getUCPTnickName() != null) {
            _hashCode += getUCPTnickName().hashCode();
        }
        if (getUCPTformatDescription() != null) {
            _hashCode += getUCPTformatDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTtypeTranslator_Rule_CfgDataPointFormat.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTtypeTranslator_Rule_Cfg>DataPointFormat"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTnickName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTnickName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTformatDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTformatDescription"));
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
