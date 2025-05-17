package com.killadeco.killadeco.repository;

import com.killadeco.killadeco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
