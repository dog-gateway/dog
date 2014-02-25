package com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl;

public class ILON100PortTypeProxy implements com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100PortType {
  private String _endpoint = null;
  private com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100PortType iLON100PortType = null;
  
  public ILON100PortTypeProxy() {
    _initILON100PortTypeProxy();
  }
  
  public ILON100PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initILON100PortTypeProxy();
  }
  
  private void _initILON100PortTypeProxy() {
    try {
      iLON100PortType = (new com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100Locator()).getiLON100httpPort();
      if (iLON100PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iLON100PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iLON100PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iLON100PortType != null)
      ((javax.xml.rpc.Stub)iLON100PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100PortType getILON100PortType() {
    if (iLON100PortType == null)
      _initILON100PortTypeProxy();
    return iLON100PortType;
  }
  
  public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll list(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_xSelect iLonItem) throws java.rmi.RemoteException{
    if (iLON100PortType == null)
      _initILON100PortTypeProxy();
    return iLON100PortType.list(iLonItem);
  }
  
  public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl get(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException{
    if (iLON100PortType == null)
      _initILON100PortTypeProxy();
    return iLON100PortType.get(iLonItem);
  }
  
  public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll set(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl iLonItem) throws java.rmi.RemoteException{
    if (iLON100PortType == null)
      _initILON100PortTypeProxy();
    return iLON100PortType.set(iLonItem);
  }
  
  public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll delete(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException{
    if (iLON100PortType == null)
      _initILON100PortTypeProxy();
    return iLON100PortType.delete(iLonItem);
  }
  
  public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl read(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException{
    if (iLON100PortType == null)
      _initILON100PortTypeProxy();
    return iLON100PortType.read(iLonItem);
  }
  
  public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll write(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl iLonItem) throws java.rmi.RemoteException{
    if (iLON100PortType == null)
      _initILON100PortTypeProxy();
    return iLON100PortType.write(iLonItem);
  }
  
  public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll clear(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException{
    if (iLON100PortType == null)
      _initILON100PortTypeProxy();
    return iLON100PortType.clear(iLonItem);
  }
  
  public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll invokeCmd(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException{
    if (iLON100PortType == null)
      _initILON100PortTypeProxy();
    return iLON100PortType.invokeCmd(iLonItem);
  }
  
  
}