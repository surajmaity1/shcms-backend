package com.averysync.shcmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "queries")
@Data
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="query")
    private String query;

    @Column(name="description")
    private String description;

    @Column(name="admin_email")
    private String adminEmail;

    @Column(name="response")
    private String response;

    @Column(name="closed")
    private boolean closed;

    public Query(){}

    public Query(String query, String description) {
        this.query = query;
        this.description = description;
    }
}













