/**
 * UFPTalarmNotifier_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized configuration type of the 'UFPTalarmNotifier'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTalarmNotifier_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="UFPTalarmNotifier_Cfg"][starts-with(UCPTname,'Net/LON/BAS/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTalarmNotifier_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Double SCPTdelayTime;

    private float UCPTsumLogSize;

    private float UCPThistLogSize;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogFormat;

    private java.lang.String UCPTsumLogFileName;

    private java.lang.String UCPThistLogFileName;

    private java.lang.Integer UCPTemailAggregTime;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgMail[] mail;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm[] activeAlarm;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm[] passiveAlarm;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDest[] alarmDest;

    public UFPTalarmNotifier_Cfg() {
    }

    public UFPTalarmNotifier_Cfg(
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
           java.lang.Double SCPTdelayTime,
           float UCPTsumLogSize,
           float UCPThistLogSize,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogFormat,
           java.lang.String UCPTsumLogFileName,
           java.lang.String UCPThistLogFileName,
           java.lang.Integer UCPTemailAggregTime,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgMail[] mail,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm[] activeAlarm,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm[] passiveAlarm,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDest[] alarmDest) {
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
        this.SCPTdelayTime = SCPTdelayTime;
        this.UCPTsumLogSize = UCPTsumLogSize;
        this.UCPThistLogSize = UCPThistLogSize;
        this.UCPTlogFormat = UCPTlogFormat;
        this.UCPTsumLogFileName = UCPTsumLogFileName;
        this.UCPThistLogFileName = UCPThistLogFileName;
        this.UCPTemailAggregTime = UCPTemailAggregTime;
        this.mail = mail;
        this.activeAlarm = activeAlarm;
        this.passiveAlarm = passiveAlarm;
        this.alarmDest = alarmDest;
    }


    /**
     * Gets the SCPTdelayTime value for this UFPTalarmNotifier_Cfg.
     * 
     * @return SCPTdelayTime
     */
    public java.lang.Double getSCPTdelayTime() {
        return SCPTdelayTime;
    }


    /**
     * Sets the SCPTdelayTime value for this UFPTalarmNotifier_Cfg.
     * 
     * @param SCPTdelayTime
     */
    public void setSCPTdelayTime(java.lang.Double SCPTdelayTime) {
        this.SCPTdelayTime = SCPTdelayTime;
    }


    /**
     * Gets the UCPTsumLogSize value for this UFPTalarmNotifier_Cfg.
     * 
     * @return UCPTsumLogSize
     */
    public float getUCPTsumLogSize() {
        return UCPTsumLogSize;
    }


    /**
     * Sets the UCPTsumLogSize value for this UFPTalarmNotifier_Cfg.
     * 
     * @param UCPTsumLogSize
     */
    public void setUCPTsumLogSize(float UCPTsumLogSize) {
        this.UCPTsumLogSize = UCPTsumLogSize;
    }


    /**
     * Gets the UCPThistLogSize value for this UFPTalarmNotifier_Cfg.
     * 
     * @return UCPThistLogSize
     */
    public float getUCPThistLogSize() {
        return UCPThistLogSize;
    }


    /**
     * Sets the UCPThistLogSize value for this UFPTalarmNotifier_Cfg.
     * 
     * @param UCPThistLogSize
     */
    public void setUCPThistLogSize(float UCPThistLogSize) {
        this.UCPThistLogSize = UCPThistLogSize;
    }


    /**
     * Gets the UCPTlogFormat value for this UFPTalarmNotifier_Cfg.
     * 
     * @return UCPTlogFormat
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTlogFormat() {
        return UCPTlogFormat;
    }


    /**
     * Sets the UCPTlogFormat value for this UFPTalarmNotifier_Cfg.
     * 
     * @param UCPTlogFormat
     */
    public void setUCPTlogFormat(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlogFormat) {
        this.UCPTlogFormat = UCPTlogFormat;
    }


    /**
     * Gets the UCPTsumLogFileName value for this UFPTalarmNotifier_Cfg.
     * 
     * @return UCPTsumLogFileName
     */
    public java.lang.String getUCPTsumLogFileName() {
        return UCPTsumLogFileName;
    }


    /**
     * Sets the UCPTsumLogFileName value for this UFPTalarmNotifier_Cfg.
     * 
     * @param UCPTsumLogFileName
     */
    public void setUCPTsumLogFileName(java.lang.String UCPTsumLogFileName) {
        this.UCPTsumLogFileName = UCPTsumLogFileName;
    }


    /**
     * Gets the UCPThistLogFileName value for this UFPTalarmNotifier_Cfg.
     * 
     * @return UCPThistLogFileName
     */
    public java.lang.String getUCPThistLogFileName() {
        return UCPThistLogFileName;
    }


    /**
     * Sets the UCPThistLogFileName value for this UFPTalarmNotifier_Cfg.
     * 
     * @param UCPThistLogFileName
     */
    public void setUCPThistLogFileName(java.lang.String UCPThistLogFileName) {
        this.UCPThistLogFileName = UCPThistLogFileName;
    }


    /**
     * Gets the UCPTemailAggregTime value for this UFPTalarmNotifier_Cfg.
     * 
     * @return UCPTemailAggregTime
     */
    public java.lang.Integer getUCPTemailAggregTime() {
        return UCPTemailAggregTime;
    }


    /**
     * Sets the UCPTemailAggregTime value for this UFPTalarmNotifier_Cfg.
     * 
     * @param UCPTemailAggregTime
     */
    public void setUCPTemailAggregTime(java.lang.Integer UCPTemailAggregTime) {
        this.UCPTemailAggregTime = UCPTemailAggregTime;
    }


    /**
     * Gets the mail value for this UFPTalarmNotifier_Cfg.
     * 
     * @return mail
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgMail[] getMail() {
        return mail;
    }


    /**
     * Sets the mail value for this UFPTalarmNotifier_Cfg.
     * 
     * @param mail
     */
    public void setMail(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgMail[] mail) {
        this.mail = mail;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgMail getMail(int i) {
        return this.mail[i];
    }

    public void setMail(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgMail _value) {
        this.mail[i] = _value;
    }


    /**
     * Gets the activeAlarm value for this UFPTalarmNotifier_Cfg.
     * 
     * @return activeAlarm
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm[] getActiveAlarm() {
        return activeAlarm;
    }


    /**
     * Sets the activeAlarm value for this UFPTalarmNotifier_Cfg.
     * 
     * @param activeAlarm
     */
    public void setActiveAlarm(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm[] activeAlarm) {
        this.activeAlarm = activeAlarm;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm getActiveAlarm(int i) {
        return this.activeAlarm[i];
    }

    public void setActiveAlarm(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm _value) {
        this.activeAlarm[i] = _value;
    }


    /**
     * Gets the passiveAlarm value for this UFPTalarmNotifier_Cfg.
     * 
     * @return passiveAlarm
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm[] getPassiveAlarm() {
        return passiveAlarm;
    }


    /**
     * Sets the passiveAlarm value for this UFPTalarmNotifier_Cfg.
     * 
     * @param passiveAlarm
     */
    public void setPassiveAlarm(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm[] passiveAlarm) {
        this.passiveAlarm = passiveAlarm;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm getPassiveAlarm(int i) {
        return this.passiveAlarm[i];
    }

    public void setPassiveAlarm(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm _value) {
        this.passiveAlarm[i] = _value;
    }


    /**
     * Gets the alarmDest value for this UFPTalarmNotifier_Cfg.
     * 
     * @return alarmDest
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDest[] getAlarmDest() {
        return alarmDest;
    }


    /**
     * Sets the alarmDest value for this UFPTalarmNotifier_Cfg.
     * 
     * @param alarmDest
     */
    public void setAlarmDest(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDest[] alarmDest) {
        this.alarmDest = alarmDest;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDest getAlarmDest(int i) {
        return this.alarmDest[i];
    }

    public void setAlarmDest(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDest _value) {
        this.alarmDest[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTalarmNotifier_Cfg)) return false;
        UFPTalarmNotifier_Cfg other = (UFPTalarmNotifier_Cfg) obj;
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
              this.SCPTdelayTime.equals(other.getSCPTdelayTime()))) &&
            this.UCPTsumLogSize == other.getUCPTsumLogSize() &&
            this.UCPThistLogSize == other.getUCPThistLogSize() &&
            ((this.UCPTlogFormat==null && other.getUCPTlogFormat()==null) || 
             (this.UCPTlogFormat!=null &&
              this.UCPTlogFormat.equals(other.getUCPTlogFormat()))) &&
            ((this.UCPTsumLogFileName==null && other.getUCPTsumLogFileName()==null) || 
             (this.UCPTsumLogFileName!=null &&
              this.UCPTsumLogFileName.equals(other.getUCPTsumLogFileName()))) &&
            ((this.UCPThistLogFileName==null && other.getUCPThistLogFileName()==null) || 
             (this.UCPThistLogFileName!=null &&
              this.UCPThistLogFileName.equals(other.getUCPThistLogFileName()))) &&
            ((this.UCPTemailAggregTime==null && other.getUCPTemailAggregTime()==null) || 
             (this.UCPTemailAggregTime!=null &&
              this.UCPTemailAggregTime.equals(other.getUCPTemailAggregTime()))) &&
            ((this.mail==null && other.getMail()==null) || 
             (this.mail!=null &&
              java.util.Arrays.equals(this.mail, other.getMail()))) &&
            ((this.activeAlarm==null && other.getActiveAlarm()==null) || 
             (this.activeAlarm!=null &&
              java.util.Arrays.equals(this.activeAlarm, other.getActiveAlarm()))) &&
            ((this.passiveAlarm==null && other.getPassiveAlarm()==null) || 
             (this.passiveAlarm!=null &&
              java.util.Arrays.equals(this.passiveAlarm, other.getPassiveAlarm()))) &&
            ((this.alarmDest==null && other.getAlarmDest()==null) || 
             (this.alarmDest!=null &&
              java.util.Arrays.equals(this.alarmDest, other.getAlarmDest())));
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
        _hashCode += new Float(getUCPTsumLogSize()).hashCode();
        _hashCode += new Float(getUCPThistLogSize()).hashCode();
        if (getUCPTlogFormat() != null) {
            _hashCode += getUCPTlogFormat().hashCode();
        }
        if (getUCPTsumLogFileName() != null) {
            _hashCode += getUCPTsumLogFileName().hashCode();
        }
        if (getUCPThistLogFileName() != null) {
            _hashCode += getUCPThistLogFileName().hashCode();
        }
        if (getUCPTemailAggregTime() != null) {
            _hashCode += getUCPTemailAggregTime().hashCode();
        }
        if (getMail() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMail());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMail(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getActiveAlarm() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getActiveAlarm());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getActiveAlarm(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPassiveAlarm() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPassiveAlarm());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPassiveAlarm(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAlarmDest() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAlarmDest());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAlarmDest(), i);
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
        new org.apache.axis.description.TypeDesc(UFPTalarmNotifier_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPTdelayTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPTdelayTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsumLogSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsumLogSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThistLogSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThistLogSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlogFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlogFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTsumLogFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTsumLogFileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThistLogFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThistLogFileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTemailAggregTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTemailAggregTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Mail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Cfg>Mail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activeAlarm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "ActiveAlarm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_CfgAlarm"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passiveAlarm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "PassiveAlarm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_CfgAlarm"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alarmDest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "AlarmDest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Cfg>AlarmDest"));
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
