package com.acciona.challenge.service.persistence;

import com.acciona.challenge.domain.model.Tweet;
import com.acciona.challenge.domain.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TweetPersistenceService {

    private static final String PERCENTAGE = "%";
    @Autowired
    private TweetRepository repository;

    public void save(Tweet tweet) {
        repository.save(tweet);
    }

    public List<Tweet> list(Integer limit, Integer offset, Tweet tweet) {
        if (Objects.nonNull(tweet) && Objects.nonNull(tweet.getUser()) && tweet.isValidated()) {
            return repository.listByUserAndValidated(limit, offset, formatUsername(tweet.getUser()));
        } else if (Objects.nonNull(tweet) && Objects.nonNull(tweet.getUser())) {
            return repository.listByUser(limit, offset, formatUsername(tweet.getUser()));
        } else if (Objects.nonNull(tweet) && tweet.isValidated()) {
            return repository.listValidated(limit, offset);
        }
        return repository.list(limit, offset);
    }

    public void update(Tweet tweet) {
        repository.updateValidated(tweet.getId());
    }

    protected String formatUsername(String user) {
        return PERCENTAGE.concat(user).concat(PERCENTAGE);
    }
}
