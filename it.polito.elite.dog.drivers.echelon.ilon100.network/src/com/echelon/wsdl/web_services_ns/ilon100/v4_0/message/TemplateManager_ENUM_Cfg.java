/**
 * TemplateManager_ENUM_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Service configuration type of the service 'TemplateManager'.
 * 						Example:  xSelect="//Item[@xsi:type="TemplateManager_ENUM_Cfg"][UCPTprogramId="900001012881040C"][UCPTlanguage="enu"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_ENUM_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_ENUM_CfgMember[] member;

    public TemplateManager_ENUM_Cfg() {
    }

    public TemplateManager_ENUM_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_ENUM_CfgMember[] member) {
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
        this.member = member;
    }


    /**
     * Gets the member value for this TemplateManager_ENUM_Cfg.
     * 
     * @return member
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_ENUM_CfgMember[] getMember() {
        return member;
    }


    /**
     * Sets the member value for this TemplateManager_ENUM_Cfg.
     * 
     * @param member
     */
    public void setMember(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_ENUM_CfgMember[] member) {
        this.member = member;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_ENUM_CfgMember getMember(int i) {
        return this.member[i];
    }

    public void setMember(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_ENUM_CfgMember _value) {
        this.member[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_ENUM_Cfg)) return false;
        TemplateManager_ENUM_Cfg other = (TemplateManager_ENUM_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.member==null && other.getMember()==null) || 
             (this.member!=null &&
              java.util.Arrays.equals(this.member, other.getMember())));
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
        if (getMember() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMember());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMember(), i);
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
        new org.apache.axis.description.TypeDesc(TemplateManager_ENUM_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_ENUM_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("member");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Member"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_ENUM_Cfg>Member"));
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
