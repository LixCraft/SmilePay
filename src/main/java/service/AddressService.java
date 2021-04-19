package service;

import model.Address;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AddressService {

    @WebMethod
    Address createAddress(Address address);

    @WebMethod
    Address[] getListAddress();

    @WebMethod
    Address getAddress(Integer id);

    @WebMethod
    Address modifyAddress(Address address);

    @WebMethod
    void deleteAddress(Address address);
    
}
