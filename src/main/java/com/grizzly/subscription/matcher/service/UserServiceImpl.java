package com.grizzly.subscription.matcher.service;

import com.grizzly.subscription.matcher.assembler.UserModelAssembler;
import com.grizzly.subscription.matcher.domain.User;
import com.grizzly.subscription.matcher.exceptions.UserNotFoundException;
import com.grizzly.subscription.matcher.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserModelAssembler userModelAssembler;

    public UserServiceImpl(UserRepository userRepository, UserModelAssembler userModelAssembler) {
        this.userRepository = userRepository;
        this.userModelAssembler = userModelAssembler;
    }

    @Override
    public CollectionModel<EntityModel<User>> getUsers() {
        List<EntityModel<User>> users = userRepository.findAll().stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(users,
                linkTo(methodOn(UserServiceImpl.class).getUsers()).withSelfRel());
    }

    @Override
    public EntityModel<User> getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userModelAssembler.toModel(user);
    }

    @Override
    public ResponseEntity<?> addUser(User user) {
        EntityModel<User> entityModel =
                userModelAssembler.toModel(userRepository.save(user));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(entityModel);
    }

    @Override
    public ResponseEntity<?> replaceUser(User newUser, Long id) {
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

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> verifyUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        Integer verifiedCount = user.getVerifiedCount() + 1;
        user.setVerifiedCount(verifiedCount);
        userRepository.save(user);
        EntityModel<User> entityModel = userModelAssembler.toModel(user);
        return ResponseEntity.ok(entityModel);
    }

    @Override
    public CollectionModel<EntityModel<User>> getUsersByTopicAndSubTopic(String topic, String subTopic) {
        List<EntityModel<User>> users = userRepository.findAll().stream()
                .filter(u -> u.getTopic().equals(topic) && u.getSubTopic().equals(subTopic))
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(users,
                linkTo(methodOn(UserServiceImpl.class).getUsersByTopicAndSubTopic(topic, subTopic)).withSelfRel());
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

    @Override
    public CollectionModel<EntityModel<User>> getUsersBySubTopic(String subTopic) {
        List<EntityModel<User>> users = userRepository.findAll().stream()
                .filter(u -> u.getSubTopic().equals(subTopic))
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(users,
                linkTo(methodOn(UserServiceImpl.class).getUsersBySubTopic(subTopic)).withSelfRel());
    }

}
