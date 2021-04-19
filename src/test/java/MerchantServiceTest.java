
import implementation.MerchantServiceImpl;
import model.Merchant;
import service.MerchantService;

import org.junit.Assert;
import org.junit.Test;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class MerchantServiceTest {

    private final static String NAME_SPACE = "http://implementation/";
    private final static String URI_WSDL_EXTENSION = "?wsdl";

    private final static String URI_ENDPOINT_MERCHANT = "http://localhost:9999/implementation/merchant";
    private final static String NAME_MERCHANT_SERVICE_WSDL = "MerchantServiceImplService";


    @Test
    public void merchantService() throws IOException {

        MerchantService service = getMerchantEndpoint();

        Merchant merchant1 = new Merchant();
        merchant1.setName("Name1");
        merchant1.setLastName("LastName1");
        merchant1.setBirthdate(new Date());
        //Step 1 test, PK must be null
        Assert.assertNull(merchant1.getId());

        merchant1 = service.createMerchant(merchant1);
        //Step 2 test, PK must be retrieved by inserting object in database
        Assert.assertNotNull(merchant1.getId());

        merchant1.setName("Name1Modified");
        merchant1.setLastName("LastName1Modified");
        Merchant merchant1Modified = service.modifyMerchant(merchant1);
        //Step 3 test, modify do a select after update. We check the select correspond to the entryParameter
        Assert.assertEquals(merchant1.getName(), merchant1Modified.getName());
        Assert.assertEquals(merchant1.getLastName(), merchant1Modified.getLastName());

        //Step 4 test, we check the 2 methods of select
        Assert.assertNotNull(service.getListMerchant());
        Assert.assertNotEquals(service.getListMerchant().length, 0);
        Merchant merchantById = service.getMerchant(merchant1.getId());
        Assert.assertNotNull(merchantById);
        Assert.assertEquals(merchantById.getId(), merchant1.getId());

        //Step 5 test, delete
        service.deleteMerchant(merchant1);
        Merchant merchantByIdVerifyDelete = service.getMerchant(merchant1.getId());
        Assert.assertNull(merchantByIdVerifyDelete.getId());
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
}
