/**
 * LON_Device_Command_Invoke.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * LON specific device command (called with 'InvokeCmd' operation).
 * 				ATTENTION: only allowded are 'QueryStatus', 'ClearStatus', 'RepeatingData',
 * 'PowerlineData', 'ProxyData', 'SendServicePin'
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Device_Command_Invoke  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_eCommand command;  // attribute

    public LON_Device_Command_Invoke() {
    }

    public LON_Device_Command_Invoke(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_eCommand command) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus);
        this.command = command;
    }


    /**
     * Gets the command value for this LON_Device_Command_Invoke.
     * 
     * @return command
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_eCommand getCommand() {
        return command;
    }


    /**
     * Sets the command value for this LON_Device_Command_Invoke.
     * 
     * @param command
     */
    public void setCommand(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_eCommand command) {
        this.command = command;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Device_Command_Invoke)) return false;
        LON_Device_Command_Invoke other = (LON_Device_Command_Invoke) obj;
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
              this.command.equals(other.getCommand())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Device_Command_Invoke.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_Command_Invoke"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("command");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Command"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_eCommand"));
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
