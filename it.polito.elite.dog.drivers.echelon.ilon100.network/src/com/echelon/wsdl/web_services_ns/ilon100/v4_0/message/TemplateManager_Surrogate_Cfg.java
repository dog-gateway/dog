/**
 * TemplateManager_Surrogate_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * This type is a common surrogate for any Item with the 'Item_Cfg'-base.
 * 				The embedded item will be stored as a template in the 'TemplateManager'.
 * 				The storage path (directory + filename) is set in the 'UCPTname'.
 * 			
 * 				Service configuration common type of the service 'TemplateManager'.
 * 					Example:  xSelect="//Item[@xsi:type="TemplateManager_Surrogate_Cfg"][UCPTname="/root/config/software/templateManager/UFPTalarmGenerator.xml"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_Surrogate_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl embeddedItemColl;

    public TemplateManager_Surrogate_Cfg() {
    }

    public TemplateManager_Surrogate_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl embeddedItemColl) {
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
        this.embeddedItemColl = embeddedItemColl;
    }


    /**
     * Gets the embeddedItemColl value for this TemplateManager_Surrogate_Cfg.
     * 
     * @return embeddedItemColl
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl getEmbeddedItemColl() {
        return embeddedItemColl;
    }


    /**
     * Sets the embeddedItemColl value for this TemplateManager_Surrogate_Cfg.
     * 
     * @param embeddedItemColl
     */
    public void setEmbeddedItemColl(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl embeddedItemColl) {
        this.embeddedItemColl = embeddedItemColl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_Surrogate_Cfg)) return false;
        TemplateManager_Surrogate_Cfg other = (TemplateManager_Surrogate_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.embeddedItemColl==null && other.getEmbeddedItemColl()==null) || 
             (this.embeddedItemColl!=null &&
              this.embeddedItemColl.equals(other.getEmbeddedItemColl())));
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
        if (getEmbeddedItemColl() != null) {
            _hashCode += getEmbeddedItemColl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateManager_Surrogate_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_Surrogate_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("embeddedItemColl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "EmbeddedItemColl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_CfgColl"));
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
