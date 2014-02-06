/**
 * LON_Device_StatusData_InvokeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Response to LON specific device command 'QueryStatus'.
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Device_StatusData_InvokeResponse  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private byte[] UCPTuniqueId;

    private int UCPTtransmitErrors;

    private int UCPTtransactionTimeouts;

    private int UCPTrcvTransactionFull;

    private int UCPTlostMessages;

    private int UCPTmissedMessages;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTresetCause;

    private int UCPTversionNumber;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTerrorLog;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTneuronModel;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTonlineStatus;

    public LON_Device_StatusData_InvokeResponse() {
    }

    public LON_Device_StatusData_InvokeResponse(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           byte[] UCPTuniqueId,
           int UCPTtransmitErrors,
           int UCPTtransactionTimeouts,
           int UCPTrcvTransactionFull,
           int UCPTlostMessages,
           int UCPTmissedMessages,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTresetCause,
           int UCPTversionNumber,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTerrorLog,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTneuronModel,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTonlineStatus) {
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
        this.UCPTuniqueId = UCPTuniqueId;
        this.UCPTtransmitErrors = UCPTtransmitErrors;
        this.UCPTtransactionTimeouts = UCPTtransactionTimeouts;
        this.UCPTrcvTransactionFull = UCPTrcvTransactionFull;
        this.UCPTlostMessages = UCPTlostMessages;
        this.UCPTmissedMessages = UCPTmissedMessages;
        this.UCPTresetCause = UCPTresetCause;
        this.UCPTversionNumber = UCPTversionNumber;
        this.UCPTerrorLog = UCPTerrorLog;
        this.UCPTneuronModel = UCPTneuronModel;
        this.UCPTonlineStatus = UCPTonlineStatus;
    }


    /**
     * Gets the UCPTuniqueId value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTuniqueId
     */
    public byte[] getUCPTuniqueId() {
        return UCPTuniqueId;
    }


    /**
     * Sets the UCPTuniqueId value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTuniqueId
     */
    public void setUCPTuniqueId(byte[] UCPTuniqueId) {
        this.UCPTuniqueId = UCPTuniqueId;
    }


    /**
     * Gets the UCPTtransmitErrors value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTtransmitErrors
     */
    public int getUCPTtransmitErrors() {
        return UCPTtransmitErrors;
    }


    /**
     * Sets the UCPTtransmitErrors value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTtransmitErrors
     */
    public void setUCPTtransmitErrors(int UCPTtransmitErrors) {
        this.UCPTtransmitErrors = UCPTtransmitErrors;
    }


    /**
     * Gets the UCPTtransactionTimeouts value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTtransactionTimeouts
     */
    public int getUCPTtransactionTimeouts() {
        return UCPTtransactionTimeouts;
    }


    /**
     * Sets the UCPTtransactionTimeouts value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTtransactionTimeouts
     */
    public void setUCPTtransactionTimeouts(int UCPTtransactionTimeouts) {
        this.UCPTtransactionTimeouts = UCPTtransactionTimeouts;
    }


    /**
     * Gets the UCPTrcvTransactionFull value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTrcvTransactionFull
     */
    public int getUCPTrcvTransactionFull() {
        return UCPTrcvTransactionFull;
    }


    /**
     * Sets the UCPTrcvTransactionFull value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTrcvTransactionFull
     */
    public void setUCPTrcvTransactionFull(int UCPTrcvTransactionFull) {
        this.UCPTrcvTransactionFull = UCPTrcvTransactionFull;
    }


    /**
     * Gets the UCPTlostMessages value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTlostMessages
     */
    public int getUCPTlostMessages() {
        return UCPTlostMessages;
    }


    /**
     * Sets the UCPTlostMessages value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTlostMessages
     */
    public void setUCPTlostMessages(int UCPTlostMessages) {
        this.UCPTlostMessages = UCPTlostMessages;
    }


    /**
     * Gets the UCPTmissedMessages value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTmissedMessages
     */
    public int getUCPTmissedMessages() {
        return UCPTmissedMessages;
    }


    /**
     * Sets the UCPTmissedMessages value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTmissedMessages
     */
    public void setUCPTmissedMessages(int UCPTmissedMessages) {
        this.UCPTmissedMessages = UCPTmissedMessages;
    }


    /**
     * Gets the UCPTresetCause value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTresetCause
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTresetCause() {
        return UCPTresetCause;
    }


    /**
     * Sets the UCPTresetCause value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTresetCause
     */
    public void setUCPTresetCause(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTresetCause) {
        this.UCPTresetCause = UCPTresetCause;
    }


    /**
     * Gets the UCPTversionNumber value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTversionNumber
     */
    public int getUCPTversionNumber() {
        return UCPTversionNumber;
    }


    /**
     * Sets the UCPTversionNumber value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTversionNumber
     */
    public void setUCPTversionNumber(int UCPTversionNumber) {
        this.UCPTversionNumber = UCPTversionNumber;
    }


    /**
     * Gets the UCPTerrorLog value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTerrorLog
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTerrorLog() {
        return UCPTerrorLog;
    }


    /**
     * Sets the UCPTerrorLog value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTerrorLog
     */
    public void setUCPTerrorLog(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTerrorLog) {
        this.UCPTerrorLog = UCPTerrorLog;
    }


    /**
     * Gets the UCPTneuronModel value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTneuronModel
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTneuronModel() {
        return UCPTneuronModel;
    }


    /**
     * Sets the UCPTneuronModel value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTneuronModel
     */
    public void setUCPTneuronModel(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTneuronModel) {
        this.UCPTneuronModel = UCPTneuronModel;
    }


    /**
     * Gets the UCPTonlineStatus value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @return UCPTonlineStatus
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTonlineStatus() {
        return UCPTonlineStatus;
    }


    /**
     * Sets the UCPTonlineStatus value for this LON_Device_StatusData_InvokeResponse.
     * 
     * @param UCPTonlineStatus
     */
    public void setUCPTonlineStatus(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTonlineStatus) {
        this.UCPTonlineStatus = UCPTonlineStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Device_StatusData_InvokeResponse)) return false;
        LON_Device_StatusData_InvokeResponse other = (LON_Device_StatusData_InvokeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTuniqueId==null && other.getUCPTuniqueId()==null) || 
             (this.UCPTuniqueId!=null &&
              java.util.Arrays.equals(this.UCPTuniqueId, other.getUCPTuniqueId()))) &&
            this.UCPTtransmitErrors == other.getUCPTtransmitErrors() &&
            this.UCPTtransactionTimeouts == other.getUCPTtransactionTimeouts() &&
            this.UCPTrcvTransactionFull == other.getUCPTrcvTransactionFull() &&
            this.UCPTlostMessages == other.getUCPTlostMessages() &&
            this.UCPTmissedMessages == other.getUCPTmissedMessages() &&
            ((this.UCPTresetCause==null && other.getUCPTresetCause()==null) || 
             (this.UCPTresetCause!=null &&
              this.UCPTresetCause.equals(other.getUCPTresetCause()))) &&
            this.UCPTversionNumber == other.getUCPTversionNumber() &&
            ((this.UCPTerrorLog==null && other.getUCPTerrorLog()==null) || 
             (this.UCPTerrorLog!=null &&
              this.UCPTerrorLog.equals(other.getUCPTerrorLog()))) &&
            ((this.UCPTneuronModel==null && other.getUCPTneuronModel()==null) || 
             (this.UCPTneuronModel!=null &&
              this.UCPTneuronModel.equals(other.getUCPTneuronModel()))) &&
            ((this.UCPTonlineStatus==null && other.getUCPTonlineStatus()==null) || 
             (this.UCPTonlineStatus!=null &&
              this.UCPTonlineStatus.equals(other.getUCPTonlineStatus())));
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
        _hashCode += getUCPTtransmitErrors();
        _hashCode += getUCPTtransactionTimeouts();
        _hashCode += getUCPTrcvTransactionFull();
        _hashCode += getUCPTlostMessages();
        _hashCode += getUCPTmissedMessages();
        if (getUCPTresetCause() != null) {
            _hashCode += getUCPTresetCause().hashCode();
        }
        _hashCode += getUCPTversionNumber();
        if (getUCPTerrorLog() != null) {
            _hashCode += getUCPTerrorLog().hashCode();
        }
        if (getUCPTneuronModel() != null) {
            _hashCode += getUCPTneuronModel().hashCode();
        }
        if (getUCPTonlineStatus() != null) {
            _hashCode += getUCPTonlineStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Device_StatusData_InvokeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_StatusData_InvokeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTuniqueId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTuniqueId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTtransmitErrors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtransmitErrors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTtransactionTimeouts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtransactionTimeouts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTrcvTransactionFull");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTrcvTransactionFull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlostMessages");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlostMessages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmissedMessages");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmissedMessages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTresetCause");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTresetCause"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTversionNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTversionNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTerrorLog");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTerrorLog"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTneuronModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTneuronModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTonlineStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTonlineStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
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
