/**
 * UFPTanalogFunctionBlock_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized configuration type of the 'UFPTanalogFunctionBlock'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTanalogFunctionBlock_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="UFPTanalogFunctionBlock_Cfg"][starts-with(UCPTname,'Net/LON/BAS/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTanalogFunctionBlock_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompFunction;

    private float UCPTmajorityValue;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtrueThreshold;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPToutputFunction;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTminRnge;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTmaxRnge;

    private double UCPTcalculationInterval;

    private java.lang.Integer UCPTcalculationMatrix;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTovrBehave;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTovrValue;

    private double UCPTpollOnResetDelay;

    public UFPTanalogFunctionBlock_Cfg() {
    }

    public UFPTanalogFunctionBlock_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef[] dataPoint,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompFunction,
           float UCPTmajorityValue,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtrueThreshold,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPToutputFunction,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTminRnge,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTmaxRnge,
           double UCPTcalculationInterval,
           java.lang.Integer UCPTcalculationMatrix,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTovrBehave,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTovrValue,
           double UCPTpollOnResetDelay) {
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
            dataPoint);
        this.UCPTcompFunction = UCPTcompFunction;
        this.UCPTmajorityValue = UCPTmajorityValue;
        this.UCPTtrueThreshold = UCPTtrueThreshold;
        this.UCPToutputFunction = UCPToutputFunction;
        this.SCPTminRnge = SCPTminRnge;
        this.SCPTmaxRnge = SCPTmaxRnge;
        this.UCPTcalculationInterval = UCPTcalculationInterval;
        this.UCPTcalculationMatrix = UCPTcalculationMatrix;
        this.SCPTovrBehave = SCPTovrBehave;
        this.SCPTovrValue = SCPTovrValue;
        this.UCPTpollOnResetDelay = UCPTpollOnResetDelay;
    }


    /**
     * Gets the UCPTcompFunction value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return UCPTcompFunction
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTcompFunction() {
        return UCPTcompFunction;
    }


    /**
     * Sets the UCPTcompFunction value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param UCPTcompFunction
     */
    public void setUCPTcompFunction(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompFunction) {
        this.UCPTcompFunction = UCPTcompFunction;
    }


    /**
     * Gets the UCPTmajorityValue value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return UCPTmajorityValue
     */
    public float getUCPTmajorityValue() {
        return UCPTmajorityValue;
    }


    /**
     * Sets the UCPTmajorityValue value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param UCPTmajorityValue
     */
    public void setUCPTmajorityValue(float UCPTmajorityValue) {
        this.UCPTmajorityValue = UCPTmajorityValue;
    }


    /**
     * Gets the UCPTtrueThreshold value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return UCPTtrueThreshold
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTtrueThreshold() {
        return UCPTtrueThreshold;
    }


    /**
     * Sets the UCPTtrueThreshold value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param UCPTtrueThreshold
     */
    public void setUCPTtrueThreshold(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTtrueThreshold) {
        this.UCPTtrueThreshold = UCPTtrueThreshold;
    }


    /**
     * Gets the UCPToutputFunction value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return UCPToutputFunction
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPToutputFunction() {
        return UCPToutputFunction;
    }


    /**
     * Sets the UCPToutputFunction value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param UCPToutputFunction
     */
    public void setUCPToutputFunction(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPToutputFunction) {
        this.UCPToutputFunction = UCPToutputFunction;
    }


    /**
     * Gets the SCPTminRnge value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return SCPTminRnge
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getSCPTminRnge() {
        return SCPTminRnge;
    }


    /**
     * Sets the SCPTminRnge value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param SCPTminRnge
     */
    public void setSCPTminRnge(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTminRnge) {
        this.SCPTminRnge = SCPTminRnge;
    }


    /**
     * Gets the SCPTmaxRnge value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return SCPTmaxRnge
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getSCPTmaxRnge() {
        return SCPTmaxRnge;
    }


    /**
     * Sets the SCPTmaxRnge value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param SCPTmaxRnge
     */
    public void setSCPTmaxRnge(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTmaxRnge) {
        this.SCPTmaxRnge = SCPTmaxRnge;
    }


    /**
     * Gets the UCPTcalculationInterval value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return UCPTcalculationInterval
     */
    public double getUCPTcalculationInterval() {
        return UCPTcalculationInterval;
    }


    /**
     * Sets the UCPTcalculationInterval value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param UCPTcalculationInterval
     */
    public void setUCPTcalculationInterval(double UCPTcalculationInterval) {
        this.UCPTcalculationInterval = UCPTcalculationInterval;
    }


    /**
     * Gets the UCPTcalculationMatrix value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return UCPTcalculationMatrix
     */
    public java.lang.Integer getUCPTcalculationMatrix() {
        return UCPTcalculationMatrix;
    }


    /**
     * Sets the UCPTcalculationMatrix value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param UCPTcalculationMatrix
     */
    public void setUCPTcalculationMatrix(java.lang.Integer UCPTcalculationMatrix) {
        this.UCPTcalculationMatrix = UCPTcalculationMatrix;
    }


    /**
     * Gets the SCPTovrBehave value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return SCPTovrBehave
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getSCPTovrBehave() {
        return SCPTovrBehave;
    }


    /**
     * Sets the SCPTovrBehave value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param SCPTovrBehave
     */
    public void setSCPTovrBehave(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTovrBehave) {
        this.SCPTovrBehave = SCPTovrBehave;
    }


    /**
     * Gets the SCPTovrValue value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return SCPTovrValue
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getSCPTovrValue() {
        return SCPTovrValue;
    }


    /**
     * Sets the SCPTovrValue value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param SCPTovrValue
     */
    public void setSCPTovrValue(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPTovrValue) {
        this.SCPTovrValue = SCPTovrValue;
    }


    /**
     * Gets the UCPTpollOnResetDelay value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @return UCPTpollOnResetDelay
     */
    public double getUCPTpollOnResetDelay() {
        return UCPTpollOnResetDelay;
    }


    /**
     * Sets the UCPTpollOnResetDelay value for this UFPTanalogFunctionBlock_Cfg.
     * 
     * @param UCPTpollOnResetDelay
     */
    public void setUCPTpollOnResetDelay(double UCPTpollOnResetDelay) {
        this.UCPTpollOnResetDelay = UCPTpollOnResetDelay;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTanalogFunctionBlock_Cfg)) return false;
        UFPTanalogFunctionBlock_Cfg other = (UFPTanalogFunctionBlock_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTcompFunction==null && other.getUCPTcompFunction()==null) || 
             (this.UCPTcompFunction!=null &&
              this.UCPTcompFunction.equals(other.getUCPTcompFunction()))) &&
            this.UCPTmajorityValue == other.getUCPTmajorityValue() &&
            ((this.UCPTtrueThreshold==null && other.getUCPTtrueThreshold()==null) || 
             (this.UCPTtrueThreshold!=null &&
              this.UCPTtrueThreshold.equals(other.getUCPTtrueThreshold()))) &&
            ((this.UCPToutputFunction==null && other.getUCPToutputFunction()==null) || 
             (this.UCPToutputFunction!=null &&
              this.UCPToutputFunction.equals(other.getUCPToutputFunction()))) &&
            ((this.SCPTminRnge==null && other.getSCPTminRnge()==null) || 
             (this.SCPTminRnge!=null &&
              this.SCPTminRnge.equals(other.getSCPTminRnge()))) &&
            ((this.SCPTmaxRnge==null && other.getSCPTmaxRnge()==null) || 
             (this.SCPTmaxRnge!=null &&
              this.SCPTmaxRnge.equals(other.getSCPTmaxRnge()))) &&
            this.UCPTcalculationInterval == other.getUCPTcalculationInterval() &&
            ((this.UCPTcalculationMatrix==null && other.getUCPTcalculationMatrix()==null) || 
             (this.UCPTcalculationMatrix!=null &&
              this.UCPTcalculationMatrix.equals(other.getUCPTcalculationMatrix()))) &&
            ((this.SCPTovrBehave==null && other.getSCPTovrBehave()==null) || 
             (this.SCPTovrBehave!=null &&
              this.SCPTovrBehave.equals(other.getSCPTovrBehave()))) &&
            ((this.SCPTovrValue==null && other.getSCPTovrValue()==null) || 
             (this.SCPTovrValue!=null &&
              this.SCPTovrValue.equals(other.getSCPTovrValue()))) &&
            this.UCPTpollOnResetDelay == other.getUCPTpollOnResetDelay();
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
        if (getUCPTcompFunction() != null) {
            _hashCode += getUCPTcompFunction().hashCode();
        }
        _hashCode += new Float(getUCPTmajorityValue()).hashCode();
        if (getUCPTtrueThreshold() != null) {
            _hashCode += getUCPTtrueThreshold().hashCode();
        }
        if (getUCPToutputFunction() != null) {
            _hashCode += getUCPToutputFunction().hashCode();
        }
        if (getSCPTminRnge() != null) {
            _hashCode += getSCPTminRnge().hashCode();
        }
        if (getSCPTmaxRnge() != null) {
            _hashCode += getSCPTmaxRnge().hashCode();
        }
        _hashCode += new Double(getUCPTcalculationInterval()).hashCode();
        if (getUCPTcalculationMatrix() != null) {
            _hashCode += getUCPTcalculationMatrix().hashCode();
        }
        if (getSCPTovrBehave() != null) {
            _hashCode += getSCPTovrBehave().hashCode();
        }
        if (getSCPTovrValue() != null) {
            _hashCode += getSCPTovrValue().hashCode();
        }
        _hashCode += new Double(getUCPTpollOnResetDelay()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTanalogFunctionBlock_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTanalogFunctionBlock_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcompFunction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcompFunction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmajorityValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmajorityValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTtrueThreshold");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtrueThreshold"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPToutputFunction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPToutputFunction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPTminRnge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPTminRnge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPTmaxRnge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPTmaxRnge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcalculationInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcalculationInterval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcalculationMatrix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcalculationMatrix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPTovrBehave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPTovrBehave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPTovrValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPTovrValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpollOnResetDelay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpollOnResetDelay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
