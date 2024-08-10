package com.locals.locals_api.modules.users.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locals.locals_api.modules.users.entities.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, UUID> {
    Optional<UsersEntity> findByEmail(String email);
}
