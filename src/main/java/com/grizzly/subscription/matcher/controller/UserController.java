package com.grizzly.subscription.matcher.controller;

import com.grizzly.subscription.matcher.domain.User;
import com.grizzly.subscription.matcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public CollectionModel<EntityModel<User>> getUsers(
            @RequestParam(value = "topic", required = false) String topic,
            @RequestParam(value = "subTopic", required = false) String subTopic
    ) {
        if (null != topic && null != subTopic) {
            return userService.getUsersByTopicAndSubTopic(topic, subTopic);
        } else if (null != topic) {
            return userService.getUsersByTopic(topic);
        } else if (null != subTopic) {
            return userService.getUsersBySubTopic(subTopic);
        } else {
            return userService.getUsers();
        }
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.replaceUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}/verify")
    public ResponseEntity<?> verifyUser(@PathVariable Long id) {
        return userService.verifyUser(id);
    }

}
