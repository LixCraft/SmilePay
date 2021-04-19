import implementation.MerchantServiceImpl;
import implementation.ProductServiceImpl;
import model.Merchant;
import model.Product;
import org.junit.Assert;
import org.junit.Test;
import service.ProductService;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class ProductServiceTest {

    private final static String NAME_SPACE = "http://implementation/";
    private final static String URI_WSDL_EXTENSION = "?wsdl";

    private final static String URI_ENDPOINT_PRODUCT = "http://localhost:9999/implementation/product";
    private final static String NAME_PRODUCT_SERVICE_WSDL = "ProductServiceImplService";


    @Test
    public void productService() throws IOException {

        ProductService service = getProductEndpoint();

        Product product1 = new Product();
        product1.setLabel("Label1");
        product1.setUnitPrice(100);
        product1.setCurrency("EUR");
        product1.setWeight(20);;
        product1.setHeight(10);
        //Step 1 test, PK must be null
        Assert.assertNull(product1.getId());

        product1 = service.createProduct(product1);
        //Step 2 test, PK must be retrieved by inserting object in database
        Assert.assertNotNull(product1.getId());

        product1.setLabel("Label1Modified");
        product1.setUnitPrice(1000);
        product1.setCurrency("EURO");
        product1.setWeight(200);;
        product1.setHeight(100);
        Product product1Modified = service.modifyProduct(product1);
        //Step 3 test, modify do a select after update. We check the select correspond to the entryParameter
        Assert.assertEquals(product1.getLabel(), product1Modified.getLabel());
        Assert.assertEquals(product1.getUnitPrice(), product1Modified.getUnitPrice());
        Assert.assertEquals(product1.getCurrency(), product1Modified.getCurrency());
        Assert.assertEquals(product1.getWeight(), product1Modified.getWeight());
        Assert.assertEquals(product1.getHeight(), product1Modified.getHeight());

        //Step 4 test, we check the 2 methods of select
        Assert.assertNotNull(service.getListProduct());
        Assert.assertNotEquals(service.getListProduct().length, 0);
        Product productById = service.getProduct(product1.getId());
        Assert.assertNotNull(productById);
        Assert.assertEquals(productById.getId(), product1.getId());

        //Step 5 test, delete
        service.deleteProduct(product1);
        Product productByIdVerifyDelete = service.getProduct(product1.getId());
        Assert.assertNull(productByIdVerifyDelete.getId());

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
