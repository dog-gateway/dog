/**
 * LON_Device_PowerlineData_InvokeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Response to LON specific device command 'PowerlineData'.
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Device_PowerlineData_InvokeResponse  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo[] frequencyInfo;

    public LON_Device_PowerlineData_InvokeResponse() {
    }

    public LON_Device_PowerlineData_InvokeResponse(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo[] frequencyInfo) {
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
        this.frequencyInfo = frequencyInfo;
    }


    /**
     * Gets the frequencyInfo value for this LON_Device_PowerlineData_InvokeResponse.
     * 
     * @return frequencyInfo
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo[] getFrequencyInfo() {
        return frequencyInfo;
    }


    /**
     * Sets the frequencyInfo value for this LON_Device_PowerlineData_InvokeResponse.
     * 
     * @param frequencyInfo
     */
    public void setFrequencyInfo(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo[] frequencyInfo) {
        this.frequencyInfo = frequencyInfo;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo getFrequencyInfo(int i) {
        return this.frequencyInfo[i];
    }

    public void setFrequencyInfo(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo _value) {
        this.frequencyInfo[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Device_PowerlineData_InvokeResponse)) return false;
        LON_Device_PowerlineData_InvokeResponse other = (LON_Device_PowerlineData_InvokeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.frequencyInfo==null && other.getFrequencyInfo()==null) || 
             (this.frequencyInfo!=null &&
              java.util.Arrays.equals(this.frequencyInfo, other.getFrequencyInfo())));
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
        if (getFrequencyInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFrequencyInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFrequencyInfo(), i);
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
        new org.apache.axis.description.TypeDesc(LON_Device_PowerlineData_InvokeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_PowerlineData_InvokeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frequencyInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "FrequencyInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_DataFrequencyInfo"));
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
