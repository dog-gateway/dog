/**
 * LON_Network_DeviceCommand_Invoke.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * LON specific network command (called with 'InvokeCmd' operation).
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Network_DeviceCommand_Invoke  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_eCommand command;  // attribute

    private byte[] UCPTuniqueId;  // attribute

    public LON_Network_DeviceCommand_Invoke() {
    }

    public LON_Network_DeviceCommand_Invoke(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_eCommand command,
           byte[] UCPTuniqueId) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus);
        this.command = command;
        this.UCPTuniqueId = UCPTuniqueId;
    }


    /**
     * Gets the command value for this LON_Network_DeviceCommand_Invoke.
     * 
     * @return command
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_eCommand getCommand() {
        return command;
    }


    /**
     * Sets the command value for this LON_Network_DeviceCommand_Invoke.
     * 
     * @param command
     */
    public void setCommand(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_eCommand command) {
        this.command = command;
    }


    /**
     * Gets the UCPTuniqueId value for this LON_Network_DeviceCommand_Invoke.
     * 
     * @return UCPTuniqueId
     */
    public byte[] getUCPTuniqueId() {
        return UCPTuniqueId;
    }


    /**
     * Sets the UCPTuniqueId value for this LON_Network_DeviceCommand_Invoke.
     * 
     * @param UCPTuniqueId
     */
    public void setUCPTuniqueId(byte[] UCPTuniqueId) {
        this.UCPTuniqueId = UCPTuniqueId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Network_DeviceCommand_Invoke)) return false;
        LON_Network_DeviceCommand_Invoke other = (LON_Network_DeviceCommand_Invoke) obj;
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
              this.command.equals(other.getCommand()))) &&
            ((this.UCPTuniqueId==null && other.getUCPTuniqueId()==null) || 
             (this.UCPTuniqueId!=null &&
              java.util.Arrays.equals(this.UCPTuniqueId, other.getUCPTuniqueId())));
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
            _hashCode += getCommand().hashCode();
        }
        if (getUCPTuniqueId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTuniqueId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTuniqueId(), i);
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
        new org.apache.axis.description.TypeDesc(LON_Network_DeviceCommand_Invoke.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Network_DeviceCommand_Invoke"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("command");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Command"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_eCommand"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("UCPTuniqueId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "UCPTuniqueId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"));
        typeDesc.addFieldDesc(attrField);
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
