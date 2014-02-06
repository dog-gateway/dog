/**
 * UFPTscheduler_ListSchedules_InvokeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * List of all schedules which are active on specified day independent
 * of their priority.
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTscheduler_ListSchedules_InvokeResponse  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.util.Calendar UCPTstart;

    private java.util.Calendar UCPTstop;

    private java.lang.String[] UCPTeventSource;

    public UFPTscheduler_ListSchedules_InvokeResponse() {
    }

    public UFPTscheduler_ListSchedules_InvokeResponse(
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
           java.lang.String[] UCPTeventSource) {
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
        this.UCPTeventSource = UCPTeventSource;
    }


    /**
     * Gets the UCPTstart value for this UFPTscheduler_ListSchedules_InvokeResponse.
     * 
     * @return UCPTstart
     */
    public java.util.Calendar getUCPTstart() {
        return UCPTstart;
    }


    /**
     * Sets the UCPTstart value for this UFPTscheduler_ListSchedules_InvokeResponse.
     * 
     * @param UCPTstart
     */
    public void setUCPTstart(java.util.Calendar UCPTstart) {
        this.UCPTstart = UCPTstart;
    }


    /**
     * Gets the UCPTstop value for this UFPTscheduler_ListSchedules_InvokeResponse.
     * 
     * @return UCPTstop
     */
    public java.util.Calendar getUCPTstop() {
        return UCPTstop;
    }


    /**
     * Sets the UCPTstop value for this UFPTscheduler_ListSchedules_InvokeResponse.
     * 
     * @param UCPTstop
     */
    public void setUCPTstop(java.util.Calendar UCPTstop) {
        this.UCPTstop = UCPTstop;
    }


    /**
     * Gets the UCPTeventSource value for this UFPTscheduler_ListSchedules_InvokeResponse.
     * 
     * @return UCPTeventSource
     */
    public java.lang.String[] getUCPTeventSource() {
        return UCPTeventSource;
    }


    /**
     * Sets the UCPTeventSource value for this UFPTscheduler_ListSchedules_InvokeResponse.
     * 
     * @param UCPTeventSource
     */
    public void setUCPTeventSource(java.lang.String[] UCPTeventSource) {
        this.UCPTeventSource = UCPTeventSource;
    }

    public java.lang.String getUCPTeventSource(int i) {
        return this.UCPTeventSource[i];
    }

    public void setUCPTeventSource(int i, java.lang.String _value) {
        this.UCPTeventSource[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTscheduler_ListSchedules_InvokeResponse)) return false;
        UFPTscheduler_ListSchedules_InvokeResponse other = (UFPTscheduler_ListSchedules_InvokeResponse) obj;
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
            ((this.UCPTeventSource==null && other.getUCPTeventSource()==null) || 
             (this.UCPTeventSource!=null &&
              java.util.Arrays.equals(this.UCPTeventSource, other.getUCPTeventSource())));
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
        if (getUCPTeventSource() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTeventSource());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTeventSource(), i);
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
        new org.apache.axis.description.TypeDesc(UFPTscheduler_ListSchedules_InvokeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_ListSchedules_InvokeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstart");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstart"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstop");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstop"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTeventSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTeventSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
