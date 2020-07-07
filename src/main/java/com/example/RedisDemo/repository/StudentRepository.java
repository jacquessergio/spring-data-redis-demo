package com.example.RedisDemo.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.RedisDemo.model.Student;

public interface StudentRepository extends CrudRepository<Student, String> {

}
