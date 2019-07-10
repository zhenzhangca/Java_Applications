package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.StringUtil;
import ca.jrvs.apps.twitter.util.TwitterUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class TwitterServiceImp implements TwitterService {
    private CrdRepository dao;

    @Autowired
    public TwitterServiceImp(@Qualifier("twitterRestDao") CrdRepository dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(String text, Double latitude, Double longitude) {
        //Build a Tweet
        Tweet postTweet = TwitterUtil.buildTweet(text, latitude, longitude);
        //Validate tweet
        TwitterUtil.validatePostTweet(postTweet);
        try {
            Tweet responseTweet = (Tweet) dao.create(postTweet);
            printTweet(responseTweet);
        } catch (Exception e) {
            throw new RuntimeException("Fail to post tweet");
        }
        return postTweet;
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        Tweet tweet;
        //Validate id
        System.out.println(TwitterUtil.validId.test(id));
        if (!TwitterUtil.validId.test(id)) {
            throw new IllegalArgumentException("Id must be a number");
        }
        //Get tweet by id, print selected fields
        try {
            tweet = (Tweet) dao.findById(id);
            printTweet(selectFields(tweet, fields));
        } catch (IOException e) {
            throw new RuntimeException("Failed to show tweet");
        }
        return tweet;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> tweetList = new ArrayList<>();
        if (ids == null || ids.length == 0) {
            return null;
        }
        //Validate each id. Delete tweets by ids
        for (String id : ids) {
            TwitterUtil.validId.test(id);
            Tweet tweet = (Tweet) dao.deleteById(id);
            printTweet(tweet);
            tweetList.add(tweet);
        }
        return tweetList;
    }

    private void printTweet(Tweet tweet) {
        try {
            System.out.println(JsonUtil.toPrettyJson(tweet));
        } catch (Exception e) {
            throw new RuntimeException("Unable to convert tweet object to Json String");
        }
    }

    /**
     * Select fields in tweet object by setting other fields to null
     *
     * @param tweet  tweet object
     * @param fields fields to be selected
     */
    protected Tweet selectFields(Tweet tweet, String[] fields) throws IOException {
        if (fields == null || fields.length == 0) {
            return tweet;
        }
        //rTweet = deep copy of tweet(make sure not to change the original tweet object)
        Tweet rTweet = JsonUtil.toObjectFromJson(JsonUtil.toPrettyJson(tweet), Tweet.class);
        //helper lambda function to remove leading and trailing spaces
        Function<String[], String[]> trimStrArray = (items) -> Arrays.stream(items).map(String::trim)
                .toArray(String[]::new);
        //Make fieldSet for fast lookup and removal
        Set<String> fieldSet = new HashSet<>(Arrays.asList(trimStrArray.apply(fields)));

        /**
         * for(method: Tweet.getMethods)
         *   if method.name.prefix == "set"
         *     if fieldSet contains JsonProperty.value
         *       remove field from fieldSet
         *     else
         *       invoke rTweet setter with null argument
         */
        Predicate<Method> isSetter = (method) -> method.getName().startsWith("set");
        Arrays.stream(Tweet.class.getMethods())
                .filter(isSetter)
                .forEach(setter ->
                {
                    JsonProperty jsonProperty = setter.getDeclaredAnnotation(JsonProperty.class);
                    if (jsonProperty == null || StringUtil.isEmpty(jsonProperty.value())) {
                        throw new RuntimeException(
                                "@JsonProperty is not defined for method" + setter.getName());
                    }
                    String value = jsonProperty.value();
                    if (fieldSet.contains(value)) {
                        fieldSet.remove(value);
                    } else {
                        try {
                            setter.invoke(rTweet, new Object[]{null});
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("unable to set setter:" + setter.getName(), e);
                        }
                    }
                });

        /**
         * if fieldSet not empty
         *   throw invalid fields exception
         */
        if (!fieldSet.isEmpty()) {
            String invalidFields = String.join(",", fieldSet);
            throw new RuntimeException("Found invalid select field(s):" + invalidFields);
        }
        return rTweet;
    }

}
