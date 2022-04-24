package com.grizzly.subscription.matcher.service.user;

import com.grizzly.subscription.matcher.domain.User;
import com.grizzly.subscription.matcher.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    CollectionModel<EntityModel<User>> getUsers();

    EntityModel<User> getUserById(Long id);

    ResponseEntity<?> addUser(User user);

    ResponseEntity<?> replaceUser(User newUser, Long id);

    ResponseEntity<?> deleteUser(Long id);

    ResponseEntity<?> verifyUser(Long id);

    CollectionModel<EntityModel<User>> getUsersByTopicAndSubTopic(String topic, String subTopic);

    CollectionModel<EntityModel<User>> getUsersByTopic(String topic);

    CollectionModel<EntityModel<User>> getUsersBySubTopic(String subTopic);
}
