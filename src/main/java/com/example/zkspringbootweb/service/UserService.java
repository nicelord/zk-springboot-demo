package com.example.zkspringbootweb.service;

import com.example.zkspringbootweb.entity.AppUser;
import io.ebean.Database;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {
    private final Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public AppUser findByUsername(String s) {
        return database.find(AppUser.class).where().eq("username", s)
                .findOneOrEmpty()
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    public AppUser whoami() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByUsername(userDetails.getUsername());
    }
}
