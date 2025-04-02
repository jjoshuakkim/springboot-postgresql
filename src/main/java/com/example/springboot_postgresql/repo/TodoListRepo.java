package com.example.springboot_postgresql.repo;

import java.util.List;
import com.example.springboot_postgresql.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepo extends JpaRepository<TodoList, Long>{
}
