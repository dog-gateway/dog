/**
 * UFPTcalendar_Data.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized data type of the 'UFPTcalendar'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTcalendar_Data"][UCPTlastUpdate>="2007-01-15T15:30:21Z"
 * and UCPTlastUpdate<"2007-02-11T15:30:21Z"][UCPTexceptionName="Holiday"][position()<500]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTcalendar_Data  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.util.Calendar UCPTstart;

    private java.util.Calendar UCPTstop;

    private java.lang.String[] UCPTexceptionName;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] dateEvent;

    public UFPTcalendar_Data() {
    }

    public UFPTcalendar_Data(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           java.util.Calendar UCPTstart,
           java.util.Calendar UCPTstop,
           java.lang.String[] UCPTexceptionName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] dateEvent) {
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
        this.UCPTstart = UCPTstart;
        this.UCPTstop = UCPTstop;
        this.UCPTexceptionName = UCPTexceptionName;
        this.dateEvent = dateEvent;
    }


    /**
     * Gets the UCPTstart value for this UFPTcalendar_Data.
     * 
     * @return UCPTstart
     */
    public java.util.Calendar getUCPTstart() {
        return UCPTstart;
    }


    /**
     * Sets the UCPTstart value for this UFPTcalendar_Data.
     * 
     * @param UCPTstart
     */
    public void setUCPTstart(java.util.Calendar UCPTstart) {
        this.UCPTstart = UCPTstart;
    }


    /**
     * Gets the UCPTstop value for this UFPTcalendar_Data.
     * 
     * @return UCPTstop
     */
    public java.util.Calendar getUCPTstop() {
        return UCPTstop;
    }


    /**
     * Sets the UCPTstop value for this UFPTcalendar_Data.
     * 
     * @param UCPTstop
     */
    public void setUCPTstop(java.util.Calendar UCPTstop) {
        this.UCPTstop = UCPTstop;
    }


    /**
     * Gets the UCPTexceptionName value for this UFPTcalendar_Data.
     * 
     * @return UCPTexceptionName
     */
    public java.lang.String[] getUCPTexceptionName() {
        return UCPTexceptionName;
    }


    /**
     * Sets the UCPTexceptionName value for this UFPTcalendar_Data.
     * 
     * @param UCPTexceptionName
     */
    public void setUCPTexceptionName(java.lang.String[] UCPTexceptionName) {
        this.UCPTexceptionName = UCPTexceptionName;
    }

    public java.lang.String getUCPTexceptionName(int i) {
        return this.UCPTexceptionName[i];
    }

    public void setUCPTexceptionName(int i, java.lang.String _value) {
        this.UCPTexceptionName[i] = _value;
    }


    /**
     * Gets the dateEvent value for this UFPTcalendar_Data.
     * 
     * @return dateEvent
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] getDateEvent() {
        return dateEvent;
    }


    /**
     * Sets the dateEvent value for this UFPTcalendar_Data.
     * 
     * @param dateEvent
     */
    public void setDateEvent(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] dateEvent) {
        this.dateEvent = dateEvent;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getDateEvent(int i) {
        return this.dateEvent[i];
    }

    public void setDateEvent(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString _value) {
        this.dateEvent[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTcalendar_Data)) return false;
        UFPTcalendar_Data other = (UFPTcalendar_Data) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTstart==null && other.getUCPTstart()==null) || 
             (this.UCPTstart!=null &&
              this.UCPTstart.equals(other.getUCPTstart()))) &&
            ((this.UCPTstop==null && other.getUCPTstop()==null) || 
             (this.UCPTstop!=null &&
              this.UCPTstop.equals(other.getUCPTstop()))) &&
            ((this.UCPTexceptionName==null && other.getUCPTexceptionName()==null) || 
             (this.UCPTexceptionName!=null &&
              java.util.Arrays.equals(this.UCPTexceptionName, other.getUCPTexceptionName()))) &&
            ((this.dateEvent==null && other.getDateEvent()==null) || 
             (this.dateEvent!=null &&
              java.util.Arrays.equals(this.dateEvent, other.getDateEvent())));
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
        if (getUCPTstart() != null) {
            _hashCode += getUCPTstart().hashCode();
        }
        if (getUCPTstop() != null) {
            _hashCode += getUCPTstop().hashCode();
        }
        if (getUCPTexceptionName() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTexceptionName());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTexceptionName(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDateEvent() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDateEvent());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDateEvent(), i);
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
        new org.apache.axis.description.TypeDesc(UFPTcalendar_Data.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTcalendar_Data"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstart");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstart"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstop");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstop"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTexceptionName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTexceptionName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateEvent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "DateEvent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
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
