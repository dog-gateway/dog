/**
 * Item_CfgColl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * 'Item_CfgColl' is the generic collection of 'Item_Cfg'.
 * 				It is used for 'Get'-resp, 'Set'-req,
 */
@SuppressWarnings({"rawtypes","unused"})
public class Item_CfgColl  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_xSelect  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTfaultCount;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg[] item;

    public Item_CfgColl() {
    }

    public Item_CfgColl(
           java.lang.String xSelect,
           java.lang.Integer UCPTfaultCount,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg[] item) {
        super(
            xSelect);
        this.UCPTfaultCount = UCPTfaultCount;
        this.fault = fault;
        this.item = item;
    }


    /**
     * Gets the UCPTfaultCount value for this Item_CfgColl.
     * 
     * @return UCPTfaultCount
     */
    public java.lang.Integer getUCPTfaultCount() {
        return UCPTfaultCount;
    }


    /**
     * Sets the UCPTfaultCount value for this Item_CfgColl.
     * 
     * @param UCPTfaultCount
     */
    public void setUCPTfaultCount(java.lang.Integer UCPTfaultCount) {
        this.UCPTfaultCount = UCPTfaultCount;
    }


    /**
     * Gets the fault value for this Item_CfgColl.
     * 
     * @return fault
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault getFault() {
        return fault;
    }


    /**
     * Sets the fault value for this Item_CfgColl.
     * 
     * @param fault
     */
    public void setFault(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault) {
        this.fault = fault;
    }


    /**
     * Gets the item value for this Item_CfgColl.
     * 
     * @return item
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg[] getItem() {
        return item;
    }


    /**
     * Sets the item value for this Item_CfgColl.
     * 
     * @param item
     */
    public void setItem(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg[] item) {
        this.item = item;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg getItem(int i) {
        return this.item[i];
    }

    public void setItem(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg _value) {
        this.item[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Item_CfgColl)) return false;
        Item_CfgColl other = (Item_CfgColl) obj;
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
        new org.apache.axis.description.TypeDesc(Item_CfgColl.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_CfgColl"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Cfg"));
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
