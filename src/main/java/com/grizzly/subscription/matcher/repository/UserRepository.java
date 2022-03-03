package com.grizzly.subscription.matcher.repository;

import com.grizzly.subscription.matcher.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
