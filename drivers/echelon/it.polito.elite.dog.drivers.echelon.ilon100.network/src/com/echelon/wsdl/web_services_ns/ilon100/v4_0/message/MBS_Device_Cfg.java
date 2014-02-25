/**
 * MBS_Device_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Specialized device configuration type.
 * 					Example:  xSelect="//Item[@xsi:type="MBS_Device_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="MBS_Device_Cfg"][starts-with(UCPTname,'Net/MBS/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class MBS_Device_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle;

    private byte[] UCPTprogramId;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTspeed;

    private java.lang.Short UCPTmbusFcbEnable;

    private java.lang.Short UCPTmbusFcvEnable;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmbusMode;

    private java.lang.String UCPTmbusMedId;

    private java.lang.String UCPTmbusManId;

    private java.lang.String UCPTmbusGenId;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MBS_Device_CfgAddress address;

    public MBS_Device_Cfg() {
    }

    public MBS_Device_Cfg(
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
           byte[] UCPTprogramId,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTspeed,
           java.lang.Short UCPTmbusFcbEnable,
           java.lang.Short UCPTmbusFcvEnable,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmbusMode,
           java.lang.String UCPTmbusMedId,
           java.lang.String UCPTmbusManId,
           java.lang.String UCPTmbusGenId,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MBS_Device_CfgAddress address) {
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
        this.UCPTprogramId = UCPTprogramId;
        this.UCPTspeed = UCPTspeed;
        this.UCPTmbusFcbEnable = UCPTmbusFcbEnable;
        this.UCPTmbusFcvEnable = UCPTmbusFcvEnable;
        this.UCPTmbusMode = UCPTmbusMode;
        this.UCPTmbusMedId = UCPTmbusMedId;
        this.UCPTmbusManId = UCPTmbusManId;
        this.UCPTmbusGenId = UCPTmbusGenId;
        this.address = address;
    }


    /**
     * Gets the UCPThandle value for this MBS_Device_Cfg.
     * 
     * @return UCPThandle
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPThandle() {
        return UCPThandle;
    }


    /**
     * Sets the UCPThandle value for this MBS_Device_Cfg.
     * 
     * @param UCPThandle
     */
    public void setUCPThandle(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle) {
        this.UCPThandle = UCPThandle;
    }


    /**
     * Gets the UCPTprogramId value for this MBS_Device_Cfg.
     * 
     * @return UCPTprogramId
     */
    public byte[] getUCPTprogramId() {
        return UCPTprogramId;
    }


    /**
     * Sets the UCPTprogramId value for this MBS_Device_Cfg.
     * 
     * @param UCPTprogramId
     */
    public void setUCPTprogramId(byte[] UCPTprogramId) {
        this.UCPTprogramId = UCPTprogramId;
    }


    /**
     * Gets the UCPTspeed value for this MBS_Device_Cfg.
     * 
     * @return UCPTspeed
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTspeed() {
        return UCPTspeed;
    }


    /**
     * Sets the UCPTspeed value for this MBS_Device_Cfg.
     * 
     * @param UCPTspeed
     */
    public void setUCPTspeed(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTspeed) {
        this.UCPTspeed = UCPTspeed;
    }


    /**
     * Gets the UCPTmbusFcbEnable value for this MBS_Device_Cfg.
     * 
     * @return UCPTmbusFcbEnable
     */
    public java.lang.Short getUCPTmbusFcbEnable() {
        return UCPTmbusFcbEnable;
    }


    /**
     * Sets the UCPTmbusFcbEnable value for this MBS_Device_Cfg.
     * 
     * @param UCPTmbusFcbEnable
     */
    public void setUCPTmbusFcbEnable(java.lang.Short UCPTmbusFcbEnable) {
        this.UCPTmbusFcbEnable = UCPTmbusFcbEnable;
    }


    /**
     * Gets the UCPTmbusFcvEnable value for this MBS_Device_Cfg.
     * 
     * @return UCPTmbusFcvEnable
     */
    public java.lang.Short getUCPTmbusFcvEnable() {
        return UCPTmbusFcvEnable;
    }


    /**
     * Sets the UCPTmbusFcvEnable value for this MBS_Device_Cfg.
     * 
     * @param UCPTmbusFcvEnable
     */
    public void setUCPTmbusFcvEnable(java.lang.Short UCPTmbusFcvEnable) {
        this.UCPTmbusFcvEnable = UCPTmbusFcvEnable;
    }


    /**
     * Gets the UCPTmbusMode value for this MBS_Device_Cfg.
     * 
     * @return UCPTmbusMode
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmbusMode() {
        return UCPTmbusMode;
    }


    /**
     * Sets the UCPTmbusMode value for this MBS_Device_Cfg.
     * 
     * @param UCPTmbusMode
     */
    public void setUCPTmbusMode(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmbusMode) {
        this.UCPTmbusMode = UCPTmbusMode;
    }


    /**
     * Gets the UCPTmbusMedId value for this MBS_Device_Cfg.
     * 
     * @return UCPTmbusMedId
     */
    public java.lang.String getUCPTmbusMedId() {
        return UCPTmbusMedId;
    }


    /**
     * Sets the UCPTmbusMedId value for this MBS_Device_Cfg.
     * 
     * @param UCPTmbusMedId
     */
    public void setUCPTmbusMedId(java.lang.String UCPTmbusMedId) {
        this.UCPTmbusMedId = UCPTmbusMedId;
    }


    /**
     * Gets the UCPTmbusManId value for this MBS_Device_Cfg.
     * 
     * @return UCPTmbusManId
     */
    public java.lang.String getUCPTmbusManId() {
        return UCPTmbusManId;
    }


    /**
     * Sets the UCPTmbusManId value for this MBS_Device_Cfg.
     * 
     * @param UCPTmbusManId
     */
    public void setUCPTmbusManId(java.lang.String UCPTmbusManId) {
        this.UCPTmbusManId = UCPTmbusManId;
    }


    /**
     * Gets the UCPTmbusGenId value for this MBS_Device_Cfg.
     * 
     * @return UCPTmbusGenId
     */
    public java.lang.String getUCPTmbusGenId() {
        return UCPTmbusGenId;
    }


    /**
     * Sets the UCPTmbusGenId value for this MBS_Device_Cfg.
     * 
     * @param UCPTmbusGenId
     */
    public void setUCPTmbusGenId(java.lang.String UCPTmbusGenId) {
        this.UCPTmbusGenId = UCPTmbusGenId;
    }


    /**
     * Gets the address value for this MBS_Device_Cfg.
     * 
     * @return address
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MBS_Device_CfgAddress getAddress() {
        return address;
    }


    /**
     * Sets the address value for this MBS_Device_Cfg.
     * 
     * @param address
     */
    public void setAddress(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MBS_Device_CfgAddress address) {
        this.address = address;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MBS_Device_Cfg)) return false;
        MBS_Device_Cfg other = (MBS_Device_Cfg) obj;
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
            ((this.UCPTprogramId==null && other.getUCPTprogramId()==null) || 
             (this.UCPTprogramId!=null &&
              java.util.Arrays.equals(this.UCPTprogramId, other.getUCPTprogramId()))) &&
            ((this.UCPTspeed==null && other.getUCPTspeed()==null) || 
             (this.UCPTspeed!=null &&
              this.UCPTspeed.equals(other.getUCPTspeed()))) &&
            ((this.UCPTmbusFcbEnable==null && other.getUCPTmbusFcbEnable()==null) || 
             (this.UCPTmbusFcbEnable!=null &&
              this.UCPTmbusFcbEnable.equals(other.getUCPTmbusFcbEnable()))) &&
            ((this.UCPTmbusFcvEnable==null && other.getUCPTmbusFcvEnable()==null) || 
             (this.UCPTmbusFcvEnable!=null &&
              this.UCPTmbusFcvEnable.equals(other.getUCPTmbusFcvEnable()))) &&
            ((this.UCPTmbusMode==null && other.getUCPTmbusMode()==null) || 
             (this.UCPTmbusMode!=null &&
              this.UCPTmbusMode.equals(other.getUCPTmbusMode()))) &&
            ((this.UCPTmbusMedId==null && other.getUCPTmbusMedId()==null) || 
             (this.UCPTmbusMedId!=null &&
              this.UCPTmbusMedId.equals(other.getUCPTmbusMedId()))) &&
            ((this.UCPTmbusManId==null && other.getUCPTmbusManId()==null) || 
             (this.UCPTmbusManId!=null &&
              this.UCPTmbusManId.equals(other.getUCPTmbusManId()))) &&
            ((this.UCPTmbusGenId==null && other.getUCPTmbusGenId()==null) || 
             (this.UCPTmbusGenId!=null &&
              this.UCPTmbusGenId.equals(other.getUCPTmbusGenId()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress())));
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
        if (getUCPTprogramId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTprogramId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTprogramId(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTspeed() != null) {
            _hashCode += getUCPTspeed().hashCode();
        }
        if (getUCPTmbusFcbEnable() != null) {
            _hashCode += getUCPTmbusFcbEnable().hashCode();
        }
        if (getUCPTmbusFcvEnable() != null) {
            _hashCode += getUCPTmbusFcvEnable().hashCode();
        }
        if (getUCPTmbusMode() != null) {
            _hashCode += getUCPTmbusMode().hashCode();
        }
        if (getUCPTmbusMedId() != null) {
            _hashCode += getUCPTmbusMedId().hashCode();
        }
        if (getUCPTmbusManId() != null) {
            _hashCode += getUCPTmbusManId().hashCode();
        }
        if (getUCPTmbusGenId() != null) {
            _hashCode += getUCPTmbusGenId().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MBS_Device_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MBS_Device_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThandle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThandle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_UniqueKey"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTprogramId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTprogramId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTspeed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTspeed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmbusFcbEnable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmbusFcbEnable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmbusFcvEnable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmbusFcvEnable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmbusMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmbusMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmbusMedId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmbusMedId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmbusManId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmbusManId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmbusGenId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmbusGenId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">MBS_Device_Cfg>Address"));
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
