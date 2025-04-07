package com.example.springboot_postgresql.controller;

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

import com.example.springboot_postgresql.model.TodoList;
import com.example.springboot_postgresql.service.TodoListService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @PostMapping("/tasks")
    public ResponseEntity<TodoList> addTask(@RequestBody TodoList task) {
        return new ResponseEntity<>(todoListService.addTask(task), HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TodoList>> getTasks() {
        return new ResponseEntity<>(todoListService.getTasks(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TodoList> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(todoListService.getTaskById(id), HttpStatus.OK);
    }

    @PutMapping("tasks/{id}")
    public ResponseEntity<TodoList> updateTask(@RequestBody TodoList updatedTask, @PathVariable Long id) {
        TodoList task = todoListService.getTaskById(id);
        todoListService.updateTask(updatedTask, id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<TodoList> deleteTask(@PathVariable Long id) {
        todoListService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
