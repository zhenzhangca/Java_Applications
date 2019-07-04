package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;

public class TwitterServiceImp implements TwitterService {
    TwitterRestDao twitterRestDao = new TwitterRestDao();

    @Override
    public void postTweet(String text, Double latitude, Double longitude) {
        Tweet tweet = new Tweet();
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordiantes(new double[]{latitude, longitude});
        tweet.setText(text);
        tweet.setCoordinates(coordinates);
        twitterRestDao.save(tweet);
    }

    @Override
    public void showTweet(String id, String[] fields) {
        Tweet tweet = twitterRestDao.findById(id);
        for(String field: fields){
        }

    }

    @Override
    public void deleteTweet(String[] ids) {
        for(String id : ids) {
            twitterRestDao.deleteById(id);
        }
    }
}
