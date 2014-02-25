/**
 * UFPTscheduler_CfgEvent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTscheduler_CfgEvent  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTindex;

    private org.apache.axis.types.Time UCPTtime;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTvalue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtimeDirection;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTbaseTimePath;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTeventType;

    public UFPTscheduler_CfgEvent() {
    }

    public UFPTscheduler_CfgEvent(
           java.lang.Integer UCPTindex,
           org.apache.axis.types.Time UCPTtime,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTvalue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtimeDirection,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTbaseTimePath,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTeventType) {
           this.UCPTindex = UCPTindex;
           this.UCPTtime = UCPTtime;
           this.UCPTvalue = UCPTvalue;
           this.UCPTtimeDirection = UCPTtimeDirection;
           this.UCPTbaseTimePath = UCPTbaseTimePath;
           this.UCPTeventType = UCPTeventType;
    }


    /**
     * Gets the UCPTindex value for this UFPTscheduler_CfgEvent.
     * 
     * @return UCPTindex
     */
    public java.lang.Integer getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this UFPTscheduler_CfgEvent.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(java.lang.Integer UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTtime value for this UFPTscheduler_CfgEvent.
     * 
     * @return UCPTtime
     */
    public org.apache.axis.types.Time getUCPTtime() {
        return UCPTtime;
    }


    /**
     * Sets the UCPTtime value for this UFPTscheduler_CfgEvent.
     * 
     * @param UCPTtime
     */
    public void setUCPTtime(org.apache.axis.types.Time UCPTtime) {
        this.UCPTtime = UCPTtime;
    }


    /**
     * Gets the UCPTvalue value for this UFPTscheduler_CfgEvent.
     * 
     * @return UCPTvalue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] getUCPTvalue() {
        return UCPTvalue;
    }


    /**
     * Sets the UCPTvalue value for this UFPTscheduler_CfgEvent.
     * 
     * @param UCPTvalue
     */
    public void setUCPTvalue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTvalue) {
        this.UCPTvalue = UCPTvalue;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTvalue(int i) {
        return this.UCPTvalue[i];
    }

    public void setUCPTvalue(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString _value) {
        this.UCPTvalue[i] = _value;
    }


    /**
     * Gets the UCPTtimeDirection value for this UFPTscheduler_CfgEvent.
     * 
     * @return UCPTtimeDirection
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTtimeDirection() {
        return UCPTtimeDirection;
    }


    /**
     * Sets the UCPTtimeDirection value for this UFPTscheduler_CfgEvent.
     * 
     * @param UCPTtimeDirection
     */
    public void setUCPTtimeDirection(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtimeDirection) {
        this.UCPTtimeDirection = UCPTtimeDirection;
    }


    /**
     * Gets the UCPTbaseTimePath value for this UFPTscheduler_CfgEvent.
     * 
     * @return UCPTbaseTimePath
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path getUCPTbaseTimePath() {
        return UCPTbaseTimePath;
    }


    /**
     * Sets the UCPTbaseTimePath value for this UFPTscheduler_CfgEvent.
     * 
     * @param UCPTbaseTimePath
     */
    public void setUCPTbaseTimePath(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTbaseTimePath) {
        this.UCPTbaseTimePath = UCPTbaseTimePath;
    }


    /**
     * Gets the UCPTeventType value for this UFPTscheduler_CfgEvent.
     * 
     * @return UCPTeventType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTeventType() {
        return UCPTeventType;
    }


    /**
     * Sets the UCPTeventType value for this UFPTscheduler_CfgEvent.
     * 
     * @param UCPTeventType
     */
    public void setUCPTeventType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTeventType) {
        this.UCPTeventType = UCPTeventType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTscheduler_CfgEvent)) return false;
        UFPTscheduler_CfgEvent other = (UFPTscheduler_CfgEvent) obj;
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
            ((this.UCPTtime==null && other.getUCPTtime()==null) || 
             (this.UCPTtime!=null &&
              this.UCPTtime.equals(other.getUCPTtime()))) &&
            ((this.UCPTvalue==null && other.getUCPTvalue()==null) || 
             (this.UCPTvalue!=null &&
              java.util.Arrays.equals(this.UCPTvalue, other.getUCPTvalue()))) &&
            ((this.UCPTtimeDirection==null && other.getUCPTtimeDirection()==null) || 
             (this.UCPTtimeDirection!=null &&
              this.UCPTtimeDirection.equals(other.getUCPTtimeDirection()))) &&
            ((this.UCPTbaseTimePath==null && other.getUCPTbaseTimePath()==null) || 
             (this.UCPTbaseTimePath!=null &&
              this.UCPTbaseTimePath.equals(other.getUCPTbaseTimePath()))) &&
            ((this.UCPTeventType==null && other.getUCPTeventType()==null) || 
             (this.UCPTeventType!=null &&
              this.UCPTeventType.equals(other.getUCPTeventType())));
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
        if (getUCPTtime() != null) {
            _hashCode += getUCPTtime().hashCode();
        }
        if (getUCPTvalue() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTvalue());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTvalue(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTtimeDirection() != null) {
            _hashCode += getUCPTtimeDirection().hashCode();
        }
        if (getUCPTbaseTimePath() != null) {
            _hashCode += getUCPTbaseTimePath().hashCode();
        }
        if (getUCPTeventType() != null) {
            _hashCode += getUCPTeventType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTscheduler_CfgEvent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_CfgEvent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTtime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "time"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTvalue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTvalue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTtimeDirection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtimeDirection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTbaseTimePath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTbaseTimePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Path"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTeventType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTeventType"));
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
