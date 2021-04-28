package com.acciona.challenge.service.persistence;

import com.acciona.challenge.domain.model.Hashtag;
import com.acciona.challenge.domain.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagPersistenceService {

    @Autowired
    private HashtagRepository repository;

    public void upsert(Hashtag hashtag) {
        if (repository.existsById(hashtag.getName())) {
            repository.update(hashtag.getName());
        } else {
            repository.save(hashtag);
        }
    }

    public List<Hashtag> list(Integer limit, Integer offset) {
        return repository.listSortByCounter(limit, offset);
    }
}
