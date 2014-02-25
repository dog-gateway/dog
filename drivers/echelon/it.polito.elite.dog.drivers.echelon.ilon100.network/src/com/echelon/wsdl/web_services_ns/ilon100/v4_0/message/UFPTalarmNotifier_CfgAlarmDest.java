/**
 * UFPTalarmNotifier_CfgAlarmDest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTalarmNotifier_CfgAlarmDest  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTindex;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTenablePath;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDestination activeDest;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDestination passiveDest;

    public UFPTalarmNotifier_CfgAlarmDest() {
    }

    public UFPTalarmNotifier_CfgAlarmDest(
           java.lang.Integer UCPTindex,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTenablePath,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDestination activeDest,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDestination passiveDest) {
           this.UCPTindex = UCPTindex;
           this.UCPTenablePath = UCPTenablePath;
           this.activeDest = activeDest;
           this.passiveDest = passiveDest;
    }


    /**
     * Gets the UCPTindex value for this UFPTalarmNotifier_CfgAlarmDest.
     * 
     * @return UCPTindex
     */
    public java.lang.Integer getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this UFPTalarmNotifier_CfgAlarmDest.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(java.lang.Integer UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTenablePath value for this UFPTalarmNotifier_CfgAlarmDest.
     * 
     * @return UCPTenablePath
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path getUCPTenablePath() {
        return UCPTenablePath;
    }


    /**
     * Sets the UCPTenablePath value for this UFPTalarmNotifier_CfgAlarmDest.
     * 
     * @param UCPTenablePath
     */
    public void setUCPTenablePath(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTenablePath) {
        this.UCPTenablePath = UCPTenablePath;
    }


    /**
     * Gets the activeDest value for this UFPTalarmNotifier_CfgAlarmDest.
     * 
     * @return activeDest
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDestination getActiveDest() {
        return activeDest;
    }


    /**
     * Sets the activeDest value for this UFPTalarmNotifier_CfgAlarmDest.
     * 
     * @param activeDest
     */
    public void setActiveDest(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDestination activeDest) {
        this.activeDest = activeDest;
    }


    /**
     * Gets the passiveDest value for this UFPTalarmNotifier_CfgAlarmDest.
     * 
     * @return passiveDest
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDestination getPassiveDest() {
        return passiveDest;
    }


    /**
     * Sets the passiveDest value for this UFPTalarmNotifier_CfgAlarmDest.
     * 
     * @param passiveDest
     */
    public void setPassiveDest(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDestination passiveDest) {
        this.passiveDest = passiveDest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTalarmNotifier_CfgAlarmDest)) return false;
        UFPTalarmNotifier_CfgAlarmDest other = (UFPTalarmNotifier_CfgAlarmDest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTindex==null && other.getUCPTindex()==null) || 
             (this.UCPTindex!=null &&
              this.UCPTindex.equals(other.getUCPTindex()))) &&
            ((this.UCPTenablePath==null && other.getUCPTenablePath()==null) || 
             (this.UCPTenablePath!=null &&
              this.UCPTenablePath.equals(other.getUCPTenablePath()))) &&
            ((this.activeDest==null && other.getActiveDest()==null) || 
             (this.activeDest!=null &&
              this.activeDest.equals(other.getActiveDest()))) &&
            ((this.passiveDest==null && other.getPassiveDest()==null) || 
             (this.passiveDest!=null &&
              this.passiveDest.equals(other.getPassiveDest())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getUCPTindex() != null) {
            _hashCode += getUCPTindex().hashCode();
        }
        if (getUCPTenablePath() != null) {
            _hashCode += getUCPTenablePath().hashCode();
        }
        if (getActiveDest() != null) {
            _hashCode += getActiveDest().hashCode();
        }
        if (getPassiveDest() != null) {
            _hashCode += getPassiveDest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTalarmNotifier_CfgAlarmDest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Cfg>AlarmDest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTenablePath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTenablePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Path"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activeDest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "ActiveDest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_CfgAlarmDestination"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passiveDest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "PassiveDest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_CfgAlarmDestination"));
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
