package com.locals.locals_api.modules.locals.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "locals")
public class LocalsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Name is not null")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "neighborhood is not null")
    @NotBlank(message = "Neighborhood is mandatory")
    private String neighborhood;

    @NotNull(message = "city is not null")
    @NotBlank(message = "City is mandatory")
    private String city;

    @NotNull(message = "state is not null")
    @NotBlank(message = "State is mandatory")
    private String state;

    private String imageName;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public String getImageURL() {
        if (imageName == null || imageName.isEmpty()) {
            return null;
        }
        return "http://seu_dominio/uploads/" + imageName;
    }
}



