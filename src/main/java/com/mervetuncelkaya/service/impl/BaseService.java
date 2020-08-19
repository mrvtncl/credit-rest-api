package com.mervetuncelkaya.service.impl;

import com.mervetuncelkaya.exception.GeneralDatabaseException;
import com.mervetuncelkaya.service.IBaseService;
import com.mervetuncelkaya.util.Enums;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.logging.Logger;

public class BaseService<T, R extends MongoRepository<T, String>> implements IBaseService<T, R> {

    private static final Logger logger = Logger.getLogger(String.valueOf(BaseService.class));

    private R repository;

    public R getRepository() {
        return repository;
    }

    public BaseService(R repository) {
        this.repository = repository;
    }

    @Override
    public T findById(String id) throws GeneralDatabaseException {
        try {
            return (T) repository.findById(id);
        } catch (Exception ex) {
            logger.throwing("BaseService", "findById", ex);
            throw new GeneralDatabaseException(ex);
        }
    }

    @Override
    public T save(T object) throws GeneralDatabaseException {
        try {
            return repository.save(object);
        } catch (Exception ex) {
            logger.throwing("BaseService", "save", ex);
            throw new GeneralDatabaseException(ex);
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            logger.throwing("BaseService", "findAll", ex);
            throw new GeneralDatabaseException(ex);
        }
    }

    @Override
    public void deleteAll() {
        try {
            repository.deleteAll();
        } catch (Exception ex) {
            logger.throwing("BaseService", "deleteAll", ex);
            throw new GeneralDatabaseException(ex);
        }
    }

}
