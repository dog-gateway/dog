/**
 * Dp_Data.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized data point data type.
 * 					Example:  xSelect="//Item[@xsi:type="Dp_Data"][starts-with(UCPTname,'Net/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class Dp_Data  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTvalue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTpointStatus;

    private java.lang.Integer UCPTpriority;

    private java.lang.Float UCPTmaxAge;

    private java.lang.Short UCPTpropagate;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTmetaDataPath;

    public Dp_Data() {
    }

    public Dp_Data(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTvalue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTpointStatus,
           java.lang.Integer UCPTpriority,
           java.lang.Float UCPTmaxAge,
           java.lang.Short UCPTpropagate,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTmetaDataPath) {
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
        this.UCPTvalue = UCPTvalue;
        this.UCPTpointStatus = UCPTpointStatus;
        this.UCPTpriority = UCPTpriority;
        this.UCPTmaxAge = UCPTmaxAge;
        this.UCPTpropagate = UCPTpropagate;
        this.UCPTmetaDataPath = UCPTmetaDataPath;
    }


    /**
     * Gets the UCPTvalue value for this Dp_Data.
     * 
     * @return UCPTvalue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] getUCPTvalue() {
        return UCPTvalue;
    }


    /**
     * Sets the UCPTvalue value for this Dp_Data.
     * 
     * @param UCPTvalue
     */
    public void setUCPTvalue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString[] UCPTvalue) {
        this.UCPTvalue = UCPTvalue;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTvalue(int i) {
        return this.UCPTvalue[i];
    }

    public void setUCPTvalue(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString _value) {
        this.UCPTvalue[i] = _value;
    }


    /**
     * Gets the UCPTpointStatus value for this Dp_Data.
     * 
     * @return UCPTpointStatus
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTpointStatus() {
        return UCPTpointStatus;
    }


    /**
     * Sets the UCPTpointStatus value for this Dp_Data.
     * 
     * @param UCPTpointStatus
     */
    public void setUCPTpointStatus(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTpointStatus) {
        this.UCPTpointStatus = UCPTpointStatus;
    }


    /**
     * Gets the UCPTpriority value for this Dp_Data.
     * 
     * @return UCPTpriority
     */
    public java.lang.Integer getUCPTpriority() {
        return UCPTpriority;
    }


    /**
     * Sets the UCPTpriority value for this Dp_Data.
     * 
     * @param UCPTpriority
     */
    public void setUCPTpriority(java.lang.Integer UCPTpriority) {
        this.UCPTpriority = UCPTpriority;
    }


    /**
     * Gets the UCPTmaxAge value for this Dp_Data.
     * 
     * @return UCPTmaxAge
     */
    public java.lang.Float getUCPTmaxAge() {
        return UCPTmaxAge;
    }


    /**
     * Sets the UCPTmaxAge value for this Dp_Data.
     * 
     * @param UCPTmaxAge
     */
    public void setUCPTmaxAge(java.lang.Float UCPTmaxAge) {
        this.UCPTmaxAge = UCPTmaxAge;
    }


    /**
     * Gets the UCPTpropagate value for this Dp_Data.
     * 
     * @return UCPTpropagate
     */
    public java.lang.Short getUCPTpropagate() {
        return UCPTpropagate;
    }


    /**
     * Sets the UCPTpropagate value for this Dp_Data.
     * 
     * @param UCPTpropagate
     */
    public void setUCPTpropagate(java.lang.Short UCPTpropagate) {
        this.UCPTpropagate = UCPTpropagate;
    }


    /**
     * Gets the UCPTmetaDataPath value for this Dp_Data.
     * 
     * @return UCPTmetaDataPath
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path getUCPTmetaDataPath() {
        return UCPTmetaDataPath;
    }


    /**
     * Sets the UCPTmetaDataPath value for this Dp_Data.
     * 
     * @param UCPTmetaDataPath
     */
    public void setUCPTmetaDataPath(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path UCPTmetaDataPath) {
        this.UCPTmetaDataPath = UCPTmetaDataPath;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dp_Data)) return false;
        Dp_Data other = (Dp_Data) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTvalue==null && other.getUCPTvalue()==null) || 
             (this.UCPTvalue!=null &&
              java.util.Arrays.equals(this.UCPTvalue, other.getUCPTvalue()))) &&
            ((this.UCPTpointStatus==null && other.getUCPTpointStatus()==null) || 
             (this.UCPTpointStatus!=null &&
              this.UCPTpointStatus.equals(other.getUCPTpointStatus()))) &&
            ((this.UCPTpriority==null && other.getUCPTpriority()==null) || 
             (this.UCPTpriority!=null &&
              this.UCPTpriority.equals(other.getUCPTpriority()))) &&
            ((this.UCPTmaxAge==null && other.getUCPTmaxAge()==null) || 
             (this.UCPTmaxAge!=null &&
              this.UCPTmaxAge.equals(other.getUCPTmaxAge()))) &&
            ((this.UCPTpropagate==null && other.getUCPTpropagate()==null) || 
             (this.UCPTpropagate!=null &&
              this.UCPTpropagate.equals(other.getUCPTpropagate()))) &&
            ((this.UCPTmetaDataPath==null && other.getUCPTmetaDataPath()==null) || 
             (this.UCPTmetaDataPath!=null &&
              this.UCPTmetaDataPath.equals(other.getUCPTmetaDataPath())));
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
        if (getUCPTvalue() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTvalue());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTvalue(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTpointStatus() != null) {
            _hashCode += getUCPTpointStatus().hashCode();
        }
        if (getUCPTpriority() != null) {
            _hashCode += getUCPTpriority().hashCode();
        }
        if (getUCPTmaxAge() != null) {
            _hashCode += getUCPTmaxAge().hashCode();
        }
        if (getUCPTpropagate() != null) {
            _hashCode += getUCPTpropagate().hashCode();
        }
        if (getUCPTmetaDataPath() != null) {
            _hashCode += getUCPTmetaDataPath().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dp_Data.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_Data"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTvalue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTvalue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpointStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpointStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpriority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpriority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxAge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxAge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpropagate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpropagate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmetaDataPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmetaDataPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Path"));
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
