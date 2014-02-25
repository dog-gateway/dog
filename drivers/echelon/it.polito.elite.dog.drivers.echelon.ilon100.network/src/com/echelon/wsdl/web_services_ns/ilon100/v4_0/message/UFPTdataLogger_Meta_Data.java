/**
 * UFPTdataLogger_Meta_Data.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Gneralized meta-data type of the 'UFPTdataLogger'. It'll only be
 * responded (never used for a request).
 */
@SuppressWarnings({"rawtypes","unused"})
public class UFPTdataLogger_Meta_Data  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Data  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.util.Calendar UCPTstart;

    private java.util.Calendar UCPTstop;

    private int UCPTmodificationNumber;

    private float UCPTlogLevel;

    private int UCPTtotalCount;

    public UFPTdataLogger_Meta_Data() {
    }

    public UFPTdataLogger_Meta_Data(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           java.util.Calendar UCPTstart,
           java.util.Calendar UCPTstop,
           int UCPTmodificationNumber,
           float UCPTlogLevel,
           int UCPTtotalCount) {
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
        this.UCPTstart = UCPTstart;
        this.UCPTstop = UCPTstop;
        this.UCPTmodificationNumber = UCPTmodificationNumber;
        this.UCPTlogLevel = UCPTlogLevel;
        this.UCPTtotalCount = UCPTtotalCount;
    }


    /**
     * Gets the UCPTstart value for this UFPTdataLogger_Meta_Data.
     * 
     * @return UCPTstart
     */
    public java.util.Calendar getUCPTstart() {
        return UCPTstart;
    }


    /**
     * Sets the UCPTstart value for this UFPTdataLogger_Meta_Data.
     * 
     * @param UCPTstart
     */
    public void setUCPTstart(java.util.Calendar UCPTstart) {
        this.UCPTstart = UCPTstart;
    }


    /**
     * Gets the UCPTstop value for this UFPTdataLogger_Meta_Data.
     * 
     * @return UCPTstop
     */
    public java.util.Calendar getUCPTstop() {
        return UCPTstop;
    }


    /**
     * Sets the UCPTstop value for this UFPTdataLogger_Meta_Data.
     * 
     * @param UCPTstop
     */
    public void setUCPTstop(java.util.Calendar UCPTstop) {
        this.UCPTstop = UCPTstop;
    }


    /**
     * Gets the UCPTmodificationNumber value for this UFPTdataLogger_Meta_Data.
     * 
     * @return UCPTmodificationNumber
     */
    public int getUCPTmodificationNumber() {
        return UCPTmodificationNumber;
    }


    /**
     * Sets the UCPTmodificationNumber value for this UFPTdataLogger_Meta_Data.
     * 
     * @param UCPTmodificationNumber
     */
    public void setUCPTmodificationNumber(int UCPTmodificationNumber) {
        this.UCPTmodificationNumber = UCPTmodificationNumber;
    }


    /**
     * Gets the UCPTlogLevel value for this UFPTdataLogger_Meta_Data.
     * 
     * @return UCPTlogLevel
     */
    public float getUCPTlogLevel() {
        return UCPTlogLevel;
    }


    /**
     * Sets the UCPTlogLevel value for this UFPTdataLogger_Meta_Data.
     * 
     * @param UCPTlogLevel
     */
    public void setUCPTlogLevel(float UCPTlogLevel) {
        this.UCPTlogLevel = UCPTlogLevel;
    }


    /**
     * Gets the UCPTtotalCount value for this UFPTdataLogger_Meta_Data.
     * 
     * @return UCPTtotalCount
     */
    public int getUCPTtotalCount() {
        return UCPTtotalCount;
    }


    /**
     * Sets the UCPTtotalCount value for this UFPTdataLogger_Meta_Data.
     * 
     * @param UCPTtotalCount
     */
    public void setUCPTtotalCount(int UCPTtotalCount) {
        this.UCPTtotalCount = UCPTtotalCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UFPTdataLogger_Meta_Data)) return false;
        UFPTdataLogger_Meta_Data other = (UFPTdataLogger_Meta_Data) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPTstart==null && other.getUCPTstart()==null) || 
             (this.UCPTstart!=null &&
              this.UCPTstart.equals(other.getUCPTstart()))) &&
            ((this.UCPTstop==null && other.getUCPTstop()==null) || 
             (this.UCPTstop!=null &&
              this.UCPTstop.equals(other.getUCPTstop()))) &&
            this.UCPTmodificationNumber == other.getUCPTmodificationNumber() &&
            this.UCPTlogLevel == other.getUCPTlogLevel() &&
            this.UCPTtotalCount == other.getUCPTtotalCount();
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
        if (getUCPTstart() != null) {
            _hashCode += getUCPTstart().hashCode();
        }
        if (getUCPTstop() != null) {
            _hashCode += getUCPTstop().hashCode();
        }
        _hashCode += getUCPTmodificationNumber();
        _hashCode += new Float(getUCPTlogLevel()).hashCode();
        _hashCode += getUCPTtotalCount();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UFPTdataLogger_Meta_Data.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdataLogger_Meta_Data"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstart");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstart"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTstop");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTstop"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmodificationNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmodificationNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlogLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlogLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTtotalCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTtotalCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
