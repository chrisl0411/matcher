package com.grizzly.subscription.matcher.service;

import com.grizzly.subscription.matcher.assembler.UserModelAssembler;
import com.grizzly.subscription.matcher.controller.UserController;
import com.grizzly.subscription.matcher.domain.User;
import com.grizzly.subscription.matcher.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserModelAssembler userModelAssembler;

    public UserServiceImpl(UserRepository userRepository, UserModelAssembler userModelAssembler) {
        this.userRepository = userRepository;
        this.userModelAssembler = userModelAssembler;
    }

    @Override
    public CollectionModel<EntityModel<User>> getUsers() {
        return null;
    }

    @Override
    public EntityModel<User> getUserById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> addUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> replaceUser(User newUser, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> verifyUser(Long id) {
        return null;
    }

    @Override
    public CollectionModel<EntityModel<User>> getUsersByTopic(String topic) {
        List<EntityModel<User>> users = userRepository.findAll().stream()
                .filter(u -> u.getTopic().equals(topic))
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(users,
                linkTo(methodOn(UserServiceImpl.class).getUsersByTopic(topic)).withSelfRel());
    }
}
