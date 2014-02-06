/**
 * TemplateManager_CPT_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Service configuration type of the service 'TemplateManager'.
 * 						Example:  xSelect="//Item[@xsi:type="TemplateManager_CPT_Cfg"][UCPTprogramId="900001012881040C"][UCPTlength="2"][UCPTlanguage="enu"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_CPT_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTshortDescription;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] UCPTunit;

    private java.lang.String UCPTmemberName;

    private java.lang.String UCPTreference;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgEnum enum_;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFloat float_;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] struct_;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] union_;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgScalar scalar_;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfield bitfield_;

    private int UCPTlength;

    private java.lang.Short UCPTinheriting;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefaultValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFormatString[] formatString;

    public TemplateManager_CPT_Cfg() {
    }

    public TemplateManager_CPT_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           java.lang.String UCPTshortDescription,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] UCPTunit,
           java.lang.String UCPTmemberName,
           java.lang.String UCPTreference,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgEnum enum_,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFloat float_,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] struct_,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] union_,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgScalar scalar_,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfield bitfield_,
           int UCPTlength,
           java.lang.Short UCPTinheriting,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefaultValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFormatString[] formatString) {
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
        this.UCPTshortDescription = UCPTshortDescription;
        this.UCPTunit = UCPTunit;
        this.UCPTmemberName = UCPTmemberName;
        this.UCPTreference = UCPTreference;
        this.enum_ = enum_;
        this.float_ = float_;
        this.struct_ = struct_;
        this.union_ = union_;
        this.scalar_ = scalar_;
        this.bitfield_ = bitfield_;
        this.UCPTlength = UCPTlength;
        this.UCPTinheriting = UCPTinheriting;
        this.UCPTminValue = UCPTminValue;
        this.UCPTmaxValue = UCPTmaxValue;
        this.UCPTdefaultValue = UCPTdefaultValue;
        this.formatString = formatString;
    }


    /**
     * Gets the UCPTshortDescription value for this TemplateManager_CPT_Cfg.
     * 
     * @return UCPTshortDescription
     */
    public java.lang.String getUCPTshortDescription() {
        return UCPTshortDescription;
    }


    /**
     * Sets the UCPTshortDescription value for this TemplateManager_CPT_Cfg.
     * 
     * @param UCPTshortDescription
     */
    public void setUCPTshortDescription(java.lang.String UCPTshortDescription) {
        this.UCPTshortDescription = UCPTshortDescription;
    }


    /**
     * Gets the UCPTunit value for this TemplateManager_CPT_Cfg.
     * 
     * @return UCPTunit
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] getUCPTunit() {
        return UCPTunit;
    }


    /**
     * Sets the UCPTunit value for this TemplateManager_CPT_Cfg.
     * 
     * @param UCPTunit
     */
    public void setUCPTunit(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] UCPTunit) {
        this.UCPTunit = UCPTunit;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit getUCPTunit(int i) {
        return this.UCPTunit[i];
    }

    public void setUCPTunit(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit _value) {
        this.UCPTunit[i] = _value;
    }


    /**
     * Gets the UCPTmemberName value for this TemplateManager_CPT_Cfg.
     * 
     * @return UCPTmemberName
     */
    public java.lang.String getUCPTmemberName() {
        return UCPTmemberName;
    }


    /**
     * Sets the UCPTmemberName value for this TemplateManager_CPT_Cfg.
     * 
     * @param UCPTmemberName
     */
    public void setUCPTmemberName(java.lang.String UCPTmemberName) {
        this.UCPTmemberName = UCPTmemberName;
    }


    /**
     * Gets the UCPTreference value for this TemplateManager_CPT_Cfg.
     * 
     * @return UCPTreference
     */
    public java.lang.String getUCPTreference() {
        return UCPTreference;
    }


    /**
     * Sets the UCPTreference value for this TemplateManager_CPT_Cfg.
     * 
     * @param UCPTreference
     */
    public void setUCPTreference(java.lang.String UCPTreference) {
        this.UCPTreference = UCPTreference;
    }


    /**
     * Gets the enum_ value for this TemplateManager_CPT_Cfg.
     * 
     * @return enum_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgEnum getEnum_() {
        return enum_;
    }


    /**
     * Sets the enum_ value for this TemplateManager_CPT_Cfg.
     * 
     * @param enum_
     */
    public void setEnum_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgEnum enum_) {
        this.enum_ = enum_;
    }


    /**
     * Gets the float_ value for this TemplateManager_CPT_Cfg.
     * 
     * @return float_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFloat getFloat_() {
        return float_;
    }


    /**
     * Sets the float_ value for this TemplateManager_CPT_Cfg.
     * 
     * @param float_
     */
    public void setFloat_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFloat float_) {
        this.float_ = float_;
    }


    /**
     * Gets the struct_ value for this TemplateManager_CPT_Cfg.
     * 
     * @return struct_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] getStruct_() {
        return struct_;
    }


    /**
     * Sets the struct_ value for this TemplateManager_CPT_Cfg.
     * 
     * @param struct_
     */
    public void setStruct_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] struct_) {
        this.struct_ = struct_;
    }


    /**
     * Gets the union_ value for this TemplateManager_CPT_Cfg.
     * 
     * @return union_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] getUnion_() {
        return union_;
    }


    /**
     * Sets the union_ value for this TemplateManager_CPT_Cfg.
     * 
     * @param union_
     */
    public void setUnion_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] union_) {
        this.union_ = union_;
    }


    /**
     * Gets the scalar_ value for this TemplateManager_CPT_Cfg.
     * 
     * @return scalar_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgScalar getScalar_() {
        return scalar_;
    }


    /**
     * Sets the scalar_ value for this TemplateManager_CPT_Cfg.
     * 
     * @param scalar_
     */
    public void setScalar_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgScalar scalar_) {
        this.scalar_ = scalar_;
    }


    /**
     * Gets the bitfield_ value for this TemplateManager_CPT_Cfg.
     * 
     * @return bitfield_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfield getBitfield_() {
        return bitfield_;
    }


    /**
     * Sets the bitfield_ value for this TemplateManager_CPT_Cfg.
     * 
     * @param bitfield_
     */
    public void setBitfield_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfield bitfield_) {
        this.bitfield_ = bitfield_;
    }


    /**
     * Gets the UCPTlength value for this TemplateManager_CPT_Cfg.
     * 
     * @return UCPTlength
     */
    public int getUCPTlength() {
        return UCPTlength;
    }


    /**
     * Sets the UCPTlength value for this TemplateManager_CPT_Cfg.
     * 
     * @param UCPTlength
     */
    public void setUCPTlength(int UCPTlength) {
        this.UCPTlength = UCPTlength;
    }


    /**
     * Gets the UCPTinheriting value for this TemplateManager_CPT_Cfg.
     * 
     * @return UCPTinheriting
     */
    public java.lang.Short getUCPTinheriting() {
        return UCPTinheriting;
    }


    /**
     * Sets the UCPTinheriting value for this TemplateManager_CPT_Cfg.
     * 
     * @param UCPTinheriting
     */
    public void setUCPTinheriting(java.lang.Short UCPTinheriting) {
        this.UCPTinheriting = UCPTinheriting;
    }


    /**
     * Gets the UCPTminValue value for this TemplateManager_CPT_Cfg.
     * 
     * @return UCPTminValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTminValue() {
        return UCPTminValue;
    }


    /**
     * Sets the UCPTminValue value for this TemplateManager_CPT_Cfg.
     * 
     * @param UCPTminValue
     */
    public void setUCPTminValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue) {
        this.UCPTminValue = UCPTminValue;
    }


    /**
     * Gets the UCPTmaxValue value for this TemplateManager_CPT_Cfg.
     * 
     * @return UCPTmaxValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmaxValue() {
        return UCPTmaxValue;
    }


    /**
     * Sets the UCPTmaxValue value for this TemplateManager_CPT_Cfg.
     * 
     * @param UCPTmaxValue
     */
    public void setUCPTmaxValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue) {
        this.UCPTmaxValue = UCPTmaxValue;
    }


    /**
     * Gets the UCPTdefaultValue value for this TemplateManager_CPT_Cfg.
     * 
     * @return UCPTdefaultValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTdefaultValue() {
        return UCPTdefaultValue;
    }


    /**
     * Sets the UCPTdefaultValue value for this TemplateManager_CPT_Cfg.
     * 
     * @param UCPTdefaultValue
     */
    public void setUCPTdefaultValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefaultValue) {
        this.UCPTdefaultValue = UCPTdefaultValue;
    }


    /**
     * Gets the formatString value for this TemplateManager_CPT_Cfg.
     * 
     * @return formatString
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFormatString[] getFormatString() {
        return formatString;
    }


    /**
     * Sets the formatString value for this TemplateManager_CPT_Cfg.
     * 
     * @param formatString
     */
    public void setFormatString(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFormatString[] formatString) {
        this.formatString = formatString;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFormatString getFormatString(int i) {
        return this.formatString[i];
    }

    public void setFormatString(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFormatString _value) {
        this.formatString[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_CPT_Cfg)) return false;
        TemplateManager_CPT_Cfg other = (TemplateManager_CPT_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTshortDescription==null && other.getUCPTshortDescription()==null) || 
             (this.UCPTshortDescription!=null &&
              this.UCPTshortDescription.equals(other.getUCPTshortDescription()))) &&
            ((this.UCPTunit==null && other.getUCPTunit()==null) || 
             (this.UCPTunit!=null &&
              java.util.Arrays.equals(this.UCPTunit, other.getUCPTunit()))) &&
            ((this.UCPTmemberName==null && other.getUCPTmemberName()==null) || 
             (this.UCPTmemberName!=null &&
              this.UCPTmemberName.equals(other.getUCPTmemberName()))) &&
            ((this.UCPTreference==null && other.getUCPTreference()==null) || 
             (this.UCPTreference!=null &&
              this.UCPTreference.equals(other.getUCPTreference()))) &&
            ((this.enum_==null && other.getEnum_()==null) || 
             (this.enum_!=null &&
              this.enum_.equals(other.getEnum_()))) &&
            ((this.float_==null && other.getFloat_()==null) || 
             (this.float_!=null &&
              this.float_.equals(other.getFloat_()))) &&
            ((this.struct_==null && other.getStruct_()==null) || 
             (this.struct_!=null &&
              java.util.Arrays.equals(this.struct_, other.getStruct_()))) &&
            ((this.union_==null && other.getUnion_()==null) || 
             (this.union_!=null &&
              java.util.Arrays.equals(this.union_, other.getUnion_()))) &&
            ((this.scalar_==null && other.getScalar_()==null) || 
             (this.scalar_!=null &&
              this.scalar_.equals(other.getScalar_()))) &&
            ((this.bitfield_==null && other.getBitfield_()==null) || 
             (this.bitfield_!=null &&
              this.bitfield_.equals(other.getBitfield_()))) &&
            this.UCPTlength == other.getUCPTlength() &&
            ((this.UCPTinheriting==null && other.getUCPTinheriting()==null) || 
             (this.UCPTinheriting!=null &&
              this.UCPTinheriting.equals(other.getUCPTinheriting()))) &&
            ((this.UCPTminValue==null && other.getUCPTminValue()==null) || 
             (this.UCPTminValue!=null &&
              this.UCPTminValue.equals(other.getUCPTminValue()))) &&
            ((this.UCPTmaxValue==null && other.getUCPTmaxValue()==null) || 
             (this.UCPTmaxValue!=null &&
              this.UCPTmaxValue.equals(other.getUCPTmaxValue()))) &&
            ((this.UCPTdefaultValue==null && other.getUCPTdefaultValue()==null) || 
             (this.UCPTdefaultValue!=null &&
              this.UCPTdefaultValue.equals(other.getUCPTdefaultValue()))) &&
            ((this.formatString==null && other.getFormatString()==null) || 
             (this.formatString!=null &&
              java.util.Arrays.equals(this.formatString, other.getFormatString())));
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
        if (getUCPTshortDescription() != null) {
            _hashCode += getUCPTshortDescription().hashCode();
        }
        if (getUCPTunit() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTunit());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTunit(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTmemberName() != null) {
            _hashCode += getUCPTmemberName().hashCode();
        }
        if (getUCPTreference() != null) {
            _hashCode += getUCPTreference().hashCode();
        }
        if (getEnum_() != null) {
            _hashCode += getEnum_().hashCode();
        }
        if (getFloat_() != null) {
            _hashCode += getFloat_().hashCode();
        }
        if (getStruct_() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStruct_());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStruct_(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUnion_() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUnion_());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUnion_(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getScalar_() != null) {
            _hashCode += getScalar_().hashCode();
        }
        if (getBitfield_() != null) {
            _hashCode += getBitfield_().hashCode();
        }
        _hashCode += getUCPTlength();
        if (getUCPTinheriting() != null) {
            _hashCode += getUCPTinheriting().hashCode();
        }
        if (getUCPTminValue() != null) {
            _hashCode += getUCPTminValue().hashCode();
        }
        if (getUCPTmaxValue() != null) {
            _hashCode += getUCPTmaxValue().hashCode();
        }
        if (getUCPTdefaultValue() != null) {
            _hashCode += getUCPTdefaultValue().hashCode();
        }
        if (getFormatString() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFormatString());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFormatString(), i);
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
        new org.apache.axis.description.TypeDesc(TemplateManager_CPT_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CPT_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTshortDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTshortDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTunit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTunit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Unit"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmemberName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmemberName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTreference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTreference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enum_");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "enum_"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("float_");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "float_"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgFloat"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("struct_");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "struct_"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_CfgStruct>Member"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Member"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("union_");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "union_"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_CfgStruct>Member"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Member"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scalar_");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "scalar_"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgScalar"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bitfield_");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "bitfield_"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgBitfield"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTinheriting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTinheriting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
        elemField.setFieldName("UCPTdefaultValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdefaultValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatString");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "FormatString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgFormatString"));
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
