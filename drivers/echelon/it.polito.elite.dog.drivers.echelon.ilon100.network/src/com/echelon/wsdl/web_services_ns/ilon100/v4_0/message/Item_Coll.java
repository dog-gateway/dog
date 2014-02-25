/**
 * Item_Coll.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * 'Item_Coll' is the generic collection of 'Item'.
 * 				It is used for 'List'-resp, 'Get'-req, 'Set'-resp, 'Delete'-req/-resp,
 * 'Read'-req, 'Write'-resp, 'Clear'-req/-resp, 'InvokeCmd'-req/-resp
 */
@SuppressWarnings({"rawtypes","unused"})
public class Item_Coll  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_xSelect  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTfaultCount;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault;

    private java.lang.String UCPTcurrentConfig;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item[] item;

    public Item_Coll() {
    }

    public Item_Coll(
           java.lang.String xSelect,
           java.lang.Integer UCPTfaultCount,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTcurrentConfig,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item[] item) {
        super(
            xSelect);
        this.UCPTfaultCount = UCPTfaultCount;
        this.fault = fault;
        this.UCPTcurrentConfig = UCPTcurrentConfig;
        this.item = item;
    }


    /**
     * Gets the UCPTfaultCount value for this Item_Coll.
     * 
     * @return UCPTfaultCount
     */
    public java.lang.Integer getUCPTfaultCount() {
        return UCPTfaultCount;
    }


    /**
     * Sets the UCPTfaultCount value for this Item_Coll.
     * 
     * @param UCPTfaultCount
     */
    public void setUCPTfaultCount(java.lang.Integer UCPTfaultCount) {
        this.UCPTfaultCount = UCPTfaultCount;
    }


    /**
     * Gets the fault value for this Item_Coll.
     * 
     * @return fault
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault getFault() {
        return fault;
    }


    /**
     * Sets the fault value for this Item_Coll.
     * 
     * @param fault
     */
    public void setFault(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault) {
        this.fault = fault;
    }


    /**
     * Gets the UCPTcurrentConfig value for this Item_Coll.
     * 
     * @return UCPTcurrentConfig
     */
    public java.lang.String getUCPTcurrentConfig() {
        return UCPTcurrentConfig;
    }


    /**
     * Sets the UCPTcurrentConfig value for this Item_Coll.
     * 
     * @param UCPTcurrentConfig
     */
    public void setUCPTcurrentConfig(java.lang.String UCPTcurrentConfig) {
        this.UCPTcurrentConfig = UCPTcurrentConfig;
    }


    /**
     * Gets the item value for this Item_Coll.
     * 
     * @return item
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item[] getItem() {
        return item;
    }


    /**
     * Sets the item value for this Item_Coll.
     * 
     * @param item
     */
    public void setItem(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item[] item) {
        this.item = item;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item getItem(int i) {
        return this.item[i];
    }

    public void setItem(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item _value) {
        this.item[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Item_Coll)) return false;
        Item_Coll other = (Item_Coll) obj;
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
            ((this.UCPTcurrentConfig==null && other.getUCPTcurrentConfig()==null) || 
             (this.UCPTcurrentConfig!=null &&
              this.UCPTcurrentConfig.equals(other.getUCPTcurrentConfig()))) &&
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
        if (getUCPTcurrentConfig() != null) {
            _hashCode += getUCPTcurrentConfig().hashCode();
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
        new org.apache.axis.description.TypeDesc(Item_Coll.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"));
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
        elemField.setFieldName("UCPTcurrentConfig");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcurrentConfig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item"));
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
