/**
 * UFPTalarmNotifier_CfgAlarm.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTalarmNotifier_CfgAlarm  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTindex;

    private java.lang.Integer UCPTlevel;

    private java.lang.String UCPTalarmText;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTalarmCondition;

    public UFPTalarmNotifier_CfgAlarm() {
    }

    public UFPTalarmNotifier_CfgAlarm(
           java.lang.Integer UCPTindex,
           java.lang.Integer UCPTlevel,
           java.lang.String UCPTalarmText,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTalarmCondition) {
           this.UCPTindex = UCPTindex;
           this.UCPTlevel = UCPTlevel;
           this.UCPTalarmText = UCPTalarmText;
           this.UCPTalarmCondition = UCPTalarmCondition;
    }


    /**
     * Gets the UCPTindex value for this UFPTalarmNotifier_CfgAlarm.
     * 
     * @return UCPTindex
     */
    public java.lang.Integer getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this UFPTalarmNotifier_CfgAlarm.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(java.lang.Integer UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTlevel value for this UFPTalarmNotifier_CfgAlarm.
     * 
     * @return UCPTlevel
     */
    public java.lang.Integer getUCPTlevel() {
        return UCPTlevel;
    }


    /**
     * Sets the UCPTlevel value for this UFPTalarmNotifier_CfgAlarm.
     * 
     * @param UCPTlevel
     */
    public void setUCPTlevel(java.lang.Integer UCPTlevel) {
        this.UCPTlevel = UCPTlevel;
    }


    /**
     * Gets the UCPTalarmText value for this UFPTalarmNotifier_CfgAlarm.
     * 
     * @return UCPTalarmText
     */
    public java.lang.String getUCPTalarmText() {
        return UCPTalarmText;
    }


    /**
     * Sets the UCPTalarmText value for this UFPTalarmNotifier_CfgAlarm.
     * 
     * @param UCPTalarmText
     */
    public void setUCPTalarmText(java.lang.String UCPTalarmText) {
        this.UCPTalarmText = UCPTalarmText;
    }


    /**
     * Gets the UCPTalarmCondition value for this UFPTalarmNotifier_CfgAlarm.
     * 
     * @return UCPTalarmCondition
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] getUCPTalarmCondition() {
        return UCPTalarmCondition;
    }


    /**
     * Sets the UCPTalarmCondition value for this UFPTalarmNotifier_CfgAlarm.
     * 
     * @param UCPTalarmCondition
     */
    public void setUCPTalarmCondition(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTalarmCondition) {
        this.UCPTalarmCondition = UCPTalarmCondition;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTalarmCondition(int i) {
        return this.UCPTalarmCondition[i];
    }

    public void setUCPTalarmCondition(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString _value) {
        this.UCPTalarmCondition[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTalarmNotifier_CfgAlarm)) return false;
        UFPTalarmNotifier_CfgAlarm other = (UFPTalarmNotifier_CfgAlarm) obj;
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
            ((this.UCPTlevel==null && other.getUCPTlevel()==null) || 
             (this.UCPTlevel!=null &&
              this.UCPTlevel.equals(other.getUCPTlevel()))) &&
            ((this.UCPTalarmText==null && other.getUCPTalarmText()==null) || 
             (this.UCPTalarmText!=null &&
              this.UCPTalarmText.equals(other.getUCPTalarmText()))) &&
            ((this.UCPTalarmCondition==null && other.getUCPTalarmCondition()==null) || 
             (this.UCPTalarmCondition!=null &&
              java.util.Arrays.equals(this.UCPTalarmCondition, other.getUCPTalarmCondition())));
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
        if (getUCPTlevel() != null) {
            _hashCode += getUCPTlevel().hashCode();
        }
        if (getUCPTalarmText() != null) {
            _hashCode += getUCPTalarmText().hashCode();
        }
        if (getUCPTalarmCondition() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTalarmCondition());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTalarmCondition(), i);
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
        new org.apache.axis.description.TypeDesc(UFPTalarmNotifier_CfgAlarm.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_CfgAlarm"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
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
