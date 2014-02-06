/**
 * UFPTtypeTranslator_Rule_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized configuration type of the 'UFPTtypeTranslator_Rule'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTtypeTranslator_Rule_Cfg"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTtypeTranslator_Rule_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgDataPointFormat[] dataPointFormat;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCase[] _case;

    public UFPTtypeTranslator_Rule_Cfg() {
    }

    public UFPTtypeTranslator_Rule_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgDataPointFormat[] dataPointFormat,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCase[] _case) {
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
        this.dataPointFormat = dataPointFormat;
        this._case = _case;
    }


    /**
     * Gets the dataPointFormat value for this UFPTtypeTranslator_Rule_Cfg.
     * 
     * @return dataPointFormat
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgDataPointFormat[] getDataPointFormat() {
        return dataPointFormat;
    }


    /**
     * Sets the dataPointFormat value for this UFPTtypeTranslator_Rule_Cfg.
     * 
     * @param dataPointFormat
     */
    public void setDataPointFormat(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgDataPointFormat[] dataPointFormat) {
        this.dataPointFormat = dataPointFormat;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgDataPointFormat getDataPointFormat(int i) {
        return this.dataPointFormat[i];
    }

    public void setDataPointFormat(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgDataPointFormat _value) {
        this.dataPointFormat[i] = _value;
    }


    /**
     * Gets the _case value for this UFPTtypeTranslator_Rule_Cfg.
     * 
     * @return _case
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCase[] get_case() {
        return _case;
    }


    /**
     * Sets the _case value for this UFPTtypeTranslator_Rule_Cfg.
     * 
     * @param _case
     */
    public void set_case(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCase[] _case) {
        this._case = _case;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCase get_case(int i) {
        return this._case[i];
    }

    public void set_case(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCase _value) {
        this._case[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTtypeTranslator_Rule_Cfg)) return false;
        UFPTtypeTranslator_Rule_Cfg other = (UFPTtypeTranslator_Rule_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.dataPointFormat==null && other.getDataPointFormat()==null) || 
             (this.dataPointFormat!=null &&
              java.util.Arrays.equals(this.dataPointFormat, other.getDataPointFormat()))) &&
            ((this._case==null && other.get_case()==null) || 
             (this._case!=null &&
              java.util.Arrays.equals(this._case, other.get_case())));
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
        if (getDataPointFormat() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDataPointFormat());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDataPointFormat(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (get_case() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(get_case());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(get_case(), i);
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
        new org.apache.axis.description.TypeDesc(UFPTtypeTranslator_Rule_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTtypeTranslator_Rule_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPointFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "DataPointFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTtypeTranslator_Rule_Cfg>DataPointFormat"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_case");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Case"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTtypeTranslator_Rule_Cfg>Case"));
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
