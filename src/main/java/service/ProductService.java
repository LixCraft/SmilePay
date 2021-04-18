package service;

import model.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ProductService {

    @WebMethod
    Product createProduct(Product product);

    @WebMethod
    Product[] getListProduct();

    @WebMethod
    Product modifyProduct(Product product);

    @WebMethod
    boolean deleteProduct(Integer productId);
}
