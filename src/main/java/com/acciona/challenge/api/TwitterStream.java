package com.acciona.challenge.api;

import com.acciona.challenge.service.TweetPublishedService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.List;
import java.util.Objects;

@Component
public class TwitterStream {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TwitterStream.class);

    @Value("${twitter.stream.enabled}")
    private boolean enabled;
    @Value("${twitter.stream.minFollowers}")
    private Integer minFollowers;
    @Value("${twitter.stream.languages}")
    private List<String> languages;

    @Autowired
    private TweetPublishedService tweetPublishedService;

    public void stream() {
        if (enabled) {
            new TwitterStreamFactory().getInstance().addListener(new StatusListener() {
                @Override
                public void onStatus(Status status) {
                    processTweet(status);
                }

                @Override
                public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                }

                @Override
                public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                }

                @Override
                public void onScrubGeo(long userId, long upToStatusId) {
                }

                @Override
                public void onStallWarning(StallWarning warning) {
                }

                @Override
                public void onException(Exception ex) {
                    LOGGER.error(ex.getMessage(), ex.getStackTrace());
                }
            }).sample();
        }
    }

    private void processTweet(Status status) {
        if (haveToSaveTweet(status)) {
            tweetPublishedService.save(status);
        }
    }

    protected boolean haveToSaveTweet(Status status) {
        return Objects.nonNull(status) &&
                Objects.nonNull(status.getUser()) &&
                status.getUser().getFollowersCount() > minFollowers &&
                Objects.nonNull(status.getLang()) &&
                languages.stream().anyMatch(lang -> lang.equalsIgnoreCase(status.getLang()));
    }
}
