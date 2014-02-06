/**
 * Item_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Extended 'Item' service responses
 */
@SuppressWarnings({"rawtypes","unused"})
public class Item_Service  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.util.Calendar UCPTlastUpdate;

    private java.lang.String UCPTdescription;

    private java.lang.Integer UCPTfileSize;

    public Item_Service() {
    }

    public Item_Service(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.Integer UCPTfileSize) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus);
        this.UCPTlastUpdate = UCPTlastUpdate;
        this.UCPTdescription = UCPTdescription;
        this.UCPTfileSize = UCPTfileSize;
    }


    /**
     * Gets the UCPTlastUpdate value for this Item_Service.
     * 
     * @return UCPTlastUpdate
     */
    public java.util.Calendar getUCPTlastUpdate() {
        return UCPTlastUpdate;
    }


    /**
     * Sets the UCPTlastUpdate value for this Item_Service.
     * 
     * @param UCPTlastUpdate
     */
    public void setUCPTlastUpdate(java.util.Calendar UCPTlastUpdate) {
        this.UCPTlastUpdate = UCPTlastUpdate;
    }


    /**
     * Gets the UCPTdescription value for this Item_Service.
     * 
     * @return UCPTdescription
     */
    public java.lang.String getUCPTdescription() {
        return UCPTdescription;
    }


    /**
     * Sets the UCPTdescription value for this Item_Service.
     * 
     * @param UCPTdescription
     */
    public void setUCPTdescription(java.lang.String UCPTdescription) {
        this.UCPTdescription = UCPTdescription;
    }


    /**
     * Gets the UCPTfileSize value for this Item_Service.
     * 
     * @return UCPTfileSize
     */
    public java.lang.Integer getUCPTfileSize() {
        return UCPTfileSize;
    }


    /**
     * Sets the UCPTfileSize value for this Item_Service.
     * 
     * @param UCPTfileSize
     */
    public void setUCPTfileSize(java.lang.Integer UCPTfileSize) {
        this.UCPTfileSize = UCPTfileSize;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Item_Service)) return false;
        Item_Service other = (Item_Service) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTlastUpdate==null && other.getUCPTlastUpdate()==null) || 
             (this.UCPTlastUpdate!=null &&
              this.UCPTlastUpdate.equals(other.getUCPTlastUpdate()))) &&
            ((this.UCPTdescription==null && other.getUCPTdescription()==null) || 
             (this.UCPTdescription!=null &&
              this.UCPTdescription.equals(other.getUCPTdescription()))) &&
            ((this.UCPTfileSize==null && other.getUCPTfileSize()==null) || 
             (this.UCPTfileSize!=null &&
              this.UCPTfileSize.equals(other.getUCPTfileSize())));
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
        if (getUCPTlastUpdate() != null) {
            _hashCode += getUCPTlastUpdate().hashCode();
        }
        if (getUCPTdescription() != null) {
            _hashCode += getUCPTdescription().hashCode();
        }
        if (getUCPTfileSize() != null) {
            _hashCode += getUCPTfileSize().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Item_Service.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Service"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlastUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlastUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfileSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfileSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
