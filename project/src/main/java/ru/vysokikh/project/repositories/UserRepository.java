package ru.vysokikh.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vysokikh.project.models.User;

public interface UserRepository  extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
