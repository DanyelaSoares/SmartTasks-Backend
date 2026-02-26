package com.smarttasks.controller;

import com.smarttasks.model.LoginRequest;
import com.smarttasks.model.User;
import com.smarttasks.repository.UserRepository;
import com.smarttasks.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {

        // ✅ aceita "nome" OU "name"
        String nome = body.get("nome");
        if (nome == null || nome.isBlank()) {
            nome = body.get("name");
        }

        String email = body.get("email");

        // ✅ aceita "senha" OU "password"
        String senha = body.get("senha");
        if (senha == null || senha.isBlank()) {
            senha = body.get("password");
        }

        if (nome == null || nome.isBlank()
                || email == null || email.isBlank()
                || senha == null || senha.isBlank()) {
            return ResponseEntity.badRequest().body("Todos os campos são obrigatórios");
        }

        User existing = userRepository.findByEmail(email);
        if (existing != null) {
            return ResponseEntity.status(409).body("Email já cadastrado");
        }

        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);

        userRepository.save(user);

        return ResponseEntity.status(201).body(Map.of(
                "message", "Cadastro realizado com sucesso",
                "email", user.getEmail()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null) {
            return ResponseEntity.status(401).body("Email não encontrado");
        }

        if (!user.getSenha().equals(loginRequest.getSenha())) {
            return ResponseEntity.status(401).body("Senha incorreta");
        }

        String token = JwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "email", user.getEmail()
        ));
    }
}
