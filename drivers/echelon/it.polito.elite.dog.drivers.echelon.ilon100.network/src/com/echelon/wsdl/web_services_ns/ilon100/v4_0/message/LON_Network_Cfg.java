/**
 * LON_Network_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Specialized network configuration type.
 * 					Example:  xSelect="//Item[@xsi:type="LON_Network_Cfg"]".
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Network_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgLnsDatabase lnsDatabase;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlnsSync;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmgmtMode;

    private java.lang.String UCPTlnsNetworkInterface;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgDomain[] domain;

    public LON_Network_Cfg() {
    }

    public LON_Network_Cfg(
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
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgLnsDatabase lnsDatabase,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlnsSync,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmgmtMode,
           java.lang.String UCPTlnsNetworkInterface,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgDomain[] domain) {
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
        this.lnsDatabase = lnsDatabase;
        this.UCPTlnsSync = UCPTlnsSync;
        this.UCPTmgmtMode = UCPTmgmtMode;
        this.UCPTlnsNetworkInterface = UCPTlnsNetworkInterface;
        this.domain = domain;
    }


    /**
     * Gets the UCPThandle value for this LON_Network_Cfg.
     * 
     * @return UCPThandle
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPThandle() {
        return UCPThandle;
    }


    /**
     * Sets the UCPThandle value for this LON_Network_Cfg.
     * 
     * @param UCPThandle
     */
    public void setUCPThandle(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle) {
        this.UCPThandle = UCPThandle;
    }


    /**
     * Gets the lnsDatabase value for this LON_Network_Cfg.
     * 
     * @return lnsDatabase
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgLnsDatabase getLnsDatabase() {
        return lnsDatabase;
    }


    /**
     * Sets the lnsDatabase value for this LON_Network_Cfg.
     * 
     * @param lnsDatabase
     */
    public void setLnsDatabase(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgLnsDatabase lnsDatabase) {
        this.lnsDatabase = lnsDatabase;
    }


    /**
     * Gets the UCPTlnsSync value for this LON_Network_Cfg.
     * 
     * @return UCPTlnsSync
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTlnsSync() {
        return UCPTlnsSync;
    }


    /**
     * Sets the UCPTlnsSync value for this LON_Network_Cfg.
     * 
     * @param UCPTlnsSync
     */
    public void setUCPTlnsSync(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlnsSync) {
        this.UCPTlnsSync = UCPTlnsSync;
    }


    /**
     * Gets the UCPTmgmtMode value for this LON_Network_Cfg.
     * 
     * @return UCPTmgmtMode
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmgmtMode() {
        return UCPTmgmtMode;
    }


    /**
     * Sets the UCPTmgmtMode value for this LON_Network_Cfg.
     * 
     * @param UCPTmgmtMode
     */
    public void setUCPTmgmtMode(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmgmtMode) {
        this.UCPTmgmtMode = UCPTmgmtMode;
    }


    /**
     * Gets the UCPTlnsNetworkInterface value for this LON_Network_Cfg.
     * 
     * @return UCPTlnsNetworkInterface
     */
    public java.lang.String getUCPTlnsNetworkInterface() {
        return UCPTlnsNetworkInterface;
    }


    /**
     * Sets the UCPTlnsNetworkInterface value for this LON_Network_Cfg.
     * 
     * @param UCPTlnsNetworkInterface
     */
    public void setUCPTlnsNetworkInterface(java.lang.String UCPTlnsNetworkInterface) {
        this.UCPTlnsNetworkInterface = UCPTlnsNetworkInterface;
    }


    /**
     * Gets the domain value for this LON_Network_Cfg.
     * 
     * @return domain
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgDomain[] getDomain() {
        return domain;
    }


    /**
     * Sets the domain value for this LON_Network_Cfg.
     * 
     * @param domain
     */
    public void setDomain(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgDomain[] domain) {
        this.domain = domain;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgDomain getDomain(int i) {
        return this.domain[i];
    }

    public void setDomain(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgDomain _value) {
        this.domain[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Network_Cfg)) return false;
        LON_Network_Cfg other = (LON_Network_Cfg) obj;
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
            ((this.lnsDatabase==null && other.getLnsDatabase()==null) || 
             (this.lnsDatabase!=null &&
              this.lnsDatabase.equals(other.getLnsDatabase()))) &&
            ((this.UCPTlnsSync==null && other.getUCPTlnsSync()==null) || 
             (this.UCPTlnsSync!=null &&
              this.UCPTlnsSync.equals(other.getUCPTlnsSync()))) &&
            ((this.UCPTmgmtMode==null && other.getUCPTmgmtMode()==null) || 
             (this.UCPTmgmtMode!=null &&
              this.UCPTmgmtMode.equals(other.getUCPTmgmtMode()))) &&
            ((this.UCPTlnsNetworkInterface==null && other.getUCPTlnsNetworkInterface()==null) || 
             (this.UCPTlnsNetworkInterface!=null &&
              this.UCPTlnsNetworkInterface.equals(other.getUCPTlnsNetworkInterface()))) &&
            ((this.domain==null && other.getDomain()==null) || 
             (this.domain!=null &&
              java.util.Arrays.equals(this.domain, other.getDomain())));
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
        if (getLnsDatabase() != null) {
            _hashCode += getLnsDatabase().hashCode();
        }
        if (getUCPTlnsSync() != null) {
            _hashCode += getUCPTlnsSync().hashCode();
        }
        if (getUCPTmgmtMode() != null) {
            _hashCode += getUCPTmgmtMode().hashCode();
        }
        if (getUCPTlnsNetworkInterface() != null) {
            _hashCode += getUCPTlnsNetworkInterface().hashCode();
        }
        if (getDomain() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDomain());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDomain(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Network_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Network_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThandle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThandle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_UniqueKey"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lnsDatabase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LnsDatabase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Network_Cfg>LnsDatabase"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlnsSync");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlnsSync"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmgmtMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmgmtMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlnsNetworkInterface");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlnsNetworkInterface"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domain");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Domain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Network_Cfg>Domain"));
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
