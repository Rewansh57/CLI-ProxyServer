package org.example.springsecuritylearning2.videotut.repository;

import org.example.springsecuritylearning2.videotut.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
