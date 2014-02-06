/**
 * UFPTalarmNotifier_Data.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized data type of the 'UFPTalarmNotifier'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTalarmNotifier_Data"][UCPTpointName="Net/LON/BAS/Alarm
 * Notifier 1/nviInput"]"
 * 					Example:  xSelect="//Item[@xsi:type="UFPTalarmNotifier_Data"][UCPTalarmTime>="2007-01-15T15:30:21Z"][position()<=60][UCPTalarmLog="SUMMARY"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTalarmNotifier_Data  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTalarmNotifierName;

    private java.util.Calendar UCPTalarmTime;

    private java.lang.String UCPTuserName;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTstatus;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_DataReadData readData;

    public UFPTalarmNotifier_Data() {
    }

    public UFPTalarmNotifier_Data(
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
           java.lang.String UCPTalarmNotifierName,
           java.util.Calendar UCPTalarmTime,
           java.lang.String UCPTuserName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTstatus,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_DataReadData readData) {
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
        this.UCPTalarmNotifierName = UCPTalarmNotifierName;
        this.UCPTalarmTime = UCPTalarmTime;
        this.UCPTuserName = UCPTuserName;
        this.UCPTstatus = UCPTstatus;
        this.readData = readData;
    }


    /**
     * Gets the UCPTalarmNotifierName value for this UFPTalarmNotifier_Data.
     * 
     * @return UCPTalarmNotifierName
     */
    public java.lang.String getUCPTalarmNotifierName() {
        return UCPTalarmNotifierName;
    }


    /**
     * Sets the UCPTalarmNotifierName value for this UFPTalarmNotifier_Data.
     * 
     * @param UCPTalarmNotifierName
     */
    public void setUCPTalarmNotifierName(java.lang.String UCPTalarmNotifierName) {
        this.UCPTalarmNotifierName = UCPTalarmNotifierName;
    }


    /**
     * Gets the UCPTalarmTime value for this UFPTalarmNotifier_Data.
     * 
     * @return UCPTalarmTime
     */
    public java.util.Calendar getUCPTalarmTime() {
        return UCPTalarmTime;
    }


    /**
     * Sets the UCPTalarmTime value for this UFPTalarmNotifier_Data.
     * 
     * @param UCPTalarmTime
     */
    public void setUCPTalarmTime(java.util.Calendar UCPTalarmTime) {
        this.UCPTalarmTime = UCPTalarmTime;
    }


    /**
     * Gets the UCPTuserName value for this UFPTalarmNotifier_Data.
     * 
     * @return UCPTuserName
     */
    public java.lang.String getUCPTuserName() {
        return UCPTuserName;
    }


    /**
     * Sets the UCPTuserName value for this UFPTalarmNotifier_Data.
     * 
     * @param UCPTuserName
     */
    public void setUCPTuserName(java.lang.String UCPTuserName) {
        this.UCPTuserName = UCPTuserName;
    }


    /**
     * Gets the UCPTstatus value for this UFPTalarmNotifier_Data.
     * 
     * @return UCPTstatus
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTstatus() {
        return UCPTstatus;
    }


    /**
     * Sets the UCPTstatus value for this UFPTalarmNotifier_Data.
     * 
     * @param UCPTstatus
     */
    public void setUCPTstatus(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTstatus) {
        this.UCPTstatus = UCPTstatus;
    }


    /**
     * Gets the readData value for this UFPTalarmNotifier_Data.
     * 
     * @return readData
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_DataReadData getReadData() {
        return readData;
    }


    /**
     * Sets the readData value for this UFPTalarmNotifier_Data.
     * 
     * @param readData
     */
    public void setReadData(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_DataReadData readData) {
        this.readData = readData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTalarmNotifier_Data)) return false;
        UFPTalarmNotifier_Data other = (UFPTalarmNotifier_Data) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTalarmNotifierName==null && other.getUCPTalarmNotifierName()==null) || 
             (this.UCPTalarmNotifierName!=null &&
              this.UCPTalarmNotifierName.equals(other.getUCPTalarmNotifierName()))) &&
            ((this.UCPTalarmTime==null && other.getUCPTalarmTime()==null) || 
             (this.UCPTalarmTime!=null &&
              this.UCPTalarmTime.equals(other.getUCPTalarmTime()))) &&
            ((this.UCPTuserName==null && other.getUCPTuserName()==null) || 
             (this.UCPTuserName!=null &&
              this.UCPTuserName.equals(other.getUCPTuserName()))) &&
            ((this.UCPTstatus==null && other.getUCPTstatus()==null) || 
             (this.UCPTstatus!=null &&
              this.UCPTstatus.equals(other.getUCPTstatus()))) &&
            ((this.readData==null && other.getReadData()==null) || 
             (this.readData!=null &&
              this.readData.equals(other.getReadData())));
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
        if (getUCPTalarmNotifierName() != null) {
            _hashCode += getUCPTalarmNotifierName().hashCode();
        }
        if (getUCPTalarmTime() != null) {
            _hashCode += getUCPTalarmTime().hashCode();
        }
        if (getUCPTuserName() != null) {
            _hashCode += getUCPTuserName().hashCode();
        }
        if (getUCPTstatus() != null) {
            _hashCode += getUCPTstatus().hashCode();
        }
        if (getReadData() != null) {
            _hashCode += getReadData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTalarmNotifier_Data.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_Data"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmNotifierName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmNotifierName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTuserName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTuserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("readData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "ReadData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Data>ReadData"));
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
