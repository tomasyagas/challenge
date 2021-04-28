package com.acciona.challenge.api.controller;

import com.acciona.challenge.domain.model.Tweet;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(
        value = "Tweets data API",
        tags = "01 - Tweets",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public abstract class TweetApi {

    public static final String BASE_PATH = "/api/tweets";

    @ApiOperation(value = "List tweets", nickname = "List and paginate tweets", notes = "", response = Tweet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Tweet.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found")})
    @RequestMapping(value = BASE_PATH,
            produces = {"application/json"},
            method = RequestMethod.GET)
    abstract ResponseEntity<List<Tweet>> list(
            @ApiParam(value = "Limiting and pagination") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Limiting and pagination") @RequestParam(value = "offset", required = false) Integer offset);

    @ApiOperation(value = "Tweets", nickname = "Validate tweet", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Updated successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Element not found, check your path variables")})
    @RequestMapping(value = BASE_PATH + "/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    abstract ResponseEntity<Void> update(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") Integer id,
            @ApiParam(value = "tweet") @Valid @RequestBody Tweet request);

    @ApiOperation(value = "Search tweets", nickname = "Search and filter tweets", notes = "", response = Tweet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Tweet.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found")})
    @RequestMapping(value = BASE_PATH,
            produces = {"application/json"},
            method = RequestMethod.POST)
    abstract ResponseEntity<List<Tweet>> search(
            @ApiParam(value = "Limiting and pagination") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Limiting and pagination") @RequestParam(value = "offset", required = false) Integer offset,
            @ApiParam(value = "Tweet") @RequestBody Tweet request);

}
