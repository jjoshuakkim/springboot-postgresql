package com.example.springboot_postgresql.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_postgresql.repo.TodoListRepo;
import com.example.springboot_postgresql.model.TodoList;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoListController {

    @Autowired
    private TodoListRepo todoListRepo;

    @PostMapping("/tasks")
    public ResponseEntity<TodoList> addTask(@RequestBody TodoList task) {;
        return new ResponseEntity<>(todoListRepo.save(task), HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TodoList>> getTasks() {
        List<TodoList> tasks = todoListRepo.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TodoList> getTaskById(@PathVariable Long id) {
        TodoList task = todoListRepo.findById(id).orElse(null);
        if (task == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("tasks/{id}")
    public ResponseEntity<TodoList> updateTask(@RequestBody TodoList updatedTask, @PathVariable Long id) {
        TodoList task = todoListRepo.findById(id).orElse(null);
        if (task == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (updatedTask.getTitle() != null) task.setTitle(updatedTask.getTitle());
        if (updatedTask.getDescription() != null) task.setDescription(updatedTask.getDescription());
        if (updatedTask.getStatus() != null) task.setStatus(updatedTask.getStatus());
        todoListRepo.save(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<TodoList> deleteTask(@PathVariable Long id) {
        todoListRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
