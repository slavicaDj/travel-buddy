package servis.soap;

public class ServisPlacanjeProxy implements clients.ServisPlacanje {
  private String _endpoint = null;
  private clients.ServisPlacanje servisPlacanje = null;
  
  public ServisPlacanjeProxy() {
    _initServisPlacanjeProxy();
  }
  
  public ServisPlacanjeProxy(String endpoint) {
    _endpoint = endpoint;
    _initServisPlacanjeProxy();
  }
  
  private void _initServisPlacanjeProxy() {
    try {
      servisPlacanje = (new servis.soap.ServisPlacanjeServiceLocator()).getServisPlacanje();
      if (servisPlacanje != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servisPlacanje)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servisPlacanje)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (servisPlacanje != null)
      ((javax.xml.rpc.Stub)servisPlacanje)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public clients.ServisPlacanje getServisPlacanje() {
    if (servisPlacanje == null)
      _initServisPlacanjeProxy();
    return servisPlacanje;
  }
  
  public java.lang.String uplati(model.KlijentBanke korisnik, double iznos) throws java.rmi.RemoteException{
    if (servisPlacanje == null)
      _initServisPlacanjeProxy();
    return servisPlacanje.uplati(korisnik, iznos);
  }
  
  
}