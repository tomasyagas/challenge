package com.acciona.challenge.api.controller;

import com.acciona.challenge.domain.model.Tweet;
import com.acciona.challenge.service.TweetPublishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class TweetApiController extends TweetApi {

    @Autowired
    private TweetPublishedService service;

    public ResponseEntity<List<Tweet>> list(
            @RequestParam(value = "limit", required = false, defaultValue = "100") Integer limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        return new ResponseEntity(service.search(limit, offset, null), HttpStatus.OK);
    }

    public ResponseEntity update(
            @PathVariable(value = "id") Integer id,
            @RequestBody Tweet request) {
        request.setId(id);
        service.update(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Tweet>> search(
            @RequestParam(value = "limit", required = false, defaultValue = "100") Integer limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestBody Tweet request) {
        return new ResponseEntity(service.search(limit, offset, request), HttpStatus.OK);
    }
}
