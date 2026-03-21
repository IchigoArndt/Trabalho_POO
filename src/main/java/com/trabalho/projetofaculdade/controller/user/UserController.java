package com.trabalho.projetofaculdade.controller.user;

import com.trabalho.projetofaculdade.dto.user.UserRequestDTO;
import com.trabalho.projetofaculdade.dto.user.UserResponseDTO;
import com.trabalho.projetofaculdade.service.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarTodos() {
        return ResponseEntity.ok(userService.GetAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.GetById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> criar(@RequestBody @Valid UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.Add(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UserRequestDTO dto) {
        return ResponseEntity.ok(userService.Update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        userService.Delete(id);
        return ResponseEntity.noContent().build();
    }

}
