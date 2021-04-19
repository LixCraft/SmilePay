package service;


import model.MerchantProduct;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MerchantProductService {

    @WebMethod
    MerchantProduct createMerchantProduct(MerchantProduct merchantProduct);

    @WebMethod
    MerchantProduct[] getListMerchantProduct();

    @WebMethod
    MerchantProduct getMerchantProduct(Integer id);

    @WebMethod
    MerchantProduct modifyMerchantProduct(MerchantProduct merchantProduct);

    @WebMethod
    void deleteMerchantProduct(MerchantProduct merchantProduct);

}
