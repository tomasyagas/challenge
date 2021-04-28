package com.acciona.challenge.api.controller;

import com.acciona.challenge.domain.model.Hashtag;
import com.acciona.challenge.service.HashtagPublishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class HashtagApiController extends HashtagApi {

    @Autowired
    private HashtagPublishedService service;

    public ResponseEntity<List<Hashtag>> list(
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        return new ResponseEntity(service.search(limit, offset), HttpStatus.OK);
    }
}
