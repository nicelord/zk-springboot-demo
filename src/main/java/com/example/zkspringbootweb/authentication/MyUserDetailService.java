package com.example.zkspringbootweb.authentication;

import com.example.zkspringbootweb.entity.AppUser;
import com.example.zkspringbootweb.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserService userService;
    private final HttpSession httpSession;

    public MyUserDetailService(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        AppUser user = userService.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException(s);
        }

        httpSession.setAttribute("userCredential", UserCredential.from(user));

        return new MyCurrentUser(user);
    }
}
