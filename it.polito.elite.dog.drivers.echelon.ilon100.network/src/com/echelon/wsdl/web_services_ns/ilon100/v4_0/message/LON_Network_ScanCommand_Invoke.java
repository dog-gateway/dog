/**
 * LON_Network_ScanCommand_Invoke.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * LON specific network command (called with 'InvokeCmd' operation).
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Network_ScanCommand_Invoke  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_ScanCommand_InvokeCommand[] command;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTscan;

    private java.lang.Short UCPTunCfgOnly;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_eScanCommand scanCommand;  // attribute

    public LON_Network_ScanCommand_Invoke() {
    }

    public LON_Network_ScanCommand_Invoke(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_eScanCommand scanCommand,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_ScanCommand_InvokeCommand[] command,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTscan,
           java.lang.Short UCPTunCfgOnly) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus);
        this.scanCommand = scanCommand;
        this.command = command;
        this.UCPTscan = UCPTscan;
        this.UCPTunCfgOnly = UCPTunCfgOnly;
    }


    /**
     * Gets the command value for this LON_Network_ScanCommand_Invoke.
     * 
     * @return command
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_ScanCommand_InvokeCommand[] getCommand() {
        return command;
    }


    /**
     * Sets the command value for this LON_Network_ScanCommand_Invoke.
     * 
     * @param command
     */
    public void setCommand(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_ScanCommand_InvokeCommand[] command) {
        this.command = command;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_ScanCommand_InvokeCommand getCommand(int i) {
        return this.command[i];
    }

    public void setCommand(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_ScanCommand_InvokeCommand _value) {
        this.command[i] = _value;
    }


    /**
     * Gets the UCPTscan value for this LON_Network_ScanCommand_Invoke.
     * 
     * @return UCPTscan
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] getUCPTscan() {
        return UCPTscan;
    }


    /**
     * Sets the UCPTscan value for this LON_Network_ScanCommand_Invoke.
     * 
     * @param UCPTscan
     */
    public void setUCPTscan(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTscan) {
        this.UCPTscan = UCPTscan;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTscan(int i) {
        return this.UCPTscan[i];
    }

    public void setUCPTscan(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString _value) {
        this.UCPTscan[i] = _value;
    }


    /**
     * Gets the UCPTunCfgOnly value for this LON_Network_ScanCommand_Invoke.
     * 
     * @return UCPTunCfgOnly
     */
    public java.lang.Short getUCPTunCfgOnly() {
        return UCPTunCfgOnly;
    }


    /**
     * Sets the UCPTunCfgOnly value for this LON_Network_ScanCommand_Invoke.
     * 
     * @param UCPTunCfgOnly
     */
    public void setUCPTunCfgOnly(java.lang.Short UCPTunCfgOnly) {
        this.UCPTunCfgOnly = UCPTunCfgOnly;
    }


    /**
     * Gets the scanCommand value for this LON_Network_ScanCommand_Invoke.
     * 
     * @return scanCommand
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_eScanCommand getScanCommand() {
        return scanCommand;
    }


    /**
     * Sets the scanCommand value for this LON_Network_ScanCommand_Invoke.
     * 
     * @param scanCommand
     */
    public void setScanCommand(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_eScanCommand scanCommand) {
        this.scanCommand = scanCommand;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Network_ScanCommand_Invoke)) return false;
        LON_Network_ScanCommand_Invoke other = (LON_Network_ScanCommand_Invoke) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.command==null && other.getCommand()==null) || 
             (this.command!=null &&
              java.util.Arrays.equals(this.command, other.getCommand()))) &&
            ((this.UCPTscan==null && other.getUCPTscan()==null) || 
             (this.UCPTscan!=null &&
              java.util.Arrays.equals(this.UCPTscan, other.getUCPTscan()))) &&
            ((this.UCPTunCfgOnly==null && other.getUCPTunCfgOnly()==null) || 
             (this.UCPTunCfgOnly!=null &&
              this.UCPTunCfgOnly.equals(other.getUCPTunCfgOnly()))) &&
            ((this.scanCommand==null && other.getScanCommand()==null) || 
             (this.scanCommand!=null &&
              this.scanCommand.equals(other.getScanCommand())));
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
        if (getCommand() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCommand());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCommand(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTscan() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTscan());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTscan(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTunCfgOnly() != null) {
            _hashCode += getUCPTunCfgOnly().hashCode();
        }
        if (getScanCommand() != null) {
            _hashCode += getScanCommand().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Network_ScanCommand_Invoke.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Network_ScanCommand_Invoke"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("scanCommand");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ScanCommand"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Network_eScanCommand"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("command");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Command"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Network_ScanCommand_Invoke>Command"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTscan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTscan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTunCfgOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTunCfgOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
