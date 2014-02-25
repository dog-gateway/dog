/**
 * LON_Device_RNI_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class LON_Device_RNI_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTport;

    private java.lang.Integer UCPTmaxRxTransactions;

    public LON_Device_RNI_Cfg() {
    }

    public LON_Device_RNI_Cfg(
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
           byte[] UCPTuniqueId,
           byte[] UCPTreplacementId,
           byte[] UCPTprogramId,
           java.lang.String UCPTgeoPosition,
           byte[] UCPTlocationId,
           java.lang.Integer UCPTmaxDynamicFb,
           java.lang.Integer UCPTmaxDynamicDp,
           java.lang.Integer UCPTmaxTxTransactions,
           java.lang.Integer UCPTmaxTxLifetime,
           java.lang.Short UCPTlocal,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTapplicationStatus,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcommissionStatus,
           java.lang.String UCPTurlImage,
           java.lang.String UCPTurlTemplate,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdynamic,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgUCPTurlCpFile[] UCPTurlCpFile,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgAddress[] address,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgCommand[] command,
           java.lang.Integer UCPTport,
           java.lang.Integer UCPTmaxRxTransactions) {
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
            UCPThandle,
            UCPTuniqueId,
            UCPTreplacementId,
            UCPTprogramId,
            UCPTgeoPosition,
            UCPTlocationId,
            UCPTmaxDynamicFb,
            UCPTmaxDynamicDp,
            UCPTmaxTxTransactions,
            UCPTmaxTxLifetime,
            UCPTlocal,
            UCPTapplicationStatus,
            UCPTcommissionStatus,
            UCPTurlImage,
            UCPTurlTemplate,
            UCPTdynamic,
            UCPTurlCpFile,
            address,
            command);
        this.UCPTport = UCPTport;
        this.UCPTmaxRxTransactions = UCPTmaxRxTransactions;
    }


    /**
     * Gets the UCPTport value for this LON_Device_RNI_Cfg.
     * 
     * @return UCPTport
     */
    public java.lang.Integer getUCPTport() {
        return UCPTport;
    }


    /**
     * Sets the UCPTport value for this LON_Device_RNI_Cfg.
     * 
     * @param UCPTport
     */
    public void setUCPTport(java.lang.Integer UCPTport) {
        this.UCPTport = UCPTport;
    }


    /**
     * Gets the UCPTmaxRxTransactions value for this LON_Device_RNI_Cfg.
     * 
     * @return UCPTmaxRxTransactions
     */
    public java.lang.Integer getUCPTmaxRxTransactions() {
        return UCPTmaxRxTransactions;
    }


    /**
     * Sets the UCPTmaxRxTransactions value for this LON_Device_RNI_Cfg.
     * 
     * @param UCPTmaxRxTransactions
     */
    public void setUCPTmaxRxTransactions(java.lang.Integer UCPTmaxRxTransactions) {
        this.UCPTmaxRxTransactions = UCPTmaxRxTransactions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Device_RNI_Cfg)) return false;
        LON_Device_RNI_Cfg other = (LON_Device_RNI_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTport==null && other.getUCPTport()==null) || 
             (this.UCPTport!=null &&
              this.UCPTport.equals(other.getUCPTport()))) &&
            ((this.UCPTmaxRxTransactions==null && other.getUCPTmaxRxTransactions()==null) || 
             (this.UCPTmaxRxTransactions!=null &&
              this.UCPTmaxRxTransactions.equals(other.getUCPTmaxRxTransactions())));
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
        if (getUCPTport() != null) {
            _hashCode += getUCPTport().hashCode();
        }
        if (getUCPTmaxRxTransactions() != null) {
            _hashCode += getUCPTmaxRxTransactions().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Device_RNI_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_RNI_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTport");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxRxTransactions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxRxTransactions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
