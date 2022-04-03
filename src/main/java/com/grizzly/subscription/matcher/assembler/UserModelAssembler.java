package com.grizzly.subscription.matcher.assembler;

import com.grizzly.subscription.matcher.controller.UserController;
import com.grizzly.subscription.matcher.domain.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User> > {

    /**
     * Assembler converts object to model-based object
     * - User object to EntityModel<User>
     */
    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getUsers(user.getTopic(), user.getSubTopic())).withRel("users"));
    }

}
