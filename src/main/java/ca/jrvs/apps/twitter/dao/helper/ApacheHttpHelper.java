package ca.jrvs.apps.twitter.dao.helper;

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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;

public class ApacheHttpHelper implements HttpHelper {
    private static String CONSUMER_KEY = System.getenv("consumerKey");
    private static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private static String ACCESS_TOKEN = System.getenv("accessToken");
    private static String TOKEN_SECRET = System.getenv("tokenSecret");

    @Override
    public HttpResponse httpPost(URI uri) {
        //setup oauth
        OAuthConsumer consumer = this.setOauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        //create a HTTP GET request
        HttpPost request = new HttpPost(uri);
        //create a httpClient
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            //sign the request
            consumer.sign(request);
            //send/execute the request
            response = httpClient.execute(request);
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

    @Override
    public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
        //setup oauth
        OAuthConsumer consumer = this.setOauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        HttpPost request = new HttpPost(uri);
        request.setEntity(stringEntity);

        //create a httpClient
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            //sign the request
            consumer.sign(request);
            //send/execute the request
            response = httpClient.execute(request);
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

    @Override
    public HttpResponse httpGet(URI uri) {
        //setup oauth
        OAuthConsumer consumer = this.setOauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);


        HttpGet request = new HttpGet(uri);
        //create a httpClient
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            //sign the request
            consumer.sign(request);
            //send/execute the request
            response = httpClient.execute(request);
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

    private OAuthConsumer setOauth(String CONSUMER_KEY, String CONSUMER_SECRET, String ACCESS_TOKEN, String TOKEN_SECRET) {
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
        return consumer;
    }




}


