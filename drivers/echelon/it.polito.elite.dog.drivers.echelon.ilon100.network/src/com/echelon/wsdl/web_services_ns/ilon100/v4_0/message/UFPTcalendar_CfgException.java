/**
 * UFPTcalendar_CfgException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTcalendar_CfgException  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTindex;

    private java.lang.String UCPTexceptionName;

    private java.lang.String UCPTaliasName;

    private java.lang.Short UCPTtemporary;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionSchedule[] schedule;

    private java.lang.Integer UCPTmaxClient;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionClient[] client;

    public UFPTcalendar_CfgException() {
    }

    public UFPTcalendar_CfgException(
           java.lang.Integer UCPTindex,
           java.lang.String UCPTexceptionName,
           java.lang.String UCPTaliasName,
           java.lang.Short UCPTtemporary,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionSchedule[] schedule,
           java.lang.Integer UCPTmaxClient,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionClient[] client) {
           this.UCPTindex = UCPTindex;
           this.UCPTexceptionName = UCPTexceptionName;
           this.UCPTaliasName = UCPTaliasName;
           this.UCPTtemporary = UCPTtemporary;
           this.schedule = schedule;
           this.UCPTmaxClient = UCPTmaxClient;
           this.client = client;
    }


    /**
     * Gets the UCPTindex value for this UFPTcalendar_CfgException.
     * 
     * @return UCPTindex
     */
    public java.lang.Integer getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this UFPTcalendar_CfgException.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(java.lang.Integer UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTexceptionName value for this UFPTcalendar_CfgException.
     * 
     * @return UCPTexceptionName
     */
    public java.lang.String getUCPTexceptionName() {
        return UCPTexceptionName;
    }


    /**
     * Sets the UCPTexceptionName value for this UFPTcalendar_CfgException.
     * 
     * @param UCPTexceptionName
     */
    public void setUCPTexceptionName(java.lang.String UCPTexceptionName) {
        this.UCPTexceptionName = UCPTexceptionName;
    }


    /**
     * Gets the UCPTaliasName value for this UFPTcalendar_CfgException.
     * 
     * @return UCPTaliasName
     */
    public java.lang.String getUCPTaliasName() {
        return UCPTaliasName;
    }


    /**
     * Sets the UCPTaliasName value for this UFPTcalendar_CfgException.
     * 
     * @param UCPTaliasName
     */
    public void setUCPTaliasName(java.lang.String UCPTaliasName) {
        this.UCPTaliasName = UCPTaliasName;
    }


    /**
     * Gets the UCPTtemporary value for this UFPTcalendar_CfgException.
     * 
     * @return UCPTtemporary
     */
    public java.lang.Short getUCPTtemporary() {
        return UCPTtemporary;
    }


    /**
     * Sets the UCPTtemporary value for this UFPTcalendar_CfgException.
     * 
     * @param UCPTtemporary
     */
    public void setUCPTtemporary(java.lang.Short UCPTtemporary) {
        this.UCPTtemporary = UCPTtemporary;
    }


    /**
     * Gets the schedule value for this UFPTcalendar_CfgException.
     * 
     * @return schedule
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionSchedule[] getSchedule() {
        return schedule;
    }


    /**
     * Sets the schedule value for this UFPTcalendar_CfgException.
     * 
     * @param schedule
     */
    public void setSchedule(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionSchedule[] schedule) {
        this.schedule = schedule;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionSchedule getSchedule(int i) {
        return this.schedule[i];
    }

    public void setSchedule(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionSchedule _value) {
        this.schedule[i] = _value;
    }


    /**
     * Gets the UCPTmaxClient value for this UFPTcalendar_CfgException.
     * 
     * @return UCPTmaxClient
     */
    public java.lang.Integer getUCPTmaxClient() {
        return UCPTmaxClient;
    }


    /**
     * Sets the UCPTmaxClient value for this UFPTcalendar_CfgException.
     * 
     * @param UCPTmaxClient
     */
    public void setUCPTmaxClient(java.lang.Integer UCPTmaxClient) {
        this.UCPTmaxClient = UCPTmaxClient;
    }


    /**
     * Gets the client value for this UFPTcalendar_CfgException.
     * 
     * @return client
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionClient[] getClient() {
        return client;
    }


    /**
     * Sets the client value for this UFPTcalendar_CfgException.
     * 
     * @param client
     */
    public void setClient(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionClient[] client) {
        this.client = client;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionClient getClient(int i) {
        return this.client[i];
    }

    public void setClient(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionClient _value) {
        this.client[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTcalendar_CfgException)) return false;
        UFPTcalendar_CfgException other = (UFPTcalendar_CfgException) obj;
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
            ((this.UCPTexceptionName==null && other.getUCPTexceptionName()==null) || 
             (this.UCPTexceptionName!=null &&
              this.UCPTexceptionName.equals(other.getUCPTexceptionName()))) &&
            ((this.UCPTaliasName==null && other.getUCPTaliasName()==null) || 
             (this.UCPTaliasName!=null &&
              this.UCPTaliasName.equals(other.getUCPTaliasName()))) &&
            ((this.UCPTtemporary==null && other.getUCPTtemporary()==null) || 
             (this.UCPTtemporary!=null &&
              this.UCPTtemporary.equals(other.getUCPTtemporary()))) &&
            ((this.schedule==null && other.getSchedule()==null) || 
             (this.schedule!=null &&
              java.util.Arrays.equals(this.schedule, other.getSchedule()))) &&
            ((this.UCPTmaxClient==null && other.getUCPTmaxClient()==null) || 
             (this.UCPTmaxClient!=null &&
              this.UCPTmaxClient.equals(other.getUCPTmaxClient()))) &&
            ((this.client==null && other.getClient()==null) || 
             (this.client!=null &&
              java.util.Arrays.equals(this.client, other.getClient())));
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
        if (getUCPTexceptionName() != null) {
            _hashCode += getUCPTexceptionName().hashCode();
        }
        if (getUCPTaliasName() != null) {
            _hashCode += getUCPTaliasName().hashCode();
        }
        if (getUCPTtemporary() != null) {
            _hashCode += getUCPTtemporary().hashCode();
        }
        if (getSchedule() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSchedule());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSchedule(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTmaxClient() != null) {
            _hashCode += getUCPTmaxClient().hashCode();
        }
        if (getClient() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClient());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClient(), i);
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
        new org.apache.axis.description.TypeDesc(UFPTcalendar_CfgException.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTcalendar_Cfg>Exception"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTexceptionName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTexceptionName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTaliasName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTaliasName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTtemporary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtemporary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Schedule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTcalendar_Cfg>Exception>Schedule"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxClient");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxClient"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("client");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Client"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTcalendar_Cfg>Exception>Client"));
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
