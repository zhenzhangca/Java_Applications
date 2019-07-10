package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.util.StringUtil;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class ApacheHttpHelper implements HttpHelper {

    private OAuthConsumer consumer;
    private HttpClient httpClient;

    /**
     * Default constructor. Get keys and token from system environment
     */
    public ApacheHttpHelper() {
        //Get keys and tokens from system environment
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        if (StringUtil.isEmpty(consumerKey, consumerSecret, accessToken, tokenSecret)) {
            throw new RuntimeException("Unable to detect keys and tokens from system environment");
        }

        //Setup OAuth
        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, tokenSecret);
        //Initialize  a httpClient
        httpClient = new DefaultHttpClient();
    }

    @Override
    public HttpResponse httpPost(URI uri) {
        HttpResponse response;
        try {
            response = excuteHttpRequest(HttpMethod.POST, uri);
            return response;
        } catch (OAuthException | IOException e) {
            throw new RuntimeException("Failed to execute", e);
        }

    }

    @Override
    public HttpResponse httpGet(URI uri) {
        HttpResponse response;
        try {
            response = excuteHttpRequest(HttpMethod.GET, uri);
            return response;
        } catch (OAuthException | IOException e) {
            throw new RuntimeException("Failed to execute", e);
        }
    }

    protected HttpResponse excuteHttpRequest(HttpMethod method, URI uri)
            throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException, IOException {
        HttpResponse response;
        if (method == HttpMethod.GET) {
            HttpGet request = new HttpGet(uri);
            consumer.sign(request);
            response = httpClient.execute(request);
            return response;
        } else if (method == HttpMethod.POST) {
            HttpPost request = new HttpPost(uri);
            consumer.sign(request);
            response = httpClient.execute(request);
            return response;
        } else {
            throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
        }
    }
}


