package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.example.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TwitterRestDao implements CrdRepository<Tweet, String> {
    HttpHelper helper = new ApacheHttpHelper();

    @Override
    public Tweet save(Tweet entity) {
        try {
            String json = new JsonParser().toJson(entity, false, true);
            //helper.httpPost(new StringEntity(json));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Tweet findById(String s) {
        Tweet tweet = null;
        try {
            HttpResponse response = helper.httpPost(new URI(s));
            String responseJson = EntityUtils.toString(response.getEntity());
            tweet = new JsonParser().toObjectFromJson(responseJson, Tweet.class);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return tweet;
    }

    @Override
    public Tweet deleteById(String s) {
        try {
            helper.httpPost(new URI(s));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
