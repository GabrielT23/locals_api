package com.locals.locals_api.modules.users.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.locals.locals_api.modules.locals.entities.LocalsEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name="users")
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UsersEntity {
    
    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Schema(example = "joao")
    @NotNull(message = "Name is not null")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Schema(example = "joao@gmail.com")
    @NotNull(message = "Email is not null")
    @NotBlank(message = "Email is mandatory")
    @Email()
    private String email;

    @Schema(example = "123456")
    @NotNull(message = "Password is not null")
    @NotBlank(message = "Password is mandatory")
    private String password;
    
    @Schema(hidden = true)
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Schema(hidden = true)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Adicionando o relacionamento com LocalsEntity
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocalsEntity> locals;
}

