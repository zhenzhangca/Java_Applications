package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApacheHttpHelperTest {
    private HttpHelper httpHelper;

    @Before
    public void setup() {
        httpHelper = new ApacheHttpHelper();
    }

    @Test
    public void httpPostCreate() {
        String createUri = "https://api.twitter.com/1.1/statuses/update.json?status=thenewtweet&lat=22.0&long=11.0";
        try {
            HttpResponse createResponse = httpHelper.httpPost(new URI(createUri));
            assertNotNull(createResponse);
            assertEquals(200, createResponse.getStatusLine().getStatusCode());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void httpPostDelete() {
        String deleteUri = "https://api.twitter.com/1.1/statuses/destroy/1148663615714725889.json";
        try {
            HttpResponse deleteResponse = httpHelper.httpPost(new URI(deleteUri));
            assertNotNull(deleteResponse);
            assertEquals(200, deleteResponse.getStatusLine().getStatusCode());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void httpGet() {
        String readUri = "https://api.twitter.com/1.1/statuses/show.json?id=1147132559194824704";
        try {
            HttpResponse readResponse = httpHelper.httpGet(new URI(readUri));
            System.out.println(EntityUtils.toString(readResponse.getEntity()));
            assertNotNull(readResponse);
            assertEquals(200, readResponse.getStatusLine().getStatusCode());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}