/**
 * UFPTalarmNotifier_CfgMail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTalarmNotifier_CfgMail  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTindex;

    private java.lang.String UCPTnickName;

    private java.lang.String UCPTemailAddress;

    private java.lang.String UCPTemailFormat;

    private java.lang.String UCPTemailSubject;

    private java.lang.String UCPTemailAttachment;

    public UFPTalarmNotifier_CfgMail() {
    }

    public UFPTalarmNotifier_CfgMail(
           java.lang.Integer UCPTindex,
           java.lang.String UCPTnickName,
           java.lang.String UCPTemailAddress,
           java.lang.String UCPTemailFormat,
           java.lang.String UCPTemailSubject,
           java.lang.String UCPTemailAttachment) {
           this.UCPTindex = UCPTindex;
           this.UCPTnickName = UCPTnickName;
           this.UCPTemailAddress = UCPTemailAddress;
           this.UCPTemailFormat = UCPTemailFormat;
           this.UCPTemailSubject = UCPTemailSubject;
           this.UCPTemailAttachment = UCPTemailAttachment;
    }


    /**
     * Gets the UCPTindex value for this UFPTalarmNotifier_CfgMail.
     * 
     * @return UCPTindex
     */
    public java.lang.Integer getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this UFPTalarmNotifier_CfgMail.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(java.lang.Integer UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTnickName value for this UFPTalarmNotifier_CfgMail.
     * 
     * @return UCPTnickName
     */
    public java.lang.String getUCPTnickName() {
        return UCPTnickName;
    }


    /**
     * Sets the UCPTnickName value for this UFPTalarmNotifier_CfgMail.
     * 
     * @param UCPTnickName
     */
    public void setUCPTnickName(java.lang.String UCPTnickName) {
        this.UCPTnickName = UCPTnickName;
    }


    /**
     * Gets the UCPTemailAddress value for this UFPTalarmNotifier_CfgMail.
     * 
     * @return UCPTemailAddress
     */
    public java.lang.String getUCPTemailAddress() {
        return UCPTemailAddress;
    }


    /**
     * Sets the UCPTemailAddress value for this UFPTalarmNotifier_CfgMail.
     * 
     * @param UCPTemailAddress
     */
    public void setUCPTemailAddress(java.lang.String UCPTemailAddress) {
        this.UCPTemailAddress = UCPTemailAddress;
    }


    /**
     * Gets the UCPTemailFormat value for this UFPTalarmNotifier_CfgMail.
     * 
     * @return UCPTemailFormat
     */
    public java.lang.String getUCPTemailFormat() {
        return UCPTemailFormat;
    }


    /**
     * Sets the UCPTemailFormat value for this UFPTalarmNotifier_CfgMail.
     * 
     * @param UCPTemailFormat
     */
    public void setUCPTemailFormat(java.lang.String UCPTemailFormat) {
        this.UCPTemailFormat = UCPTemailFormat;
    }


    /**
     * Gets the UCPTemailSubject value for this UFPTalarmNotifier_CfgMail.
     * 
     * @return UCPTemailSubject
     */
    public java.lang.String getUCPTemailSubject() {
        return UCPTemailSubject;
    }


    /**
     * Sets the UCPTemailSubject value for this UFPTalarmNotifier_CfgMail.
     * 
     * @param UCPTemailSubject
     */
    public void setUCPTemailSubject(java.lang.String UCPTemailSubject) {
        this.UCPTemailSubject = UCPTemailSubject;
    }


    /**
     * Gets the UCPTemailAttachment value for this UFPTalarmNotifier_CfgMail.
     * 
     * @return UCPTemailAttachment
     */
    public java.lang.String getUCPTemailAttachment() {
        return UCPTemailAttachment;
    }


    /**
     * Sets the UCPTemailAttachment value for this UFPTalarmNotifier_CfgMail.
     * 
     * @param UCPTemailAttachment
     */
    public void setUCPTemailAttachment(java.lang.String UCPTemailAttachment) {
        this.UCPTemailAttachment = UCPTemailAttachment;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTalarmNotifier_CfgMail)) return false;
        UFPTalarmNotifier_CfgMail other = (UFPTalarmNotifier_CfgMail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTindex==null && other.getUCPTindex()==null) || 
             (this.UCPTindex!=null &&
              this.UCPTindex.equals(other.getUCPTindex()))) &&
            ((this.UCPTnickName==null && other.getUCPTnickName()==null) || 
             (this.UCPTnickName!=null &&
              this.UCPTnickName.equals(other.getUCPTnickName()))) &&
            ((this.UCPTemailAddress==null && other.getUCPTemailAddress()==null) || 
             (this.UCPTemailAddress!=null &&
              this.UCPTemailAddress.equals(other.getUCPTemailAddress()))) &&
            ((this.UCPTemailFormat==null && other.getUCPTemailFormat()==null) || 
             (this.UCPTemailFormat!=null &&
              this.UCPTemailFormat.equals(other.getUCPTemailFormat()))) &&
            ((this.UCPTemailSubject==null && other.getUCPTemailSubject()==null) || 
             (this.UCPTemailSubject!=null &&
              this.UCPTemailSubject.equals(other.getUCPTemailSubject()))) &&
            ((this.UCPTemailAttachment==null && other.getUCPTemailAttachment()==null) || 
             (this.UCPTemailAttachment!=null &&
              this.UCPTemailAttachment.equals(other.getUCPTemailAttachment())));
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
        if (getUCPTindex() != null) {
            _hashCode += getUCPTindex().hashCode();
        }
        if (getUCPTnickName() != null) {
            _hashCode += getUCPTnickName().hashCode();
        }
        if (getUCPTemailAddress() != null) {
            _hashCode += getUCPTemailAddress().hashCode();
        }
        if (getUCPTemailFormat() != null) {
            _hashCode += getUCPTemailFormat().hashCode();
        }
        if (getUCPTemailSubject() != null) {
            _hashCode += getUCPTemailSubject().hashCode();
        }
        if (getUCPTemailAttachment() != null) {
            _hashCode += getUCPTemailAttachment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTalarmNotifier_CfgMail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Cfg>Mail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTnickName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTnickName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTemailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTemailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTemailFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTemailFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTemailSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTemailSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTemailAttachment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTemailAttachment"));
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
