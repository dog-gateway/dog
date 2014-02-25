/**
 * Item.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Any further 'Item' type extends this generic type.
 */
@SuppressWarnings({"rawtypes","unused"})
public class Item  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault;

    private java.lang.String UCPTname;

    private java.lang.String UCPTannotation;

    private java.lang.Short UCPThidden;

    private java.lang.String UCPTaliasName;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus;

    public Item() {
    }

    public Item(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus) {
           this.fault = fault;
           this.UCPTname = UCPTname;
           this.UCPTannotation = UCPTannotation;
           this.UCPThidden = UCPThidden;
           this.UCPTaliasName = UCPTaliasName;
           this.UCPTitemStatus = UCPTitemStatus;
    }


    /**
     * Gets the fault value for this Item.
     * 
     * @return fault
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault getFault() {
        return fault;
    }


    /**
     * Sets the fault value for this Item.
     * 
     * @param fault
     */
    public void setFault(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault) {
        this.fault = fault;
    }


    /**
     * Gets the UCPTname value for this Item.
     * 
     * @return UCPTname
     */
    public java.lang.String getUCPTname() {
        return UCPTname;
    }


    /**
     * Sets the UCPTname value for this Item.
     * 
     * @param UCPTname
     */
    public void setUCPTname(java.lang.String UCPTname) {
        this.UCPTname = UCPTname;
    }


    /**
     * Gets the UCPTannotation value for this Item.
     * 
     * @return UCPTannotation
     */
    public java.lang.String getUCPTannotation() {
        return UCPTannotation;
    }


    /**
     * Sets the UCPTannotation value for this Item.
     * 
     * @param UCPTannotation
     */
    public void setUCPTannotation(java.lang.String UCPTannotation) {
        this.UCPTannotation = UCPTannotation;
    }


    /**
     * Gets the UCPThidden value for this Item.
     * 
     * @return UCPThidden
     */
    public java.lang.Short getUCPThidden() {
        return UCPThidden;
    }


    /**
     * Sets the UCPThidden value for this Item.
     * 
     * @param UCPThidden
     */
    public void setUCPThidden(java.lang.Short UCPThidden) {
        this.UCPThidden = UCPThidden;
    }


    /**
     * Gets the UCPTaliasName value for this Item.
     * 
     * @return UCPTaliasName
     */
    public java.lang.String getUCPTaliasName() {
        return UCPTaliasName;
    }


    /**
     * Sets the UCPTaliasName value for this Item.
     * 
     * @param UCPTaliasName
     */
    public void setUCPTaliasName(java.lang.String UCPTaliasName) {
        this.UCPTaliasName = UCPTaliasName;
    }


    /**
     * Gets the UCPTitemStatus value for this Item.
     * 
     * @return UCPTitemStatus
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTitemStatus() {
        return UCPTitemStatus;
    }


    /**
     * Sets the UCPTitemStatus value for this Item.
     * 
     * @param UCPTitemStatus
     */
    public void setUCPTitemStatus(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus) {
        this.UCPTitemStatus = UCPTitemStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Item)) return false;
        Item other = (Item) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fault==null && other.getFault()==null) || 
             (this.fault!=null &&
              this.fault.equals(other.getFault()))) &&
            ((this.UCPTname==null && other.getUCPTname()==null) || 
             (this.UCPTname!=null &&
              this.UCPTname.equals(other.getUCPTname()))) &&
            ((this.UCPTannotation==null && other.getUCPTannotation()==null) || 
             (this.UCPTannotation!=null &&
              this.UCPTannotation.equals(other.getUCPTannotation()))) &&
            ((this.UCPThidden==null && other.getUCPThidden()==null) || 
             (this.UCPThidden!=null &&
              this.UCPThidden.equals(other.getUCPThidden()))) &&
            ((this.UCPTaliasName==null && other.getUCPTaliasName()==null) || 
             (this.UCPTaliasName!=null &&
              this.UCPTaliasName.equals(other.getUCPTaliasName()))) &&
            ((this.UCPTitemStatus==null && other.getUCPTitemStatus()==null) || 
             (this.UCPTitemStatus!=null &&
              this.UCPTitemStatus.equals(other.getUCPTitemStatus())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getFault() != null) {
            _hashCode += getFault().hashCode();
        }
        if (getUCPTname() != null) {
            _hashCode += getUCPTname().hashCode();
        }
        if (getUCPTannotation() != null) {
            _hashCode += getUCPTannotation().hashCode();
        }
        if (getUCPThidden() != null) {
            _hashCode += getUCPThidden().hashCode();
        }
        if (getUCPTaliasName() != null) {
            _hashCode += getUCPTaliasName().hashCode();
        }
        if (getUCPTitemStatus() != null) {
            _hashCode += getUCPTitemStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Item.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fault");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "fault"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Fault"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTannotation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTannotation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThidden");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThidden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTaliasName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTaliasName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTitemStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTitemStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
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
