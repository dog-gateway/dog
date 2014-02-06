/**
 * Virtual_Dp_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Specialized data point configuration type.
 * 				Example: xSelect="//Item[@xsi:type="Virtual_Dp_Cfg"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class Virtual_Dp_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Cfg_Base  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle;

    private java.lang.Double UCPTpollRate;

    private java.lang.String UCPTprogrammaticName;

    public Virtual_Dp_Cfg() {
    }

    public Virtual_Dp_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           java.lang.String UCPTformatDescription,
           java.lang.Integer UCPTlength,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdirection,
           java.lang.Short UCPTpersist,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefOutput,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] UCPTunit,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle,
           java.lang.Double UCPTpollRate,
           java.lang.String UCPTprogrammaticName) {
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
            UCPTformatDescription,
            UCPTlength,
            UCPTdirection,
            UCPTpersist,
            UCPTdefOutput,
            UCPTunit);
        this.UCPThandle = UCPThandle;
        this.UCPTpollRate = UCPTpollRate;
        this.UCPTprogrammaticName = UCPTprogrammaticName;
    }


    /**
     * Gets the UCPThandle value for this Virtual_Dp_Cfg.
     * 
     * @return UCPThandle
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPThandle() {
        return UCPThandle;
    }


    /**
     * Sets the UCPThandle value for this Virtual_Dp_Cfg.
     * 
     * @param UCPThandle
     */
    public void setUCPThandle(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle) {
        this.UCPThandle = UCPThandle;
    }


    /**
     * Gets the UCPTpollRate value for this Virtual_Dp_Cfg.
     * 
     * @return UCPTpollRate
     */
    public java.lang.Double getUCPTpollRate() {
        return UCPTpollRate;
    }


    /**
     * Sets the UCPTpollRate value for this Virtual_Dp_Cfg.
     * 
     * @param UCPTpollRate
     */
    public void setUCPTpollRate(java.lang.Double UCPTpollRate) {
        this.UCPTpollRate = UCPTpollRate;
    }


    /**
     * Gets the UCPTprogrammaticName value for this Virtual_Dp_Cfg.
     * 
     * @return UCPTprogrammaticName
     */
    public java.lang.String getUCPTprogrammaticName() {
        return UCPTprogrammaticName;
    }


    /**
     * Sets the UCPTprogrammaticName value for this Virtual_Dp_Cfg.
     * 
     * @param UCPTprogrammaticName
     */
    public void setUCPTprogrammaticName(java.lang.String UCPTprogrammaticName) {
        this.UCPTprogrammaticName = UCPTprogrammaticName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Virtual_Dp_Cfg)) return false;
        Virtual_Dp_Cfg other = (Virtual_Dp_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPThandle==null && other.getUCPThandle()==null) || 
             (this.UCPThandle!=null &&
              this.UCPThandle.equals(other.getUCPThandle()))) &&
            ((this.UCPTpollRate==null && other.getUCPTpollRate()==null) || 
             (this.UCPTpollRate!=null &&
              this.UCPTpollRate.equals(other.getUCPTpollRate()))) &&
            ((this.UCPTprogrammaticName==null && other.getUCPTprogrammaticName()==null) || 
             (this.UCPTprogrammaticName!=null &&
              this.UCPTprogrammaticName.equals(other.getUCPTprogrammaticName())));
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
        if (getUCPThandle() != null) {
            _hashCode += getUCPThandle().hashCode();
        }
        if (getUCPTpollRate() != null) {
            _hashCode += getUCPTpollRate().hashCode();
        }
        if (getUCPTprogrammaticName() != null) {
            _hashCode += getUCPTprogrammaticName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Virtual_Dp_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Virtual_Dp_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThandle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThandle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_UniqueKey"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpollRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpollRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTprogrammaticName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTprogrammaticName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
