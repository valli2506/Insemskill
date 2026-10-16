package com.klu.insemskill;

import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final JdbcTemplate jdbc;

    public UserController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @PostMapping("/login-db")
    public String login(@RequestBody Map<String, String> data) {

        var users = jdbc.query(
            "SELECT password FROM users WHERE username = ?",
            (rs, rowNum) -> rs.getString("password"),
            data.get("username")
        );

        if (!users.isEmpty() &&
            BCrypt.checkpw(data.get("password"), users.get(0))) {
            return "Authentication Successful";
        }

        return "Authentication Failed";
    }
}