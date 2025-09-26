package vn.BAITAP.TestAPI.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.service.Except.InforLoginFalse;

@Component("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    private UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFind = this.userService.findUserByEmail(username);
        if (userFind == null) {
            throw new InforLoginFalse("Login Information is false");
        }

        return new org.springframework.security.core.userdetails.User(
                userFind.getEmail(),
                userFind.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
