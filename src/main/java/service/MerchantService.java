package service;

import model.Merchant;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MerchantService {

    @WebMethod
    Merchant createMerchant(Merchant merchant);

    @WebMethod
    Merchant[] getListMerchant();

    @WebMethod
    Merchant getMerchant(Integer id);

    @WebMethod
    Merchant modifyMerchant(Merchant merchant);

    @WebMethod
    void deleteMerchant(Merchant merchantId);
}
