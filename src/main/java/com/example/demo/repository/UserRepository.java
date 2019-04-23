package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
