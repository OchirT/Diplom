package ru.netology.diplomproject.repository;

import ru.netology.diplomproject.model.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String login);
}
