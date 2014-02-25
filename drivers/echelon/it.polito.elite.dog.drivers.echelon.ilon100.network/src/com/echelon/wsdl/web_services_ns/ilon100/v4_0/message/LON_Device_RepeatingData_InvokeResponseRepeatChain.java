/**
 * LON_Device_RepeatingData_InvokeResponseRepeatChain.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class LON_Device_RepeatingData_InvokeResponseRepeatChain  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTname;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo frequencyInfo;

    public LON_Device_RepeatingData_InvokeResponseRepeatChain() {
    }

    public LON_Device_RepeatingData_InvokeResponseRepeatChain(
           java.lang.String UCPTname,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo frequencyInfo) {
           this.UCPTname = UCPTname;
           this.frequencyInfo = frequencyInfo;
    }


    /**
     * Gets the UCPTname value for this LON_Device_RepeatingData_InvokeResponseRepeatChain.
     * 
     * @return UCPTname
     */
    public java.lang.String getUCPTname() {
        return UCPTname;
    }


    /**
     * Sets the UCPTname value for this LON_Device_RepeatingData_InvokeResponseRepeatChain.
     * 
     * @param UCPTname
     */
    public void setUCPTname(java.lang.String UCPTname) {
        this.UCPTname = UCPTname;
    }


    /**
     * Gets the frequencyInfo value for this LON_Device_RepeatingData_InvokeResponseRepeatChain.
     * 
     * @return frequencyInfo
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo getFrequencyInfo() {
        return frequencyInfo;
    }


    /**
     * Sets the frequencyInfo value for this LON_Device_RepeatingData_InvokeResponseRepeatChain.
     * 
     * @param frequencyInfo
     */
    public void setFrequencyInfo(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo frequencyInfo) {
        this.frequencyInfo = frequencyInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Device_RepeatingData_InvokeResponseRepeatChain)) return false;
        LON_Device_RepeatingData_InvokeResponseRepeatChain other = (LON_Device_RepeatingData_InvokeResponseRepeatChain) obj;
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
            ((this.frequencyInfo==null && other.getFrequencyInfo()==null) || 
             (this.frequencyInfo!=null &&
              this.frequencyInfo.equals(other.getFrequencyInfo())));
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
        if (getFrequencyInfo() != null) {
            _hashCode += getFrequencyInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Device_RepeatingData_InvokeResponseRepeatChain.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_RepeatingData_InvokeResponse>RepeatChain"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frequencyInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "FrequencyInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_DataFrequencyInfo"));
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
