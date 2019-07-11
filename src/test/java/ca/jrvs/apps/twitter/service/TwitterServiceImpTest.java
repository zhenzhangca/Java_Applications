package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
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
        Tweet tweet = service.showTweet(mockTweet.getId().toString(), null);
        System.out.println(tweet);
        try {
            service.showTweet("", new String[]{"text", "id"});
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid id");
        }
    }

    @Test
    public void deleteTweets() throws IOException {
        Tweet mockTweeta = new Tweet();
        mockTweeta.setId(123456789l);
        mockTweeta.setText("first test tweet" + System.currentTimeMillis());
        Tweet mockTweetb = new Tweet();
        mockTweetb.setId(987654321l);
        mockTweetb.setText("second test tweet" + System.currentTimeMillis());
        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(mockTweeta);
        tweetList.add(mockTweetb);
        String[] arr = new String[]{mockTweeta.getId().toString(), mockTweetb.getId().toString()};
        List<Tweet> results = service.deleteTweets(arr);
        System.out.println(results);
        when(mockDao.deleteById(any())).thenReturn(new Tweet());
        try {
            service.deleteTweets(null);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ids");
        }
    }
}
