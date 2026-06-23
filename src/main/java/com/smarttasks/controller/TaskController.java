package com.smarttasks.controller;

import com.smarttasks.model.Task;
import com.smarttasks.model.User;
import com.smarttasks.repository.TaskRepository;
import com.smarttasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // listar tarefas do usuário
    @GetMapping("/user/{email}")
    public List<Task> listar(@PathVariable String email) {
        User user = userRepository.findByEmail(email);
        return taskRepository.findByUser(user);
    }

    // criar tarefa
    @PostMapping("/user/{email}")
    public Task criar(@PathVariable String email, @RequestBody Task task) {
        User user = userRepository.findByEmail(email);
        task.setUser(user);
        return taskRepository.save(task);
    }
    // concluir tarefa
    @PutMapping("/{id}")
    public Task concluir(@PathVariable Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        task.setConcluida(true);

        return taskRepository.save(task);
    }
    // excluir tarefa
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
