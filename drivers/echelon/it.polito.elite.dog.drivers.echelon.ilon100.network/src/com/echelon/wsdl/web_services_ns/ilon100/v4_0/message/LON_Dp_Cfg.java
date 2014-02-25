/**
 * LON_Dp_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Specialized data point configuration type.
 * 					Example:  xSelect="//Item[@xsi:type="LON_Dp_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="LON_Dp_Cfg"][starts-with(UCPTname,'Net/LON/BAS/Alarm
 * Generator 1/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Dp_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Cfg_Base  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPTnvIndex;

    private byte[] UCPTnvSelector;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdynamic;

    private java.lang.Double UCPTpollRate;

    public LON_Dp_Cfg() {
    }

    public LON_Dp_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           java.lang.String UCPTformatDescription,
           java.lang.Integer UCPTlength,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdirection,
           java.lang.Short UCPTpersist,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefOutput,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] UCPTunit,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPTnvIndex,
           byte[] UCPTnvSelector,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdynamic,
           java.lang.Double UCPTpollRate) {
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
            UCPTformatDescription,
            UCPTlength,
            UCPTdirection,
            UCPTpersist,
            UCPTdefOutput,
            UCPTunit);
        this.UCPTnvIndex = UCPTnvIndex;
        this.UCPTnvSelector = UCPTnvSelector;
        this.UCPTdynamic = UCPTdynamic;
        this.UCPTpollRate = UCPTpollRate;
    }


    /**
     * Gets the UCPTnvIndex value for this LON_Dp_Cfg.
     * 
     * @return UCPTnvIndex
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPTnvIndex() {
        return UCPTnvIndex;
    }


    /**
     * Sets the UCPTnvIndex value for this LON_Dp_Cfg.
     * 
     * @param UCPTnvIndex
     */
    public void setUCPTnvIndex(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPTnvIndex) {
        this.UCPTnvIndex = UCPTnvIndex;
    }


    /**
     * Gets the UCPTnvSelector value for this LON_Dp_Cfg.
     * 
     * @return UCPTnvSelector
     */
    public byte[] getUCPTnvSelector() {
        return UCPTnvSelector;
    }


    /**
     * Sets the UCPTnvSelector value for this LON_Dp_Cfg.
     * 
     * @param UCPTnvSelector
     */
    public void setUCPTnvSelector(byte[] UCPTnvSelector) {
        this.UCPTnvSelector = UCPTnvSelector;
    }


    /**
     * Gets the UCPTdynamic value for this LON_Dp_Cfg.
     * 
     * @return UCPTdynamic
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTdynamic() {
        return UCPTdynamic;
    }


    /**
     * Sets the UCPTdynamic value for this LON_Dp_Cfg.
     * 
     * @param UCPTdynamic
     */
    public void setUCPTdynamic(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdynamic) {
        this.UCPTdynamic = UCPTdynamic;
    }


    /**
     * Gets the UCPTpollRate value for this LON_Dp_Cfg.
     * 
     * @return UCPTpollRate
     */
    public java.lang.Double getUCPTpollRate() {
        return UCPTpollRate;
    }


    /**
     * Sets the UCPTpollRate value for this LON_Dp_Cfg.
     * 
     * @param UCPTpollRate
     */
    public void setUCPTpollRate(java.lang.Double UCPTpollRate) {
        this.UCPTpollRate = UCPTpollRate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Dp_Cfg)) return false;
        LON_Dp_Cfg other = (LON_Dp_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTnvIndex==null && other.getUCPTnvIndex()==null) || 
             (this.UCPTnvIndex!=null &&
              this.UCPTnvIndex.equals(other.getUCPTnvIndex()))) &&
            ((this.UCPTnvSelector==null && other.getUCPTnvSelector()==null) || 
             (this.UCPTnvSelector!=null &&
              java.util.Arrays.equals(this.UCPTnvSelector, other.getUCPTnvSelector()))) &&
            ((this.UCPTdynamic==null && other.getUCPTdynamic()==null) || 
             (this.UCPTdynamic!=null &&
              this.UCPTdynamic.equals(other.getUCPTdynamic()))) &&
            ((this.UCPTpollRate==null && other.getUCPTpollRate()==null) || 
             (this.UCPTpollRate!=null &&
              this.UCPTpollRate.equals(other.getUCPTpollRate())));
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
        if (getUCPTnvIndex() != null) {
            _hashCode += getUCPTnvIndex().hashCode();
        }
        if (getUCPTnvSelector() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTnvSelector());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTnvSelector(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTdynamic() != null) {
            _hashCode += getUCPTdynamic().hashCode();
        }
        if (getUCPTpollRate() != null) {
            _hashCode += getUCPTpollRate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Dp_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Dp_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTnvIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTnvIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_UniqueKey"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTnvSelector");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTnvSelector"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpollRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpollRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
