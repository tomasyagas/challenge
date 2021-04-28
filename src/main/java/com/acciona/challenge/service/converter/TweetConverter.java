package com.acciona.challenge.service.converter;

import com.acciona.challenge.domain.model.Tweet;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.Objects;

@Component
public class TweetConverter {

    private static final String WHITE_SPACE = " ";

    public Tweet convert(Status source) {
        Tweet dest = null;
        if (Objects.nonNull(source)) {
            String location = Strings.EMPTY;
            if (Objects.nonNull(source.getPlace())) {
                StringBuilder locationStringBuilder = new StringBuilder(source.getPlace().getName());
                locationStringBuilder.append(WHITE_SPACE);
                locationStringBuilder.append(source.getPlace().getCountry());
                location = locationStringBuilder.toString();
            }
            dest = Tweet.builder()
                    .user(source.getUser().getScreenName())
                    .text(source.getText())
                    .location(location)
                    .build();
        }
        return dest;
    }
}
