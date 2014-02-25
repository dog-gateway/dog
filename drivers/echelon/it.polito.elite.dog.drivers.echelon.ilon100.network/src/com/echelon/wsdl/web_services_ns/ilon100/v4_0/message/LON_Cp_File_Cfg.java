/**
 * LON_Cp_File_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Specialized file configuration type.
 * 					Example:  xSelect="//Item[@xsi:type="LON_Cp_File_Cfg"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Cp_File_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Cfg_Base  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle;

    private java.lang.Integer UCPToffset;

    private java.lang.Integer UCPTfileIndex;

    private java.lang.Short UCPTreadOnlyFlag;

    private java.lang.Short UCPTdeviceFlag;

    private java.lang.Short UCPTmanufactureFlag;

    private java.lang.Short UCPTobjDisableFlag;

    private java.lang.Short UCPTofflineFlag;

    private java.lang.Short UCPTresetFlag;

    private java.lang.String UCPTrelation;

    public LON_Cp_File_Cfg() {
    }

    public LON_Cp_File_Cfg(
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
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle,
           java.lang.Integer UCPToffset,
           java.lang.Integer UCPTfileIndex,
           java.lang.Short UCPTreadOnlyFlag,
           java.lang.Short UCPTdeviceFlag,
           java.lang.Short UCPTmanufactureFlag,
           java.lang.Short UCPTobjDisableFlag,
           java.lang.Short UCPTofflineFlag,
           java.lang.Short UCPTresetFlag,
           java.lang.String UCPTrelation) {
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
        this.UCPThandle = UCPThandle;
        this.UCPToffset = UCPToffset;
        this.UCPTfileIndex = UCPTfileIndex;
        this.UCPTreadOnlyFlag = UCPTreadOnlyFlag;
        this.UCPTdeviceFlag = UCPTdeviceFlag;
        this.UCPTmanufactureFlag = UCPTmanufactureFlag;
        this.UCPTobjDisableFlag = UCPTobjDisableFlag;
        this.UCPTofflineFlag = UCPTofflineFlag;
        this.UCPTresetFlag = UCPTresetFlag;
        this.UCPTrelation = UCPTrelation;
    }


    /**
     * Gets the UCPThandle value for this LON_Cp_File_Cfg.
     * 
     * @return UCPThandle
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPThandle() {
        return UCPThandle;
    }


    /**
     * Sets the UCPThandle value for this LON_Cp_File_Cfg.
     * 
     * @param UCPThandle
     */
    public void setUCPThandle(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle) {
        this.UCPThandle = UCPThandle;
    }


    /**
     * Gets the UCPToffset value for this LON_Cp_File_Cfg.
     * 
     * @return UCPToffset
     */
    public java.lang.Integer getUCPToffset() {
        return UCPToffset;
    }


    /**
     * Sets the UCPToffset value for this LON_Cp_File_Cfg.
     * 
     * @param UCPToffset
     */
    public void setUCPToffset(java.lang.Integer UCPToffset) {
        this.UCPToffset = UCPToffset;
    }


    /**
     * Gets the UCPTfileIndex value for this LON_Cp_File_Cfg.
     * 
     * @return UCPTfileIndex
     */
    public java.lang.Integer getUCPTfileIndex() {
        return UCPTfileIndex;
    }


    /**
     * Sets the UCPTfileIndex value for this LON_Cp_File_Cfg.
     * 
     * @param UCPTfileIndex
     */
    public void setUCPTfileIndex(java.lang.Integer UCPTfileIndex) {
        this.UCPTfileIndex = UCPTfileIndex;
    }


    /**
     * Gets the UCPTreadOnlyFlag value for this LON_Cp_File_Cfg.
     * 
     * @return UCPTreadOnlyFlag
     */
    public java.lang.Short getUCPTreadOnlyFlag() {
        return UCPTreadOnlyFlag;
    }


    /**
     * Sets the UCPTreadOnlyFlag value for this LON_Cp_File_Cfg.
     * 
     * @param UCPTreadOnlyFlag
     */
    public void setUCPTreadOnlyFlag(java.lang.Short UCPTreadOnlyFlag) {
        this.UCPTreadOnlyFlag = UCPTreadOnlyFlag;
    }


    /**
     * Gets the UCPTdeviceFlag value for this LON_Cp_File_Cfg.
     * 
     * @return UCPTdeviceFlag
     */
    public java.lang.Short getUCPTdeviceFlag() {
        return UCPTdeviceFlag;
    }


    /**
     * Sets the UCPTdeviceFlag value for this LON_Cp_File_Cfg.
     * 
     * @param UCPTdeviceFlag
     */
    public void setUCPTdeviceFlag(java.lang.Short UCPTdeviceFlag) {
        this.UCPTdeviceFlag = UCPTdeviceFlag;
    }


    /**
     * Gets the UCPTmanufactureFlag value for this LON_Cp_File_Cfg.
     * 
     * @return UCPTmanufactureFlag
     */
    public java.lang.Short getUCPTmanufactureFlag() {
        return UCPTmanufactureFlag;
    }


    /**
     * Sets the UCPTmanufactureFlag value for this LON_Cp_File_Cfg.
     * 
     * @param UCPTmanufactureFlag
     */
    public void setUCPTmanufactureFlag(java.lang.Short UCPTmanufactureFlag) {
        this.UCPTmanufactureFlag = UCPTmanufactureFlag;
    }


    /**
     * Gets the UCPTobjDisableFlag value for this LON_Cp_File_Cfg.
     * 
     * @return UCPTobjDisableFlag
     */
    public java.lang.Short getUCPTobjDisableFlag() {
        return UCPTobjDisableFlag;
    }


    /**
     * Sets the UCPTobjDisableFlag value for this LON_Cp_File_Cfg.
     * 
     * @param UCPTobjDisableFlag
     */
    public void setUCPTobjDisableFlag(java.lang.Short UCPTobjDisableFlag) {
        this.UCPTobjDisableFlag = UCPTobjDisableFlag;
    }


    /**
     * Gets the UCPTofflineFlag value for this LON_Cp_File_Cfg.
     * 
     * @return UCPTofflineFlag
     */
    public java.lang.Short getUCPTofflineFlag() {
        return UCPTofflineFlag;
    }


    /**
     * Sets the UCPTofflineFlag value for this LON_Cp_File_Cfg.
     * 
     * @param UCPTofflineFlag
     */
    public void setUCPTofflineFlag(java.lang.Short UCPTofflineFlag) {
        this.UCPTofflineFlag = UCPTofflineFlag;
    }


    /**
     * Gets the UCPTresetFlag value for this LON_Cp_File_Cfg.
     * 
     * @return UCPTresetFlag
     */
    public java.lang.Short getUCPTresetFlag() {
        return UCPTresetFlag;
    }


    /**
     * Sets the UCPTresetFlag value for this LON_Cp_File_Cfg.
     * 
     * @param UCPTresetFlag
     */
    public void setUCPTresetFlag(java.lang.Short UCPTresetFlag) {
        this.UCPTresetFlag = UCPTresetFlag;
    }


    /**
     * Gets the UCPTrelation value for this LON_Cp_File_Cfg.
     * 
     * @return UCPTrelation
     */
    public java.lang.String getUCPTrelation() {
        return UCPTrelation;
    }


    /**
     * Sets the UCPTrelation value for this LON_Cp_File_Cfg.
     * 
     * @param UCPTrelation
     */
    public void setUCPTrelation(java.lang.String UCPTrelation) {
        this.UCPTrelation = UCPTrelation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Cp_File_Cfg)) return false;
        LON_Cp_File_Cfg other = (LON_Cp_File_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPThandle==null && other.getUCPThandle()==null) || 
             (this.UCPThandle!=null &&
              this.UCPThandle.equals(other.getUCPThandle()))) &&
            ((this.UCPToffset==null && other.getUCPToffset()==null) || 
             (this.UCPToffset!=null &&
              this.UCPToffset.equals(other.getUCPToffset()))) &&
            ((this.UCPTfileIndex==null && other.getUCPTfileIndex()==null) || 
             (this.UCPTfileIndex!=null &&
              this.UCPTfileIndex.equals(other.getUCPTfileIndex()))) &&
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
            ((this.UCPTrelation==null && other.getUCPTrelation()==null) || 
             (this.UCPTrelation!=null &&
              this.UCPTrelation.equals(other.getUCPTrelation())));
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
        if (getUCPThandle() != null) {
            _hashCode += getUCPThandle().hashCode();
        }
        if (getUCPToffset() != null) {
            _hashCode += getUCPToffset().hashCode();
        }
        if (getUCPTfileIndex() != null) {
            _hashCode += getUCPTfileIndex().hashCode();
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
        if (getUCPTrelation() != null) {
            _hashCode += getUCPTrelation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Cp_File_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Cp_File_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThandle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThandle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_UniqueKey"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPToffset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPToffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfileIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfileIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("UCPTrelation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTrelation"));
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
