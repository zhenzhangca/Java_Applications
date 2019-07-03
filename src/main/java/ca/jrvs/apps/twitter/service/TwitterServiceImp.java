package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;

public class TwitterServiceImp implements TwitterService {
    TwitterRestDao dao = new TwitterRestDao();

    @Override
    public void postTweet(String text, Double latitude, Double longitude) {

    }

    @Override
    public void showTweet(String id, String[] fields) {

    }

    @Override
    public void deleteTweet(String[] ids) {

    }
}
