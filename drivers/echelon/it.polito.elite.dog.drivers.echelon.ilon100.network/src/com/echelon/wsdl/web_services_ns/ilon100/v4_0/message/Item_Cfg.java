/**
 * Item_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * 'Item_Cfg' is the generic configuration type (Get-resp/Set-req).
 */
@SuppressWarnings({"rawtypes","unused"})
public class Item_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.util.Calendar UCPTlastUpdate;

    private java.lang.String UCPTdescription;

    private java.lang.String UCPTuri;

    public Item_Cfg() {
    }

    public Item_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus);
        this.UCPTlastUpdate = UCPTlastUpdate;
        this.UCPTdescription = UCPTdescription;
        this.UCPTuri = UCPTuri;
    }


    /**
     * Gets the UCPTlastUpdate value for this Item_Cfg.
     * 
     * @return UCPTlastUpdate
     */
    public java.util.Calendar getUCPTlastUpdate() {
        return UCPTlastUpdate;
    }


    /**
     * Sets the UCPTlastUpdate value for this Item_Cfg.
     * 
     * @param UCPTlastUpdate
     */
    public void setUCPTlastUpdate(java.util.Calendar UCPTlastUpdate) {
        this.UCPTlastUpdate = UCPTlastUpdate;
    }


    /**
     * Gets the UCPTdescription value for this Item_Cfg.
     * 
     * @return UCPTdescription
     */
    public java.lang.String getUCPTdescription() {
        return UCPTdescription;
    }


    /**
     * Sets the UCPTdescription value for this Item_Cfg.
     * 
     * @param UCPTdescription
     */
    public void setUCPTdescription(java.lang.String UCPTdescription) {
        this.UCPTdescription = UCPTdescription;
    }


    /**
     * Gets the UCPTuri value for this Item_Cfg.
     * 
     * @return UCPTuri
     */
    public java.lang.String getUCPTuri() {
        return UCPTuri;
    }


    /**
     * Sets the UCPTuri value for this Item_Cfg.
     * 
     * @param UCPTuri
     */
    public void setUCPTuri(java.lang.String UCPTuri) {
        this.UCPTuri = UCPTuri;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Item_Cfg)) return false;
        Item_Cfg other = (Item_Cfg) obj;
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
            ((this.UCPTuri==null && other.getUCPTuri()==null) || 
             (this.UCPTuri!=null &&
              this.UCPTuri.equals(other.getUCPTuri())));
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
        if (getUCPTuri() != null) {
            _hashCode += getUCPTuri().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Item_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Cfg"));
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
        elemField.setFieldName("UCPTuri");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTuri"));
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
