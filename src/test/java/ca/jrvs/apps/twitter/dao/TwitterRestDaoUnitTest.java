package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Entities;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterRestDaoUnitTest {
    TwitterRestDao twitterRestDao = null;
    Tweet tweet = null;
    HttpHelper helper = new ApacheHttpHelper();

    @Test
    public void testSave() {
        twitterRestDao = new TwitterRestDao(helper);
        tweet = new Tweet();
        tweet.setCreatedAt("1234e44rrt");
        tweet.setId(1112233344L);
        tweet.setIdStr("112ddd2343344");
        tweet.setEntities(new Entities());
        tweet.setCoordinates(new Coordinates());
        tweet.setRetweetCount(5l);
        tweet.setFavoriteCount(5l);
        tweet.setRetweeted(false);
        tweet.setText("hello world!!@!!");
        tweet.setFavorited(false);
        Tweet returnValue = twitterRestDao.save(tweet);

        System.out.println(returnValue);
        assertNotNull(returnValue);
        assertEquals(tweet, returnValue);
    }

    @Test
    public void testFindById() {
        twitterRestDao = new TwitterRestDao(helper);
        String id = "1147181000818143234";
        Tweet returnValue = twitterRestDao.findById(id);
        assertNotNull(returnValue);
    }

    @Test
    public void testDeleteById() {
        twitterRestDao = new TwitterRestDao(helper);
        String id = "1146920388036911104";
        Tweet returnValue = twitterRestDao.deleteById(id);
        assertNotNull(returnValue);
     }
}