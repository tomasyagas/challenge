package com.acciona.challenge.domain.repository;

import com.acciona.challenge.domain.model.Hashtag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HashtagRepository extends CrudRepository<Hashtag, String> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE hashtags h SET h.counter = h.counter + 1 WHERE h.name = :name",
            nativeQuery = true)
    void update(@Param("name") String name);

    @Query(value = "SELECT * FROM hashtags h ORDER BY h.counter DESC LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Hashtag> listSortByCounter(@Param("limit") Integer limit, @Param("offset") Integer offset);
}
