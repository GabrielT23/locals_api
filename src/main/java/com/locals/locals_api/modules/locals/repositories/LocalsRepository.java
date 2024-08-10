package com.locals.locals_api.modules.locals.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locals.locals_api.modules.locals.entities.LocalsEntity;

public interface  LocalsRepository extends JpaRepository<LocalsEntity, UUID>{
    List<LocalsEntity> findAllByOrderByCreatedAtAsc();
}
