/**
 * UFPTdataLogger_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized configuration type of the 'UFPTdataLogger'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTdataLogger_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="UFPTdataLogger_Cfg"][starts-with(UCPTname,'Net/LON/BAS/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTdataLogger_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogType;

    private float UCPTlogSize;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogFormat;

    private float UCPTlogLevelAlarm;

    private java.lang.String UCPTlogFileName;

    public UFPTdataLogger_Cfg() {
    }

    public UFPTdataLogger_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef[] dataPoint,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogType,
           float UCPTlogSize,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogFormat,
           float UCPTlogLevelAlarm,
           java.lang.String UCPTlogFileName) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus,
            UCPTlastUpdate,
            UCPTdescription,
            UCPTuri,
            dataPoint);
        this.UCPTlogType = UCPTlogType;
        this.UCPTlogSize = UCPTlogSize;
        this.UCPTlogFormat = UCPTlogFormat;
        this.UCPTlogLevelAlarm = UCPTlogLevelAlarm;
        this.UCPTlogFileName = UCPTlogFileName;
    }


    /**
     * Gets the UCPTlogType value for this UFPTdataLogger_Cfg.
     * 
     * @return UCPTlogType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTlogType() {
        return UCPTlogType;
    }


    /**
     * Sets the UCPTlogType value for this UFPTdataLogger_Cfg.
     * 
     * @param UCPTlogType
     */
    public void setUCPTlogType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogType) {
        this.UCPTlogType = UCPTlogType;
    }


    /**
     * Gets the UCPTlogSize value for this UFPTdataLogger_Cfg.
     * 
     * @return UCPTlogSize
     */
    public float getUCPTlogSize() {
        return UCPTlogSize;
    }


    /**
     * Sets the UCPTlogSize value for this UFPTdataLogger_Cfg.
     * 
     * @param UCPTlogSize
     */
    public void setUCPTlogSize(float UCPTlogSize) {
        this.UCPTlogSize = UCPTlogSize;
    }


    /**
     * Gets the UCPTlogFormat value for this UFPTdataLogger_Cfg.
     * 
     * @return UCPTlogFormat
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTlogFormat() {
        return UCPTlogFormat;
    }


    /**
     * Sets the UCPTlogFormat value for this UFPTdataLogger_Cfg.
     * 
     * @param UCPTlogFormat
     */
    public void setUCPTlogFormat(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogFormat) {
        this.UCPTlogFormat = UCPTlogFormat;
    }


    /**
     * Gets the UCPTlogLevelAlarm value for this UFPTdataLogger_Cfg.
     * 
     * @return UCPTlogLevelAlarm
     */
    public float getUCPTlogLevelAlarm() {
        return UCPTlogLevelAlarm;
    }


    /**
     * Sets the UCPTlogLevelAlarm value for this UFPTdataLogger_Cfg.
     * 
     * @param UCPTlogLevelAlarm
     */
    public void setUCPTlogLevelAlarm(float UCPTlogLevelAlarm) {
        this.UCPTlogLevelAlarm = UCPTlogLevelAlarm;
    }


    /**
     * Gets the UCPTlogFileName value for this UFPTdataLogger_Cfg.
     * 
     * @return UCPTlogFileName
     */
    public java.lang.String getUCPTlogFileName() {
        return UCPTlogFileName;
    }


    /**
     * Sets the UCPTlogFileName value for this UFPTdataLogger_Cfg.
     * 
     * @param UCPTlogFileName
     */
    public void setUCPTlogFileName(java.lang.String UCPTlogFileName) {
        this.UCPTlogFileName = UCPTlogFileName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTdataLogger_Cfg)) return false;
        UFPTdataLogger_Cfg other = (UFPTdataLogger_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTlogType==null && other.getUCPTlogType()==null) || 
             (this.UCPTlogType!=null &&
              this.UCPTlogType.equals(other.getUCPTlogType()))) &&
            this.UCPTlogSize == other.getUCPTlogSize() &&
            ((this.UCPTlogFormat==null && other.getUCPTlogFormat()==null) || 
             (this.UCPTlogFormat!=null &&
              this.UCPTlogFormat.equals(other.getUCPTlogFormat()))) &&
            this.UCPTlogLevelAlarm == other.getUCPTlogLevelAlarm() &&
            ((this.UCPTlogFileName==null && other.getUCPTlogFileName()==null) || 
             (this.UCPTlogFileName!=null &&
              this.UCPTlogFileName.equals(other.getUCPTlogFileName())));
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
        if (getUCPTlogType() != null) {
            _hashCode += getUCPTlogType().hashCode();
        }
        _hashCode += new Float(getUCPTlogSize()).hashCode();
        if (getUCPTlogFormat() != null) {
            _hashCode += getUCPTlogFormat().hashCode();
        }
        _hashCode += new Float(getUCPTlogLevelAlarm()).hashCode();
        if (getUCPTlogFileName() != null) {
            _hashCode += getUCPTlogFileName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTdataLogger_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdataLogger_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlogType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlogType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlogSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlogSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlogFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlogFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlogLevelAlarm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlogLevelAlarm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlogFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlogFileName"));
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
