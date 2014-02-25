/**
 * MOD_Dp_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Specialized data point configuration type.
 * 					Example:  xSelect="//Item[@xsi:type="MOD_Dp_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="MOD_Dp_Cfg"][starts-with(UCPTname,'Net/MOD/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class MOD_Dp_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Cfg_Base  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTbaseType;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmodbusTable;

    private int UCPTstartAddress;

    private int UCPTstartBit;

    private java.lang.Integer UCPTbitLength;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdataOrdering;

    private java.lang.Double UCPTpollRate;

    public MOD_Dp_Cfg() {
    }

    public MOD_Dp_Cfg(
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
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTbaseType,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmodbusTable,
           int UCPTstartAddress,
           int UCPTstartBit,
           java.lang.Integer UCPTbitLength,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdataOrdering,
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
        this.UCPThandle = UCPThandle;
        this.UCPTbaseType = UCPTbaseType;
        this.UCPTmodbusTable = UCPTmodbusTable;
        this.UCPTstartAddress = UCPTstartAddress;
        this.UCPTstartBit = UCPTstartBit;
        this.UCPTbitLength = UCPTbitLength;
        this.UCPTdataOrdering = UCPTdataOrdering;
        this.UCPTpollRate = UCPTpollRate;
    }


    /**
     * Gets the UCPThandle value for this MOD_Dp_Cfg.
     * 
     * @return UCPThandle
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPThandle() {
        return UCPThandle;
    }


    /**
     * Sets the UCPThandle value for this MOD_Dp_Cfg.
     * 
     * @param UCPThandle
     */
    public void setUCPThandle(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle) {
        this.UCPThandle = UCPThandle;
    }


    /**
     * Gets the UCPTbaseType value for this MOD_Dp_Cfg.
     * 
     * @return UCPTbaseType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTbaseType() {
        return UCPTbaseType;
    }


    /**
     * Sets the UCPTbaseType value for this MOD_Dp_Cfg.
     * 
     * @param UCPTbaseType
     */
    public void setUCPTbaseType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTbaseType) {
        this.UCPTbaseType = UCPTbaseType;
    }


    /**
     * Gets the UCPTmodbusTable value for this MOD_Dp_Cfg.
     * 
     * @return UCPTmodbusTable
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmodbusTable() {
        return UCPTmodbusTable;
    }


    /**
     * Sets the UCPTmodbusTable value for this MOD_Dp_Cfg.
     * 
     * @param UCPTmodbusTable
     */
    public void setUCPTmodbusTable(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmodbusTable) {
        this.UCPTmodbusTable = UCPTmodbusTable;
    }


    /**
     * Gets the UCPTstartAddress value for this MOD_Dp_Cfg.
     * 
     * @return UCPTstartAddress
     */
    public int getUCPTstartAddress() {
        return UCPTstartAddress;
    }


    /**
     * Sets the UCPTstartAddress value for this MOD_Dp_Cfg.
     * 
     * @param UCPTstartAddress
     */
    public void setUCPTstartAddress(int UCPTstartAddress) {
        this.UCPTstartAddress = UCPTstartAddress;
    }


    /**
     * Gets the UCPTstartBit value for this MOD_Dp_Cfg.
     * 
     * @return UCPTstartBit
     */
    public int getUCPTstartBit() {
        return UCPTstartBit;
    }


    /**
     * Sets the UCPTstartBit value for this MOD_Dp_Cfg.
     * 
     * @param UCPTstartBit
     */
    public void setUCPTstartBit(int UCPTstartBit) {
        this.UCPTstartBit = UCPTstartBit;
    }


    /**
     * Gets the UCPTbitLength value for this MOD_Dp_Cfg.
     * 
     * @return UCPTbitLength
     */
    public java.lang.Integer getUCPTbitLength() {
        return UCPTbitLength;
    }


    /**
     * Sets the UCPTbitLength value for this MOD_Dp_Cfg.
     * 
     * @param UCPTbitLength
     */
    public void setUCPTbitLength(java.lang.Integer UCPTbitLength) {
        this.UCPTbitLength = UCPTbitLength;
    }


    /**
     * Gets the UCPTdataOrdering value for this MOD_Dp_Cfg.
     * 
     * @return UCPTdataOrdering
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTdataOrdering() {
        return UCPTdataOrdering;
    }


    /**
     * Sets the UCPTdataOrdering value for this MOD_Dp_Cfg.
     * 
     * @param UCPTdataOrdering
     */
    public void setUCPTdataOrdering(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdataOrdering) {
        this.UCPTdataOrdering = UCPTdataOrdering;
    }


    /**
     * Gets the UCPTpollRate value for this MOD_Dp_Cfg.
     * 
     * @return UCPTpollRate
     */
    public java.lang.Double getUCPTpollRate() {
        return UCPTpollRate;
    }


    /**
     * Sets the UCPTpollRate value for this MOD_Dp_Cfg.
     * 
     * @param UCPTpollRate
     */
    public void setUCPTpollRate(java.lang.Double UCPTpollRate) {
        this.UCPTpollRate = UCPTpollRate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MOD_Dp_Cfg)) return false;
        MOD_Dp_Cfg other = (MOD_Dp_Cfg) obj;
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
            ((this.UCPTbaseType==null && other.getUCPTbaseType()==null) || 
             (this.UCPTbaseType!=null &&
              this.UCPTbaseType.equals(other.getUCPTbaseType()))) &&
            ((this.UCPTmodbusTable==null && other.getUCPTmodbusTable()==null) || 
             (this.UCPTmodbusTable!=null &&
              this.UCPTmodbusTable.equals(other.getUCPTmodbusTable()))) &&
            this.UCPTstartAddress == other.getUCPTstartAddress() &&
            this.UCPTstartBit == other.getUCPTstartBit() &&
            ((this.UCPTbitLength==null && other.getUCPTbitLength()==null) || 
             (this.UCPTbitLength!=null &&
              this.UCPTbitLength.equals(other.getUCPTbitLength()))) &&
            ((this.UCPTdataOrdering==null && other.getUCPTdataOrdering()==null) || 
             (this.UCPTdataOrdering!=null &&
              this.UCPTdataOrdering.equals(other.getUCPTdataOrdering()))) &&
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
        if (getUCPThandle() != null) {
            _hashCode += getUCPThandle().hashCode();
        }
        if (getUCPTbaseType() != null) {
            _hashCode += getUCPTbaseType().hashCode();
        }
        if (getUCPTmodbusTable() != null) {
            _hashCode += getUCPTmodbusTable().hashCode();
        }
        _hashCode += getUCPTstartAddress();
        _hashCode += getUCPTstartBit();
        if (getUCPTbitLength() != null) {
            _hashCode += getUCPTbitLength().hashCode();
        }
        if (getUCPTdataOrdering() != null) {
            _hashCode += getUCPTdataOrdering().hashCode();
        }
        if (getUCPTpollRate() != null) {
            _hashCode += getUCPTpollRate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MOD_Dp_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MOD_Dp_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThandle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThandle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_UniqueKey"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTbaseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTbaseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmodbusTable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmodbusTable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstartAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstartAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstartBit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstartBit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTbitLength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTbitLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdataOrdering");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdataOrdering"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
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
