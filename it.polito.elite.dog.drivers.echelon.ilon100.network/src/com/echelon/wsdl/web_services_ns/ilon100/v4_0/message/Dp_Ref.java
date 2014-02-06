/**
 * Dp_Ref.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * Service configuration type of the service 'WebBinder'.
 * 					Example:  xSelect="//Item[@xsi:type="Dp_Ref"]"
 */
@SuppressWarnings({"rawtypes","unused"})
public class Dp_Ref  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_RefDataPoint[] dataPoint;

    public Dp_Ref() {
    }

    public Dp_Ref(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_RefDataPoint[] dataPoint) {
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
        this.dataPoint = dataPoint;
    }


    /**
     * Gets the dataPoint value for this Dp_Ref.
     * 
     * @return dataPoint
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_RefDataPoint[] getDataPoint() {
        return dataPoint;
    }


    /**
     * Sets the dataPoint value for this Dp_Ref.
     * 
     * @param dataPoint
     */
    public void setDataPoint(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_RefDataPoint[] dataPoint) {
        this.dataPoint = dataPoint;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_RefDataPoint getDataPoint(int i) {
        return this.dataPoint[i];
    }

    public void setDataPoint(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_RefDataPoint _value) {
        this.dataPoint[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dp_Ref)) return false;
        Dp_Ref other = (Dp_Ref) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.dataPoint==null && other.getDataPoint()==null) || 
             (this.dataPoint!=null &&
              java.util.Arrays.equals(this.dataPoint, other.getDataPoint())));
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
        if (getDataPoint() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDataPoint());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDataPoint(), i);
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
        new org.apache.axis.description.TypeDesc(Dp_Ref.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_Ref"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPoint");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "DataPoint"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">Dp_Ref>DataPoint"));
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
