/**
 * LON_Channel_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Specialized channel configuration type.
 * 					Example:  xSelect="//Item[@xsi:type="LON_Channel_Cfg"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Channel_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTchannelType;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtransceiverId;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtransmitTimer;

    private java.lang.Integer UCPTretryCount;

    private java.lang.Integer UCPTmaxPriority;

    private java.lang.Double UCPTdelay;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTchannelMode;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminOfflineTime;

    private java.lang.Double UCPTofflineIhbD;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdynamic;

    public LON_Channel_Cfg() {
    }

    public LON_Channel_Cfg(
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
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtransceiverId,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtransmitTimer,
           java.lang.Integer UCPTretryCount,
           java.lang.Integer UCPTmaxPriority,
           java.lang.Double UCPTdelay,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTchannelMode,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminOfflineTime,
           java.lang.Double UCPTofflineIhbD,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdynamic) {
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
        this.UCPTtransceiverId = UCPTtransceiverId;
        this.UCPTtransmitTimer = UCPTtransmitTimer;
        this.UCPTretryCount = UCPTretryCount;
        this.UCPTmaxPriority = UCPTmaxPriority;
        this.UCPTdelay = UCPTdelay;
        this.UCPTchannelMode = UCPTchannelMode;
        this.UCPTminOfflineTime = UCPTminOfflineTime;
        this.UCPTofflineIhbD = UCPTofflineIhbD;
        this.UCPTdynamic = UCPTdynamic;
    }


    /**
     * Gets the UCPThandle value for this LON_Channel_Cfg.
     * 
     * @return UCPThandle
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPThandle() {
        return UCPThandle;
    }


    /**
     * Sets the UCPThandle value for this LON_Channel_Cfg.
     * 
     * @param UCPThandle
     */
    public void setUCPThandle(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle) {
        this.UCPThandle = UCPThandle;
    }


    /**
     * Gets the UCPTchannelType value for this LON_Channel_Cfg.
     * 
     * @return UCPTchannelType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTchannelType() {
        return UCPTchannelType;
    }


    /**
     * Sets the UCPTchannelType value for this LON_Channel_Cfg.
     * 
     * @param UCPTchannelType
     */
    public void setUCPTchannelType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTchannelType) {
        this.UCPTchannelType = UCPTchannelType;
    }


    /**
     * Gets the UCPTtransceiverId value for this LON_Channel_Cfg.
     * 
     * @return UCPTtransceiverId
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTtransceiverId() {
        return UCPTtransceiverId;
    }


    /**
     * Sets the UCPTtransceiverId value for this LON_Channel_Cfg.
     * 
     * @param UCPTtransceiverId
     */
    public void setUCPTtransceiverId(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtransceiverId) {
        this.UCPTtransceiverId = UCPTtransceiverId;
    }


    /**
     * Gets the UCPTtransmitTimer value for this LON_Channel_Cfg.
     * 
     * @return UCPTtransmitTimer
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTtransmitTimer() {
        return UCPTtransmitTimer;
    }


    /**
     * Sets the UCPTtransmitTimer value for this LON_Channel_Cfg.
     * 
     * @param UCPTtransmitTimer
     */
    public void setUCPTtransmitTimer(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtransmitTimer) {
        this.UCPTtransmitTimer = UCPTtransmitTimer;
    }


    /**
     * Gets the UCPTretryCount value for this LON_Channel_Cfg.
     * 
     * @return UCPTretryCount
     */
    public java.lang.Integer getUCPTretryCount() {
        return UCPTretryCount;
    }


    /**
     * Sets the UCPTretryCount value for this LON_Channel_Cfg.
     * 
     * @param UCPTretryCount
     */
    public void setUCPTretryCount(java.lang.Integer UCPTretryCount) {
        this.UCPTretryCount = UCPTretryCount;
    }


    /**
     * Gets the UCPTmaxPriority value for this LON_Channel_Cfg.
     * 
     * @return UCPTmaxPriority
     */
    public java.lang.Integer getUCPTmaxPriority() {
        return UCPTmaxPriority;
    }


    /**
     * Sets the UCPTmaxPriority value for this LON_Channel_Cfg.
     * 
     * @param UCPTmaxPriority
     */
    public void setUCPTmaxPriority(java.lang.Integer UCPTmaxPriority) {
        this.UCPTmaxPriority = UCPTmaxPriority;
    }


    /**
     * Gets the UCPTdelay value for this LON_Channel_Cfg.
     * 
     * @return UCPTdelay
     */
    public java.lang.Double getUCPTdelay() {
        return UCPTdelay;
    }


    /**
     * Sets the UCPTdelay value for this LON_Channel_Cfg.
     * 
     * @param UCPTdelay
     */
    public void setUCPTdelay(java.lang.Double UCPTdelay) {
        this.UCPTdelay = UCPTdelay;
    }


    /**
     * Gets the UCPTchannelMode value for this LON_Channel_Cfg.
     * 
     * @return UCPTchannelMode
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTchannelMode() {
        return UCPTchannelMode;
    }


    /**
     * Sets the UCPTchannelMode value for this LON_Channel_Cfg.
     * 
     * @param UCPTchannelMode
     */
    public void setUCPTchannelMode(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTchannelMode) {
        this.UCPTchannelMode = UCPTchannelMode;
    }


    /**
     * Gets the UCPTminOfflineTime value for this LON_Channel_Cfg.
     * 
     * @return UCPTminOfflineTime
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTminOfflineTime() {
        return UCPTminOfflineTime;
    }


    /**
     * Sets the UCPTminOfflineTime value for this LON_Channel_Cfg.
     * 
     * @param UCPTminOfflineTime
     */
    public void setUCPTminOfflineTime(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminOfflineTime) {
        this.UCPTminOfflineTime = UCPTminOfflineTime;
    }


    /**
     * Gets the UCPTofflineIhbD value for this LON_Channel_Cfg.
     * 
     * @return UCPTofflineIhbD
     */
    public java.lang.Double getUCPTofflineIhbD() {
        return UCPTofflineIhbD;
    }


    /**
     * Sets the UCPTofflineIhbD value for this LON_Channel_Cfg.
     * 
     * @param UCPTofflineIhbD
     */
    public void setUCPTofflineIhbD(java.lang.Double UCPTofflineIhbD) {
        this.UCPTofflineIhbD = UCPTofflineIhbD;
    }


    /**
     * Gets the UCPTdynamic value for this LON_Channel_Cfg.
     * 
     * @return UCPTdynamic
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTdynamic() {
        return UCPTdynamic;
    }


    /**
     * Sets the UCPTdynamic value for this LON_Channel_Cfg.
     * 
     * @param UCPTdynamic
     */
    public void setUCPTdynamic(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdynamic) {
        this.UCPTdynamic = UCPTdynamic;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Channel_Cfg)) return false;
        LON_Channel_Cfg other = (LON_Channel_Cfg) obj;
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
            ((this.UCPTtransceiverId==null && other.getUCPTtransceiverId()==null) || 
             (this.UCPTtransceiverId!=null &&
              this.UCPTtransceiverId.equals(other.getUCPTtransceiverId()))) &&
            ((this.UCPTtransmitTimer==null && other.getUCPTtransmitTimer()==null) || 
             (this.UCPTtransmitTimer!=null &&
              this.UCPTtransmitTimer.equals(other.getUCPTtransmitTimer()))) &&
            ((this.UCPTretryCount==null && other.getUCPTretryCount()==null) || 
             (this.UCPTretryCount!=null &&
              this.UCPTretryCount.equals(other.getUCPTretryCount()))) &&
            ((this.UCPTmaxPriority==null && other.getUCPTmaxPriority()==null) || 
             (this.UCPTmaxPriority!=null &&
              this.UCPTmaxPriority.equals(other.getUCPTmaxPriority()))) &&
            ((this.UCPTdelay==null && other.getUCPTdelay()==null) || 
             (this.UCPTdelay!=null &&
              this.UCPTdelay.equals(other.getUCPTdelay()))) &&
            ((this.UCPTchannelMode==null && other.getUCPTchannelMode()==null) || 
             (this.UCPTchannelMode!=null &&
              this.UCPTchannelMode.equals(other.getUCPTchannelMode()))) &&
            ((this.UCPTminOfflineTime==null && other.getUCPTminOfflineTime()==null) || 
             (this.UCPTminOfflineTime!=null &&
              this.UCPTminOfflineTime.equals(other.getUCPTminOfflineTime()))) &&
            ((this.UCPTofflineIhbD==null && other.getUCPTofflineIhbD()==null) || 
             (this.UCPTofflineIhbD!=null &&
              this.UCPTofflineIhbD.equals(other.getUCPTofflineIhbD()))) &&
            ((this.UCPTdynamic==null && other.getUCPTdynamic()==null) || 
             (this.UCPTdynamic!=null &&
              this.UCPTdynamic.equals(other.getUCPTdynamic())));
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
        if (getUCPTtransceiverId() != null) {
            _hashCode += getUCPTtransceiverId().hashCode();
        }
        if (getUCPTtransmitTimer() != null) {
            _hashCode += getUCPTtransmitTimer().hashCode();
        }
        if (getUCPTretryCount() != null) {
            _hashCode += getUCPTretryCount().hashCode();
        }
        if (getUCPTmaxPriority() != null) {
            _hashCode += getUCPTmaxPriority().hashCode();
        }
        if (getUCPTdelay() != null) {
            _hashCode += getUCPTdelay().hashCode();
        }
        if (getUCPTchannelMode() != null) {
            _hashCode += getUCPTchannelMode().hashCode();
        }
        if (getUCPTminOfflineTime() != null) {
            _hashCode += getUCPTminOfflineTime().hashCode();
        }
        if (getUCPTofflineIhbD() != null) {
            _hashCode += getUCPTofflineIhbD().hashCode();
        }
        if (getUCPTdynamic() != null) {
            _hashCode += getUCPTdynamic().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Channel_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Channel_Cfg"));
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
        elemField.setFieldName("UCPTtransceiverId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtransceiverId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTtransmitTimer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtransmitTimer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTretryCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTretryCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxPriority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxPriority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdelay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdelay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTchannelMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTchannelMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTminOfflineTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTminOfflineTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTofflineIhbD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTofflineIhbD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdynamic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdynamic"));
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
