package com.grizzly.subscription.matcher.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
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
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String subscriptionsHave;
    @Column(nullable = false)
    private String subscriptionsWant;
    @Column(nullable = false)
    private String country;

    User() {}

    public User(String firstName, String lastName, String gender, String email, String phone, String subscriptionsHave, String subscriptionsWanted, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.subscriptionsHave = subscriptionsHave;
        this.subscriptionsWant = subscriptionsWanted;
        this.country = country;
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

    public String getSubscriptionsHave() {
        return subscriptionsHave;
    }

    public void setSubscriptionsHave(String subscriptionsHave) {
        this.subscriptionsHave = subscriptionsHave;
    }

    public String getSubscriptionsWant() {
        return subscriptionsWant;
    }

    public void setSubscriptionsWant(String subscriptionsWant) {
        this.subscriptionsWant = subscriptionsWant;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && Objects.equals(gender, user.gender) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(subscriptionsHave, user.subscriptionsHave) && Objects.equals(subscriptionsWant, user.subscriptionsWant) && Objects.equals(country, user.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, email, phone, subscriptionsHave, subscriptionsWant, country);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", subscriptionsHave=" + subscriptionsHave +
                ", subscriptionsWanted=" + subscriptionsWant +
                ", country=" + country +
                '}';
    }
}
