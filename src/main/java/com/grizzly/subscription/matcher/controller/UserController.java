package com.grizzly.subscription.matcher.controller;

import com.grizzly.subscription.matcher.assembler.UserModelAssembler;
import com.grizzly.subscription.matcher.domain.User;
import com.grizzly.subscription.matcher.exceptions.UserNotFoundException;
import com.grizzly.subscription.matcher.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserModelAssembler userModelAssembler;

    public UserController(UserRepository userRepository, UserModelAssembler userModelAssembler) {
        this.userRepository = userRepository;
        this.userModelAssembler = userModelAssembler;
    }

    @GetMapping()
    public CollectionModel<EntityModel<User>> getUsers(
            @RequestParam(value = "topic", required = false) String topic,
            @RequestParam(value = "subTopic", required = false) String subTopic
    ) {
        if (null != topic && null != subTopic) {
            List<EntityModel<User>> users = userRepository.findAll().stream()
                    .filter(u -> u.getTopic().equals(topic) && u.getSubTopic().equals(subTopic))
                    .map(userModelAssembler::toModel)
                    .collect(Collectors.toList());
            return CollectionModel.of(users,
                    linkTo(methodOn(UserController.class).getUsers(topic, subTopic)).withSelfRel());
        } else if (null != topic) {
            List<EntityModel<User>> users = userRepository.findAll().stream()
                    .filter(u -> u.getTopic().equals(topic))
                    .map(userModelAssembler::toModel)
                    .collect(Collectors.toList());
            return CollectionModel.of(users,
                    linkTo(methodOn(UserController.class).getUsers(topic, subTopic)).withSelfRel());
        } else if (null != subTopic) {
            List<EntityModel<User>> users = userRepository.findAll().stream()
                    .filter(u -> u.getSubTopic().equals(subTopic))
                    .map(userModelAssembler::toModel)
                    .collect(Collectors.toList());
            return CollectionModel.of(users,
                    linkTo(methodOn(UserController.class).getUsers(topic, subTopic)).withSelfRel());
        } else {
            List<EntityModel<User>> users = userRepository.findAll().stream()
                    .map(userModelAssembler::toModel)
                    .collect(Collectors.toList());
            return CollectionModel.of(users,
                    linkTo(methodOn(UserController.class).getUsers(topic, subTopic)).withSelfRel());
        }
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userModelAssembler.toModel(user);

    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        EntityModel<User> entityModel =
                userModelAssembler.toModel(userRepository.save(user));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                .toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        User userObject = userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setGender(newUser.getGender());
                    user.setAge((newUser.getAge()));
                    user.setCity((newUser.getCity()));
                    user.setState((newUser.getState()));
                    user.setOccupation((newUser.getOccupation()));
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    user.setTopic(newUser.getTopic());
                    user.setSubTopic((newUser.getSubTopic()));
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                            newUser.setId(id);
                            return userRepository.save(newUser);
                        }
                );
        EntityModel<User> entityModel = userModelAssembler.toModel(userObject);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/verify")
    public ResponseEntity<?> verifyUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        Integer verifiedCount = user.getVerifiedCount() + 1;
        user.setVerifiedCount(verifiedCount);
        userRepository.save(user);
        EntityModel<User> entityModel = userModelAssembler.toModel(user);
        return ResponseEntity.ok(entityModel);
    }

}
