package com.grizzly.subscription.matcher.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String occupation;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private Integer verifiedCount = 0;
    @Column(nullable = false)
    private String topic;
    @Column(nullable = false)
    private String subTopic;
    @Column()
    private Long rating;
    @Column()
    private String aboutMe;

    User() {}

    public User(String firstName, String lastName, String gender, Integer age, String city, String state, String occupation, String email, String phone, Integer verifiedCount, String topic, String subTopic, Long rating, String aboutMe) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.state = state;
        this.occupation = occupation;
        this.email = email;
        this.phone = phone;
        this.verifiedCount = verifiedCount;
        this.topic = topic;
        this.subTopic = subTopic;
        this.rating = rating;
        this.aboutMe = aboutMe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getVerifiedCount() {
        return verifiedCount;
    }

    public void setVerifiedCount(Integer verifiedCount) {
        this.verifiedCount = verifiedCount;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(gender, user.gender) && Objects.equals(age, user.age) && Objects.equals(city, user.city) && Objects.equals(state, user.state) && Objects.equals(occupation, user.occupation) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(verifiedCount, user.verifiedCount) && Objects.equals(topic, user.topic) && Objects.equals(subTopic, user.subTopic) && Objects.equals(rating, user.rating) && Objects.equals(aboutMe, user.aboutMe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, age, city, state, occupation, email, phone, verifiedCount, topic, subTopic, rating, aboutMe);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", occupation='" + occupation + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", verifiedCount=" + verifiedCount +
                ", topic='" + topic + '\'' +
                ", subTopic='" + subTopic + '\'' +
                ", rating=" + rating +
                ", aboutMe='" + aboutMe + '\'' +
                '}';
    }
}
