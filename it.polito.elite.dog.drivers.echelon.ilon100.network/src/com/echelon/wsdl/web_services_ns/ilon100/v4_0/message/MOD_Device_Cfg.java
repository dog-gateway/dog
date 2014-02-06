/**
 * MOD_Device_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Specialized device configuration type.
 * 					Example:  xSelect="//Item[@xsi:type="MOD_Device_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="MOD_Device_Cfg"][starts-with(UCPTname,'Net/MOD/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class MOD_Device_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle;

    private byte[] UCPTprogramId;

    private java.lang.Integer UCPTmaxElements;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MOD_Device_CfgAddress address;

    public MOD_Device_Cfg() {
    }

    public MOD_Device_Cfg(
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
           java.lang.Integer UCPTmaxElements,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MOD_Device_CfgAddress address) {
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
        this.UCPTmaxElements = UCPTmaxElements;
        this.address = address;
    }


    /**
     * Gets the UCPThandle value for this MOD_Device_Cfg.
     * 
     * @return UCPThandle
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPThandle() {
        return UCPThandle;
    }


    /**
     * Sets the UCPThandle value for this MOD_Device_Cfg.
     * 
     * @param UCPThandle
     */
    public void setUCPThandle(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle) {
        this.UCPThandle = UCPThandle;
    }


    /**
     * Gets the UCPTprogramId value for this MOD_Device_Cfg.
     * 
     * @return UCPTprogramId
     */
    public byte[] getUCPTprogramId() {
        return UCPTprogramId;
    }


    /**
     * Sets the UCPTprogramId value for this MOD_Device_Cfg.
     * 
     * @param UCPTprogramId
     */
    public void setUCPTprogramId(byte[] UCPTprogramId) {
        this.UCPTprogramId = UCPTprogramId;
    }


    /**
     * Gets the UCPTmaxElements value for this MOD_Device_Cfg.
     * 
     * @return UCPTmaxElements
     */
    public java.lang.Integer getUCPTmaxElements() {
        return UCPTmaxElements;
    }


    /**
     * Sets the UCPTmaxElements value for this MOD_Device_Cfg.
     * 
     * @param UCPTmaxElements
     */
    public void setUCPTmaxElements(java.lang.Integer UCPTmaxElements) {
        this.UCPTmaxElements = UCPTmaxElements;
    }


    /**
     * Gets the address value for this MOD_Device_Cfg.
     * 
     * @return address
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MOD_Device_CfgAddress getAddress() {
        return address;
    }


    /**
     * Sets the address value for this MOD_Device_Cfg.
     * 
     * @param address
     */
    public void setAddress(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MOD_Device_CfgAddress address) {
        this.address = address;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MOD_Device_Cfg)) return false;
        MOD_Device_Cfg other = (MOD_Device_Cfg) obj;
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
            ((this.UCPTmaxElements==null && other.getUCPTmaxElements()==null) || 
             (this.UCPTmaxElements!=null &&
              this.UCPTmaxElements.equals(other.getUCPTmaxElements()))) &&
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
        if (getUCPTmaxElements() != null) {
            _hashCode += getUCPTmaxElements().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MOD_Device_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MOD_Device_Cfg"));
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
        elemField.setFieldName("UCPTmaxElements");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxElements"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">MOD_Device_Cfg>Address"));
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
