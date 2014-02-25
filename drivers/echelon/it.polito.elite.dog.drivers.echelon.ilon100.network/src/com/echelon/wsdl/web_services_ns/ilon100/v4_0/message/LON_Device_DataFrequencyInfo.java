/**
 * LON_Device_DataFrequencyInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class LON_Device_DataFrequencyInfo  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int UCPTfrequency;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTsignalMeasurementType;

    private int UCPTsignalStrength;

    private int UCPTsignalMargin;

    public LON_Device_DataFrequencyInfo() {
    }

    public LON_Device_DataFrequencyInfo(
           int UCPTfrequency,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTsignalMeasurementType,
           int UCPTsignalStrength,
           int UCPTsignalMargin) {
           this.UCPTfrequency = UCPTfrequency;
           this.UCPTsignalMeasurementType = UCPTsignalMeasurementType;
           this.UCPTsignalStrength = UCPTsignalStrength;
           this.UCPTsignalMargin = UCPTsignalMargin;
    }


    /**
     * Gets the UCPTfrequency value for this LON_Device_DataFrequencyInfo.
     * 
     * @return UCPTfrequency
     */
    public int getUCPTfrequency() {
        return UCPTfrequency;
    }


    /**
     * Sets the UCPTfrequency value for this LON_Device_DataFrequencyInfo.
     * 
     * @param UCPTfrequency
     */
    public void setUCPTfrequency(int UCPTfrequency) {
        this.UCPTfrequency = UCPTfrequency;
    }


    /**
     * Gets the UCPTsignalMeasurementType value for this LON_Device_DataFrequencyInfo.
     * 
     * @return UCPTsignalMeasurementType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTsignalMeasurementType() {
        return UCPTsignalMeasurementType;
    }


    /**
     * Sets the UCPTsignalMeasurementType value for this LON_Device_DataFrequencyInfo.
     * 
     * @param UCPTsignalMeasurementType
     */
    public void setUCPTsignalMeasurementType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTsignalMeasurementType) {
        this.UCPTsignalMeasurementType = UCPTsignalMeasurementType;
    }


    /**
     * Gets the UCPTsignalStrength value for this LON_Device_DataFrequencyInfo.
     * 
     * @return UCPTsignalStrength
     */
    public int getUCPTsignalStrength() {
        return UCPTsignalStrength;
    }


    /**
     * Sets the UCPTsignalStrength value for this LON_Device_DataFrequencyInfo.
     * 
     * @param UCPTsignalStrength
     */
    public void setUCPTsignalStrength(int UCPTsignalStrength) {
        this.UCPTsignalStrength = UCPTsignalStrength;
    }


    /**
     * Gets the UCPTsignalMargin value for this LON_Device_DataFrequencyInfo.
     * 
     * @return UCPTsignalMargin
     */
    public int getUCPTsignalMargin() {
        return UCPTsignalMargin;
    }


    /**
     * Sets the UCPTsignalMargin value for this LON_Device_DataFrequencyInfo.
     * 
     * @param UCPTsignalMargin
     */
    public void setUCPTsignalMargin(int UCPTsignalMargin) {
        this.UCPTsignalMargin = UCPTsignalMargin;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Device_DataFrequencyInfo)) return false;
        LON_Device_DataFrequencyInfo other = (LON_Device_DataFrequencyInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.UCPTfrequency == other.getUCPTfrequency() &&
            ((this.UCPTsignalMeasurementType==null && other.getUCPTsignalMeasurementType()==null) || 
             (this.UCPTsignalMeasurementType!=null &&
              this.UCPTsignalMeasurementType.equals(other.getUCPTsignalMeasurementType()))) &&
            this.UCPTsignalStrength == other.getUCPTsignalStrength() &&
            this.UCPTsignalMargin == other.getUCPTsignalMargin();
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
        _hashCode += getUCPTfrequency();
        if (getUCPTsignalMeasurementType() != null) {
            _hashCode += getUCPTsignalMeasurementType().hashCode();
        }
        _hashCode += getUCPTsignalStrength();
        _hashCode += getUCPTsignalMargin();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Device_DataFrequencyInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_DataFrequencyInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfrequency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfrequency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsignalMeasurementType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsignalMeasurementType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsignalStrength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsignalStrength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsignalMargin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsignalMargin"));
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
