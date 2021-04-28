package com.acciona.challenge.domain.repository;

import com.acciona.challenge.domain.model.Hashtag;
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
public class HashtagRepositoryTest {

    @Autowired
    private HashtagRepository repository;

    @After
    public void after() {
        repository.deleteAll();
    }

    @Test
    public void saveTest() {
        Hashtag hashtag = Hashtag.builder()
                .name("tests")
                .counter(1)
                .build();
        repository.save(hashtag);
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    public void listTest() {
        assertThat(repository.listSortByCounter(100, 0)).isEmpty();

        Hashtag hashtag1 = Hashtag.builder()
                .name("tests")
                .counter(1)
                .build();
        Hashtag hashtag2 = Hashtag.builder()
                .name("tests asd")
                .counter(5)
                .build();
        Hashtag hashtag3 = Hashtag.builder()
                .name("lololo")
                .counter(10)
                .build();
        repository.save(hashtag1);
        repository.save(hashtag2);
        repository.save(hashtag3);
        List<Hashtag> hashtags = repository.listSortByCounter(100, 0);
        assertThat(hashtags).isNotEmpty();
        assertThat(hashtags.size()).isEqualTo(3);
        assertThat(hashtags.get(0).getName()).isEqualTo("lololo");
    }

    @Test
    public void updateTest() {
        Integer id = 1;
        Hashtag hashtag = Hashtag.builder()
                .name("tests rrr")
                .counter(1)
                .build();
        repository.save(hashtag);
        Iterable<Hashtag> hashtagsSaved = repository.findAll();
        assertThat(hashtagsSaved.iterator().hasNext()).isTrue();
        assertThat(hashtagsSaved.iterator().next().getCounter()).isEqualTo(1);
        repository.update("tests rrr");
        Iterable<Hashtag> hashtagsUpdated = repository.findAll();
        assertThat(hashtagsUpdated.iterator().hasNext()).isTrue();
        assertThat(hashtagsUpdated.iterator().next().getCounter()).isEqualTo(2);
    }
}
