package ru.skypro.lessons.springboot.springf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.lessons.springboot.springf.config.AuthUser;

public interface  AuthUserRepository extends JpaRepository<AuthUser,Long> {
    AuthUser findByUsername(String username);
}
