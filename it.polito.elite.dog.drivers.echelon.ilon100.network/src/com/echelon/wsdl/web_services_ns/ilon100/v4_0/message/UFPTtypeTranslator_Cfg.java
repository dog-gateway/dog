/**
 * UFPTtypeTranslator_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized configuration type of the 'UFPTtypeTranslator'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTtypeTranslator_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="UFPTtypeTranslator_Cfg"][starts-with(UCPTname,'Net/LON/BAS/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTtypeTranslator_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTtranslatorRule;

    private java.lang.Double SCPTdelayTime;

    public UFPTtypeTranslator_Cfg() {
    }

    public UFPTtypeTranslator_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef[] dataPoint,
           java.lang.String UCPTtranslatorRule,
           java.lang.Double SCPTdelayTime) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus,
            UCPTlastUpdate,
            UCPTdescription,
            UCPTuri,
            dataPoint);
        this.UCPTtranslatorRule = UCPTtranslatorRule;
        this.SCPTdelayTime = SCPTdelayTime;
    }


    /**
     * Gets the UCPTtranslatorRule value for this UFPTtypeTranslator_Cfg.
     * 
     * @return UCPTtranslatorRule
     */
    public java.lang.String getUCPTtranslatorRule() {
        return UCPTtranslatorRule;
    }


    /**
     * Sets the UCPTtranslatorRule value for this UFPTtypeTranslator_Cfg.
     * 
     * @param UCPTtranslatorRule
     */
    public void setUCPTtranslatorRule(java.lang.String UCPTtranslatorRule) {
        this.UCPTtranslatorRule = UCPTtranslatorRule;
    }


    /**
     * Gets the SCPTdelayTime value for this UFPTtypeTranslator_Cfg.
     * 
     * @return SCPTdelayTime
     */
    public java.lang.Double getSCPTdelayTime() {
        return SCPTdelayTime;
    }


    /**
     * Sets the SCPTdelayTime value for this UFPTtypeTranslator_Cfg.
     * 
     * @param SCPTdelayTime
     */
    public void setSCPTdelayTime(java.lang.Double SCPTdelayTime) {
        this.SCPTdelayTime = SCPTdelayTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTtypeTranslator_Cfg)) return false;
        UFPTtypeTranslator_Cfg other = (UFPTtypeTranslator_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTtranslatorRule==null && other.getUCPTtranslatorRule()==null) || 
             (this.UCPTtranslatorRule!=null &&
              this.UCPTtranslatorRule.equals(other.getUCPTtranslatorRule()))) &&
            ((this.SCPTdelayTime==null && other.getSCPTdelayTime()==null) || 
             (this.SCPTdelayTime!=null &&
              this.SCPTdelayTime.equals(other.getSCPTdelayTime())));
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
        if (getUCPTtranslatorRule() != null) {
            _hashCode += getUCPTtranslatorRule().hashCode();
        }
        if (getSCPTdelayTime() != null) {
            _hashCode += getSCPTdelayTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTtypeTranslator_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTtypeTranslator_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTtranslatorRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtranslatorRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPTdelayTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPTdelayTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
