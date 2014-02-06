/**
 * LON_Device_RepeatingData_InvokeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Response to LON specific device command 'RepeatingData'.
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Device_RepeatingData_InvokeResponse  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTplrCommissionStatus;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTplrOnlineStatus;

    private int UCPTcommAttempts;

    private int UCPTcommFailures;

    private int UCPTcommAgentSwitch;

    private int UCPTcommSkipped;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTfailureHistory;

    private int UCPTproxyAgentCount;

    private int UCPThopCount;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_RepeatingData_InvokeResponseRepeatChain[] repeatChain;

    public LON_Device_RepeatingData_InvokeResponse() {
    }

    public LON_Device_RepeatingData_InvokeResponse(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTplrCommissionStatus,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTplrOnlineStatus,
           int UCPTcommAttempts,
           int UCPTcommFailures,
           int UCPTcommAgentSwitch,
           int UCPTcommSkipped,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTfailureHistory,
           int UCPTproxyAgentCount,
           int UCPThopCount,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_RepeatingData_InvokeResponseRepeatChain[] repeatChain) {
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
        this.UCPTplrCommissionStatus = UCPTplrCommissionStatus;
        this.UCPTplrOnlineStatus = UCPTplrOnlineStatus;
        this.UCPTcommAttempts = UCPTcommAttempts;
        this.UCPTcommFailures = UCPTcommFailures;
        this.UCPTcommAgentSwitch = UCPTcommAgentSwitch;
        this.UCPTcommSkipped = UCPTcommSkipped;
        this.UCPTfailureHistory = UCPTfailureHistory;
        this.UCPTproxyAgentCount = UCPTproxyAgentCount;
        this.UCPThopCount = UCPThopCount;
        this.repeatChain = repeatChain;
    }


    /**
     * Gets the UCPTplrCommissionStatus value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @return UCPTplrCommissionStatus
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTplrCommissionStatus() {
        return UCPTplrCommissionStatus;
    }


    /**
     * Sets the UCPTplrCommissionStatus value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @param UCPTplrCommissionStatus
     */
    public void setUCPTplrCommissionStatus(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTplrCommissionStatus) {
        this.UCPTplrCommissionStatus = UCPTplrCommissionStatus;
    }


    /**
     * Gets the UCPTplrOnlineStatus value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @return UCPTplrOnlineStatus
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTplrOnlineStatus() {
        return UCPTplrOnlineStatus;
    }


    /**
     * Sets the UCPTplrOnlineStatus value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @param UCPTplrOnlineStatus
     */
    public void setUCPTplrOnlineStatus(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTplrOnlineStatus) {
        this.UCPTplrOnlineStatus = UCPTplrOnlineStatus;
    }


    /**
     * Gets the UCPTcommAttempts value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @return UCPTcommAttempts
     */
    public int getUCPTcommAttempts() {
        return UCPTcommAttempts;
    }


    /**
     * Sets the UCPTcommAttempts value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @param UCPTcommAttempts
     */
    public void setUCPTcommAttempts(int UCPTcommAttempts) {
        this.UCPTcommAttempts = UCPTcommAttempts;
    }


    /**
     * Gets the UCPTcommFailures value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @return UCPTcommFailures
     */
    public int getUCPTcommFailures() {
        return UCPTcommFailures;
    }


    /**
     * Sets the UCPTcommFailures value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @param UCPTcommFailures
     */
    public void setUCPTcommFailures(int UCPTcommFailures) {
        this.UCPTcommFailures = UCPTcommFailures;
    }


    /**
     * Gets the UCPTcommAgentSwitch value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @return UCPTcommAgentSwitch
     */
    public int getUCPTcommAgentSwitch() {
        return UCPTcommAgentSwitch;
    }


    /**
     * Sets the UCPTcommAgentSwitch value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @param UCPTcommAgentSwitch
     */
    public void setUCPTcommAgentSwitch(int UCPTcommAgentSwitch) {
        this.UCPTcommAgentSwitch = UCPTcommAgentSwitch;
    }


    /**
     * Gets the UCPTcommSkipped value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @return UCPTcommSkipped
     */
    public int getUCPTcommSkipped() {
        return UCPTcommSkipped;
    }


    /**
     * Sets the UCPTcommSkipped value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @param UCPTcommSkipped
     */
    public void setUCPTcommSkipped(int UCPTcommSkipped) {
        this.UCPTcommSkipped = UCPTcommSkipped;
    }


    /**
     * Gets the UCPTfailureHistory value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @return UCPTfailureHistory
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTfailureHistory() {
        return UCPTfailureHistory;
    }


    /**
     * Sets the UCPTfailureHistory value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @param UCPTfailureHistory
     */
    public void setUCPTfailureHistory(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTfailureHistory) {
        this.UCPTfailureHistory = UCPTfailureHistory;
    }


    /**
     * Gets the UCPTproxyAgentCount value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @return UCPTproxyAgentCount
     */
    public int getUCPTproxyAgentCount() {
        return UCPTproxyAgentCount;
    }


    /**
     * Sets the UCPTproxyAgentCount value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @param UCPTproxyAgentCount
     */
    public void setUCPTproxyAgentCount(int UCPTproxyAgentCount) {
        this.UCPTproxyAgentCount = UCPTproxyAgentCount;
    }


    /**
     * Gets the UCPThopCount value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @return UCPThopCount
     */
    public int getUCPThopCount() {
        return UCPThopCount;
    }


    /**
     * Sets the UCPThopCount value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @param UCPThopCount
     */
    public void setUCPThopCount(int UCPThopCount) {
        this.UCPThopCount = UCPThopCount;
    }


    /**
     * Gets the repeatChain value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @return repeatChain
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_RepeatingData_InvokeResponseRepeatChain[] getRepeatChain() {
        return repeatChain;
    }


    /**
     * Sets the repeatChain value for this LON_Device_RepeatingData_InvokeResponse.
     * 
     * @param repeatChain
     */
    public void setRepeatChain(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_RepeatingData_InvokeResponseRepeatChain[] repeatChain) {
        this.repeatChain = repeatChain;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_RepeatingData_InvokeResponseRepeatChain getRepeatChain(int i) {
        return this.repeatChain[i];
    }

    public void setRepeatChain(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_RepeatingData_InvokeResponseRepeatChain _value) {
        this.repeatChain[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Device_RepeatingData_InvokeResponse)) return false;
        LON_Device_RepeatingData_InvokeResponse other = (LON_Device_RepeatingData_InvokeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTplrCommissionStatus==null && other.getUCPTplrCommissionStatus()==null) || 
             (this.UCPTplrCommissionStatus!=null &&
              this.UCPTplrCommissionStatus.equals(other.getUCPTplrCommissionStatus()))) &&
            ((this.UCPTplrOnlineStatus==null && other.getUCPTplrOnlineStatus()==null) || 
             (this.UCPTplrOnlineStatus!=null &&
              this.UCPTplrOnlineStatus.equals(other.getUCPTplrOnlineStatus()))) &&
            this.UCPTcommAttempts == other.getUCPTcommAttempts() &&
            this.UCPTcommFailures == other.getUCPTcommFailures() &&
            this.UCPTcommAgentSwitch == other.getUCPTcommAgentSwitch() &&
            this.UCPTcommSkipped == other.getUCPTcommSkipped() &&
            ((this.UCPTfailureHistory==null && other.getUCPTfailureHistory()==null) || 
             (this.UCPTfailureHistory!=null &&
              this.UCPTfailureHistory.equals(other.getUCPTfailureHistory()))) &&
            this.UCPTproxyAgentCount == other.getUCPTproxyAgentCount() &&
            this.UCPThopCount == other.getUCPThopCount() &&
            ((this.repeatChain==null && other.getRepeatChain()==null) || 
             (this.repeatChain!=null &&
              java.util.Arrays.equals(this.repeatChain, other.getRepeatChain())));
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
        if (getUCPTplrCommissionStatus() != null) {
            _hashCode += getUCPTplrCommissionStatus().hashCode();
        }
        if (getUCPTplrOnlineStatus() != null) {
            _hashCode += getUCPTplrOnlineStatus().hashCode();
        }
        _hashCode += getUCPTcommAttempts();
        _hashCode += getUCPTcommFailures();
        _hashCode += getUCPTcommAgentSwitch();
        _hashCode += getUCPTcommSkipped();
        if (getUCPTfailureHistory() != null) {
            _hashCode += getUCPTfailureHistory().hashCode();
        }
        _hashCode += getUCPTproxyAgentCount();
        _hashCode += getUCPThopCount();
        if (getRepeatChain() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRepeatChain());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRepeatChain(), i);
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
        new org.apache.axis.description.TypeDesc(LON_Device_RepeatingData_InvokeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_RepeatingData_InvokeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTplrCommissionStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTplrCommissionStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTplrOnlineStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTplrOnlineStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcommAttempts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcommAttempts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcommFailures");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcommFailures"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcommAgentSwitch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcommAgentSwitch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcommSkipped");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcommSkipped"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfailureHistory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfailureHistory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTproxyAgentCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTproxyAgentCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThopCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThopCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repeatChain");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "RepeatChain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_RepeatingData_InvokeResponse>RepeatChain"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
