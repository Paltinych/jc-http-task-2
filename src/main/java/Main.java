import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod?api_key=kavb5pc4PzqtOfsnzVCXuzTywg0CcfrvEmOJjphf";
    public static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("My Test Service")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);

        Post post = mapper.readValue(
                response.getEntity().getContent(),
                Post.class
        );
        String url = post.getHdurl();

        String[] s = url.split("/");

        File jpeg = new File(s[s.length - 1]);

        HttpGet hdurl = new HttpGet(post.getHdurl());
        hdurl.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        response = httpClient.execute(hdurl);

        try (FileOutputStream fos = new FileOutputStream(jpeg)) {
            fos.write(response.getEntity().getContent().readAllBytes());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        response.close();
        httpClient.close();
    }
}
