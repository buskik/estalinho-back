package Estalinho.estalinho.controller;

import Estalinho.estalinho.domain.user.dto.user.UserRequestDTO;
import Estalinho.estalinho.domain.user.dto.user.UserResponseDTO;
import Estalinho.estalinho.repository.UserRepository;
import Estalinho.estalinho.service.UserService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll() {
        return ResponseEntity.ok().body(userService.listAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserRequestDTO user) {
        boolean wasCreated = userService.create(UserRequestDTO.fromUser(user));
        return wasCreated ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @Builder
    @PutMapping
    public ResponseEntity<Void> updateOne(@Valid @RequestBody UserRequestDTO clientRequestDTO) {
        boolean responseDTO = userService.updateOne(UserRequestDTO.fromUser(clientRequestDTO));
        return responseDTO ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteOne(@PathVariable("id") long id) {
        boolean responseDTO = userService.DeleteOne(id);
        return responseDTO ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    };
}
