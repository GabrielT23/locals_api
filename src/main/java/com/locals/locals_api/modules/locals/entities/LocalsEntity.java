package com.locals.locals_api.modules.locals.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.locals.locals_api.modules.users.entities.UsersEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "locals")
public class LocalsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden = true, requiredMode = RequiredMode.NOT_REQUIRED)
    private UUID id;

    @Schema(example = "Local A")
    @NotNull(message = "Name is not null")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Schema(example = "Bairro A")
    @NotNull(message = "neighborhood is not null")
    @NotBlank(message = "Neighborhood is mandatory")
    private String neighborhood;

    @Schema(example = "Cidade A")
    @NotNull(message = "city is not null")
    @NotBlank(message = "City is mandatory")
    private String city;

    @Schema(example = "Estado A")
    @NotNull(message = "state is not null")
    @NotBlank(message = "State is mandatory")
    private String state;

    @Schema(hidden = true)
    private String imageName;

    @Schema(hidden = true)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Schema(hidden = true)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Schema(hidden = true)
    public String getImageURL() {
        if (imageName == null || imageName.isEmpty()) {
            return null;
        }
        return "http://seu_dominio/uploads/" + imageName;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private UsersEntity user;

    @Transient
    private UUID userId;
    
}




