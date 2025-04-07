package com.example.springboot_postgresql.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot_postgresql.repo.TodoListRepo;
import com.example.springboot_postgresql.model.TodoList;

import java.util.List;

@Service
public class TodoListService {
    @Autowired
    private TodoListRepo todoListRepo;
    private final ChatClient chatClient;
    private final String prompt = "What do you think of this given task? Answer in the following format Difficulty, one sentence reasoning: ";

    public TodoListService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public TodoList addTask(TodoList task) {
        task.setAiInput(this.chatClient.prompt().user(prompt + task.getDescription()).call().content());
        return todoListRepo.save(task);
    }

    public List<TodoList> getTasks () {
        return todoListRepo.findAll();
    }

    public TodoList getTaskById(Long id) {
        return todoListRepo.findById(id).orElse(null);
    }

    public void deleteTask(Long id) {
        todoListRepo.deleteById(id);
    }

    public TodoList updateTask(TodoList updatedTask, Long id) {
        TodoList task = todoListRepo.findById(id).orElse(null);
        if (task == null) {
            return null;
        }
        if (updatedTask.getTitle() != null) task.setTitle(updatedTask.getTitle());
        if (updatedTask.getDescription() != null) task.setDescription(updatedTask.getDescription());
        if (updatedTask.getStatus() != null) task.setStatus(updatedTask.getStatus());
        return todoListRepo.save(task);
    }
}
