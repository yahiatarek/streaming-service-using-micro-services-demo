package com.example.demo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {
    public static class Views {
        public interface Public {}
        public interface Internal extends Public {}
    }

    @NotNull
    @JsonView(Views.Public.class)
    @JsonProperty("user_name")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    @NotNull
    @JsonView(Views.Public.class)
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    @NotNull
    @JsonView(Views.Public.class)
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
    @NotNull
    @JsonView(Views.Internal.class)
    private int id;

    @JsonProperty("user_password")
    @NotNull
    @JsonIgnore
    @Size(min = 2, max = 50, message = "Password must be between 2 and 50 characters")
    private String password;
    public User(int id, String name, String lastName, LocalDate birthDate, String password) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
