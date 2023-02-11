package com.example.demousermanagement.web.rest;

import com.example.demousermanagement.service.UserService;
import com.example.demousermanagement.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        val userCreated = userService.createUser(user);
        return ResponseEntity
                .created(new URI("/users/" + userCreated.getId()))
                .body(userCreated);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        Optional<User> user = userService.getUser(id);
        return ResponseEntity.of(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> replaceUser(@PathVariable Integer id, @RequestBody User user) {
        if (!id.equals(user.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Optional<User> userUpdated = userService.replaceUser(user);
        return ResponseEntity.of(userUpdated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        if (!id.equals(user.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Optional<User> userUpdated = userService.updateUser(user);
        return ResponseEntity.of(userUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
