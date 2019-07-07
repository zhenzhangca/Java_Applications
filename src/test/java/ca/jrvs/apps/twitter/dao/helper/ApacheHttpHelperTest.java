package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApacheHttpHelperTest {
    HttpHelper helper = null;

    @Test
    public void testHttpPost() {
        helper = new ApacheHttpHelper();
        String createUri = "https://api.twitter.com/1.1/statuses/update.json?status=thenewtweet";
        String deleteUri = "https://api.twitter.com/1.1/statuses/destroy/1147181000818143234.json";
        try {
            HttpResponse createResponse = helper.httpPost(new URI(createUri));
            HttpResponse deleteResponse = helper.httpPost(new URI(deleteUri));
            assertNotNull(createResponse);
            assertEquals(200, createResponse.getStatusLine().getStatusCode());
            assertNotNull(deleteResponse);
            assertEquals(200, deleteResponse.getStatusLine().getStatusCode());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHttpGet() {
        helper = new ApacheHttpHelper();
        String readUri = "https://api.twitter.com/1.1/statuses/show.json?id=1147201005773316102";
        try {
            HttpResponse readResponse = helper.httpGet(new URI(readUri));
            assertNotNull(readResponse);
            assertEquals(200, readResponse.getStatusLine().getStatusCode());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}