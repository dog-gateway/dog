/**
 * UFPTalarmNotifier_Input_DpRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTalarmNotifier_Input_DpRef  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_Input_DpRefAlarmFlags alarmFlags;

    private int UCPTalarmGroup;

    private int UCPTalarmPriority2;

    private java.lang.String UCPTdescription;

    public UFPTalarmNotifier_Input_DpRef() {
    }

    public UFPTalarmNotifier_Input_DpRef(
           java.lang.String dpType,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator discrim,
           java.lang.String UCPTname,
           java.lang.String UCPTformatDescription,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_Input_DpRefAlarmFlags alarmFlags,
           int UCPTalarmGroup,
           int UCPTalarmPriority2,
           java.lang.String UCPTdescription) {
    	super(
        		UCPTname,
                UCPTformatDescription,
                dpType,
                discrim
            );
        this.alarmFlags = alarmFlags;
        this.UCPTalarmGroup = UCPTalarmGroup;
        this.UCPTalarmPriority2 = UCPTalarmPriority2;
        this.UCPTdescription = UCPTdescription;
    }


    /**
     * Gets the alarmFlags value for this UFPTalarmNotifier_Input_DpRef.
     * 
     * @return alarmFlags
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_Input_DpRefAlarmFlags getAlarmFlags() {
        return alarmFlags;
    }


    /**
     * Sets the alarmFlags value for this UFPTalarmNotifier_Input_DpRef.
     * 
     * @param alarmFlags
     */
    public void setAlarmFlags(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_Input_DpRefAlarmFlags alarmFlags) {
        this.alarmFlags = alarmFlags;
    }


    /**
     * Gets the UCPTalarmGroup value for this UFPTalarmNotifier_Input_DpRef.
     * 
     * @return UCPTalarmGroup
     */
    public int getUCPTalarmGroup() {
        return UCPTalarmGroup;
    }


    /**
     * Sets the UCPTalarmGroup value for this UFPTalarmNotifier_Input_DpRef.
     * 
     * @param UCPTalarmGroup
     */
    public void setUCPTalarmGroup(int UCPTalarmGroup) {
        this.UCPTalarmGroup = UCPTalarmGroup;
    }


    /**
     * Gets the UCPTalarmPriority2 value for this UFPTalarmNotifier_Input_DpRef.
     * 
     * @return UCPTalarmPriority2
     */
    public int getUCPTalarmPriority2() {
        return UCPTalarmPriority2;
    }


    /**
     * Sets the UCPTalarmPriority2 value for this UFPTalarmNotifier_Input_DpRef.
     * 
     * @param UCPTalarmPriority2
     */
    public void setUCPTalarmPriority2(int UCPTalarmPriority2) {
        this.UCPTalarmPriority2 = UCPTalarmPriority2;
    }


    /**
     * Gets the UCPTdescription value for this UFPTalarmNotifier_Input_DpRef.
     * 
     * @return UCPTdescription
     */
    public java.lang.String getUCPTdescription() {
        return UCPTdescription;
    }


    /**
     * Sets the UCPTdescription value for this UFPTalarmNotifier_Input_DpRef.
     * 
     * @param UCPTdescription
     */
    public void setUCPTdescription(java.lang.String UCPTdescription) {
        this.UCPTdescription = UCPTdescription;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTalarmNotifier_Input_DpRef)) return false;
        UFPTalarmNotifier_Input_DpRef other = (UFPTalarmNotifier_Input_DpRef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.alarmFlags==null && other.getAlarmFlags()==null) || 
             (this.alarmFlags!=null &&
              this.alarmFlags.equals(other.getAlarmFlags()))) &&
            this.UCPTalarmGroup == other.getUCPTalarmGroup() &&
            this.UCPTalarmPriority2 == other.getUCPTalarmPriority2() &&
            ((this.UCPTdescription==null && other.getUCPTdescription()==null) || 
             (this.UCPTdescription!=null &&
              this.UCPTdescription.equals(other.getUCPTdescription())));
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
        if (getAlarmFlags() != null) {
            _hashCode += getAlarmFlags().hashCode();
        }
        _hashCode += getUCPTalarmGroup();
        _hashCode += getUCPTalarmPriority2();
        if (getUCPTdescription() != null) {
            _hashCode += getUCPTdescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTalarmNotifier_Input_DpRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_Input_DpRef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alarmFlags");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "AlarmFlags"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Input_DpRef>AlarmFlags"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmPriority2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmPriority2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
