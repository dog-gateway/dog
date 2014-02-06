/**
 * Dp_RefDataPoint.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class Dp_RefDataPoint  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTserviceType;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTservicePath;

    private java.lang.Integer UCPTpriority;

    private java.lang.Short UCPTpropagate;

    private java.lang.String UCPTattachmentFileName;

    public Dp_RefDataPoint() {
    }

    public Dp_RefDataPoint(
           java.lang.String dpType,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator discrim,
           java.lang.String UCPTname,
           java.lang.String UCPTformatDescription,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTserviceType,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTservicePath,
           java.lang.Integer UCPTpriority,
           java.lang.Short UCPTpropagate,
           java.lang.String UCPTattachmentFileName) {
        super(
        		UCPTname,
                UCPTformatDescription,
                dpType,
                discrim
            );
        this.UCPTserviceType = UCPTserviceType;
        this.UCPTservicePath = UCPTservicePath;
        this.UCPTpriority = UCPTpriority;
        this.UCPTpropagate = UCPTpropagate;
        this.UCPTattachmentFileName = UCPTattachmentFileName;
    }


    /**
     * Gets the UCPTserviceType value for this Dp_RefDataPoint.
     * 
     * @return UCPTserviceType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTserviceType() {
        return UCPTserviceType;
    }


    /**
     * Sets the UCPTserviceType value for this Dp_RefDataPoint.
     * 
     * @param UCPTserviceType
     */
    public void setUCPTserviceType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTserviceType) {
        this.UCPTserviceType = UCPTserviceType;
    }


    /**
     * Gets the UCPTservicePath value for this Dp_RefDataPoint.
     * 
     * @return UCPTservicePath
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path getUCPTservicePath() {
        return UCPTservicePath;
    }


    /**
     * Sets the UCPTservicePath value for this Dp_RefDataPoint.
     * 
     * @param UCPTservicePath
     */
    public void setUCPTservicePath(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTservicePath) {
        this.UCPTservicePath = UCPTservicePath;
    }


    /**
     * Gets the UCPTpriority value for this Dp_RefDataPoint.
     * 
     * @return UCPTpriority
     */
    public java.lang.Integer getUCPTpriority() {
        return UCPTpriority;
    }


    /**
     * Sets the UCPTpriority value for this Dp_RefDataPoint.
     * 
     * @param UCPTpriority
     */
    public void setUCPTpriority(java.lang.Integer UCPTpriority) {
        this.UCPTpriority = UCPTpriority;
    }


    /**
     * Gets the UCPTpropagate value for this Dp_RefDataPoint.
     * 
     * @return UCPTpropagate
     */
    public java.lang.Short getUCPTpropagate() {
        return UCPTpropagate;
    }


    /**
     * Sets the UCPTpropagate value for this Dp_RefDataPoint.
     * 
     * @param UCPTpropagate
     */
    public void setUCPTpropagate(java.lang.Short UCPTpropagate) {
        this.UCPTpropagate = UCPTpropagate;
    }


    /**
     * Gets the UCPTattachmentFileName value for this Dp_RefDataPoint.
     * 
     * @return UCPTattachmentFileName
     */
    public java.lang.String getUCPTattachmentFileName() {
        return UCPTattachmentFileName;
    }


    /**
     * Sets the UCPTattachmentFileName value for this Dp_RefDataPoint.
     * 
     * @param UCPTattachmentFileName
     */
    public void setUCPTattachmentFileName(java.lang.String UCPTattachmentFileName) {
        this.UCPTattachmentFileName = UCPTattachmentFileName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dp_RefDataPoint)) return false;
        Dp_RefDataPoint other = (Dp_RefDataPoint) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTserviceType==null && other.getUCPTserviceType()==null) || 
             (this.UCPTserviceType!=null &&
              this.UCPTserviceType.equals(other.getUCPTserviceType()))) &&
            ((this.UCPTservicePath==null && other.getUCPTservicePath()==null) || 
             (this.UCPTservicePath!=null &&
              this.UCPTservicePath.equals(other.getUCPTservicePath()))) &&
            ((this.UCPTpriority==null && other.getUCPTpriority()==null) || 
             (this.UCPTpriority!=null &&
              this.UCPTpriority.equals(other.getUCPTpriority()))) &&
            ((this.UCPTpropagate==null && other.getUCPTpropagate()==null) || 
             (this.UCPTpropagate!=null &&
              this.UCPTpropagate.equals(other.getUCPTpropagate()))) &&
            ((this.UCPTattachmentFileName==null && other.getUCPTattachmentFileName()==null) || 
             (this.UCPTattachmentFileName!=null &&
              this.UCPTattachmentFileName.equals(other.getUCPTattachmentFileName())));
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
        if (getUCPTserviceType() != null) {
            _hashCode += getUCPTserviceType().hashCode();
        }
        if (getUCPTservicePath() != null) {
            _hashCode += getUCPTservicePath().hashCode();
        }
        if (getUCPTpriority() != null) {
            _hashCode += getUCPTpriority().hashCode();
        }
        if (getUCPTpropagate() != null) {
            _hashCode += getUCPTpropagate().hashCode();
        }
        if (getUCPTattachmentFileName() != null) {
            _hashCode += getUCPTattachmentFileName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dp_RefDataPoint.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">Dp_Ref>DataPoint"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTserviceType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTserviceType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTservicePath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTservicePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Path"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpriority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpriority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpropagate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpropagate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTattachmentFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTattachmentFileName"));
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
