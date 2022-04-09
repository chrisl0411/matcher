package com.grizzly.subscription.matcher.domain;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID ratingId = randomUUID();

    @Column(nullable = false)
    private Long ratedUserId;
    @Column(nullable = false)
    private Long ratingUserId;
    @Column(nullable = false)
    private Long rating;
    @Column(nullable = false)
    private String reviewDescription;
    @Column(nullable = false)
    private Boolean anonymous;
    @Column(nullable = false)
    private LocalDateTime currentTimeStamp;

    public void setRatingId(UUID ratingId) {
        this.ratingId = ratingId;
    }

    public Long getRatedUserId() {
        return ratedUserId;
    }

    public void setRatedUserId(Long ratedUserId) {
        this.ratedUserId = ratedUserId;
    }

    public Long getRatingUserId() {
        return ratingUserId;
    }

    public void setRatingUserId(Long ratingUserId) {
        this.ratingUserId = ratingUserId;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public LocalDateTime getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public void setCurrentTimeStamp(LocalDateTime currentTimeStamp) {
        this.currentTimeStamp = currentTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return Objects.equals(ratingId, rating1.ratingId) && Objects.equals(ratedUserId, rating1.ratedUserId) && Objects.equals(ratingUserId, rating1.ratingUserId) && Objects.equals(rating, rating1.rating) && Objects.equals(reviewDescription, rating1.reviewDescription) && Objects.equals(anonymous, rating1.anonymous) && Objects.equals(currentTimeStamp, rating1.currentTimeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingId, ratedUserId, ratingUserId, rating, reviewDescription, anonymous, currentTimeStamp);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", ratedUserId=" + ratedUserId +
                ", ratingUserId=" + ratingUserId +
                ", rating=" + rating +
                ", reviewDescription='" + reviewDescription + '\'' +
                ", anonymous=" + anonymous +
                ", currentTimeStamp=" + currentTimeStamp +
                '}';
    }
}
