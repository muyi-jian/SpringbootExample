package com.fincodehub.jpa.repository;


import com.fincodehub.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author YangJian
 * @version 1.0.0
 * @title UserRepository
 * @create 2025/5/8 21:24
 * @description <TODO description class purpose>
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
