package com.nicogmerz4.portfolio.security;

import com.nicogmerz4.portfolio.model.User;
import com.nicogmerz4.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRep;
    
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRep.findOneByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + name + " not found."));   
                
        return new UserDetailsImpl(user);
    }
}
