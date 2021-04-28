package com.acciona.challenge.service.converter;

import com.acciona.challenge.domain.model.Hashtag;
import org.springframework.stereotype.Component;
import twitter4j.HashtagEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class HashtagConverter {

    private static final int DEFAULT_COUNTER = 1;

    public List<Hashtag> convert(HashtagEntity[] sources) {
        List<Hashtag> dest = new ArrayList<>();
        if (Objects.nonNull(sources)) {
            for (HashtagEntity source : sources) {
                Hashtag hashtag = convert(source);
                if (Objects.nonNull(hashtag)) {
                    dest.add(hashtag);
                }
            }
        }
        return dest;
    }

    private Hashtag convert(HashtagEntity source) {
        Hashtag dest = null;
        if (Objects.nonNull(source) && Objects.nonNull(source.getText())) {
            dest = Hashtag.builder()
                    .name(source.getText())
                    .counter(DEFAULT_COUNTER)
                    .build();
        }
        return dest;
    }
}
