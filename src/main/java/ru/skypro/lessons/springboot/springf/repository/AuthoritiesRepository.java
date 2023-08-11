package ru.skypro.lessons.springboot.springf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.lessons.springboot.springf.config.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities,Long> {
}
