/**
 * MOD_Channel_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Specialized channel configuration type.
 * 					Example:  xSelect="//Item[@xsi:type="MOD_Channel_Cfg"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class MOD_Channel_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTchannelType;

    private java.lang.Integer UCPTport;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_InterfaceOptions interfaceOptions;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTserialMode;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTretryCount;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminOfflineTime;

    public MOD_Channel_Cfg() {
    }

    public MOD_Channel_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTchannelType,
           java.lang.Integer UCPTport,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_InterfaceOptions interfaceOptions,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTserialMode,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTretryCount,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminOfflineTime) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus,
            UCPTlastUpdate,
            UCPTdescription,
            UCPTuri);
        this.UCPThandle = UCPThandle;
        this.UCPTchannelType = UCPTchannelType;
        this.UCPTport = UCPTport;
        this.interfaceOptions = interfaceOptions;
        this.UCPTserialMode = UCPTserialMode;
        this.UCPTretryCount = UCPTretryCount;
        this.UCPTminOfflineTime = UCPTminOfflineTime;
    }


    /**
     * Gets the UCPThandle value for this MOD_Channel_Cfg.
     * 
     * @return UCPThandle
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPThandle() {
        return UCPThandle;
    }


    /**
     * Sets the UCPThandle value for this MOD_Channel_Cfg.
     * 
     * @param UCPThandle
     */
    public void setUCPThandle(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle) {
        this.UCPThandle = UCPThandle;
    }


    /**
     * Gets the UCPTchannelType value for this MOD_Channel_Cfg.
     * 
     * @return UCPTchannelType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTchannelType() {
        return UCPTchannelType;
    }


    /**
     * Sets the UCPTchannelType value for this MOD_Channel_Cfg.
     * 
     * @param UCPTchannelType
     */
    public void setUCPTchannelType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTchannelType) {
        this.UCPTchannelType = UCPTchannelType;
    }


    /**
     * Gets the UCPTport value for this MOD_Channel_Cfg.
     * 
     * @return UCPTport
     */
    public java.lang.Integer getUCPTport() {
        return UCPTport;
    }


    /**
     * Sets the UCPTport value for this MOD_Channel_Cfg.
     * 
     * @param UCPTport
     */
    public void setUCPTport(java.lang.Integer UCPTport) {
        this.UCPTport = UCPTport;
    }


    /**
     * Gets the interfaceOptions value for this MOD_Channel_Cfg.
     * 
     * @return interfaceOptions
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_InterfaceOptions getInterfaceOptions() {
        return interfaceOptions;
    }


    /**
     * Sets the interfaceOptions value for this MOD_Channel_Cfg.
     * 
     * @param interfaceOptions
     */
    public void setInterfaceOptions(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_InterfaceOptions interfaceOptions) {
        this.interfaceOptions = interfaceOptions;
    }


    /**
     * Gets the UCPTserialMode value for this MOD_Channel_Cfg.
     * 
     * @return UCPTserialMode
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTserialMode() {
        return UCPTserialMode;
    }


    /**
     * Sets the UCPTserialMode value for this MOD_Channel_Cfg.
     * 
     * @param UCPTserialMode
     */
    public void setUCPTserialMode(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTserialMode) {
        this.UCPTserialMode = UCPTserialMode;
    }


    /**
     * Gets the UCPTretryCount value for this MOD_Channel_Cfg.
     * 
     * @return UCPTretryCount
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTretryCount() {
        return UCPTretryCount;
    }


    /**
     * Sets the UCPTretryCount value for this MOD_Channel_Cfg.
     * 
     * @param UCPTretryCount
     */
    public void setUCPTretryCount(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTretryCount) {
        this.UCPTretryCount = UCPTretryCount;
    }


    /**
     * Gets the UCPTminOfflineTime value for this MOD_Channel_Cfg.
     * 
     * @return UCPTminOfflineTime
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTminOfflineTime() {
        return UCPTminOfflineTime;
    }


    /**
     * Sets the UCPTminOfflineTime value for this MOD_Channel_Cfg.
     * 
     * @param UCPTminOfflineTime
     */
    public void setUCPTminOfflineTime(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminOfflineTime) {
        this.UCPTminOfflineTime = UCPTminOfflineTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MOD_Channel_Cfg)) return false;
        MOD_Channel_Cfg other = (MOD_Channel_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPThandle==null && other.getUCPThandle()==null) || 
             (this.UCPThandle!=null &&
              this.UCPThandle.equals(other.getUCPThandle()))) &&
            ((this.UCPTchannelType==null && other.getUCPTchannelType()==null) || 
             (this.UCPTchannelType!=null &&
              this.UCPTchannelType.equals(other.getUCPTchannelType()))) &&
            ((this.UCPTport==null && other.getUCPTport()==null) || 
             (this.UCPTport!=null &&
              this.UCPTport.equals(other.getUCPTport()))) &&
            ((this.interfaceOptions==null && other.getInterfaceOptions()==null) || 
             (this.interfaceOptions!=null &&
              this.interfaceOptions.equals(other.getInterfaceOptions()))) &&
            ((this.UCPTserialMode==null && other.getUCPTserialMode()==null) || 
             (this.UCPTserialMode!=null &&
              this.UCPTserialMode.equals(other.getUCPTserialMode()))) &&
            ((this.UCPTretryCount==null && other.getUCPTretryCount()==null) || 
             (this.UCPTretryCount!=null &&
              this.UCPTretryCount.equals(other.getUCPTretryCount()))) &&
            ((this.UCPTminOfflineTime==null && other.getUCPTminOfflineTime()==null) || 
             (this.UCPTminOfflineTime!=null &&
              this.UCPTminOfflineTime.equals(other.getUCPTminOfflineTime())));
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
        if (getUCPThandle() != null) {
            _hashCode += getUCPThandle().hashCode();
        }
        if (getUCPTchannelType() != null) {
            _hashCode += getUCPTchannelType().hashCode();
        }
        if (getUCPTport() != null) {
            _hashCode += getUCPTport().hashCode();
        }
        if (getInterfaceOptions() != null) {
            _hashCode += getInterfaceOptions().hashCode();
        }
        if (getUCPTserialMode() != null) {
            _hashCode += getUCPTserialMode().hashCode();
        }
        if (getUCPTretryCount() != null) {
            _hashCode += getUCPTretryCount().hashCode();
        }
        if (getUCPTminOfflineTime() != null) {
            _hashCode += getUCPTminOfflineTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MOD_Channel_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MOD_Channel_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThandle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThandle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_UniqueKey"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTchannelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTchannelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTport");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interfaceOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "InterfaceOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_InterfaceOptions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTserialMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTserialMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTretryCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTretryCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTminOfflineTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTminOfflineTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
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
