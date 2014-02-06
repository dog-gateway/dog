/**
 * UFPTcalendar_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized configuration type of the 'UFPTcalendar'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTcalendar_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="UFPTcalendar_Cfg"][starts-with(UCPTname,'Net/LON/BAS/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTcalendar_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEffectivePeriod scheduleEffectivePeriod;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgException[] exception;

    public UFPTcalendar_Cfg() {
    }

    public UFPTcalendar_Cfg(
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
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEffectivePeriod scheduleEffectivePeriod,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgException[] exception) {
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
        this.scheduleEffectivePeriod = scheduleEffectivePeriod;
        this.exception = exception;
    }


    /**
     * Gets the scheduleEffectivePeriod value for this UFPTcalendar_Cfg.
     * 
     * @return scheduleEffectivePeriod
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEffectivePeriod getScheduleEffectivePeriod() {
        return scheduleEffectivePeriod;
    }


    /**
     * Sets the scheduleEffectivePeriod value for this UFPTcalendar_Cfg.
     * 
     * @param scheduleEffectivePeriod
     */
    public void setScheduleEffectivePeriod(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEffectivePeriod scheduleEffectivePeriod) {
        this.scheduleEffectivePeriod = scheduleEffectivePeriod;
    }


    /**
     * Gets the exception value for this UFPTcalendar_Cfg.
     * 
     * @return exception
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgException[] getException() {
        return exception;
    }


    /**
     * Sets the exception value for this UFPTcalendar_Cfg.
     * 
     * @param exception
     */
    public void setException(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgException[] exception) {
        this.exception = exception;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgException getException(int i) {
        return this.exception[i];
    }

    public void setException(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgException _value) {
        this.exception[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTcalendar_Cfg)) return false;
        UFPTcalendar_Cfg other = (UFPTcalendar_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.scheduleEffectivePeriod==null && other.getScheduleEffectivePeriod()==null) || 
             (this.scheduleEffectivePeriod!=null &&
              this.scheduleEffectivePeriod.equals(other.getScheduleEffectivePeriod()))) &&
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
        int _hashCode = super.hashCode();
        if (getScheduleEffectivePeriod() != null) {
            _hashCode += getScheduleEffectivePeriod().hashCode();
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
        new org.apache.axis.description.TypeDesc(UFPTcalendar_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTcalendar_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduleEffectivePeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "ScheduleEffectivePeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_CfgEffectivePeriod"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exception");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Exception"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTcalendar_Cfg>Exception"));
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
