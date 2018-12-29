package com.test.seminar.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/**
 * @author xmr
 * @date 2018/11/28
 */
public interface UserService{
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
