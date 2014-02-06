/**
 * ILON100Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl;

@SuppressWarnings("rawtypes")
public class ILON100Locator extends org.apache.axis.client.Service implements com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100 {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ILON100Locator() {
    }


    public ILON100Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ILON100Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for iLON100httpPort
    private java.lang.String iLON100httpPort_address = "http://localhost/WSDL/iLON100.wsdl";

    public java.lang.String getiLON100httpPortAddress() {
        return iLON100httpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String iLON100httpPortWSDDServiceName = "iLON100httpPort";

    public java.lang.String getiLON100httpPortWSDDServiceName() {
        return iLON100httpPortWSDDServiceName;
    }

    public void setiLON100httpPortWSDDServiceName(java.lang.String name) {
        iLON100httpPortWSDDServiceName = name;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100PortType getiLON100httpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(iLON100httpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getiLON100httpPort(endpoint);
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100PortType getiLON100httpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100Soap11BindingStub _stub = new com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100Soap11BindingStub(portAddress, this);
            _stub.setPortName(getiLON100httpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setiLON100httpPortEndpointAddress(java.lang.String address) {
        iLON100httpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100Soap11BindingStub _stub = new com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100Soap11BindingStub(new java.net.URL(iLON100httpPort_address), this);
                _stub.setPortName(getiLON100httpPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("iLON100httpPort".equals(inputPortName)) {
            return getiLON100httpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/wsdl/", "iLON100");
    }

    private java.util.HashSet ports = null;

    @SuppressWarnings("unchecked")
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/wsdl/", "iLON100httpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("iLON100httpPort".equals(portName)) {
            setiLON100httpPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
