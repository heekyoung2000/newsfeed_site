package com.example.reservation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "newsfeedClient", url = "${feign.newsfeedClient.url}")
public interface NewsfeedClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/internal/newsfeeds", consumes = "application/json")
    boolean checkBoardExists(@RequestParam(name = "boardId") Long boardId);
}
