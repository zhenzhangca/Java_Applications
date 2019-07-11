package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceImpTest {

    @InjectMocks
    private TwitterServiceImp service;

    @Mock
    private CrdRepository mockDao;

    @Test
    public void postTweet() {
        Tweet mockTweet = new Tweet();
        mockTweet.setText("test tweet " + System.currentTimeMillis());
        when(mockDao.create(any())).thenReturn(mockTweet);

        Tweet tweet = service.postTweet("post a tweet demo" + System.currentTimeMillis(), 11.0, 12.0);
        System.out.println(tweet);

        try {
            service.postTweet("", 0.0, 0.0);
            fail();
        } catch (Exception e) {
            throw new IllegalArgumentException("Empty tweet");
        }
    }

    @Test
    public void showTweet() {
        Tweet mockTweet = new Tweet();
        mockTweet.setId(1234567890l);
        mockTweet.setText("test tweet " + System.currentTimeMillis());
        when(mockDao.findById(any())).thenReturn(mockTweet);
        try {
            service.showTweet(mockTweet.getId().toString(), new String[]{"text", "id"});
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid id");
        }
    }

    @Test
    public void deleteTweets() {
        Tweet mockTweeta = new Tweet();
        mockTweeta.setId(123456789l);
        mockTweeta.setText("first test tweet" + System.currentTimeMillis());
        Tweet mockTweetb = new Tweet();
        mockTweetb.setId(987654321l);
        mockTweetb.setText("second test tweet" + System.currentTimeMillis());
        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(mockTweeta);
        tweetList.add(mockTweetb);
        when(mockDao.deleteById(any())).thenReturn(tweetList);
        try {
            service.deleteTweets(new String[]{mockTweeta.getId().toString(), mockTweetb.getId().toString()});
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ids");
        }
    }
}