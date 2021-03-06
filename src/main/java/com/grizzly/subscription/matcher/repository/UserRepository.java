package com.grizzly.subscription.matcher.repository;

import com.grizzly.subscription.matcher.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {

}
