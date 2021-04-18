package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionUtils {

    public static Integer checkResponseCodeEndpoint(URL url) throws IOException {

        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("HEAD");

        return huc.getResponseCode();
    }
}
