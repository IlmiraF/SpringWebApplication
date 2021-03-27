package ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
