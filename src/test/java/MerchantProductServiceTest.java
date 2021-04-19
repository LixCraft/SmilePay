import implementation.MerchantProductServiceImpl;
import implementation.MerchantServiceImpl;
import implementation.ProductServiceImpl;
import model.Merchant;
import model.MerchantProduct;
import model.Product;
import org.junit.Assert;
import org.junit.Test;
import service.MerchantProductService;
import service.MerchantService;
import service.ProductService;

import javax.xml.crypto.Data;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class MerchantProductServiceTest {

    private final static String NAME_SPACE = "http://implementation/";
    private final static String URI_WSDL_EXTENSION = "?wsdl";

    private final static String URI_ENDPOINT_MERCHANTPRODUCT = "http://localhost:9997/implementation/merchantproduct";
    private final static String NAME_MERCHANTPRODUCT_SERVICE_WSDL = "MerchantProductServiceImplService";

    private final static String URI_ENDPOINT_MERCHANT = "http://localhost:9997/implementation/merchant";
    private final static String NAME_MERCHANT_SERVICE_WSDL = "MerchantServiceImplService";

    private final static String URI_ENDPOINT_PRODUCT = "http://localhost:9997/implementation/product";
    private final static String NAME_PRODUCT_SERVICE_WSDL = "ProductServiceImplService";

    @Test
    public void merchantProductService() throws IOException {

        MerchantProductService merchantProductService = getMerchantProductEndpoint();
        MerchantService merchantService = getMerchantEndpoint();
        ProductService productService = getProductEndpoint();

        //----- START - Initialize merchant object for relation Table -----
        Merchant merchant1 = new Merchant();
        merchant1.setName("Name1");
        merchant1.setLastName("LastName1");
        merchant1.setBirthdate(new Date());
        //Step 1 test, PK must be null
        Assert.assertNull(merchant1.getId());

        merchant1 = merchantService.createMerchant(merchant1);
        //Step 2 test, PK must be retrieved by inserting object in database
        Assert.assertNotNull(merchant1.getId());
        //----- END - Initialize merchant object for relation Table -----


        //----- START - Initialize product object for relation Table -----
        Product product1 = new Product();
        product1.setLabel("Label1");
        product1.setUnitPrice(100);
        product1.setCurrency("EUR");
        product1.setWeight(20);;
        product1.setHeight(10);
        //Step 3a test, PK must be null
        Assert.assertNull(product1.getId());

        Product product2 = new Product();
        product2.setLabel("Label2");
        product2.setUnitPrice(1000);
        product2.setCurrency("EURO");
        product2.setWeight(200);;
        product2.setHeight(100);
        //Step 3b test, PK must be null
        Assert.assertNull(product2.getId());

        product1 = productService.createProduct(product1);
        //Step 4a test, PK must be retrieved by inserting object in database
        Assert.assertNotNull(product1.getId());

        product2 = productService.createProduct(product2);
        //Step 4b test, PK must be retrieved by inserting object in database
        Assert.assertNotNull(product2.getId());

        //----- END - Initialize product object for relation Table -----

        MerchantProduct merchantProduct1 = new MerchantProduct();
        merchantProduct1.setAffiliationDate(new Date());
        merchantProduct1.setProduct(product1);
        merchantProduct1.setMerchant(merchant1);
        //Step 5 test, PK must be null
        Assert.assertNull(merchantProduct1.getId());

        merchantProduct1 = merchantProductService.createMerchantProduct(merchantProduct1);
        //Step 6 test, PK must be retrieved by inserting object in database
        Assert.assertNotNull(merchantProduct1.getId());

        merchantProduct1.setAffiliationDate(new Date());
        Assert.assertEquals(merchantProduct1.getProduct().getId(), product1.getId());
        merchantProduct1.setProduct(product2);
        MerchantProduct merchantProductModified = merchantProductService.modifyMerchantProduct(merchantProduct1);
        //Step 7 test, modify do a select after update. We check the select correspond to the entryParameter
        Assert.assertEquals(merchantProduct1.getProduct().getId(), merchantProductModified.getProduct().getId());
        Assert.assertEquals(product2.getId(), merchantProductModified.getProduct().getId());

        //Step 8 test, we check the 2 methods of select
        Assert.assertNotNull(merchantProductService.getListMerchantProduct());
        Assert.assertNotEquals(merchantProductService.getListMerchantProduct().length, 0);
        MerchantProduct merchantProductById = merchantProductService.getMerchantProduct(merchantProduct1.getId());
        Assert.assertNotNull(merchantProductById);
        Assert.assertEquals(merchantProductById.getId(), merchantProduct1.getId());

        //Step 9 test, delete
        merchantProductService.deleteMerchantProduct(merchantProduct1);
        MerchantProduct merchantProductByIdVerifyDelete = merchantProductService.getMerchantProduct(merchantProduct1.getId());
        Assert.assertNull(merchantProductByIdVerifyDelete.getId());


        //----- START - Clean merchant object for relation Table -----
        //Step 10 test, delete
        merchantService.deleteMerchant(merchant1);
        Merchant merchantByIdVerifyDelete = merchantService.getMerchant(merchant1.getId());
        Assert.assertNull(merchantByIdVerifyDelete.getId());
        //----- END - Clean merchant object for relation Table -----

        //----- START - Clean product object for relation Table -----
        //Step 11a test, delete
        productService.deleteProduct(product1);
        Product productByIdVerifyDelete = productService.getProduct(product1.getId());
        Assert.assertNull(productByIdVerifyDelete.getId());

        //Step 11b test, delete
        productService.deleteProduct(product2);
        Product productByIdVerifyDelete2 = productService.getProduct(product2.getId());
        Assert.assertNull(productByIdVerifyDelete2.getId());

        //----- END - Clean product object for relation Table -----
    }

    private MerchantProductService getMerchantProductEndpoint() throws IOException {
        Endpoint endpointMerchantProduct = Endpoint.publish(URI_ENDPOINT_MERCHANTPRODUCT, new MerchantProductServiceImpl());

        URL url = new URL(URI_ENDPOINT_MERCHANTPRODUCT + URI_WSDL_EXTENSION);

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName(NAME_SPACE, NAME_MERCHANTPRODUCT_SERVICE_WSDL);

        Service service = Service.create(url, qname);

        return service.getPort(MerchantProductService.class);

    }


    private MerchantService getMerchantEndpoint() throws IOException {
        Endpoint endpointMerchant = Endpoint.publish(URI_ENDPOINT_MERCHANT, new MerchantServiceImpl());

        URL url = new URL(URI_ENDPOINT_MERCHANT + URI_WSDL_EXTENSION);

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName(NAME_SPACE, NAME_MERCHANT_SERVICE_WSDL);

        Service service = Service.create(url, qname);

        return service.getPort(MerchantService.class);

    }


    private ProductService getProductEndpoint() throws IOException {
        Endpoint endpointProduct = Endpoint.publish(URI_ENDPOINT_PRODUCT, new ProductServiceImpl());

        URL url = new URL(URI_ENDPOINT_PRODUCT + URI_WSDL_EXTENSION);

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName(NAME_SPACE, NAME_PRODUCT_SERVICE_WSDL);

        Service service = Service.create(url, qname);

        return service.getPort(ProductService.class);

    }
}
