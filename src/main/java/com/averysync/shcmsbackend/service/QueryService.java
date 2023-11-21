package com.averysync.shcmsbackend.service;


import com.averysync.shcmsbackend.dao.QueryRepository;
import com.averysync.shcmsbackend.entity.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QueryService {

    private QueryRepository queryRepository;

    @Autowired
    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public void sendQuery(Query queryRequest, String userEmail) {
        Query query = new Query(queryRequest.getQuery(), queryRequest.getDescription());
        query.setUserEmail(userEmail);
        queryRepository.save(query);
    }
}
