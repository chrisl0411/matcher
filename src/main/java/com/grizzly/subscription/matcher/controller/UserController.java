package com.grizzly.subscription.matcher.controller;

import com.grizzly.subscription.matcher.domain.User;
import com.grizzly.subscription.matcher.exceptions.UserNotFoundException;
import com.grizzly.subscription.matcher.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setGender(newUser.getGender());
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    user.setSubscriptionsHave(newUser.getSubscriptionsHave());
                    user.setSubscriptionsWant(newUser.getSubscriptionsWant());
                    user.setCountry(newUser.getCountry());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                    }
                );
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

}
