package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIRunner {
    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";
    private TwitterService twitterService;

    @Autowired
    public TwitterCLIRunner(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    public void run(String[] args) {
        if (args.length < 2) {
            throw new RuntimeException("USAGE: TwitterCLIApp post|show|deleteTweets");
        }
        switch (args[0].toLowerCase()) {
            case "post":
                postTweet(args);
                break;
            case "show":
                showTweet(args);
                break;
            case "delete":
                deleteTweet(args);
                break;
            default:
                System.out.println("USAGE: TwitterCLIApp post|show|deleteTweets");
                break;
        }
    }

    protected void postTweet(String[] args) {
        if (args.length != 3) {
            throw new RuntimeException("USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        String tweet_text = args[1];
        String coordinate = args[2];
        String[] coordinatesArray = coordinate.split(COORD_SEP);
        if (coordinatesArray.length != 2 || StringUtil.isEmpty(tweet_text)) {
            throw new RuntimeException("Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        Double latitude;
        Double longitude;
        try {
            latitude = Double.parseDouble(coordinatesArray[0]);
            longitude = Double.parseDouble(coordinatesArray[1]);
        } catch (Exception e) {
            throw new RuntimeException("Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"", e);
        }
        twitterService.postTweet(tweet_text, longitude, latitude);
    }

    protected void showTweet(String[] args) {
        String tweet_id = args[1];
        if (StringUtil.isEmpty(tweet_id)) {
            throw new RuntimeException("Error:empty ID\nUSAGE: TwitterCLIApp show tweet_id [fields]");
        }
        String fields = args[2];
        if (StringUtil.isEmpty(fields)) {
            throw new RuntimeException("Error:empty fields. USAGE: TwitterCLIApp show tweet_id [fields]");
        }
        String[] fieldsArray = fields.split(COMMA);
        twitterService.showTweet(tweet_id, fieldsArray);
    }

    protected void deleteTweet(String[] args) {
        if (args.length != 2 || StringUtil.isEmpty(args[1])) {
            throw new RuntimeException("USAGE: TwitterCLIApp deleteTweets tweet_ids");
        }

        String tweetIds = args[1];
        String[] ids = tweetIds.split(COMMA);
        twitterService.deleteTweets(ids);
    }
}
