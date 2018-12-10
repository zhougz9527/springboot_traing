package com.example.springboot_traing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Author: Think
 * @Date: 2018/12/10
 * @Time: 15:06
 */
@NoRepositoryBean
public interface BaseMongoRepository<T, ID> extends MongoRepository<T, ID> {

}
