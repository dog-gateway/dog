/**
 * UFPTalarmGenerator_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Generalized configuration type of the 'UFPTalarmGenerator'.
 * 					Example:  xSelect="//Item[@xsi:type="UFPTalarmGenerator_Cfg"]"
 * 					Example:  xSelect="//Item[@xsi:type="UFPTalarmGenerator_Cfg"][starts-with(UCPTname,'Net/LON/BAS/')]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTalarmGenerator_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double UCPTalrmIhbD;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTalarmPriority;

    private double UCPTpollOnResetDelay;

    private double UCPTpollRate;

    private java.lang.String UCPTalarm2Description;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompFunction;

    private double UCPTalarmSetTimeD;

    private double UCPTalarmClrTimeD;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlowLimit1Offset;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlowLimit2Offset;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPThighLimit1Offset;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPThighLimit2Offset;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystHigh1;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystHigh2;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystLow1;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystLow2;

    public UFPTalarmGenerator_Cfg() {
    }

    public UFPTalarmGenerator_Cfg(
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
           double UCPTalrmIhbD,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTalarmPriority,
           double UCPTpollOnResetDelay,
           double UCPTpollRate,
           java.lang.String UCPTalarm2Description,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompFunction,
           double UCPTalarmSetTimeD,
           double UCPTalarmClrTimeD,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlowLimit1Offset,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlowLimit2Offset,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPThighLimit1Offset,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPThighLimit2Offset,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystHigh1,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystHigh2,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystLow1,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystLow2) {
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
        this.UCPTalrmIhbD = UCPTalrmIhbD;
        this.UCPTalarmPriority = UCPTalarmPriority;
        this.UCPTpollOnResetDelay = UCPTpollOnResetDelay;
        this.UCPTpollRate = UCPTpollRate;
        this.UCPTalarm2Description = UCPTalarm2Description;
        this.UCPTcompFunction = UCPTcompFunction;
        this.UCPTalarmSetTimeD = UCPTalarmSetTimeD;
        this.UCPTalarmClrTimeD = UCPTalarmClrTimeD;
        this.UCPTlowLimit1Offset = UCPTlowLimit1Offset;
        this.UCPTlowLimit2Offset = UCPTlowLimit2Offset;
        this.UCPThighLimit1Offset = UCPThighLimit1Offset;
        this.UCPThighLimit2Offset = UCPThighLimit2Offset;
        this.SCPThystHigh1 = SCPThystHigh1;
        this.SCPThystHigh2 = SCPThystHigh2;
        this.SCPThystLow1 = SCPThystLow1;
        this.SCPThystLow2 = SCPThystLow2;
    }


    /**
     * Gets the UCPTalrmIhbD value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPTalrmIhbD
     */
    public double getUCPTalrmIhbD() {
        return UCPTalrmIhbD;
    }


    /**
     * Sets the UCPTalrmIhbD value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPTalrmIhbD
     */
    public void setUCPTalrmIhbD(double UCPTalrmIhbD) {
        this.UCPTalrmIhbD = UCPTalrmIhbD;
    }


    /**
     * Gets the UCPTalarmPriority value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPTalarmPriority
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTalarmPriority() {
        return UCPTalarmPriority;
    }


    /**
     * Sets the UCPTalarmPriority value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPTalarmPriority
     */
    public void setUCPTalarmPriority(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTalarmPriority) {
        this.UCPTalarmPriority = UCPTalarmPriority;
    }


    /**
     * Gets the UCPTpollOnResetDelay value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPTpollOnResetDelay
     */
    public double getUCPTpollOnResetDelay() {
        return UCPTpollOnResetDelay;
    }


    /**
     * Sets the UCPTpollOnResetDelay value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPTpollOnResetDelay
     */
    public void setUCPTpollOnResetDelay(double UCPTpollOnResetDelay) {
        this.UCPTpollOnResetDelay = UCPTpollOnResetDelay;
    }


    /**
     * Gets the UCPTpollRate value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPTpollRate
     */
    public double getUCPTpollRate() {
        return UCPTpollRate;
    }


    /**
     * Sets the UCPTpollRate value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPTpollRate
     */
    public void setUCPTpollRate(double UCPTpollRate) {
        this.UCPTpollRate = UCPTpollRate;
    }


    /**
     * Gets the UCPTalarm2Description value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPTalarm2Description
     */
    public java.lang.String getUCPTalarm2Description() {
        return UCPTalarm2Description;
    }


    /**
     * Sets the UCPTalarm2Description value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPTalarm2Description
     */
    public void setUCPTalarm2Description(java.lang.String UCPTalarm2Description) {
        this.UCPTalarm2Description = UCPTalarm2Description;
    }


    /**
     * Gets the UCPTcompFunction value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPTcompFunction
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTcompFunction() {
        return UCPTcompFunction;
    }


    /**
     * Sets the UCPTcompFunction value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPTcompFunction
     */
    public void setUCPTcompFunction(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcompFunction) {
        this.UCPTcompFunction = UCPTcompFunction;
    }


    /**
     * Gets the UCPTalarmSetTimeD value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPTalarmSetTimeD
     */
    public double getUCPTalarmSetTimeD() {
        return UCPTalarmSetTimeD;
    }


    /**
     * Sets the UCPTalarmSetTimeD value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPTalarmSetTimeD
     */
    public void setUCPTalarmSetTimeD(double UCPTalarmSetTimeD) {
        this.UCPTalarmSetTimeD = UCPTalarmSetTimeD;
    }


    /**
     * Gets the UCPTalarmClrTimeD value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPTalarmClrTimeD
     */
    public double getUCPTalarmClrTimeD() {
        return UCPTalarmClrTimeD;
    }


    /**
     * Sets the UCPTalarmClrTimeD value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPTalarmClrTimeD
     */
    public void setUCPTalarmClrTimeD(double UCPTalarmClrTimeD) {
        this.UCPTalarmClrTimeD = UCPTalarmClrTimeD;
    }


    /**
     * Gets the UCPTlowLimit1Offset value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPTlowLimit1Offset
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTlowLimit1Offset() {
        return UCPTlowLimit1Offset;
    }


    /**
     * Sets the UCPTlowLimit1Offset value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPTlowLimit1Offset
     */
    public void setUCPTlowLimit1Offset(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlowLimit1Offset) {
        this.UCPTlowLimit1Offset = UCPTlowLimit1Offset;
    }


    /**
     * Gets the UCPTlowLimit2Offset value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPTlowLimit2Offset
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTlowLimit2Offset() {
        return UCPTlowLimit2Offset;
    }


    /**
     * Sets the UCPTlowLimit2Offset value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPTlowLimit2Offset
     */
    public void setUCPTlowLimit2Offset(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTlowLimit2Offset) {
        this.UCPTlowLimit2Offset = UCPTlowLimit2Offset;
    }


    /**
     * Gets the UCPThighLimit1Offset value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPThighLimit1Offset
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPThighLimit1Offset() {
        return UCPThighLimit1Offset;
    }


    /**
     * Sets the UCPThighLimit1Offset value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPThighLimit1Offset
     */
    public void setUCPThighLimit1Offset(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPThighLimit1Offset) {
        this.UCPThighLimit1Offset = UCPThighLimit1Offset;
    }


    /**
     * Gets the UCPThighLimit2Offset value for this UFPTalarmGenerator_Cfg.
     * 
     * @return UCPThighLimit2Offset
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPThighLimit2Offset() {
        return UCPThighLimit2Offset;
    }


    /**
     * Sets the UCPThighLimit2Offset value for this UFPTalarmGenerator_Cfg.
     * 
     * @param UCPThighLimit2Offset
     */
    public void setUCPThighLimit2Offset(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPThighLimit2Offset) {
        this.UCPThighLimit2Offset = UCPThighLimit2Offset;
    }


    /**
     * Gets the SCPThystHigh1 value for this UFPTalarmGenerator_Cfg.
     * 
     * @return SCPThystHigh1
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getSCPThystHigh1() {
        return SCPThystHigh1;
    }


    /**
     * Sets the SCPThystHigh1 value for this UFPTalarmGenerator_Cfg.
     * 
     * @param SCPThystHigh1
     */
    public void setSCPThystHigh1(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystHigh1) {
        this.SCPThystHigh1 = SCPThystHigh1;
    }


    /**
     * Gets the SCPThystHigh2 value for this UFPTalarmGenerator_Cfg.
     * 
     * @return SCPThystHigh2
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getSCPThystHigh2() {
        return SCPThystHigh2;
    }


    /**
     * Sets the SCPThystHigh2 value for this UFPTalarmGenerator_Cfg.
     * 
     * @param SCPThystHigh2
     */
    public void setSCPThystHigh2(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystHigh2) {
        this.SCPThystHigh2 = SCPThystHigh2;
    }


    /**
     * Gets the SCPThystLow1 value for this UFPTalarmGenerator_Cfg.
     * 
     * @return SCPThystLow1
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getSCPThystLow1() {
        return SCPThystLow1;
    }


    /**
     * Sets the SCPThystLow1 value for this UFPTalarmGenerator_Cfg.
     * 
     * @param SCPThystLow1
     */
    public void setSCPThystLow1(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystLow1) {
        this.SCPThystLow1 = SCPThystLow1;
    }


    /**
     * Gets the SCPThystLow2 value for this UFPTalarmGenerator_Cfg.
     * 
     * @return SCPThystLow2
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getSCPThystLow2() {
        return SCPThystLow2;
    }


    /**
     * Sets the SCPThystLow2 value for this UFPTalarmGenerator_Cfg.
     * 
     * @param SCPThystLow2
     */
    public void setSCPThystLow2(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString SCPThystLow2) {
        this.SCPThystLow2 = SCPThystLow2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTalarmGenerator_Cfg)) return false;
        UFPTalarmGenerator_Cfg other = (UFPTalarmGenerator_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.UCPTalrmIhbD == other.getUCPTalrmIhbD() &&
            ((this.UCPTalarmPriority==null && other.getUCPTalarmPriority()==null) || 
             (this.UCPTalarmPriority!=null &&
              this.UCPTalarmPriority.equals(other.getUCPTalarmPriority()))) &&
            this.UCPTpollOnResetDelay == other.getUCPTpollOnResetDelay() &&
            this.UCPTpollRate == other.getUCPTpollRate() &&
            ((this.UCPTalarm2Description==null && other.getUCPTalarm2Description()==null) || 
             (this.UCPTalarm2Description!=null &&
              this.UCPTalarm2Description.equals(other.getUCPTalarm2Description()))) &&
            ((this.UCPTcompFunction==null && other.getUCPTcompFunction()==null) || 
             (this.UCPTcompFunction!=null &&
              this.UCPTcompFunction.equals(other.getUCPTcompFunction()))) &&
            this.UCPTalarmSetTimeD == other.getUCPTalarmSetTimeD() &&
            this.UCPTalarmClrTimeD == other.getUCPTalarmClrTimeD() &&
            ((this.UCPTlowLimit1Offset==null && other.getUCPTlowLimit1Offset()==null) || 
             (this.UCPTlowLimit1Offset!=null &&
              this.UCPTlowLimit1Offset.equals(other.getUCPTlowLimit1Offset()))) &&
            ((this.UCPTlowLimit2Offset==null && other.getUCPTlowLimit2Offset()==null) || 
             (this.UCPTlowLimit2Offset!=null &&
              this.UCPTlowLimit2Offset.equals(other.getUCPTlowLimit2Offset()))) &&
            ((this.UCPThighLimit1Offset==null && other.getUCPThighLimit1Offset()==null) || 
             (this.UCPThighLimit1Offset!=null &&
              this.UCPThighLimit1Offset.equals(other.getUCPThighLimit1Offset()))) &&
            ((this.UCPThighLimit2Offset==null && other.getUCPThighLimit2Offset()==null) || 
             (this.UCPThighLimit2Offset!=null &&
              this.UCPThighLimit2Offset.equals(other.getUCPThighLimit2Offset()))) &&
            ((this.SCPThystHigh1==null && other.getSCPThystHigh1()==null) || 
             (this.SCPThystHigh1!=null &&
              this.SCPThystHigh1.equals(other.getSCPThystHigh1()))) &&
            ((this.SCPThystHigh2==null && other.getSCPThystHigh2()==null) || 
             (this.SCPThystHigh2!=null &&
              this.SCPThystHigh2.equals(other.getSCPThystHigh2()))) &&
            ((this.SCPThystLow1==null && other.getSCPThystLow1()==null) || 
             (this.SCPThystLow1!=null &&
              this.SCPThystLow1.equals(other.getSCPThystLow1()))) &&
            ((this.SCPThystLow2==null && other.getSCPThystLow2()==null) || 
             (this.SCPThystLow2!=null &&
              this.SCPThystLow2.equals(other.getSCPThystLow2())));
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
        _hashCode += new Double(getUCPTalrmIhbD()).hashCode();
        if (getUCPTalarmPriority() != null) {
            _hashCode += getUCPTalarmPriority().hashCode();
        }
        _hashCode += new Double(getUCPTpollOnResetDelay()).hashCode();
        _hashCode += new Double(getUCPTpollRate()).hashCode();
        if (getUCPTalarm2Description() != null) {
            _hashCode += getUCPTalarm2Description().hashCode();
        }
        if (getUCPTcompFunction() != null) {
            _hashCode += getUCPTcompFunction().hashCode();
        }
        _hashCode += new Double(getUCPTalarmSetTimeD()).hashCode();
        _hashCode += new Double(getUCPTalarmClrTimeD()).hashCode();
        if (getUCPTlowLimit1Offset() != null) {
            _hashCode += getUCPTlowLimit1Offset().hashCode();
        }
        if (getUCPTlowLimit2Offset() != null) {
            _hashCode += getUCPTlowLimit2Offset().hashCode();
        }
        if (getUCPThighLimit1Offset() != null) {
            _hashCode += getUCPThighLimit1Offset().hashCode();
        }
        if (getUCPThighLimit2Offset() != null) {
            _hashCode += getUCPThighLimit2Offset().hashCode();
        }
        if (getSCPThystHigh1() != null) {
            _hashCode += getSCPThystHigh1().hashCode();
        }
        if (getSCPThystHigh2() != null) {
            _hashCode += getSCPThystHigh2().hashCode();
        }
        if (getSCPThystLow1() != null) {
            _hashCode += getSCPThystLow1().hashCode();
        }
        if (getSCPThystLow2() != null) {
            _hashCode += getSCPThystLow2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTalarmGenerator_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmGenerator_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalrmIhbD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalrmIhbD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmPriority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmPriority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpollOnResetDelay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpollOnResetDelay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTpollRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTpollRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarm2Description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarm2Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcompFunction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcompFunction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmSetTimeD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmSetTimeD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTalarmClrTimeD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTalarmClrTimeD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlowLimit1Offset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlowLimit1Offset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlowLimit2Offset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlowLimit2Offset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThighLimit1Offset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThighLimit1Offset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThighLimit2Offset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThighLimit2Offset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPThystHigh1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPThystHigh1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPThystHigh2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPThystHigh2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPThystLow1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPThystLow1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SCPThystLow2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "SCPThystLow2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
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
