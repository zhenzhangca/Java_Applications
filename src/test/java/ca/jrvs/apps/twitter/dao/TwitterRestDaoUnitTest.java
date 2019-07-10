package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwitterRestDaoUnitTest {
    private CrdRepository twitterRestDao;
    private Tweet expectedTweet;
    private String id;

    @Before
    public void setup() {
        //Set up an expectedTweet
        expectedTweet = new Tweet();
        //Set up a dao
        HttpHelper httpHelper = new ApacheHttpHelper();
        twitterRestDao = new TwitterRestDao(httpHelper);
    }

    @After
    public void cleanup() {
        System.out.println("Deleting " + this.id);
        //remove the tweet
        twitterRestDao.deleteById(this.id);
    }

    @Test
    public void create() throws JsonProcessingException {
        //return by api
        String tweetStr = "hello world!";
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(Arrays.asList(10.0, 10.0));
        coordinates.setType("Point");
        expectedTweet.setText(tweetStr);
        expectedTweet.setCoordinates(coordinates);

        System.out.println(expectedTweet);
        System.out.println(JsonUtil.toPrettyJson(expectedTweet));

        //call create method
        Tweet createTweet = (Tweet) twitterRestDao.create(expectedTweet);
        System.out.println(JsonUtil.toPrettyJson(createTweet));

        //validate two object
        assertTweets(expectedTweet, createTweet);
        id = createTweet.getIdStr();

        //call findById method
        Tweet showTweet = (Tweet) twitterRestDao.findById(this.id);
        assertTweets(expectedTweet, showTweet);
    }

    public void assertTweets(Tweet expectedTweet, Tweet actualTweet) {
        assertNotNull(actualTweet);
        assertNotNull(actualTweet.getIdStr());
        assertEquals(expectedTweet.getText(), actualTweet.getText());
        assertEquals(expectedTweet.getCoordinates(), actualTweet.getCoordinates());
    }
}