/**
 * UFPTdataLogger_DpRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTdataLogger_DpRef  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double UCPTlogMinDeltaTime;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogMinDeltaValue;

    private double UCPTpollRate;

    public UFPTdataLogger_DpRef() {
    }

    public UFPTdataLogger_DpRef(
           java.lang.String dpType,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator discrim,
           java.lang.String UCPTname,
           java.lang.String UCPTformatDescription,
           double UCPTlogMinDeltaTime,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogMinDeltaValue,
           double UCPTpollRate) {
    	super(
        		UCPTname,
                UCPTformatDescription,
                dpType,
                discrim
            );
        this.UCPTlogMinDeltaTime = UCPTlogMinDeltaTime;
        this.UCPTlogMinDeltaValue = UCPTlogMinDeltaValue;
        this.UCPTpollRate = UCPTpollRate;
    }


    /**
     * Gets the UCPTlogMinDeltaTime value for this UFPTdataLogger_DpRef.
     * 
     * @return UCPTlogMinDeltaTime
     */
    public double getUCPTlogMinDeltaTime() {
        return UCPTlogMinDeltaTime;
    }


    /**
     * Sets the UCPTlogMinDeltaTime value for this UFPTdataLogger_DpRef.
     * 
     * @param UCPTlogMinDeltaTime
     */
    public void setUCPTlogMinDeltaTime(double UCPTlogMinDeltaTime) {
        this.UCPTlogMinDeltaTime = UCPTlogMinDeltaTime;
    }


    /**
     * Gets the UCPTlogMinDeltaValue value for this UFPTdataLogger_DpRef.
     * 
     * @return UCPTlogMinDeltaValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTlogMinDeltaValue() {
        return UCPTlogMinDeltaValue;
    }


    /**
     * Sets the UCPTlogMinDeltaValue value for this UFPTdataLogger_DpRef.
     * 
     * @param UCPTlogMinDeltaValue
     */
    public void setUCPTlogMinDeltaValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogMinDeltaValue) {
        this.UCPTlogMinDeltaValue = UCPTlogMinDeltaValue;
    }


    /**
     * Gets the UCPTpollRate value for this UFPTdataLogger_DpRef.
     * 
     * @return UCPTpollRate
     */
    public double getUCPTpollRate() {
        return UCPTpollRate;
    }


    /**
     * Sets the UCPTpollRate value for this UFPTdataLogger_DpRef.
     * 
     * @param UCPTpollRate
     */
    public void setUCPTpollRate(double UCPTpollRate) {
        this.UCPTpollRate = UCPTpollRate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTdataLogger_DpRef)) return false;
        UFPTdataLogger_DpRef other = (UFPTdataLogger_DpRef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.UCPTlogMinDeltaTime == other.getUCPTlogMinDeltaTime() &&
            ((this.UCPTlogMinDeltaValue==null && other.getUCPTlogMinDeltaValue()==null) || 
             (this.UCPTlogMinDeltaValue!=null &&
              this.UCPTlogMinDeltaValue.equals(other.getUCPTlogMinDeltaValue()))) &&
            this.UCPTpollRate == other.getUCPTpollRate();
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
        _hashCode += new Double(getUCPTlogMinDeltaTime()).hashCode();
        if (getUCPTlogMinDeltaValue() != null) {
            _hashCode += getUCPTlogMinDeltaValue().hashCode();
        }
        _hashCode += new Double(getUCPTpollRate()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTdataLogger_DpRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdataLogger_DpRef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlogMinDeltaTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlogMinDeltaTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlogMinDeltaValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlogMinDeltaValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpollRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpollRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
