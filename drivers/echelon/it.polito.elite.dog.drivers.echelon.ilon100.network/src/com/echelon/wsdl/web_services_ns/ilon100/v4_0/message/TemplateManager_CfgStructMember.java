/**
 * TemplateManager_CfgStructMember.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_CfgStructMember  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTdescription;

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

    public TemplateManager_CfgStructMember() {
    }

    public TemplateManager_CfgStructMember(
           java.lang.String UCPTdescription,
           java.lang.String UCPTshortDescription,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] UCPTunit,
           java.lang.String UCPTmemberName,
           java.lang.String UCPTreference,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgEnum enum_,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFloat float_,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] struct_,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] union_,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgScalar scalar_,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfield bitfield_) {
           this.UCPTdescription = UCPTdescription;
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
    }


    /**
     * Gets the UCPTdescription value for this TemplateManager_CfgStructMember.
     * 
     * @return UCPTdescription
     */
    public java.lang.String getUCPTdescription() {
        return UCPTdescription;
    }


    /**
     * Sets the UCPTdescription value for this TemplateManager_CfgStructMember.
     * 
     * @param UCPTdescription
     */
    public void setUCPTdescription(java.lang.String UCPTdescription) {
        this.UCPTdescription = UCPTdescription;
    }


    /**
     * Gets the UCPTshortDescription value for this TemplateManager_CfgStructMember.
     * 
     * @return UCPTshortDescription
     */
    public java.lang.String getUCPTshortDescription() {
        return UCPTshortDescription;
    }


    /**
     * Sets the UCPTshortDescription value for this TemplateManager_CfgStructMember.
     * 
     * @param UCPTshortDescription
     */
    public void setUCPTshortDescription(java.lang.String UCPTshortDescription) {
        this.UCPTshortDescription = UCPTshortDescription;
    }


    /**
     * Gets the UCPTunit value for this TemplateManager_CfgStructMember.
     * 
     * @return UCPTunit
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] getUCPTunit() {
        return UCPTunit;
    }


    /**
     * Sets the UCPTunit value for this TemplateManager_CfgStructMember.
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
     * Gets the UCPTmemberName value for this TemplateManager_CfgStructMember.
     * 
     * @return UCPTmemberName
     */
    public java.lang.String getUCPTmemberName() {
        return UCPTmemberName;
    }


    /**
     * Sets the UCPTmemberName value for this TemplateManager_CfgStructMember.
     * 
     * @param UCPTmemberName
     */
    public void setUCPTmemberName(java.lang.String UCPTmemberName) {
        this.UCPTmemberName = UCPTmemberName;
    }


    /**
     * Gets the UCPTreference value for this TemplateManager_CfgStructMember.
     * 
     * @return UCPTreference
     */
    public java.lang.String getUCPTreference() {
        return UCPTreference;
    }


    /**
     * Sets the UCPTreference value for this TemplateManager_CfgStructMember.
     * 
     * @param UCPTreference
     */
    public void setUCPTreference(java.lang.String UCPTreference) {
        this.UCPTreference = UCPTreference;
    }


    /**
     * Gets the enum_ value for this TemplateManager_CfgStructMember.
     * 
     * @return enum_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgEnum getEnum_() {
        return enum_;
    }


    /**
     * Sets the enum_ value for this TemplateManager_CfgStructMember.
     * 
     * @param enum_
     */
    public void setEnum_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgEnum enum_) {
        this.enum_ = enum_;
    }


    /**
     * Gets the float_ value for this TemplateManager_CfgStructMember.
     * 
     * @return float_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFloat getFloat_() {
        return float_;
    }


    /**
     * Sets the float_ value for this TemplateManager_CfgStructMember.
     * 
     * @param float_
     */
    public void setFloat_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFloat float_) {
        this.float_ = float_;
    }


    /**
     * Gets the struct_ value for this TemplateManager_CfgStructMember.
     * 
     * @return struct_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] getStruct_() {
        return struct_;
    }


    /**
     * Sets the struct_ value for this TemplateManager_CfgStructMember.
     * 
     * @param struct_
     */
    public void setStruct_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] struct_) {
        this.struct_ = struct_;
    }


    /**
     * Gets the union_ value for this TemplateManager_CfgStructMember.
     * 
     * @return union_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] getUnion_() {
        return union_;
    }


    /**
     * Sets the union_ value for this TemplateManager_CfgStructMember.
     * 
     * @param union_
     */
    public void setUnion_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[] union_) {
        this.union_ = union_;
    }


    /**
     * Gets the scalar_ value for this TemplateManager_CfgStructMember.
     * 
     * @return scalar_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgScalar getScalar_() {
        return scalar_;
    }


    /**
     * Sets the scalar_ value for this TemplateManager_CfgStructMember.
     * 
     * @param scalar_
     */
    public void setScalar_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgScalar scalar_) {
        this.scalar_ = scalar_;
    }


    /**
     * Gets the bitfield_ value for this TemplateManager_CfgStructMember.
     * 
     * @return bitfield_
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfield getBitfield_() {
        return bitfield_;
    }


    /**
     * Sets the bitfield_ value for this TemplateManager_CfgStructMember.
     * 
     * @param bitfield_
     */
    public void setBitfield_(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfield bitfield_) {
        this.bitfield_ = bitfield_;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_CfgStructMember)) return false;
        TemplateManager_CfgStructMember other = (TemplateManager_CfgStructMember) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UCPTdescription==null && other.getUCPTdescription()==null) || 
             (this.UCPTdescription!=null &&
              this.UCPTdescription.equals(other.getUCPTdescription()))) &&
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
              this.bitfield_.equals(other.getBitfield_())));
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
        if (getUCPTdescription() != null) {
            _hashCode += getUCPTdescription().hashCode();
        }
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateManager_CfgStructMember.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_CfgStruct>Member"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
