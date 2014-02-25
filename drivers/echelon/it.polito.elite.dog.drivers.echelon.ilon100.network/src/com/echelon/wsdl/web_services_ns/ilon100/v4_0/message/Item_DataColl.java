/**
 * Item_DataColl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * 'Item_DataColl' is the generic collection of 'Item_Data'.
 * 				It is used for 'Read'-resp, 'Write'-req,
 */
@SuppressWarnings({"rawtypes","unused"})
public class Item_DataColl  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_xSelect  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTfaultCount;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data[] item;

    public Item_DataColl() {
    }

    public Item_DataColl(
           java.lang.String xSelect,
           java.lang.Integer UCPTfaultCount,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data[] item) {
        super(
            xSelect);
        this.UCPTfaultCount = UCPTfaultCount;
        this.fault = fault;
        this.item = item;
    }


    /**
     * Gets the UCPTfaultCount value for this Item_DataColl.
     * 
     * @return UCPTfaultCount
     */
    public java.lang.Integer getUCPTfaultCount() {
        return UCPTfaultCount;
    }


    /**
     * Sets the UCPTfaultCount value for this Item_DataColl.
     * 
     * @param UCPTfaultCount
     */
    public void setUCPTfaultCount(java.lang.Integer UCPTfaultCount) {
        this.UCPTfaultCount = UCPTfaultCount;
    }


    /**
     * Gets the fault value for this Item_DataColl.
     * 
     * @return fault
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault getFault() {
        return fault;
    }


    /**
     * Sets the fault value for this Item_DataColl.
     * 
     * @param fault
     */
    public void setFault(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault) {
        this.fault = fault;
    }


    /**
     * Gets the item value for this Item_DataColl.
     * 
     * @return item
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data[] getItem() {
        return item;
    }


    /**
     * Sets the item value for this Item_DataColl.
     * 
     * @param item
     */
    public void setItem(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data[] item) {
        this.item = item;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data getItem(int i) {
        return this.item[i];
    }

    public void setItem(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data _value) {
        this.item[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Item_DataColl)) return false;
        Item_DataColl other = (Item_DataColl) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTfaultCount==null && other.getUCPTfaultCount()==null) || 
             (this.UCPTfaultCount!=null &&
              this.UCPTfaultCount.equals(other.getUCPTfaultCount()))) &&
            ((this.fault==null && other.getFault()==null) || 
             (this.fault!=null &&
              this.fault.equals(other.getFault()))) &&
            ((this.item==null && other.getItem()==null) || 
             (this.item!=null &&
              java.util.Arrays.equals(this.item, other.getItem())));
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
        if (getUCPTfaultCount() != null) {
            _hashCode += getUCPTfaultCount().hashCode();
        }
        if (getFault() != null) {
            _hashCode += getFault().hashCode();
        }
        if (getItem() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItem());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItem(), i);
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
        new org.apache.axis.description.TypeDesc(Item_DataColl.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_DataColl"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfaultCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfaultCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fault");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "fault"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Fault"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Data"));
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
