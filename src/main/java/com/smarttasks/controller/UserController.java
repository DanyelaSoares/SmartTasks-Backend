package com.smarttasks.controller;
import com.smarttasks.model.User;
import com.smarttasks.model.LoginRequest;
import com.smarttasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.smarttasks.security.JwtUtil;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null) {
            return ResponseEntity.status(401).body("Email não encontrado");
        }

        if (!user.getSenha().equals(loginRequest.getSenha())) {
            return ResponseEntity.status(401).body("Senha incorreta");
        }

        String token = JwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(token);

    }

}