package com.tinnt.user_service.repository;

import com.tinnt.user_service.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, Long> {

}
