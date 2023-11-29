package com.averysync.shcmsbackend.controller;

import com.averysync.shcmsbackend.entity.Query;
import com.averysync.shcmsbackend.requestmodels.AdminQuestionRequest;
import com.averysync.shcmsbackend.service.QueryService;
import com.averysync.shcmsbackend.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
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

    @PutMapping("/secure/admin/query")
    public void responseQuery(@RequestHeader(value="Authorization") String token,
                           @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("ONLY ADMINISTRATION GRANTED");
        }
        queryService.responseQuery(adminQuestionRequest, userEmail);
    }
}
