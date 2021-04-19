import implementation.AddressServiceImpl;
import implementation.MerchantServiceImpl;
import model.Address;
import model.Merchant;
import org.junit.Assert;
import org.junit.Test;
import service.AddressService;
import service.MerchantService;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class AddressServiceTest {

    private final static String NAME_SPACE = "http://implementation/";
    private final static String URI_WSDL_EXTENSION = "?wsdl";

    private final static String URI_ENDPOINT_ADDRESS = "http://localhost:9998/implementation/address";
    private final static String NAME_ADDRESS_SERVICE_WSDL = "AddressServiceImplService";

    private final static String URI_ENDPOINT_MERCHANT = "http://localhost:9998/implementation/merchant";
    private final static String NAME_MERCHANT_SERVICE_WSDL = "MerchantServiceImplService";


    @Test
    public void addressService() throws IOException {

        AddressService addressService = getAddressEndpoint();
        MerchantService merchantService = getMerchantEndpoint();

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

        Address address1 = new Address();
        address1.setNumber(15);
        address1.setStreet("rue Edgard");
        address1.setZipcode("75001");
        address1.setMerchant(merchant1);
        //Step 3 test, PK must be null
        Assert.assertNull(address1.getId());

        address1 = addressService.createAddress(address1);
        //Step 4 test, PK must be retrieved by inserting object in database
        Assert.assertNotNull(address1.getId());

        address1.setNumber(15);
        address1.setStreet("rue Edgard Moinard");
        address1.setZipcode("75002");
        Address address1Modified = addressService.modifyAddress(address1);
        //Step 5 test, modify do a select after update. We check the select correspond to the entryParameter
        Assert.assertEquals(address1.getNumber(), address1Modified.getNumber());
        Assert.assertEquals(address1.getStreet(), address1Modified.getStreet());

        //Step 6 test, we check the 2 methods of select
        Assert.assertNotNull(addressService.getListAddress());
        Assert.assertNotEquals(addressService.getListAddress().length, 0);
        Address addressById = addressService.getAddress(address1.getId());
        Assert.assertNotNull(addressById);
        Assert.assertEquals(addressById.getId(), address1.getId());

        //Step 7 test, delete
        addressService.deleteAddress(address1);
        Address addressByIdVerifyDelete = addressService.getAddress(address1.getId());
        Assert.assertNull(addressByIdVerifyDelete.getId());


        //----- START - Clean merchant object for relation Table -----
        //Step 8 test, delete
        merchantService.deleteMerchant(merchant1);
        Merchant merchantByIdVerifyDelete = merchantService.getMerchant(merchant1.getId());
        Assert.assertNull(merchantByIdVerifyDelete.getId());
        //----- END - Clean merchant object for relation Table -----
    }

    private AddressService getAddressEndpoint() throws IOException {
        Endpoint endpointAddress = Endpoint.publish(URI_ENDPOINT_ADDRESS, new AddressServiceImpl());

        URL url = new URL(URI_ENDPOINT_ADDRESS + URI_WSDL_EXTENSION);

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName(NAME_SPACE, NAME_ADDRESS_SERVICE_WSDL);

        Service service = Service.create(url, qname);

        return service.getPort(AddressService.class);

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
