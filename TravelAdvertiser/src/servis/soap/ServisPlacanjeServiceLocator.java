/**
 * ServisPlacanjeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package servis.soap;

public class ServisPlacanjeServiceLocator extends org.apache.axis.client.Service implements servis.soap.ServisPlacanjeService {

    public ServisPlacanjeServiceLocator() {
    }


    public ServisPlacanjeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServisPlacanjeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServisPlacanje
    private java.lang.String ServisPlacanje_address = "http://localhost:8080/IPBankaSOAP/services/ServisPlacanje";

    public java.lang.String getServisPlacanjeAddress() {
        return ServisPlacanje_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServisPlacanjeWSDDServiceName = "ServisPlacanje";

    public java.lang.String getServisPlacanjeWSDDServiceName() {
        return ServisPlacanjeWSDDServiceName;
    }

    public void setServisPlacanjeWSDDServiceName(java.lang.String name) {
        ServisPlacanjeWSDDServiceName = name;
    }

    public clients.ServisPlacanje getServisPlacanje() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServisPlacanje_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServisPlacanje(endpoint);
    }

    public clients.ServisPlacanje getServisPlacanje(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            servis.soap.ServisPlacanjeSoapBindingStub _stub = new servis.soap.ServisPlacanjeSoapBindingStub(portAddress, this);
            _stub.setPortName(getServisPlacanjeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServisPlacanjeEndpointAddress(java.lang.String address) {
        ServisPlacanje_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (clients.ServisPlacanje.class.isAssignableFrom(serviceEndpointInterface)) {
                servis.soap.ServisPlacanjeSoapBindingStub _stub = new servis.soap.ServisPlacanjeSoapBindingStub(new java.net.URL(ServisPlacanje_address), this);
                _stub.setPortName(getServisPlacanjeWSDDServiceName());
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
        if ("ServisPlacanje".equals(inputPortName)) {
            return getServisPlacanje();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.servis", "ServisPlacanjeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.servis", "ServisPlacanje"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServisPlacanje".equals(portName)) {
            setServisPlacanjeEndpointAddress(address);
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
