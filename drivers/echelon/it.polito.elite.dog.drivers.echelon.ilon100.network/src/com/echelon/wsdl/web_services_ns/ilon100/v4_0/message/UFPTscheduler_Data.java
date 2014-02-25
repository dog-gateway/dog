/**
 * UFPTscheduler_Data.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized data type of the 'UFPTscheduler'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTscheduler_Data"][UCPTname="Net/LON/BAS/Scheduler
 * 1"][UCPTeventFilter="EF_SCHEDULE"][position()<10]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTscheduler_Data  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTeventType;

    private java.lang.String UCPTeventSource;

    private java.lang.String UCPTexceptionName;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTeventInfo;

    private org.apache.axis.types.Time UCPTbaseTime;

    public UFPTscheduler_Data() {
    }

    public UFPTscheduler_Data(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTvalue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTpointStatus,
           java.lang.Integer UCPTpriority,
           java.lang.Float UCPTmaxAge,
           java.lang.Short UCPTpropagate,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTmetaDataPath,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTeventType,
           java.lang.String UCPTeventSource,
           java.lang.String UCPTexceptionName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTeventInfo,
           org.apache.axis.types.Time UCPTbaseTime) {
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
            UCPTvalue,
            UCPTpointStatus,
            UCPTpriority,
            UCPTmaxAge,
            UCPTpropagate,
            UCPTmetaDataPath);
        this.UCPTeventType = UCPTeventType;
        this.UCPTeventSource = UCPTeventSource;
        this.UCPTexceptionName = UCPTexceptionName;
        this.UCPTeventInfo = UCPTeventInfo;
        this.UCPTbaseTime = UCPTbaseTime;
    }


    /**
     * Gets the UCPTeventType value for this UFPTscheduler_Data.
     * 
     * @return UCPTeventType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTeventType() {
        return UCPTeventType;
    }


    /**
     * Sets the UCPTeventType value for this UFPTscheduler_Data.
     * 
     * @param UCPTeventType
     */
    public void setUCPTeventType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTeventType) {
        this.UCPTeventType = UCPTeventType;
    }


    /**
     * Gets the UCPTeventSource value for this UFPTscheduler_Data.
     * 
     * @return UCPTeventSource
     */
    public java.lang.String getUCPTeventSource() {
        return UCPTeventSource;
    }


    /**
     * Sets the UCPTeventSource value for this UFPTscheduler_Data.
     * 
     * @param UCPTeventSource
     */
    public void setUCPTeventSource(java.lang.String UCPTeventSource) {
        this.UCPTeventSource = UCPTeventSource;
    }


    /**
     * Gets the UCPTexceptionName value for this UFPTscheduler_Data.
     * 
     * @return UCPTexceptionName
     */
    public java.lang.String getUCPTexceptionName() {
        return UCPTexceptionName;
    }


    /**
     * Sets the UCPTexceptionName value for this UFPTscheduler_Data.
     * 
     * @param UCPTexceptionName
     */
    public void setUCPTexceptionName(java.lang.String UCPTexceptionName) {
        this.UCPTexceptionName = UCPTexceptionName;
    }


    /**
     * Gets the UCPTeventInfo value for this UFPTscheduler_Data.
     * 
     * @return UCPTeventInfo
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTeventInfo() {
        return UCPTeventInfo;
    }


    /**
     * Sets the UCPTeventInfo value for this UFPTscheduler_Data.
     * 
     * @param UCPTeventInfo
     */
    public void setUCPTeventInfo(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTeventInfo) {
        this.UCPTeventInfo = UCPTeventInfo;
    }


    /**
     * Gets the UCPTbaseTime value for this UFPTscheduler_Data.
     * 
     * @return UCPTbaseTime
     */
    public org.apache.axis.types.Time getUCPTbaseTime() {
        return UCPTbaseTime;
    }


    /**
     * Sets the UCPTbaseTime value for this UFPTscheduler_Data.
     * 
     * @param UCPTbaseTime
     */
    public void setUCPTbaseTime(org.apache.axis.types.Time UCPTbaseTime) {
        this.UCPTbaseTime = UCPTbaseTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTscheduler_Data)) return false;
        UFPTscheduler_Data other = (UFPTscheduler_Data) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTeventType==null && other.getUCPTeventType()==null) || 
             (this.UCPTeventType!=null &&
              this.UCPTeventType.equals(other.getUCPTeventType()))) &&
            ((this.UCPTeventSource==null && other.getUCPTeventSource()==null) || 
             (this.UCPTeventSource!=null &&
              this.UCPTeventSource.equals(other.getUCPTeventSource()))) &&
            ((this.UCPTexceptionName==null && other.getUCPTexceptionName()==null) || 
             (this.UCPTexceptionName!=null &&
              this.UCPTexceptionName.equals(other.getUCPTexceptionName()))) &&
            ((this.UCPTeventInfo==null && other.getUCPTeventInfo()==null) || 
             (this.UCPTeventInfo!=null &&
              this.UCPTeventInfo.equals(other.getUCPTeventInfo()))) &&
            ((this.UCPTbaseTime==null && other.getUCPTbaseTime()==null) || 
             (this.UCPTbaseTime!=null &&
              this.UCPTbaseTime.equals(other.getUCPTbaseTime())));
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
        if (getUCPTeventType() != null) {
            _hashCode += getUCPTeventType().hashCode();
        }
        if (getUCPTeventSource() != null) {
            _hashCode += getUCPTeventSource().hashCode();
        }
        if (getUCPTexceptionName() != null) {
            _hashCode += getUCPTexceptionName().hashCode();
        }
        if (getUCPTeventInfo() != null) {
            _hashCode += getUCPTeventInfo().hashCode();
        }
        if (getUCPTbaseTime() != null) {
            _hashCode += getUCPTbaseTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTscheduler_Data.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_Data"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTeventType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTeventType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTeventSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTeventSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTexceptionName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTexceptionName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTeventInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTeventInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTbaseTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTbaseTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "time"));
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
