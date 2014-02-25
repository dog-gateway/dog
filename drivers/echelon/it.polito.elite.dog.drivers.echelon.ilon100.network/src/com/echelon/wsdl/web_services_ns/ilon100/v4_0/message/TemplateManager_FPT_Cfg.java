/**
 * TemplateManager_FPT_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Service configuration type of the service 'TemplateManager'.
 * 						Example:  xSelect="//Item[@xsi:type="TemplateManager_FPT_Cfg"][UCPTprogramId="900001012881040C"][UCPTlanguage="enu"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class TemplateManager_FPT_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String UCPTshortDescription;

    private java.lang.String UCPTfptKey;

    private short UCPTinheriting;

    private java.lang.String UCPTreference;

    private java.lang.String UCPTprincipalName;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgNV[] NV;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgCP[] CP;

    public TemplateManager_FPT_Cfg() {
    }

    public TemplateManager_FPT_Cfg(
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
           java.lang.String UCPTfptKey,
           short UCPTinheriting,
           java.lang.String UCPTreference,
           java.lang.String UCPTprincipalName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgNV[] NV,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgCP[] CP) {
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
        this.UCPTfptKey = UCPTfptKey;
        this.UCPTinheriting = UCPTinheriting;
        this.UCPTreference = UCPTreference;
        this.UCPTprincipalName = UCPTprincipalName;
        this.NV = NV;
        this.CP = CP;
    }


    /**
     * Gets the UCPTshortDescription value for this TemplateManager_FPT_Cfg.
     * 
     * @return UCPTshortDescription
     */
    public java.lang.String getUCPTshortDescription() {
        return UCPTshortDescription;
    }


    /**
     * Sets the UCPTshortDescription value for this TemplateManager_FPT_Cfg.
     * 
     * @param UCPTshortDescription
     */
    public void setUCPTshortDescription(java.lang.String UCPTshortDescription) {
        this.UCPTshortDescription = UCPTshortDescription;
    }


    /**
     * Gets the UCPTfptKey value for this TemplateManager_FPT_Cfg.
     * 
     * @return UCPTfptKey
     */
    public java.lang.String getUCPTfptKey() {
        return UCPTfptKey;
    }


    /**
     * Sets the UCPTfptKey value for this TemplateManager_FPT_Cfg.
     * 
     * @param UCPTfptKey
     */
    public void setUCPTfptKey(java.lang.String UCPTfptKey) {
        this.UCPTfptKey = UCPTfptKey;
    }


    /**
     * Gets the UCPTinheriting value for this TemplateManager_FPT_Cfg.
     * 
     * @return UCPTinheriting
     */
    public short getUCPTinheriting() {
        return UCPTinheriting;
    }


    /**
     * Sets the UCPTinheriting value for this TemplateManager_FPT_Cfg.
     * 
     * @param UCPTinheriting
     */
    public void setUCPTinheriting(short UCPTinheriting) {
        this.UCPTinheriting = UCPTinheriting;
    }


    /**
     * Gets the UCPTreference value for this TemplateManager_FPT_Cfg.
     * 
     * @return UCPTreference
     */
    public java.lang.String getUCPTreference() {
        return UCPTreference;
    }


    /**
     * Sets the UCPTreference value for this TemplateManager_FPT_Cfg.
     * 
     * @param UCPTreference
     */
    public void setUCPTreference(java.lang.String UCPTreference) {
        this.UCPTreference = UCPTreference;
    }


    /**
     * Gets the UCPTprincipalName value for this TemplateManager_FPT_Cfg.
     * 
     * @return UCPTprincipalName
     */
    public java.lang.String getUCPTprincipalName() {
        return UCPTprincipalName;
    }


    /**
     * Sets the UCPTprincipalName value for this TemplateManager_FPT_Cfg.
     * 
     * @param UCPTprincipalName
     */
    public void setUCPTprincipalName(java.lang.String UCPTprincipalName) {
        this.UCPTprincipalName = UCPTprincipalName;
    }


    /**
     * Gets the NV value for this TemplateManager_FPT_Cfg.
     * 
     * @return NV
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgNV[] getNV() {
        return NV;
    }


    /**
     * Sets the NV value for this TemplateManager_FPT_Cfg.
     * 
     * @param NV
     */
    public void setNV(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgNV[] NV) {
        this.NV = NV;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgNV getNV(int i) {
        return this.NV[i];
    }

    public void setNV(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgNV _value) {
        this.NV[i] = _value;
    }


    /**
     * Gets the CP value for this TemplateManager_FPT_Cfg.
     * 
     * @return CP
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgCP[] getCP() {
        return CP;
    }


    /**
     * Sets the CP value for this TemplateManager_FPT_Cfg.
     * 
     * @param CP
     */
    public void setCP(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgCP[] CP) {
        this.CP = CP;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgCP getCP(int i) {
        return this.CP[i];
    }

    public void setCP(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgCP _value) {
        this.CP[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateManager_FPT_Cfg)) return false;
        TemplateManager_FPT_Cfg other = (TemplateManager_FPT_Cfg) obj;
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
            ((this.UCPTfptKey==null && other.getUCPTfptKey()==null) || 
             (this.UCPTfptKey!=null &&
              this.UCPTfptKey.equals(other.getUCPTfptKey()))) &&
            this.UCPTinheriting == other.getUCPTinheriting() &&
            ((this.UCPTreference==null && other.getUCPTreference()==null) || 
             (this.UCPTreference!=null &&
              this.UCPTreference.equals(other.getUCPTreference()))) &&
            ((this.UCPTprincipalName==null && other.getUCPTprincipalName()==null) || 
             (this.UCPTprincipalName!=null &&
              this.UCPTprincipalName.equals(other.getUCPTprincipalName()))) &&
            ((this.NV==null && other.getNV()==null) || 
             (this.NV!=null &&
              java.util.Arrays.equals(this.NV, other.getNV()))) &&
            ((this.CP==null && other.getCP()==null) || 
             (this.CP!=null &&
              java.util.Arrays.equals(this.CP, other.getCP())));
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
        if (getUCPTfptKey() != null) {
            _hashCode += getUCPTfptKey().hashCode();
        }
        _hashCode += getUCPTinheriting();
        if (getUCPTreference() != null) {
            _hashCode += getUCPTreference().hashCode();
        }
        if (getUCPTprincipalName() != null) {
            _hashCode += getUCPTprincipalName().hashCode();
        }
        if (getNV() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNV());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNV(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCP() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCP());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCP(), i);
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
        new org.apache.axis.description.TypeDesc(TemplateManager_FPT_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_FPT_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTshortDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTshortDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTfptKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTfptKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTinheriting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTinheriting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
        elemField.setFieldName("UCPTprincipalName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTprincipalName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NV");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "NV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_FPT_Cfg>NV"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "CP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_FPT_Cfg>CP"));
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
