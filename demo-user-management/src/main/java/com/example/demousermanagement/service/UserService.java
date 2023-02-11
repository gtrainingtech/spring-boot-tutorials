package com.example.demousermanagement.service;

import com.example.demousermanagement.domain.User;
import com.example.demousermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        log.info("Creating user: {}", user);
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    public Optional<User> getUser(Integer id) {
        log.info("Getting user with id: {}", id);
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(User user) {
        log.info("Updating user: {}", user);
        return getUser(user.getId())
                .map(u -> {
                    if (user.getFirstName() != null)
                        u.setFirstName(user.getFirstName());
                    if (user.getLastName() != null)
                        u.setLastName(user.getLastName());
                    if (user.getEmail() != null)
                        u.setEmail(user.getEmail());
                    if (user.getPassword() != null)
                        u.setPassword(user.getPassword());
                    if (user.getPhone() != null)
                        u.setPhone(user.getPhone());
                    if (user.getUserStatus() != null)
                        u.setUserStatus(user.getUserStatus());
                    return userRepository.save(u);
                });
    }

    public Optional<User> replaceUser(User user) {
        log.info("Replacing user: {}", user);
        return getUser(user.getId())
                .map(u -> userRepository.save(user));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
