package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.util.StringUtil;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.net.URI;

public class ApacheHttpHelper implements HttpHelper {

    private OAuthConsumer consumer;
    private HttpClient client;

    //Initialize parameters
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
        client = new DefaultHttpClient();
    }


    //initialize member fields
    public ApacheHttpHelper(OAuthConsumer consumer, HttpClient client) {
        this.consumer = consumer;
        this.client = client;
    }

    @Override
    public HttpResponse httpPost(URI uri) {
        //create a HTTP GET request
        HttpPost request = new HttpPost(uri);
        return getResponse(request);
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        //create a HTTP POst request
        HttpGet request = new HttpGet(uri);
        return getResponse(request);
    }



    private HttpResponse getResponse(HttpRequestBase request) {
        //create a httpClient
        HttpResponse response = null;
        try {
            //sign the request
            consumer.sign(request);
            //send/execute the request
            response = client.execute(request);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}


