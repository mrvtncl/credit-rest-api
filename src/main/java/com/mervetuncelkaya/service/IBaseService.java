package com.mervetuncelkaya.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IBaseService<T, R extends MongoRepository<T, String>> {
    public T findById(String id);

    public List<T> findAll();

    public T save(T object);

    public void deleteAll();
}
