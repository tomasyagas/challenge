package com.acciona.challenge.api.controller;

import com.acciona.challenge.domain.model.Hashtag;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(
        value = "Hashtags data API",
        tags = "02 - Hashtags",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public abstract class HashtagApi {

    public static final String BASE_PATH = "/api/hashtags";

    @ApiOperation(value = "List hashtags", nickname = "List and paginate hashtags", notes = "", response = Hashtag.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Hashtag.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found")})
    @RequestMapping(value = BASE_PATH,
            produces = {"application/json"},
            method = RequestMethod.GET)
    abstract ResponseEntity<List<Hashtag>> list(
            @ApiParam(value = "Limiting and pagination") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Limiting and pagination") @RequestParam(value = "offset", required = false) Integer offset);
}
