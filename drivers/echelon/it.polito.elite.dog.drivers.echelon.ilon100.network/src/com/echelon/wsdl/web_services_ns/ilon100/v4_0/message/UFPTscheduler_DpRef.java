/**
 * UFPTscheduler_DpRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTscheduler_DpRef  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Double SCPTdelayTime;

    public UFPTscheduler_DpRef() {
    }

    public UFPTscheduler_DpRef(
           java.lang.String dpType,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator discrim,
           java.lang.String UCPTname,
           java.lang.String UCPTformatDescription,
           java.lang.Double SCPTdelayTime) {
    	super(
        		UCPTname,
                UCPTformatDescription,
                dpType,
                discrim
            );
        this.SCPTdelayTime = SCPTdelayTime;
    }


    /**
     * Gets the SCPTdelayTime value for this UFPTscheduler_DpRef.
     * 
     * @return SCPTdelayTime
     */
    public java.lang.Double getSCPTdelayTime() {
        return SCPTdelayTime;
    }


    /**
     * Sets the SCPTdelayTime value for this UFPTscheduler_DpRef.
     * 
     * @param SCPTdelayTime
     */
    public void setSCPTdelayTime(java.lang.Double SCPTdelayTime) {
        this.SCPTdelayTime = SCPTdelayTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTscheduler_DpRef)) return false;
        UFPTscheduler_DpRef other = (UFPTscheduler_DpRef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
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
        if (getSCPTdelayTime() != null) {
            _hashCode += getSCPTdelayTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTscheduler_DpRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_DpRef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
