package com.sageyoon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sageyoon.blog.model.User;

// 기본적인 CRUD 가능 
// DAO 기능
// 자동으로 bean등록이 된다.
// @Repository 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {

}
