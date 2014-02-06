/**
 * UFPTscheduler_CfgDateBased.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTscheduler_CfgDateBased  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTindex;

    private java.lang.String UCPTdescription;

    private int UCPTpriority;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEvent[] event;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBasedException[] exception;

    public UFPTscheduler_CfgDateBased() {
    }

    public UFPTscheduler_CfgDateBased(
           java.lang.Integer UCPTindex,
           java.lang.String UCPTdescription,
           int UCPTpriority,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEvent[] event,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBasedException[] exception) {
           this.UCPTindex = UCPTindex;
           this.UCPTdescription = UCPTdescription;
           this.UCPTpriority = UCPTpriority;
           this.event = event;
           this.exception = exception;
    }


    /**
     * Gets the UCPTindex value for this UFPTscheduler_CfgDateBased.
     * 
     * @return UCPTindex
     */
    public java.lang.Integer getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this UFPTscheduler_CfgDateBased.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(java.lang.Integer UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTdescription value for this UFPTscheduler_CfgDateBased.
     * 
     * @return UCPTdescription
     */
    public java.lang.String getUCPTdescription() {
        return UCPTdescription;
    }


    /**
     * Sets the UCPTdescription value for this UFPTscheduler_CfgDateBased.
     * 
     * @param UCPTdescription
     */
    public void setUCPTdescription(java.lang.String UCPTdescription) {
        this.UCPTdescription = UCPTdescription;
    }


    /**
     * Gets the UCPTpriority value for this UFPTscheduler_CfgDateBased.
     * 
     * @return UCPTpriority
     */
    public int getUCPTpriority() {
        return UCPTpriority;
    }


    /**
     * Sets the UCPTpriority value for this UFPTscheduler_CfgDateBased.
     * 
     * @param UCPTpriority
     */
    public void setUCPTpriority(int UCPTpriority) {
        this.UCPTpriority = UCPTpriority;
    }


    /**
     * Gets the event value for this UFPTscheduler_CfgDateBased.
     * 
     * @return event
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEvent[] getEvent() {
        return event;
    }


    /**
     * Sets the event value for this UFPTscheduler_CfgDateBased.
     * 
     * @param event
     */
    public void setEvent(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEvent[] event) {
        this.event = event;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEvent getEvent(int i) {
        return this.event[i];
    }

    public void setEvent(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEvent _value) {
        this.event[i] = _value;
    }


    /**
     * Gets the exception value for this UFPTscheduler_CfgDateBased.
     * 
     * @return exception
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBasedException[] getException() {
        return exception;
    }


    /**
     * Sets the exception value for this UFPTscheduler_CfgDateBased.
     * 
     * @param exception
     */
    public void setException(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBasedException[] exception) {
        this.exception = exception;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBasedException getException(int i) {
        return this.exception[i];
    }

    public void setException(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBasedException _value) {
        this.exception[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTscheduler_CfgDateBased)) return false;
        UFPTscheduler_CfgDateBased other = (UFPTscheduler_CfgDateBased) obj;
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
            ((this.UCPTdescription==null && other.getUCPTdescription()==null) || 
             (this.UCPTdescription!=null &&
              this.UCPTdescription.equals(other.getUCPTdescription()))) &&
            this.UCPTpriority == other.getUCPTpriority() &&
            ((this.event==null && other.getEvent()==null) || 
             (this.event!=null &&
              java.util.Arrays.equals(this.event, other.getEvent()))) &&
            ((this.exception==null && other.getException()==null) || 
             (this.exception!=null &&
              java.util.Arrays.equals(this.exception, other.getException())));
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
        if (getUCPTdescription() != null) {
            _hashCode += getUCPTdescription().hashCode();
        }
        _hashCode += getUCPTpriority();
        if (getEvent() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEvent());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEvent(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getException() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getException());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getException(), i);
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
        new org.apache.axis.description.TypeDesc(UFPTscheduler_CfgDateBased.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTscheduler_Cfg>DateBased"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpriority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpriority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("event");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Event"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_CfgEvent"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exception");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Exception"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTscheduler_Cfg>DateBased>Exception"));
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
