package com.acciona.challenge.domain.repository;

import com.acciona.challenge.domain.model.Tweet;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TweetRepositoryTest {

    @Autowired
    private TweetRepository repository;

    @After
    public void after() {
        repository.deleteAll();
    }

    @Test
    public void saveTest() {
        Tweet tweet = Tweet.builder()
                .id(1)
                .user("tester")
                .text("test test")
                .build();
        repository.save(tweet);
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    public void listTest() {
        assertThat(repository.list(100, 0)).isEmpty();

        Tweet tweet1 = Tweet.builder()
                .id(1)
                .user("tester")
                .text("test test")
                .build();
        Tweet tweet2 = Tweet.builder()
                .id(2)
                .user("tester2")
                .text("test aaaa")
                .build();
        repository.save(tweet1);
        repository.save(tweet2);
        List<Tweet> tweets = repository.list(100, 0);
        assertThat(tweets).isNotEmpty();
        assertThat(tweets.size()).isEqualTo(2);
    }

    @Test
    public void updateTest() {
        Integer id = 1;
        Tweet tweet = Tweet.builder()
                .id(id)
                .user("tester")
                .text("test test")
                .build();
        repository.save(tweet);
        Iterable<Tweet> tweetSaved = repository.findAll();
        assertThat(tweetSaved.iterator().hasNext()).isTrue();
        assertThat(tweetSaved.iterator().next().isValidated()).isFalse();
        repository.updateValidated(id);
        Iterable<Tweet> tweetUpdated = repository.findAll();
        assertThat(tweetUpdated.iterator().hasNext()).isTrue();
        assertThat(tweetUpdated.iterator().next().isValidated()).isTrue();
    }
}
