package ca.jrvs.apps.twitter.example;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;

public class TwitterApiTest {
    private static String CONSUMER_KEY = System.getenv("consumerKey");
    private static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private static String ACCESS_TOKEN = System.getenv("accessToken");
    private static String TOKEN_SECRET = System.getenv("tokenSecret");

    public static void main(String[] args) throws Exception {

        //setup oauth
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        //create a HTTP GET request
        HttpGet request = new HttpGet("https://api.twitter.com/1.1/users/search.json?" +
                "q=ZhenZha99395429");

        //sign the request (add header)
        consumer.sign(request);
        System.out.println("Http Rquest Headers:");
        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

        //send/execute the request
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);

        System.out.println(EntityUtils.toString(response.getEntity()));

    }
}
