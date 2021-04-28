package com.acciona.challenge.api;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TwitterStreamTest {

    @Test
    public void whenStatusNull_thenFalse() {
        assertThat(new TwitterStream().haveToSaveTweet(null)).isFalse();
    }

}
