/**
 * TemplateManager_FILE_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Service configuration type of the service 'TemplateManager'.
 * 					Example:  xSelect="//Item[@xsi:type="TemplateManager_FILE_Cfg"][UCPTprogramId="900001012881040C"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_FILE_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTresourceFile;

    private java.lang.String UCPTdataVersion;

    private java.lang.String UCPTformatVersion;

    private java.lang.String UCPTcreator;

    public TemplateManager_FILE_Cfg() {
    }

    public TemplateManager_FILE_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           java.lang.String UCPTresourceFile,
           java.lang.String UCPTdataVersion,
           java.lang.String UCPTformatVersion,
           java.lang.String UCPTcreator) {
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
        this.UCPTresourceFile = UCPTresourceFile;
        this.UCPTdataVersion = UCPTdataVersion;
        this.UCPTformatVersion = UCPTformatVersion;
        this.UCPTcreator = UCPTcreator;
    }


    /**
     * Gets the UCPTresourceFile value for this TemplateManager_FILE_Cfg.
     * 
     * @return UCPTresourceFile
     */
    public java.lang.String getUCPTresourceFile() {
        return UCPTresourceFile;
    }


    /**
     * Sets the UCPTresourceFile value for this TemplateManager_FILE_Cfg.
     * 
     * @param UCPTresourceFile
     */
    public void setUCPTresourceFile(java.lang.String UCPTresourceFile) {
        this.UCPTresourceFile = UCPTresourceFile;
    }


    /**
     * Gets the UCPTdataVersion value for this TemplateManager_FILE_Cfg.
     * 
     * @return UCPTdataVersion
     */
    public java.lang.String getUCPTdataVersion() {
        return UCPTdataVersion;
    }


    /**
     * Sets the UCPTdataVersion value for this TemplateManager_FILE_Cfg.
     * 
     * @param UCPTdataVersion
     */
    public void setUCPTdataVersion(java.lang.String UCPTdataVersion) {
        this.UCPTdataVersion = UCPTdataVersion;
    }


    /**
     * Gets the UCPTformatVersion value for this TemplateManager_FILE_Cfg.
     * 
     * @return UCPTformatVersion
     */
    public java.lang.String getUCPTformatVersion() {
        return UCPTformatVersion;
    }


    /**
     * Sets the UCPTformatVersion value for this TemplateManager_FILE_Cfg.
     * 
     * @param UCPTformatVersion
     */
    public void setUCPTformatVersion(java.lang.String UCPTformatVersion) {
        this.UCPTformatVersion = UCPTformatVersion;
    }


    /**
     * Gets the UCPTcreator value for this TemplateManager_FILE_Cfg.
     * 
     * @return UCPTcreator
     */
    public java.lang.String getUCPTcreator() {
        return UCPTcreator;
    }


    /**
     * Sets the UCPTcreator value for this TemplateManager_FILE_Cfg.
     * 
     * @param UCPTcreator
     */
    public void setUCPTcreator(java.lang.String UCPTcreator) {
        this.UCPTcreator = UCPTcreator;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_FILE_Cfg)) return false;
        TemplateManager_FILE_Cfg other = (TemplateManager_FILE_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTresourceFile==null && other.getUCPTresourceFile()==null) || 
             (this.UCPTresourceFile!=null &&
              this.UCPTresourceFile.equals(other.getUCPTresourceFile()))) &&
            ((this.UCPTdataVersion==null && other.getUCPTdataVersion()==null) || 
             (this.UCPTdataVersion!=null &&
              this.UCPTdataVersion.equals(other.getUCPTdataVersion()))) &&
            ((this.UCPTformatVersion==null && other.getUCPTformatVersion()==null) || 
             (this.UCPTformatVersion!=null &&
              this.UCPTformatVersion.equals(other.getUCPTformatVersion()))) &&
            ((this.UCPTcreator==null && other.getUCPTcreator()==null) || 
             (this.UCPTcreator!=null &&
              this.UCPTcreator.equals(other.getUCPTcreator())));
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
        if (getUCPTresourceFile() != null) {
            _hashCode += getUCPTresourceFile().hashCode();
        }
        if (getUCPTdataVersion() != null) {
            _hashCode += getUCPTdataVersion().hashCode();
        }
        if (getUCPTformatVersion() != null) {
            _hashCode += getUCPTformatVersion().hashCode();
        }
        if (getUCPTcreator() != null) {
            _hashCode += getUCPTcreator().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateManager_FILE_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_FILE_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTresourceFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTresourceFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdataVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdataVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTformatVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTformatVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcreator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcreator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
