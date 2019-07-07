package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dto.Tweet;

import static ca.jrvs.apps.twitter.util.TwitterUtil.*;

public class TwitterServiceImp implements TwitterService {
    private CrdRepository dao;

    public TwitterServiceImp(CrdRepository dao) {
        this.dao = dao;
    }
    @Override
    public void postTweet(String text, Double latitude, Double longitude) {
        //Build a Tweet
        Tweet postTweet = buildTweet(text, latitude, longitude);
        //Validate tweet
        validatePostTweet(postTweet);
        try {
            Tweet responseTweet = (Tweet)dao.save(postTweet);
            printTweet(responseTweet);
        }catch(Exception e) {
            throw new RuntimeException("Fail to post tweet");
        }
    }
    @Override
    public void showTweet(String id, String[] fields) {
        //Validate id
        if(!validateId.test(id)) {
            throw new IllegalArgumentException("id must be a number");
        }
        //Get tweet by id, print selected fields
        try {
            Tweet tweet = (Tweet)dao.findById(id);
            printTweet(selectFields(tweet, fields));
        }catch(Exception e) {
            throw new RuntimeException("Fail to show tweet");
        }
    }
    @Override
    public void deleteTweets(String[] ids) {

    }

    protected void printTweet(Tweet tweet){

    }

    protected Tweet selectFields(Tweet tweet, String[] fields){
        return null;
    }
}
