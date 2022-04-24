package com.grizzly.subscription.matcher.controller;

import com.grizzly.subscription.matcher.assembler.RatingModelAssembler;
import com.grizzly.subscription.matcher.domain.Rating;
import com.grizzly.subscription.matcher.exceptions.RatingNotFoundException;
import com.grizzly.subscription.matcher.repository.RatingRepository;
import com.grizzly.subscription.matcher.service.user.UserServiceImpl;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingRepository ratingRepository;
    private final RatingModelAssembler ratingModelAssembler;

    private final UserServiceImpl userService;

    public RatingController(RatingRepository ratingRepository, RatingModelAssembler ratingModelAssembler, UserServiceImpl userService) {
        this.ratingRepository = ratingRepository;
        this.ratingModelAssembler = ratingModelAssembler;
        this.userService = userService;
    }

    @PostMapping("")
    public EntityModel<Rating> addRating(@RequestBody Rating rating) {
        Long ratedUserId = rating.getRatedUserId();
        userService.getUserById(ratedUserId);
        return ratingModelAssembler.toModel(ratingRepository.save(rating));
    }

    @GetMapping("")
    public CollectionModel<EntityModel<Rating>> getAllRatings() {
        List<EntityModel<Rating>> allRatings = ratingRepository.findAll().stream()
                .map(ratingModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(allRatings,
                linkTo(methodOn(RatingController.class).getAllRatings()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Rating> getRatingById(@PathVariable Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException(id));
        return ratingModelAssembler.toModel(rating);
    }

    @GetMapping("/user/{userId}")
    public CollectionModel<EntityModel<Rating>> getRatingsForUserId(@PathVariable Long userId) {
        userService.getUserById(userId);
        final List<EntityModel<Rating>> userRatings = ratingRepository.findAll().stream()
                .filter(rating -> rating.getRatedUserId().equals(userId))
                .map(ratingModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(userRatings,
                linkTo(methodOn(RatingController.class).getRatingsForUserId(userId)).withSelfRel());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRatingById(@PathVariable Long id) {
        getRatingById(id);
        ratingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
