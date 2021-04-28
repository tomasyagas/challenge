package com.acciona.challenge.service;

import com.acciona.challenge.domain.model.Hashtag;
import com.acciona.challenge.domain.model.Tweet;
import com.acciona.challenge.service.converter.HashtagConverter;
import com.acciona.challenge.service.converter.TweetConverter;
import com.acciona.challenge.service.persistence.HashtagPersistenceService;
import com.acciona.challenge.service.persistence.TweetPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.Status;

import java.util.List;
import java.util.Objects;

@Service
public class TweetPublishedService {

    @Value("${tweets.service.defaultLimit}")
    private Integer defaultLimit;
    @Value("${tweets.service.defaultOffset}")
    private Integer defaultOffset;
    @Autowired
    private TweetConverter tweetConverter;
    @Autowired
    private HashtagConverter hashtagConverter;
    @Autowired
    private TweetPersistenceService tweetPersistenceService;
    @Autowired
    private HashtagPersistenceService hashtagPersistenceService;

    public void save(Status status) {
        Tweet tweet = tweetConverter.convert(status);
        tweetPersistenceService.save(tweet);
        List<Hashtag> hashtags = hashtagConverter.convert(status.getHashtagEntities());
        hashtags.forEach(hashtagPersistenceService::upsert);
    }

    public List<Tweet> search(Integer limit, Integer offset, Tweet request) {
        if (Objects.isNull(limit)) {
            limit = defaultLimit;
        }
        if (Objects.isNull(offset)) {
            offset = defaultOffset;
        }
        return tweetPersistenceService.list(limit, offset, request);
    }

    public void update(Tweet request) {
        tweetPersistenceService.update(request);
    }
}
