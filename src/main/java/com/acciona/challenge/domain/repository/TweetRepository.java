package com.acciona.challenge.domain.repository;

import com.acciona.challenge.domain.model.Tweet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Integer> {

    @Query(value = "SELECT * FROM tweets t LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Tweet> list(@Param("limit") Integer limit, @Param("offset") Integer offset);

    @Query(value = "SELECT * FROM tweets t where t.user LIKE :user AND t.validated = true LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Tweet> listByUserAndValidated(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("user") String user);

    @Query(value = "SELECT * FROM tweets t where t.user LIKE :user LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Tweet> listByUser(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("user") String user);

    @Query(value = "SELECT * FROM tweets t where t.validated = true LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Tweet> listValidated(@Param("limit") Integer limit, @Param("offset") Integer offset);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tweets t SET t.validated = true WHERE t.id = :id",
            nativeQuery = true)
    void updateValidated(@Param("id") Integer id);
}
