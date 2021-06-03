package com.example.zkspringbootweb.authentication;

import com.example.zkspringbootweb.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {
    String name;
    String role;

    public static UserCredential from(AppUser user) {
        return new UserCredential(user.getUsername(), user.getRole().name());
    }
}
