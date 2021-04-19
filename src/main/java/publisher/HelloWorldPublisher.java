package publisher;

import implementation.MerchantServiceImpl;
import implementation.ProductServiceImpl;
import model.Address;
import model.Merchant;
import service.MerchantService;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HelloWorldPublisher {

    private final static String URI_ENDPOINT_MERCHANT = "http://localhost:9988/service/merchant";
    private final static String NAME_SPACE_MERCHANT = "http://implementation/";
    private final static String URI_WSDL_EXTENSION = "?wsdl";
    private final static String NAME_MERCHANT_SERVICE_WSDL = "MerchantServiceImplService";

    private final static String URI_ENDPOINT_PRODUCT = "http://localhost:9999/implementation/product";
    private final static String NAME_SPACE_PRODUCT = "http://implementation/";
    private final static String NAME_PRODUCT_SERVICE_WSDL = "ProductServiceImplService";

    public static void main(String[] args) throws IOException {
        Endpoint.publish("http://localhost:9988/service/product", new ProductServiceImpl());
        Endpoint.publish("http://localhost:9988/service/merchant", new MerchantServiceImpl());

        Address address = new Address();
        address.setId(Integer.valueOf(1));
        address.setStreet("rue rue");
        address.setNumber(14);
        address.setZipcode("94400");

        Address address1 = new Address();
        address1.setId(Integer.valueOf(2));
        address1.setStreet("rue rue");
        address1.setNumber(14);
        address1.setZipcode("94400");

        ArrayList<Address> addressArrayList = new ArrayList<>();
        addressArrayList.add(address);
        addressArrayList.add(address1);

        System.out.println(addressArrayList.toString());

        URL url = new URL(URI_ENDPOINT_MERCHANT + URI_WSDL_EXTENSION);

        QName qname = new QName(NAME_SPACE_MERCHANT, NAME_MERCHANT_SERVICE_WSDL);

        Service service = Service.create(url, qname);

        MerchantService hello = service.getPort(MerchantService.class);

        Merchant[] result = hello.getListMerchant();
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("HEAD");

        int responseCode = huc.getResponseCode();

        System.out.println(HttpURLConnection.HTTP_OK + " " + responseCode);
//        URL url = new URL("http://localhost:9988/service/smilepay?wsdl");
//
//        //1st argument service URI, refer to wsdl document above
//        //2nd argument is service name, refer to wsdl document above
//        QName qname = new QName("http://implementation/", "SmilePayImplService");
//
//        Service service = Service.create(url, qname);
//
//        SmilePayService hello = service.getPort(SmilePayService.class);
//
//        System.out.println(hello.getHelloWorldAsString("mkyong"));
    }
}
