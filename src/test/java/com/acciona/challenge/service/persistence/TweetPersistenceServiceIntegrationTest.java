package com.acciona.challenge.service.persistence;

import com.acciona.challenge.domain.model.Tweet;
import com.acciona.challenge.domain.repository.TweetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
public class TweetPersistenceServiceIntegrationTest {

    @TestConfiguration
    static class TweetPersistenceServiceIntegrationTestContextConfiguration {

        @Bean
        public TweetPersistenceService tweetPersistenceService() {
            return new TweetPersistenceService();
        }
    }

    @Autowired
    private TweetPersistenceService tweetPersistenceService;

    @MockBean
    private TweetRepository tweetRepository;

    @Before
    public void setUp() {
        List<Tweet> tweets = new ArrayList<>();
        Tweet tweet1 = Tweet.builder()
                .id(1)
                .text("test")
                .user("tester")
                .build();
        tweets.add(tweet1);
        Tweet tweet2 = Tweet.builder()
                .id(2)
                .text("test test")
                .user("tester2")
                .validated(true)
                .build();
        tweets.add(tweet2);

        Mockito.when(tweetRepository.list(100, 0))
                .thenReturn(tweets);
        Mockito.when(tweetRepository.list(100, 10))
                .thenReturn(new ArrayList<>());

        List<Tweet> tweetsByUserAndValidate = new ArrayList<>();
        tweetsByUserAndValidate.add(tweet2);
        Mockito.when(tweetRepository.listByUserAndValidated(any(), any(), eq(tweetPersistenceService.formatUsername("tester2"))))
                .thenReturn(tweetsByUserAndValidate);
        Mockito.when(tweetRepository.listByUser(any(), any(), eq(tweetPersistenceService.formatUsername("tester2"))))
                .thenReturn(tweetsByUserAndValidate);
        Mockito.when(tweetRepository.listValidated(any(), any()))
                .thenReturn(tweetsByUserAndValidate);
    }

    @Test
    public void whenValidListParams_thenTweetsShouldBeFound() {
        List<Tweet> tweets = tweetPersistenceService.list(100, 0, null);

        assertThat(tweets.size()).isEqualTo(2);
    }

    @Test
    public void whenInvalidListParams_thenTweetsShouldNotBeFound() {
        List<Tweet> tweets = tweetPersistenceService.list(100, 10, null);

        assertThat(tweets.size()).isEqualTo(0);
    }

    @Test
    public void whenListByUserAndValidated_thenTweetShouldBeFound() {
        String username = "tester2";
        List<Tweet> tweets = tweetPersistenceService.list(100, 10, Tweet.builder().user(username).validated(true).build());

        assertThat(tweets.size()).isEqualTo(1);
        assertThat(tweets.get(0).getUser()).isEqualTo(username);
    }

    @Test
    public void whenListByUser_thenTweetShouldBeFound() {
        String username = "tester2";
        List<Tweet> tweets = tweetPersistenceService.list(100, 10, Tweet.builder().user(username).build());

        assertThat(tweets.size()).isEqualTo(1);
        assertThat(tweets.get(0).getUser()).isEqualTo(username);
    }

    @Test
    public void whenListByOtherUser_thenTweetShouldNotBeFound() {
        String username = "tester1";
        List<Tweet> tweets = tweetPersistenceService.list(100, 10, Tweet.builder().user(username).build());

        assertThat(tweets.size()).isEqualTo(0);
    }


    @Test
    public void whenListByValidated_thenTweetShouldBeFound() {
        String username = "tester2";
        List<Tweet> tweets = tweetPersistenceService.list(100, 10, Tweet.builder().validated(true).build());

        assertThat(tweets.size()).isEqualTo(1);
        assertThat(tweets.get(0).getUser()).isEqualTo(username);
    }
}
