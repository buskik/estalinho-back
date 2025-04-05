package Estalinho.estalinho.controller;

import Estalinho.estalinho.domain.user.dto.UserResponseDTO;
import Estalinho.estalinho.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll() {
        return ResponseEntity.ok().body(userService.listAll());
    }
}
