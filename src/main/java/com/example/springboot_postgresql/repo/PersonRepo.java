package com.example.springboot_postgresql.repo;

import com.example.springboot_postgresql.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long>{

}
