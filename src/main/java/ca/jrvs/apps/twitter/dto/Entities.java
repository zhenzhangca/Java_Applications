package ca.jrvs.apps.twitter.dto;

import java.util.List;

public class Entities {
    private List<Hashtag> hashtags;
    private List<User_Mention> user_mentions;

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public List<User_Mention> getUser_mentions() {
        return user_mentions;
    }

    public void setUser_mentions(List<User_Mention> user_mentions) {
        this.user_mentions = user_mentions;
    }
}
