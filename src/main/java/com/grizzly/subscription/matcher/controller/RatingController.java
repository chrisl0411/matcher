package com.grizzly.subscription.matcher.controller;

import com.grizzly.subscription.matcher.domain.Rating;
import com.grizzly.subscription.matcher.repository.RatingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    RatingRepository ratingRepository;

    @PostMapping()
    public Rating addRating(
            @PathVariable Long ratingUserId,
            @RequestBody Rating rating) {
        return ratingRepository.save(rating);
    }

    @GetMapping()
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

}
