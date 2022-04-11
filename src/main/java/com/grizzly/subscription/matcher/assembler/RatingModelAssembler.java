package com.grizzly.subscription.matcher.assembler;

import com.grizzly.subscription.matcher.controller.RatingController;
import com.grizzly.subscription.matcher.controller.UserController;
import com.grizzly.subscription.matcher.domain.Rating;
import com.grizzly.subscription.matcher.domain.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RatingModelAssembler implements RepresentationModelAssembler<Rating, EntityModel<Rating>> {

    @Override
    public EntityModel<Rating> toModel(Rating rating) {
        return EntityModel.of(rating,
                linkTo(methodOn(RatingController.class).getRatingById(rating.getId())).withSelfRel()
        );
    }
}
