/**
 * TemplateManager_FPT_CfgCP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;

@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_FPT_CfgCP  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int UCPTindex;

    private java.lang.String UCPTname;

    private java.lang.String UCPTdescription;

    private int UCPTmemberIndex;

    private short UCPTmandatory;

    private java.lang.String UCPTshortDescription;

    private java.lang.String UCPTreference;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefaultValue;

    private java.lang.String UCPTappliesTo;

    private java.lang.Short UCPTreadOnlyFlag;

    private java.lang.Short UCPTdeviceFlag;

    private java.lang.Short UCPTmanufactureFlag;

    private java.lang.Short UCPTobjDisableFlag;

    private java.lang.Short UCPTofflineFlag;

    private java.lang.Short UCPTresetFlag;

    private java.lang.Integer UCPTminArraySize;

    private java.lang.Integer UCPTmaxArraySize;

    public TemplateManager_FPT_CfgCP() {
    }

    public TemplateManager_FPT_CfgCP(
           int UCPTindex,
           java.lang.String UCPTname,
           java.lang.String UCPTdescription,
           int UCPTmemberIndex,
           short UCPTmandatory,
           java.lang.String UCPTshortDescription,
           java.lang.String UCPTreference,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefaultValue,
           java.lang.String UCPTappliesTo,
           java.lang.Short UCPTreadOnlyFlag,
           java.lang.Short UCPTdeviceFlag,
           java.lang.Short UCPTmanufactureFlag,
           java.lang.Short UCPTobjDisableFlag,
           java.lang.Short UCPTofflineFlag,
           java.lang.Short UCPTresetFlag,
           java.lang.Integer UCPTminArraySize,
           java.lang.Integer UCPTmaxArraySize) {
           this.UCPTindex = UCPTindex;
           this.UCPTname = UCPTname;
           this.UCPTdescription = UCPTdescription;
           this.UCPTmemberIndex = UCPTmemberIndex;
           this.UCPTmandatory = UCPTmandatory;
           this.UCPTshortDescription = UCPTshortDescription;
           this.UCPTreference = UCPTreference;
           this.UCPTminValue = UCPTminValue;
           this.UCPTmaxValue = UCPTmaxValue;
           this.UCPTdefaultValue = UCPTdefaultValue;
           this.UCPTappliesTo = UCPTappliesTo;
           this.UCPTreadOnlyFlag = UCPTreadOnlyFlag;
           this.UCPTdeviceFlag = UCPTdeviceFlag;
           this.UCPTmanufactureFlag = UCPTmanufactureFlag;
           this.UCPTobjDisableFlag = UCPTobjDisableFlag;
           this.UCPTofflineFlag = UCPTofflineFlag;
           this.UCPTresetFlag = UCPTresetFlag;
           this.UCPTminArraySize = UCPTminArraySize;
           this.UCPTmaxArraySize = UCPTmaxArraySize;
    }


    /**
     * Gets the UCPTindex value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTindex
     */
    public int getUCPTindex() {
        return UCPTindex;
    }


    /**
     * Sets the UCPTindex value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTindex
     */
    public void setUCPTindex(int UCPTindex) {
        this.UCPTindex = UCPTindex;
    }


    /**
     * Gets the UCPTname value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTname
     */
    public java.lang.String getUCPTname() {
        return UCPTname;
    }


    /**
     * Sets the UCPTname value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTname
     */
    public void setUCPTname(java.lang.String UCPTname) {
        this.UCPTname = UCPTname;
    }


    /**
     * Gets the UCPTdescription value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTdescription
     */
    public java.lang.String getUCPTdescription() {
        return UCPTdescription;
    }


    /**
     * Sets the UCPTdescription value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTdescription
     */
    public void setUCPTdescription(java.lang.String UCPTdescription) {
        this.UCPTdescription = UCPTdescription;
    }


    /**
     * Gets the UCPTmemberIndex value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTmemberIndex
     */
    public int getUCPTmemberIndex() {
        return UCPTmemberIndex;
    }


    /**
     * Sets the UCPTmemberIndex value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTmemberIndex
     */
    public void setUCPTmemberIndex(int UCPTmemberIndex) {
        this.UCPTmemberIndex = UCPTmemberIndex;
    }


    /**
     * Gets the UCPTmandatory value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTmandatory
     */
    public short getUCPTmandatory() {
        return UCPTmandatory;
    }


    /**
     * Sets the UCPTmandatory value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTmandatory
     */
    public void setUCPTmandatory(short UCPTmandatory) {
        this.UCPTmandatory = UCPTmandatory;
    }


    /**
     * Gets the UCPTshortDescription value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTshortDescription
     */
    public java.lang.String getUCPTshortDescription() {
        return UCPTshortDescription;
    }


    /**
     * Sets the UCPTshortDescription value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTshortDescription
     */
    public void setUCPTshortDescription(java.lang.String UCPTshortDescription) {
        this.UCPTshortDescription = UCPTshortDescription;
    }


    /**
     * Gets the UCPTreference value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTreference
     */
    public java.lang.String getUCPTreference() {
        return UCPTreference;
    }


    /**
     * Sets the UCPTreference value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTreference
     */
    public void setUCPTreference(java.lang.String UCPTreference) {
        this.UCPTreference = UCPTreference;
    }


    /**
     * Gets the UCPTminValue value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTminValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTminValue() {
        return UCPTminValue;
    }


    /**
     * Sets the UCPTminValue value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTminValue
     */
    public void setUCPTminValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTminValue) {
        this.UCPTminValue = UCPTminValue;
    }


    /**
     * Gets the UCPTmaxValue value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTmaxValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTmaxValue() {
        return UCPTmaxValue;
    }


    /**
     * Sets the UCPTmaxValue value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTmaxValue
     */
    public void setUCPTmaxValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTmaxValue) {
        this.UCPTmaxValue = UCPTmaxValue;
    }


    /**
     * Gets the UCPTdefaultValue value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTdefaultValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTdefaultValue() {
        return UCPTdefaultValue;
    }


    /**
     * Sets the UCPTdefaultValue value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTdefaultValue
     */
    public void setUCPTdefaultValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefaultValue) {
        this.UCPTdefaultValue = UCPTdefaultValue;
    }


    /**
     * Gets the UCPTappliesTo value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTappliesTo
     */
    public java.lang.String getUCPTappliesTo() {
        return UCPTappliesTo;
    }


    /**
     * Sets the UCPTappliesTo value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTappliesTo
     */
    public void setUCPTappliesTo(java.lang.String UCPTappliesTo) {
        this.UCPTappliesTo = UCPTappliesTo;
    }


    /**
     * Gets the UCPTreadOnlyFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTreadOnlyFlag
     */
    public java.lang.Short getUCPTreadOnlyFlag() {
        return UCPTreadOnlyFlag;
    }


    /**
     * Sets the UCPTreadOnlyFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTreadOnlyFlag
     */
    public void setUCPTreadOnlyFlag(java.lang.Short UCPTreadOnlyFlag) {
        this.UCPTreadOnlyFlag = UCPTreadOnlyFlag;
    }


    /**
     * Gets the UCPTdeviceFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTdeviceFlag
     */
    public java.lang.Short getUCPTdeviceFlag() {
        return UCPTdeviceFlag;
    }


    /**
     * Sets the UCPTdeviceFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTdeviceFlag
     */
    public void setUCPTdeviceFlag(java.lang.Short UCPTdeviceFlag) {
        this.UCPTdeviceFlag = UCPTdeviceFlag;
    }


    /**
     * Gets the UCPTmanufactureFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTmanufactureFlag
     */
    public java.lang.Short getUCPTmanufactureFlag() {
        return UCPTmanufactureFlag;
    }


    /**
     * Sets the UCPTmanufactureFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTmanufactureFlag
     */
    public void setUCPTmanufactureFlag(java.lang.Short UCPTmanufactureFlag) {
        this.UCPTmanufactureFlag = UCPTmanufactureFlag;
    }


    /**
     * Gets the UCPTobjDisableFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTobjDisableFlag
     */
    public java.lang.Short getUCPTobjDisableFlag() {
        return UCPTobjDisableFlag;
    }


    /**
     * Sets the UCPTobjDisableFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTobjDisableFlag
     */
    public void setUCPTobjDisableFlag(java.lang.Short UCPTobjDisableFlag) {
        this.UCPTobjDisableFlag = UCPTobjDisableFlag;
    }


    /**
     * Gets the UCPTofflineFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTofflineFlag
     */
    public java.lang.Short getUCPTofflineFlag() {
        return UCPTofflineFlag;
    }


    /**
     * Sets the UCPTofflineFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTofflineFlag
     */
    public void setUCPTofflineFlag(java.lang.Short UCPTofflineFlag) {
        this.UCPTofflineFlag = UCPTofflineFlag;
    }


    /**
     * Gets the UCPTresetFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTresetFlag
     */
    public java.lang.Short getUCPTresetFlag() {
        return UCPTresetFlag;
    }


    /**
     * Sets the UCPTresetFlag value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTresetFlag
     */
    public void setUCPTresetFlag(java.lang.Short UCPTresetFlag) {
        this.UCPTresetFlag = UCPTresetFlag;
    }


    /**
     * Gets the UCPTminArraySize value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTminArraySize
     */
    public java.lang.Integer getUCPTminArraySize() {
        return UCPTminArraySize;
    }


    /**
     * Sets the UCPTminArraySize value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTminArraySize
     */
    public void setUCPTminArraySize(java.lang.Integer UCPTminArraySize) {
        this.UCPTminArraySize = UCPTminArraySize;
    }


    /**
     * Gets the UCPTmaxArraySize value for this TemplateManager_FPT_CfgCP.
     * 
     * @return UCPTmaxArraySize
     */
    public java.lang.Integer getUCPTmaxArraySize() {
        return UCPTmaxArraySize;
    }


    /**
     * Sets the UCPTmaxArraySize value for this TemplateManager_FPT_CfgCP.
     * 
     * @param UCPTmaxArraySize
     */
    public void setUCPTmaxArraySize(java.lang.Integer UCPTmaxArraySize) {
        this.UCPTmaxArraySize = UCPTmaxArraySize;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_FPT_CfgCP)) return false;
        TemplateManager_FPT_CfgCP other = (TemplateManager_FPT_CfgCP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.UCPTindex == other.getUCPTindex() &&
            ((this.UCPTname==null && other.getUCPTname()==null) || 
             (this.UCPTname!=null &&
              this.UCPTname.equals(other.getUCPTname()))) &&
            ((this.UCPTdescription==null && other.getUCPTdescription()==null) || 
             (this.UCPTdescription!=null &&
              this.UCPTdescription.equals(other.getUCPTdescription()))) &&
            this.UCPTmemberIndex == other.getUCPTmemberIndex() &&
            this.UCPTmandatory == other.getUCPTmandatory() &&
            ((this.UCPTshortDescription==null && other.getUCPTshortDescription()==null) || 
             (this.UCPTshortDescription!=null &&
              this.UCPTshortDescription.equals(other.getUCPTshortDescription()))) &&
            ((this.UCPTreference==null && other.getUCPTreference()==null) || 
             (this.UCPTreference!=null &&
              this.UCPTreference.equals(other.getUCPTreference()))) &&
            ((this.UCPTminValue==null && other.getUCPTminValue()==null) || 
             (this.UCPTminValue!=null &&
              this.UCPTminValue.equals(other.getUCPTminValue()))) &&
            ((this.UCPTmaxValue==null && other.getUCPTmaxValue()==null) || 
             (this.UCPTmaxValue!=null &&
              this.UCPTmaxValue.equals(other.getUCPTmaxValue()))) &&
            ((this.UCPTdefaultValue==null && other.getUCPTdefaultValue()==null) || 
             (this.UCPTdefaultValue!=null &&
              this.UCPTdefaultValue.equals(other.getUCPTdefaultValue()))) &&
            ((this.UCPTappliesTo==null && other.getUCPTappliesTo()==null) || 
             (this.UCPTappliesTo!=null &&
              this.UCPTappliesTo.equals(other.getUCPTappliesTo()))) &&
            ((this.UCPTreadOnlyFlag==null && other.getUCPTreadOnlyFlag()==null) || 
             (this.UCPTreadOnlyFlag!=null &&
              this.UCPTreadOnlyFlag.equals(other.getUCPTreadOnlyFlag()))) &&
            ((this.UCPTdeviceFlag==null && other.getUCPTdeviceFlag()==null) || 
             (this.UCPTdeviceFlag!=null &&
              this.UCPTdeviceFlag.equals(other.getUCPTdeviceFlag()))) &&
            ((this.UCPTmanufactureFlag==null && other.getUCPTmanufactureFlag()==null) || 
             (this.UCPTmanufactureFlag!=null &&
              this.UCPTmanufactureFlag.equals(other.getUCPTmanufactureFlag()))) &&
            ((this.UCPTobjDisableFlag==null && other.getUCPTobjDisableFlag()==null) || 
             (this.UCPTobjDisableFlag!=null &&
              this.UCPTobjDisableFlag.equals(other.getUCPTobjDisableFlag()))) &&
            ((this.UCPTofflineFlag==null && other.getUCPTofflineFlag()==null) || 
             (this.UCPTofflineFlag!=null &&
              this.UCPTofflineFlag.equals(other.getUCPTofflineFlag()))) &&
            ((this.UCPTresetFlag==null && other.getUCPTresetFlag()==null) || 
             (this.UCPTresetFlag!=null &&
              this.UCPTresetFlag.equals(other.getUCPTresetFlag()))) &&
            ((this.UCPTminArraySize==null && other.getUCPTminArraySize()==null) || 
             (this.UCPTminArraySize!=null &&
              this.UCPTminArraySize.equals(other.getUCPTminArraySize()))) &&
            ((this.UCPTmaxArraySize==null && other.getUCPTmaxArraySize()==null) || 
             (this.UCPTmaxArraySize!=null &&
              this.UCPTmaxArraySize.equals(other.getUCPTmaxArraySize())));
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
        _hashCode += getUCPTindex();
        if (getUCPTname() != null) {
            _hashCode += getUCPTname().hashCode();
        }
        if (getUCPTdescription() != null) {
            _hashCode += getUCPTdescription().hashCode();
        }
        _hashCode += getUCPTmemberIndex();
        _hashCode += getUCPTmandatory();
        if (getUCPTshortDescription() != null) {
            _hashCode += getUCPTshortDescription().hashCode();
        }
        if (getUCPTreference() != null) {
            _hashCode += getUCPTreference().hashCode();
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
        if (getUCPTappliesTo() != null) {
            _hashCode += getUCPTappliesTo().hashCode();
        }
        if (getUCPTreadOnlyFlag() != null) {
            _hashCode += getUCPTreadOnlyFlag().hashCode();
        }
        if (getUCPTdeviceFlag() != null) {
            _hashCode += getUCPTdeviceFlag().hashCode();
        }
        if (getUCPTmanufactureFlag() != null) {
            _hashCode += getUCPTmanufactureFlag().hashCode();
        }
        if (getUCPTobjDisableFlag() != null) {
            _hashCode += getUCPTobjDisableFlag().hashCode();
        }
        if (getUCPTofflineFlag() != null) {
            _hashCode += getUCPTofflineFlag().hashCode();
        }
        if (getUCPTresetFlag() != null) {
            _hashCode += getUCPTresetFlag().hashCode();
        }
        if (getUCPTminArraySize() != null) {
            _hashCode += getUCPTminArraySize().hashCode();
        }
        if (getUCPTmaxArraySize() != null) {
            _hashCode += getUCPTmaxArraySize().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateManager_FPT_CfgCP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_FPT_Cfg>CP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTindex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("UCPTmemberIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmemberIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmandatory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmandatory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
        elemField.setFieldName("UCPTreference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTreference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("UCPTappliesTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTappliesTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTreadOnlyFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTreadOnlyFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdeviceFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdeviceFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmanufactureFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmanufactureFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTobjDisableFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTobjDisableFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTofflineFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTofflineFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTresetFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTresetFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTminArraySize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTminArraySize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxArraySize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxArraySize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
