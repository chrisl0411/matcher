package com.grizzly.subscription.matcher.controller;

import com.grizzly.subscription.matcher.assembler.RatingModelAssembler;
import com.grizzly.subscription.matcher.domain.Rating;
import com.grizzly.subscription.matcher.exceptions.RatingNotFoundException;
import com.grizzly.subscription.matcher.repository.RatingRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingRepository ratingRepository;
    private final RatingModelAssembler ratingModelAssembler;

    public RatingController(RatingRepository ratingRepository, RatingModelAssembler ratingModelAssembler) {
        this.ratingRepository = ratingRepository;
        this.ratingModelAssembler = ratingModelAssembler;
    }

    // TODO: 4/10/22 Add functionality to check if user giving rating exists. if user doesn't exist, throw UserNotFoundException
    @PostMapping("")
    public EntityModel<Rating> addRating(@RequestBody Rating rating) {
        return ratingModelAssembler.toModel(ratingRepository.save(rating));
    }

    @GetMapping("")
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Rating> getRatingById(@PathVariable Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException(id));
        return ratingModelAssembler.toModel(rating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRatingById(@PathVariable Long id) {
        ratingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
