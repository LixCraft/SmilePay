import implementation.MerchantServiceImpl;
import service.MerchantService;

import org.junit.Assert;
import org.junit.Test;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;

public class MerchantServiceTest {

    private final static String URI_ENDPOINT_MERCHANT = "http://localhost:9999/implementation/merchant";
    private final static String NAME_SPACE_MERCHANT = "http://implementation/";
    private final static String URI_WSDL_EXTENSION = "?wsdl";
    private final static String NAME_MERCHANT_SERVICE_WSDL = "MerchantServiceImplService";


    @Test
    public void getProduct() throws IOException {

        MerchantService service = getMerchantEndpoint();

        System.out.println(service.getListMerchant().toString());

        Assert.assertNotNull(service.getListMerchant());


    }

    private MerchantService getMerchantEndpoint() throws IOException {
        Endpoint endpointMerchant = Endpoint.publish(URI_ENDPOINT_MERCHANT, new MerchantServiceImpl());

        URL url = new URL(URI_ENDPOINT_MERCHANT + URI_WSDL_EXTENSION);

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName(NAME_SPACE_MERCHANT, NAME_MERCHANT_SERVICE_WSDL);

        Service service = Service.create(url, qname);

        return service.getPort(MerchantService.class);

    }
}
