/**
 * UFPTscheduler_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized configuration type of the 'UFPTscheduler'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTscheduler_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="UFPTscheduler_Cfg"][starts-with(UCPTname,'Net/LON/BAS/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTscheduler_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEffectivePeriod scheduleEffectivePeriod;

    private java.lang.Short UCPTsuppressEventRecovery;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDayBased[] dayBased;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBased[] dateBased;

    public UFPTscheduler_Cfg() {
    }

    public UFPTscheduler_Cfg(
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
           java.lang.Short UCPTsuppressEventRecovery,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDayBased[] dayBased,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBased[] dateBased) {
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
        this.UCPTsuppressEventRecovery = UCPTsuppressEventRecovery;
        this.dayBased = dayBased;
        this.dateBased = dateBased;
    }


    /**
     * Gets the scheduleEffectivePeriod value for this UFPTscheduler_Cfg.
     * 
     * @return scheduleEffectivePeriod
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEffectivePeriod getScheduleEffectivePeriod() {
        return scheduleEffectivePeriod;
    }


    /**
     * Sets the scheduleEffectivePeriod value for this UFPTscheduler_Cfg.
     * 
     * @param scheduleEffectivePeriod
     */
    public void setScheduleEffectivePeriod(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEffectivePeriod scheduleEffectivePeriod) {
        this.scheduleEffectivePeriod = scheduleEffectivePeriod;
    }


    /**
     * Gets the UCPTsuppressEventRecovery value for this UFPTscheduler_Cfg.
     * 
     * @return UCPTsuppressEventRecovery
     */
    public java.lang.Short getUCPTsuppressEventRecovery() {
        return UCPTsuppressEventRecovery;
    }


    /**
     * Sets the UCPTsuppressEventRecovery value for this UFPTscheduler_Cfg.
     * 
     * @param UCPTsuppressEventRecovery
     */
    public void setUCPTsuppressEventRecovery(java.lang.Short UCPTsuppressEventRecovery) {
        this.UCPTsuppressEventRecovery = UCPTsuppressEventRecovery;
    }


    /**
     * Gets the dayBased value for this UFPTscheduler_Cfg.
     * 
     * @return dayBased
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDayBased[] getDayBased() {
        return dayBased;
    }


    /**
     * Sets the dayBased value for this UFPTscheduler_Cfg.
     * 
     * @param dayBased
     */
    public void setDayBased(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDayBased[] dayBased) {
        this.dayBased = dayBased;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDayBased getDayBased(int i) {
        return this.dayBased[i];
    }

    public void setDayBased(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDayBased _value) {
        this.dayBased[i] = _value;
    }


    /**
     * Gets the dateBased value for this UFPTscheduler_Cfg.
     * 
     * @return dateBased
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBased[] getDateBased() {
        return dateBased;
    }


    /**
     * Sets the dateBased value for this UFPTscheduler_Cfg.
     * 
     * @param dateBased
     */
    public void setDateBased(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBased[] dateBased) {
        this.dateBased = dateBased;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBased getDateBased(int i) {
        return this.dateBased[i];
    }

    public void setDateBased(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBased _value) {
        this.dateBased[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTscheduler_Cfg)) return false;
        UFPTscheduler_Cfg other = (UFPTscheduler_Cfg) obj;
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
            ((this.UCPTsuppressEventRecovery==null && other.getUCPTsuppressEventRecovery()==null) || 
             (this.UCPTsuppressEventRecovery!=null &&
              this.UCPTsuppressEventRecovery.equals(other.getUCPTsuppressEventRecovery()))) &&
            ((this.dayBased==null && other.getDayBased()==null) || 
             (this.dayBased!=null &&
              java.util.Arrays.equals(this.dayBased, other.getDayBased()))) &&
            ((this.dateBased==null && other.getDateBased()==null) || 
             (this.dateBased!=null &&
              java.util.Arrays.equals(this.dateBased, other.getDateBased())));
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
        if (getUCPTsuppressEventRecovery() != null) {
            _hashCode += getUCPTsuppressEventRecovery().hashCode();
        }
        if (getDayBased() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDayBased());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDayBased(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDateBased() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDateBased());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDateBased(), i);
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
        new org.apache.axis.description.TypeDesc(UFPTscheduler_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduleEffectivePeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "ScheduleEffectivePeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_CfgEffectivePeriod"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsuppressEventRecovery");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsuppressEventRecovery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dayBased");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "DayBased"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTscheduler_Cfg>DayBased"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateBased");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "DateBased"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTscheduler_Cfg>DateBased"));
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
