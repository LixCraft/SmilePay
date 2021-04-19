package service;

import model.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ProductService {

    @WebMethod
    Product createProduct(Product product);

    @WebMethod
    Product[] getListProduct();

    @WebMethod
    Product getProduct(Integer id);

    @WebMethod
    Product modifyProduct(Product product);

    @WebMethod
    void deleteProduct(Product product);
}
