package com.nicogmerz4.portfolio.repository;

import com.nicogmerz4.portfolio.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByName(String name);
}
