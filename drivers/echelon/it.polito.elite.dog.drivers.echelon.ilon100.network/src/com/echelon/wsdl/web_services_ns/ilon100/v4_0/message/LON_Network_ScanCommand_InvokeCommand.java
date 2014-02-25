/**
 * LON_Network_ScanCommand_InvokeCommand.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class LON_Network_ScanCommand_InvokeCommand  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_IlonNi_eCommand UCPTcommand;

    private java.util.Calendar UCPTlastUpdate;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTstatus;

    public LON_Network_ScanCommand_InvokeCommand() {
    }

    public LON_Network_ScanCommand_InvokeCommand(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_IlonNi_eCommand UCPTcommand,
           java.util.Calendar UCPTlastUpdate,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTstatus) {
           this.fault = fault;
           this.UCPTcommand = UCPTcommand;
           this.UCPTlastUpdate = UCPTlastUpdate;
           this.UCPTstatus = UCPTstatus;
    }


    /**
     * Gets the fault value for this LON_Network_ScanCommand_InvokeCommand.
     * 
     * @return fault
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault getFault() {
        return fault;
    }


    /**
     * Sets the fault value for this LON_Network_ScanCommand_InvokeCommand.
     * 
     * @param fault
     */
    public void setFault(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault) {
        this.fault = fault;
    }


    /**
     * Gets the UCPTcommand value for this LON_Network_ScanCommand_InvokeCommand.
     * 
     * @return UCPTcommand
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_IlonNi_eCommand getUCPTcommand() {
        return UCPTcommand;
    }


    /**
     * Sets the UCPTcommand value for this LON_Network_ScanCommand_InvokeCommand.
     * 
     * @param UCPTcommand
     */
    public void setUCPTcommand(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_IlonNi_eCommand UCPTcommand) {
        this.UCPTcommand = UCPTcommand;
    }


    /**
     * Gets the UCPTlastUpdate value for this LON_Network_ScanCommand_InvokeCommand.
     * 
     * @return UCPTlastUpdate
     */
    public java.util.Calendar getUCPTlastUpdate() {
        return UCPTlastUpdate;
    }


    /**
     * Sets the UCPTlastUpdate value for this LON_Network_ScanCommand_InvokeCommand.
     * 
     * @param UCPTlastUpdate
     */
    public void setUCPTlastUpdate(java.util.Calendar UCPTlastUpdate) {
        this.UCPTlastUpdate = UCPTlastUpdate;
    }


    /**
     * Gets the UCPTstatus value for this LON_Network_ScanCommand_InvokeCommand.
     * 
     * @return UCPTstatus
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTstatus() {
        return UCPTstatus;
    }


    /**
     * Sets the UCPTstatus value for this LON_Network_ScanCommand_InvokeCommand.
     * 
     * @param UCPTstatus
     */
    public void setUCPTstatus(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTstatus) {
        this.UCPTstatus = UCPTstatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Network_ScanCommand_InvokeCommand)) return false;
        LON_Network_ScanCommand_InvokeCommand other = (LON_Network_ScanCommand_InvokeCommand) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fault==null && other.getFault()==null) || 
             (this.fault!=null &&
              this.fault.equals(other.getFault()))) &&
            ((this.UCPTcommand==null && other.getUCPTcommand()==null) || 
             (this.UCPTcommand!=null &&
              this.UCPTcommand.equals(other.getUCPTcommand()))) &&
            ((this.UCPTlastUpdate==null && other.getUCPTlastUpdate()==null) || 
             (this.UCPTlastUpdate!=null &&
              this.UCPTlastUpdate.equals(other.getUCPTlastUpdate()))) &&
            ((this.UCPTstatus==null && other.getUCPTstatus()==null) || 
             (this.UCPTstatus!=null &&
              this.UCPTstatus.equals(other.getUCPTstatus())));
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
        if (getFault() != null) {
            _hashCode += getFault().hashCode();
        }
        if (getUCPTcommand() != null) {
            _hashCode += getUCPTcommand().hashCode();
        }
        if (getUCPTlastUpdate() != null) {
            _hashCode += getUCPTlastUpdate().hashCode();
        }
        if (getUCPTstatus() != null) {
            _hashCode += getUCPTstatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Network_ScanCommand_InvokeCommand.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Network_ScanCommand_Invoke>Command"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fault");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "fault"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Fault"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcommand");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcommand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_IlonNi_eCommand"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlastUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlastUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstatus"));
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
