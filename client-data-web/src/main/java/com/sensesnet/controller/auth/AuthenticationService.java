package com.sensesnet.controller.auth;

import com.sensesnet.model.User;
import com.sensesnet.service.UserRoleService;
import com.sensesnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("authService")
public class AuthenticationService implements UserDetailsService
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername (String userLogin) throws UsernameNotFoundException
    {
        User user = userService.getUserByLogin(userLogin);
        if (user == null)
        {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails
                .User(user.getUserLogin(),
                      user.getUserPassword(),
                      true, true,
                      true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities (User user)
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(
                new SimpleGrantedAuthority(
                        userRoleService.getRoleById(user.getUserRole()).getRoleName()));
        return authorities;
    }

}
