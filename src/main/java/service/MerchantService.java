package service;

import model.Merchant;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MerchantService {

    @WebMethod
    String isRunning();

    @WebMethod
    Merchant createMerchant(Merchant merchant);

    @WebMethod
    Merchant[] getListMerchant();

    @WebMethod
    Merchant modifyMerchant(Merchant merchant);

    @WebMethod
    boolean deleteMerchant(Integer merchantId);
}
