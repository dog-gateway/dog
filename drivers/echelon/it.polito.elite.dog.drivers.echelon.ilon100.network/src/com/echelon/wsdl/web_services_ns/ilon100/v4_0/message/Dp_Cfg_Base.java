/**
 * Dp_Cfg_Base.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * This is the base class for all further <foo>Dp_Cfg classes
 */
@SuppressWarnings({"rawtypes","unused"})
public abstract class Dp_Cfg_Base  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTformatDescription;

    private java.lang.Integer UCPTlength;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdirection;

    private java.lang.Short UCPTpersist;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefOutput;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] UCPTunit;

    public Dp_Cfg_Base() {
    }

    public Dp_Cfg_Base(
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
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] UCPTunit) {
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
        this.UCPTformatDescription = UCPTformatDescription;
        this.UCPTlength = UCPTlength;
        this.UCPTdirection = UCPTdirection;
        this.UCPTpersist = UCPTpersist;
        this.UCPTdefOutput = UCPTdefOutput;
        this.UCPTunit = UCPTunit;
    }


    /**
     * Gets the UCPTformatDescription value for this Dp_Cfg_Base.
     * 
     * @return UCPTformatDescription
     */
    public java.lang.String getUCPTformatDescription() {
        return UCPTformatDescription;
    }


    /**
     * Sets the UCPTformatDescription value for this Dp_Cfg_Base.
     * 
     * @param UCPTformatDescription
     */
    public void setUCPTformatDescription(java.lang.String UCPTformatDescription) {
        this.UCPTformatDescription = UCPTformatDescription;
    }


    /**
     * Gets the UCPTlength value for this Dp_Cfg_Base.
     * 
     * @return UCPTlength
     */
    public java.lang.Integer getUCPTlength() {
        return UCPTlength;
    }


    /**
     * Sets the UCPTlength value for this Dp_Cfg_Base.
     * 
     * @param UCPTlength
     */
    public void setUCPTlength(java.lang.Integer UCPTlength) {
        this.UCPTlength = UCPTlength;
    }


    /**
     * Gets the UCPTdirection value for this Dp_Cfg_Base.
     * 
     * @return UCPTdirection
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTdirection() {
        return UCPTdirection;
    }


    /**
     * Sets the UCPTdirection value for this Dp_Cfg_Base.
     * 
     * @param UCPTdirection
     */
    public void setUCPTdirection(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdirection) {
        this.UCPTdirection = UCPTdirection;
    }


    /**
     * Gets the UCPTpersist value for this Dp_Cfg_Base.
     * 
     * @return UCPTpersist
     */
    public java.lang.Short getUCPTpersist() {
        return UCPTpersist;
    }


    /**
     * Sets the UCPTpersist value for this Dp_Cfg_Base.
     * 
     * @param UCPTpersist
     */
    public void setUCPTpersist(java.lang.Short UCPTpersist) {
        this.UCPTpersist = UCPTpersist;
    }


    /**
     * Gets the UCPTdefOutput value for this Dp_Cfg_Base.
     * 
     * @return UCPTdefOutput
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTdefOutput() {
        return UCPTdefOutput;
    }


    /**
     * Sets the UCPTdefOutput value for this Dp_Cfg_Base.
     * 
     * @param UCPTdefOutput
     */
    public void setUCPTdefOutput(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdefOutput) {
        this.UCPTdefOutput = UCPTdefOutput;
    }


    /**
     * Gets the UCPTunit value for this Dp_Cfg_Base.
     * 
     * @return UCPTunit
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit[] getUCPTunit() {
        return UCPTunit;
    }


    /**
     * Sets the UCPTunit value for this Dp_Cfg_Base.
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

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dp_Cfg_Base)) return false;
        Dp_Cfg_Base other = (Dp_Cfg_Base) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTformatDescription==null && other.getUCPTformatDescription()==null) || 
             (this.UCPTformatDescription!=null &&
              this.UCPTformatDescription.equals(other.getUCPTformatDescription()))) &&
            ((this.UCPTlength==null && other.getUCPTlength()==null) || 
             (this.UCPTlength!=null &&
              this.UCPTlength.equals(other.getUCPTlength()))) &&
            ((this.UCPTdirection==null && other.getUCPTdirection()==null) || 
             (this.UCPTdirection!=null &&
              this.UCPTdirection.equals(other.getUCPTdirection()))) &&
            ((this.UCPTpersist==null && other.getUCPTpersist()==null) || 
             (this.UCPTpersist!=null &&
              this.UCPTpersist.equals(other.getUCPTpersist()))) &&
            ((this.UCPTdefOutput==null && other.getUCPTdefOutput()==null) || 
             (this.UCPTdefOutput!=null &&
              this.UCPTdefOutput.equals(other.getUCPTdefOutput()))) &&
            ((this.UCPTunit==null && other.getUCPTunit()==null) || 
             (this.UCPTunit!=null &&
              java.util.Arrays.equals(this.UCPTunit, other.getUCPTunit())));
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
        if (getUCPTformatDescription() != null) {
            _hashCode += getUCPTformatDescription().hashCode();
        }
        if (getUCPTlength() != null) {
            _hashCode += getUCPTlength().hashCode();
        }
        if (getUCPTdirection() != null) {
            _hashCode += getUCPTdirection().hashCode();
        }
        if (getUCPTpersist() != null) {
            _hashCode += getUCPTpersist().hashCode();
        }
        if (getUCPTdefOutput() != null) {
            _hashCode += getUCPTdefOutput().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dp_Cfg_Base.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_Cfg_Base"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTformatDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTformatDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdirection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdirection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpersist");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpersist"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdefOutput");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdefOutput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
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
