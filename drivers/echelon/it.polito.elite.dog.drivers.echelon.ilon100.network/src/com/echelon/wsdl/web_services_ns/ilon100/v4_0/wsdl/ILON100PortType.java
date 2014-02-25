/**
 * ILON100PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl;

public interface ILON100PortType extends java.rmi.Remote {
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll list(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_xSelect iLonItem) throws java.rmi.RemoteException;
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl get(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException;
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll set(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl iLonItem) throws java.rmi.RemoteException;
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll delete(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException;
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl read(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException;
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll write(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl iLonItem) throws java.rmi.RemoteException;
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll clear(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException;
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll invokeCmd(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException;
}
