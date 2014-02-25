/**
 * LON_Device_ProxyData_InvokeResponseProxyAgent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class LON_Device_ProxyData_InvokeResponseProxyAgent  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTname;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTfailureHistory;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo[] frequencyInfo;

    public LON_Device_ProxyData_InvokeResponseProxyAgent() {
    }

    public LON_Device_ProxyData_InvokeResponseProxyAgent(
           java.lang.String UCPTname,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTfailureHistory,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo[] frequencyInfo) {
           this.UCPTname = UCPTname;
           this.UCPTfailureHistory = UCPTfailureHistory;
           this.frequencyInfo = frequencyInfo;
    }


    /**
     * Gets the UCPTname value for this LON_Device_ProxyData_InvokeResponseProxyAgent.
     * 
     * @return UCPTname
     */
    public java.lang.String getUCPTname() {
        return UCPTname;
    }


    /**
     * Sets the UCPTname value for this LON_Device_ProxyData_InvokeResponseProxyAgent.
     * 
     * @param UCPTname
     */
    public void setUCPTname(java.lang.String UCPTname) {
        this.UCPTname = UCPTname;
    }


    /**
     * Gets the UCPTfailureHistory value for this LON_Device_ProxyData_InvokeResponseProxyAgent.
     * 
     * @return UCPTfailureHistory
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTfailureHistory() {
        return UCPTfailureHistory;
    }


    /**
     * Sets the UCPTfailureHistory value for this LON_Device_ProxyData_InvokeResponseProxyAgent.
     * 
     * @param UCPTfailureHistory
     */
    public void setUCPTfailureHistory(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTfailureHistory) {
        this.UCPTfailureHistory = UCPTfailureHistory;
    }


    /**
     * Gets the frequencyInfo value for this LON_Device_ProxyData_InvokeResponseProxyAgent.
     * 
     * @return frequencyInfo
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo[] getFrequencyInfo() {
        return frequencyInfo;
    }


    /**
     * Sets the frequencyInfo value for this LON_Device_ProxyData_InvokeResponseProxyAgent.
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
        if (!(obj instanceof LON_Device_ProxyData_InvokeResponseProxyAgent)) return false;
        LON_Device_ProxyData_InvokeResponseProxyAgent other = (LON_Device_ProxyData_InvokeResponseProxyAgent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTname==null && other.getUCPTname()==null) || 
             (this.UCPTname!=null &&
              this.UCPTname.equals(other.getUCPTname()))) &&
            ((this.UCPTfailureHistory==null && other.getUCPTfailureHistory()==null) || 
             (this.UCPTfailureHistory!=null &&
              this.UCPTfailureHistory.equals(other.getUCPTfailureHistory()))) &&
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
        int _hashCode = 1;
        if (getUCPTname() != null) {
            _hashCode += getUCPTname().hashCode();
        }
        if (getUCPTfailureHistory() != null) {
            _hashCode += getUCPTfailureHistory().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(LON_Device_ProxyData_InvokeResponseProxyAgent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_ProxyData_InvokeResponse>ProxyAgent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfailureHistory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfailureHistory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
