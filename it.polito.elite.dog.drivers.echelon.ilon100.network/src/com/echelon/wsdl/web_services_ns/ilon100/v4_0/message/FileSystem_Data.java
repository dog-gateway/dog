/**
 * FileSystem_Data.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Service data type of the service 'FileSystem'.
 * 					Example:  xSelect="//Item[@xsi:type="FileSystem_Data"][UCPTname="/root/alarmLog/histlog0.csv"][UCPTfileFormat="text"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class FileSystem_Data  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTfileFormat;

    private java.lang.Integer UCPTfileSize;

    private java.lang.String value;

    public FileSystem_Data() {
    }

    public FileSystem_Data(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTfileFormat,
           java.lang.Integer UCPTfileSize,
           java.lang.String value) {
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
        this.UCPTfileFormat = UCPTfileFormat;
        this.UCPTfileSize = UCPTfileSize;
        this.value = value;
    }


    /**
     * Gets the UCPTfileFormat value for this FileSystem_Data.
     * 
     * @return UCPTfileFormat
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTfileFormat() {
        return UCPTfileFormat;
    }


    /**
     * Sets the UCPTfileFormat value for this FileSystem_Data.
     * 
     * @param UCPTfileFormat
     */
    public void setUCPTfileFormat(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTfileFormat) {
        this.UCPTfileFormat = UCPTfileFormat;
    }


    /**
     * Gets the UCPTfileSize value for this FileSystem_Data.
     * 
     * @return UCPTfileSize
     */
    public java.lang.Integer getUCPTfileSize() {
        return UCPTfileSize;
    }


    /**
     * Sets the UCPTfileSize value for this FileSystem_Data.
     * 
     * @param UCPTfileSize
     */
    public void setUCPTfileSize(java.lang.Integer UCPTfileSize) {
        this.UCPTfileSize = UCPTfileSize;
    }


    /**
     * Gets the value value for this FileSystem_Data.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this FileSystem_Data.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FileSystem_Data)) return false;
        FileSystem_Data other = (FileSystem_Data) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTfileFormat==null && other.getUCPTfileFormat()==null) || 
             (this.UCPTfileFormat!=null &&
              this.UCPTfileFormat.equals(other.getUCPTfileFormat()))) &&
            ((this.UCPTfileSize==null && other.getUCPTfileSize()==null) || 
             (this.UCPTfileSize!=null &&
              this.UCPTfileSize.equals(other.getUCPTfileSize()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue())));
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
        if (getUCPTfileFormat() != null) {
            _hashCode += getUCPTfileFormat().hashCode();
        }
        if (getUCPTfileSize() != null) {
            _hashCode += getUCPTfileSize().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FileSystem_Data.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "FileSystem_Data"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfileFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfileFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfileSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfileSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Value"));
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
