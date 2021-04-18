import implementation.MerchantServiceImpl;
import implementation.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class SmilePayServiceTest {

    @Test
    public void test() throws MalformedURLException {
/**
        try{
            Endpoint.publish("http://localhost:9999/service/product", new ProductServiceImpl());
            Endpoint.publish("http://localhost:9999/service/merchant", new MerchantServiceImpl());
        } catch (Exception e) {
            System.out.print(e.getMessage() + " " + e.getCause());
            throw e;
        }

        URL url = new URL("http://localhost:9999/service/smilepay?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://implementation/", "ProductServiceImplService");

        Service service = Service.create(url, qname);

        ProductService hello = service.getPort(ProductService.class);

        Assert.assertEquals(hello.getListProduct().size(), 2);*/
    }
}
