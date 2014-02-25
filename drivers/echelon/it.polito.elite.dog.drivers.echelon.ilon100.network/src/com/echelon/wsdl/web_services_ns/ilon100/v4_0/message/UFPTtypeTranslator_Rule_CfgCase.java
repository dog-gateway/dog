/**
 * UFPTtypeTranslator_Rule_CfgCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class UFPTtypeTranslator_Rule_CfgCase  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.Integer UCPTindex;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTinputPath;

    private java.lang.String UCPTinputFieldName;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompFunction;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCaseRule[] rule;

    public UFPTtypeTranslator_Rule_CfgCase() {
    }

    public UFPTtypeTranslator_Rule_CfgCase(
           java.lang.Integer UCPTindex,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTinputPath,
           java.lang.String UCPTinputFieldName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompFunction,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCaseRule[] rule) {
           this.UCPTindex = UCPTindex;
           this.UCPTinputPath = UCPTinputPath;
           this.UCPTinputFieldName = UCPTinputFieldName;
           this.UCPTcompFunction = UCPTcompFunction;
           this.UCPTcompValue = UCPTcompValue;
           this.rule = rule;
    }


    /**
     * Gets the UCPTindex value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @return UCPTindex
     */
    public java.lang.Integer getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(java.lang.Integer UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTinputPath value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @return UCPTinputPath
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path getUCPTinputPath() {
        return UCPTinputPath;
    }


    /**
     * Sets the UCPTinputPath value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @param UCPTinputPath
     */
    public void setUCPTinputPath(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTinputPath) {
        this.UCPTinputPath = UCPTinputPath;
    }


    /**
     * Gets the UCPTinputFieldName value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @return UCPTinputFieldName
     */
    public java.lang.String getUCPTinputFieldName() {
        return UCPTinputFieldName;
    }


    /**
     * Sets the UCPTinputFieldName value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @param UCPTinputFieldName
     */
    public void setUCPTinputFieldName(java.lang.String UCPTinputFieldName) {
        this.UCPTinputFieldName = UCPTinputFieldName;
    }


    /**
     * Gets the UCPTcompFunction value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @return UCPTcompFunction
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTcompFunction() {
        return UCPTcompFunction;
    }


    /**
     * Sets the UCPTcompFunction value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @param UCPTcompFunction
     */
    public void setUCPTcompFunction(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompFunction) {
        this.UCPTcompFunction = UCPTcompFunction;
    }


    /**
     * Gets the UCPTcompValue value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @return UCPTcompValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTcompValue() {
        return UCPTcompValue;
    }


    /**
     * Sets the UCPTcompValue value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @param UCPTcompValue
     */
    public void setUCPTcompValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompValue) {
        this.UCPTcompValue = UCPTcompValue;
    }


    /**
     * Gets the rule value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @return rule
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCaseRule[] getRule() {
        return rule;
    }


    /**
     * Sets the rule value for this UFPTtypeTranslator_Rule_CfgCase.
     * 
     * @param rule
     */
    public void setRule(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCaseRule[] rule) {
        this.rule = rule;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCaseRule getRule(int i) {
        return this.rule[i];
    }

    public void setRule(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCaseRule _value) {
        this.rule[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTtypeTranslator_Rule_CfgCase)) return false;
        UFPTtypeTranslator_Rule_CfgCase other = (UFPTtypeTranslator_Rule_CfgCase) obj;
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
            ((this.UCPTinputPath==null && other.getUCPTinputPath()==null) || 
             (this.UCPTinputPath!=null &&
              this.UCPTinputPath.equals(other.getUCPTinputPath()))) &&
            ((this.UCPTinputFieldName==null && other.getUCPTinputFieldName()==null) || 
             (this.UCPTinputFieldName!=null &&
              this.UCPTinputFieldName.equals(other.getUCPTinputFieldName()))) &&
            ((this.UCPTcompFunction==null && other.getUCPTcompFunction()==null) || 
             (this.UCPTcompFunction!=null &&
              this.UCPTcompFunction.equals(other.getUCPTcompFunction()))) &&
            ((this.UCPTcompValue==null && other.getUCPTcompValue()==null) || 
             (this.UCPTcompValue!=null &&
              this.UCPTcompValue.equals(other.getUCPTcompValue()))) &&
            ((this.rule==null && other.getRule()==null) || 
             (this.rule!=null &&
              java.util.Arrays.equals(this.rule, other.getRule())));
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
        if (getUCPTinputPath() != null) {
            _hashCode += getUCPTinputPath().hashCode();
        }
        if (getUCPTinputFieldName() != null) {
            _hashCode += getUCPTinputFieldName().hashCode();
        }
        if (getUCPTcompFunction() != null) {
            _hashCode += getUCPTcompFunction().hashCode();
        }
        if (getUCPTcompValue() != null) {
            _hashCode += getUCPTcompValue().hashCode();
        }
        if (getRule() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRule());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRule(), i);
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
        new org.apache.axis.description.TypeDesc(UFPTtypeTranslator_Rule_CfgCase.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTtypeTranslator_Rule_Cfg>Case"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTinputPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTinputPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Path"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTinputFieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTinputFieldName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcompFunction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcompFunction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcompValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcompValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Rule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTtypeTranslator_Rule_Cfg>Case>Rule"));
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
