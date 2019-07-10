package ca.jrvs.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;

public class TwitterApiTest {
    private static String CONSUMER_KEY = System.getenv("consumerKey");
    private static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private static String ACCESS_TOKEN = System.getenv("accessToken");
    private static String TOKEN_SECRET = System.getenv("tokenSecret");

    public static void main(String[] args) throws Exception {

        //Setup oauth
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        //Create a HTTP GET request
        HttpGet getRequest = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json?" +
                "screen_name=ZhenZha99395429&count=2");
        //Create a HTTP POST request
        String status = "have a nice weekend!" + System.currentTimeMillis();
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        HttpPost postRequest = new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status));

        HttpClient httpClient = new DefaultHttpClient();

        //Send the GET request
        sendGetRequest(consumer, getRequest, httpClient);
        //Send the POST request
        sendPostRequest(consumer, postRequest, httpClient);
    }

    private static void sendGetRequest(OAuthConsumer consumer, HttpGet getRequest, HttpClient httpClient) throws Exception {
        //Sign the request (add header)
        consumer.sign(getRequest);
        System.out.println("Http Rquest Headers:");
        Arrays.stream(getRequest.getAllHeaders()).forEach(System.out::println);

        //Send/execute the GET request
        HttpResponse getResponse = httpClient.execute(getRequest);
        System.out.println(EntityUtils.toString(getResponse.getEntity()));

    }

    private static void sendPostRequest(OAuthConsumer consumer, HttpPost postRequest, HttpClient httpClient) throws Exception {

        //Sign the request (add header)
        consumer.sign(postRequest);
        System.out.println("Http Rquest Headers:");
        Arrays.stream(postRequest.getAllHeaders()).forEach(System.out::println);

        //Send/execute the POST request
        HttpResponse postResponse = httpClient.execute(postRequest);
        System.out.println(EntityUtils.toString(postResponse.getEntity()));
    }
}
