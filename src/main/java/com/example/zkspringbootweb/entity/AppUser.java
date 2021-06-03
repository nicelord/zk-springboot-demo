package com.example.zkspringbootweb.entity;

import io.ebean.annotation.DbDefault;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Table(name = "users")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser extends BaseModel{

    @NotEmpty
    @Column(unique = true)
    String username;

    @NotNull
    @NotEmpty
    String password;

    @Email
    String email;

    @Enumerated(EnumType.STRING)
    @DbDefault("USER")
    Role role;
}