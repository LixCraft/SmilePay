package publisher;

import implementation.MerchantServiceImpl;
import implementation.ProductServiceImpl;
import model.Adress;
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

    public static void main(String[] args) throws IOException {
        Endpoint.publish("http://localhost:9988/service/product", new ProductServiceImpl());
        Endpoint.publish("http://localhost:9988/service/merchant", new MerchantServiceImpl());

        Adress adress = new Adress();
        adress.setId(Integer.valueOf(1));
        adress.setStreet("rue rue");
        adress.setNumber(14);
        adress.setZipcode("94400");

        Adress adress1 = new Adress();
        adress1.setId(Integer.valueOf(2));
        adress1.setStreet("rue rue");
        adress1.setNumber(14);
        adress1.setZipcode("94400");

        ArrayList<Adress> adressArrayList = new ArrayList<>();
        adressArrayList.add(adress);
        adressArrayList.add(adress1);

        System.out.println(adressArrayList.toString());

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
