/**
 * Dp_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized data point configuration type.
 * 					Example:  xSelect="//Item[@xsi:type="Dp_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="Dp_Cfg"][starts-with(UCPTname,'Net/LON/BAS/Alarm
 * Generator 1/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class Dp_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Cfg_Base  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTbaseType;

    private java.lang.Integer UCPTprecision;

    private java.lang.Integer UCPTmaxFields;

    private java.lang.Float SCPTmaxSendTime;

    private java.lang.Float SCPTminSendTime;

    private java.lang.Float SCPTmaxRcvTime;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTsndDelta;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTinvalidValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_CfgValueDef[] valueDef;

    public Dp_Cfg() {
    }

    public Dp_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           java.lang.String UCPTformatDescription,
           java.lang.Integer UCPTlength,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdirection,
           java.lang.Short UCPTpersist,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefOutput,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] UCPTunit,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTbaseType,
           java.lang.Integer UCPTprecision,
           java.lang.Integer UCPTmaxFields,
           java.lang.Float SCPTmaxSendTime,
           java.lang.Float SCPTminSendTime,
           java.lang.Float SCPTmaxRcvTime,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTsndDelta,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTinvalidValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_CfgValueDef[] valueDef) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus,
            UCPTlastUpdate,
            UCPTdescription,
            UCPTuri,
            UCPTformatDescription,
            UCPTlength,
            UCPTdirection,
            UCPTpersist,
            UCPTdefOutput,
            UCPTunit);
        this.UCPTbaseType = UCPTbaseType;
        this.UCPTprecision = UCPTprecision;
        this.UCPTmaxFields = UCPTmaxFields;
        this.SCPTmaxSendTime = SCPTmaxSendTime;
        this.SCPTminSendTime = SCPTminSendTime;
        this.SCPTmaxRcvTime = SCPTmaxRcvTime;
        this.SCPTsndDelta = SCPTsndDelta;
        this.UCPTminValue = UCPTminValue;
        this.UCPTmaxValue = UCPTmaxValue;
        this.UCPTinvalidValue = UCPTinvalidValue;
        this.valueDef = valueDef;
    }


    /**
     * Gets the UCPTbaseType value for this Dp_Cfg.
     * 
     * @return UCPTbaseType
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTbaseType() {
        return UCPTbaseType;
    }


    /**
     * Sets the UCPTbaseType value for this Dp_Cfg.
     * 
     * @param UCPTbaseType
     */
    public void setUCPTbaseType(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTbaseType) {
        this.UCPTbaseType = UCPTbaseType;
    }


    /**
     * Gets the UCPTprecision value for this Dp_Cfg.
     * 
     * @return UCPTprecision
     */
    public java.lang.Integer getUCPTprecision() {
        return UCPTprecision;
    }


    /**
     * Sets the UCPTprecision value for this Dp_Cfg.
     * 
     * @param UCPTprecision
     */
    public void setUCPTprecision(java.lang.Integer UCPTprecision) {
        this.UCPTprecision = UCPTprecision;
    }


    /**
     * Gets the UCPTmaxFields value for this Dp_Cfg.
     * 
     * @return UCPTmaxFields
     */
    public java.lang.Integer getUCPTmaxFields() {
        return UCPTmaxFields;
    }


    /**
     * Sets the UCPTmaxFields value for this Dp_Cfg.
     * 
     * @param UCPTmaxFields
     */
    public void setUCPTmaxFields(java.lang.Integer UCPTmaxFields) {
        this.UCPTmaxFields = UCPTmaxFields;
    }


    /**
     * Gets the SCPTmaxSendTime value for this Dp_Cfg.
     * 
     * @return SCPTmaxSendTime
     */
    public java.lang.Float getSCPTmaxSendTime() {
        return SCPTmaxSendTime;
    }


    /**
     * Sets the SCPTmaxSendTime value for this Dp_Cfg.
     * 
     * @param SCPTmaxSendTime
     */
    public void setSCPTmaxSendTime(java.lang.Float SCPTmaxSendTime) {
        this.SCPTmaxSendTime = SCPTmaxSendTime;
    }


    /**
     * Gets the SCPTminSendTime value for this Dp_Cfg.
     * 
     * @return SCPTminSendTime
     */
    public java.lang.Float getSCPTminSendTime() {
        return SCPTminSendTime;
    }


    /**
     * Sets the SCPTminSendTime value for this Dp_Cfg.
     * 
     * @param SCPTminSendTime
     */
    public void setSCPTminSendTime(java.lang.Float SCPTminSendTime) {
        this.SCPTminSendTime = SCPTminSendTime;
    }


    /**
     * Gets the SCPTmaxRcvTime value for this Dp_Cfg.
     * 
     * @return SCPTmaxRcvTime
     */
    public java.lang.Float getSCPTmaxRcvTime() {
        return SCPTmaxRcvTime;
    }


    /**
     * Sets the SCPTmaxRcvTime value for this Dp_Cfg.
     * 
     * @param SCPTmaxRcvTime
     */
    public void setSCPTmaxRcvTime(java.lang.Float SCPTmaxRcvTime) {
        this.SCPTmaxRcvTime = SCPTmaxRcvTime;
    }


    /**
     * Gets the SCPTsndDelta value for this Dp_Cfg.
     * 
     * @return SCPTsndDelta
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getSCPTsndDelta() {
        return SCPTsndDelta;
    }


    /**
     * Sets the SCPTsndDelta value for this Dp_Cfg.
     * 
     * @param SCPTsndDelta
     */
    public void setSCPTsndDelta(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTsndDelta) {
        this.SCPTsndDelta = SCPTsndDelta;
    }


    /**
     * Gets the UCPTminValue value for this Dp_Cfg.
     * 
     * @return UCPTminValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTminValue() {
        return UCPTminValue;
    }


    /**
     * Sets the UCPTminValue value for this Dp_Cfg.
     * 
     * @param UCPTminValue
     */
    public void setUCPTminValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue) {
        this.UCPTminValue = UCPTminValue;
    }


    /**
     * Gets the UCPTmaxValue value for this Dp_Cfg.
     * 
     * @return UCPTmaxValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmaxValue() {
        return UCPTmaxValue;
    }


    /**
     * Sets the UCPTmaxValue value for this Dp_Cfg.
     * 
     * @param UCPTmaxValue
     */
    public void setUCPTmaxValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue) {
        this.UCPTmaxValue = UCPTmaxValue;
    }


    /**
     * Gets the UCPTinvalidValue value for this Dp_Cfg.
     * 
     * @return UCPTinvalidValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTinvalidValue() {
        return UCPTinvalidValue;
    }


    /**
     * Sets the UCPTinvalidValue value for this Dp_Cfg.
     * 
     * @param UCPTinvalidValue
     */
    public void setUCPTinvalidValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTinvalidValue) {
        this.UCPTinvalidValue = UCPTinvalidValue;
    }


    /**
     * Gets the valueDef value for this Dp_Cfg.
     * 
     * @return valueDef
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_CfgValueDef[] getValueDef() {
        return valueDef;
    }


    /**
     * Sets the valueDef value for this Dp_Cfg.
     * 
     * @param valueDef
     */
    public void setValueDef(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_CfgValueDef[] valueDef) {
        this.valueDef = valueDef;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_CfgValueDef getValueDef(int i) {
        return this.valueDef[i];
    }

    public void setValueDef(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_CfgValueDef _value) {
        this.valueDef[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dp_Cfg)) return false;
        Dp_Cfg other = (Dp_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTbaseType==null && other.getUCPTbaseType()==null) || 
             (this.UCPTbaseType!=null &&
              this.UCPTbaseType.equals(other.getUCPTbaseType()))) &&
            ((this.UCPTprecision==null && other.getUCPTprecision()==null) || 
             (this.UCPTprecision!=null &&
              this.UCPTprecision.equals(other.getUCPTprecision()))) &&
            ((this.UCPTmaxFields==null && other.getUCPTmaxFields()==null) || 
             (this.UCPTmaxFields!=null &&
              this.UCPTmaxFields.equals(other.getUCPTmaxFields()))) &&
            ((this.SCPTmaxSendTime==null && other.getSCPTmaxSendTime()==null) || 
             (this.SCPTmaxSendTime!=null &&
              this.SCPTmaxSendTime.equals(other.getSCPTmaxSendTime()))) &&
            ((this.SCPTminSendTime==null && other.getSCPTminSendTime()==null) || 
             (this.SCPTminSendTime!=null &&
              this.SCPTminSendTime.equals(other.getSCPTminSendTime()))) &&
            ((this.SCPTmaxRcvTime==null && other.getSCPTmaxRcvTime()==null) || 
             (this.SCPTmaxRcvTime!=null &&
              this.SCPTmaxRcvTime.equals(other.getSCPTmaxRcvTime()))) &&
            ((this.SCPTsndDelta==null && other.getSCPTsndDelta()==null) || 
             (this.SCPTsndDelta!=null &&
              this.SCPTsndDelta.equals(other.getSCPTsndDelta()))) &&
            ((this.UCPTminValue==null && other.getUCPTminValue()==null) || 
             (this.UCPTminValue!=null &&
              this.UCPTminValue.equals(other.getUCPTminValue()))) &&
            ((this.UCPTmaxValue==null && other.getUCPTmaxValue()==null) || 
             (this.UCPTmaxValue!=null &&
              this.UCPTmaxValue.equals(other.getUCPTmaxValue()))) &&
            ((this.UCPTinvalidValue==null && other.getUCPTinvalidValue()==null) || 
             (this.UCPTinvalidValue!=null &&
              this.UCPTinvalidValue.equals(other.getUCPTinvalidValue()))) &&
            ((this.valueDef==null && other.getValueDef()==null) || 
             (this.valueDef!=null &&
              java.util.Arrays.equals(this.valueDef, other.getValueDef())));
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
        if (getUCPTbaseType() != null) {
            _hashCode += getUCPTbaseType().hashCode();
        }
        if (getUCPTprecision() != null) {
            _hashCode += getUCPTprecision().hashCode();
        }
        if (getUCPTmaxFields() != null) {
            _hashCode += getUCPTmaxFields().hashCode();
        }
        if (getSCPTmaxSendTime() != null) {
            _hashCode += getSCPTmaxSendTime().hashCode();
        }
        if (getSCPTminSendTime() != null) {
            _hashCode += getSCPTminSendTime().hashCode();
        }
        if (getSCPTmaxRcvTime() != null) {
            _hashCode += getSCPTmaxRcvTime().hashCode();
        }
        if (getSCPTsndDelta() != null) {
            _hashCode += getSCPTsndDelta().hashCode();
        }
        if (getUCPTminValue() != null) {
            _hashCode += getUCPTminValue().hashCode();
        }
        if (getUCPTmaxValue() != null) {
            _hashCode += getUCPTmaxValue().hashCode();
        }
        if (getUCPTinvalidValue() != null) {
            _hashCode += getUCPTinvalidValue().hashCode();
        }
        if (getValueDef() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValueDef());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValueDef(), i);
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
        new org.apache.axis.description.TypeDesc(Dp_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTbaseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTbaseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTprecision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTprecision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPTmaxSendTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPTmaxSendTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPTminSendTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPTminSendTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPTmaxRcvTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPTmaxRcvTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPTsndDelta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPTsndDelta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTminValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTminValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTinvalidValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTinvalidValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueDef");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "ValueDef"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_CfgValueDef"));
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
