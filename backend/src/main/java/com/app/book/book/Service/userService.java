package com.app.book.book.Service;

import com.app.book.book.Entity.user;
import com.app.book.book.Repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class userService {

    @Autowired
    private userRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public user create(user user){
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

}
