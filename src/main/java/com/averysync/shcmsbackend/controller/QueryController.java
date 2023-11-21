package com.averysync.shcmsbackend.controller;

import com.averysync.shcmsbackend.entity.Query;
import com.averysync.shcmsbackend.service.QueryService;
import com.averysync.shcmsbackend.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/shcms/queries")
public class QueryController {

    private QueryService queryService;

    @Autowired
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping("/secure/send/query")
    public void sendQuery(@RequestHeader(value="Authorization") String token,
                          @RequestBody Query queryRequest) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        queryService.sendQuery(queryRequest, userEmail);
    }
}
