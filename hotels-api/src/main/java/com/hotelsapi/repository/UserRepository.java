package com.hotelsapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotelsapi.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
