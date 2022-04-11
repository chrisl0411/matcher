package com.grizzly.subscription.matcher.domain;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column
    private Date currentTimeStamp;

    public Rating() {
    }

    public Rating(Long ratedUserId, Long ratingUserId, Long rating, String reviewDescription, Boolean anonymous) {
        this.ratedUserId = ratedUserId;
        this.ratingUserId = ratingUserId;
        this.rating = rating;
        this.reviewDescription = reviewDescription;
        this.anonymous = anonymous;
    }

    @PrePersist
    protected void onCreate() {
        currentTimeStamp = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public void setCurrentTimeStamp(Date currentTimeStamp) {
        this.currentTimeStamp = currentTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return Objects.equals(id, rating1.id) && Objects.equals(ratedUserId, rating1.ratedUserId) && Objects.equals(ratingUserId, rating1.ratingUserId) && Objects.equals(rating, rating1.rating) && Objects.equals(reviewDescription, rating1.reviewDescription) && Objects.equals(anonymous, rating1.anonymous) && Objects.equals(currentTimeStamp, rating1.currentTimeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ratedUserId, ratingUserId, rating, reviewDescription, anonymous, currentTimeStamp);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", ratedUserId=" + ratedUserId +
                ", ratingUserId=" + ratingUserId +
                ", rating=" + rating +
                ", reviewDescription='" + reviewDescription + '\'' +
                ", anonymous=" + anonymous +
                ", currentTimeStamp=" + currentTimeStamp +
                '}';
    }
}
