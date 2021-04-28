package com.acciona.challenge.service;

import com.acciona.challenge.domain.model.Hashtag;
import com.acciona.challenge.service.persistence.HashtagPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HashtagPublishedService {

    @Value("${hashtags.service.defaultLimit}")
    private Integer defaultLimit;
    @Value("${hashtags.service.defaultOffset}")
    private Integer defaultOffset;
    @Autowired
    private HashtagPersistenceService hashtagPersistenceService;

    public List<Hashtag> search(Integer limit, Integer offset) {
        if (Objects.isNull(limit)) {
            limit = defaultLimit;
        }
        if (Objects.isNull(offset)) {
            offset = defaultOffset;
        }
        return hashtagPersistenceService.list(limit, offset);
    }
}
