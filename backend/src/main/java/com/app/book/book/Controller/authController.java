package com.app.book.book.Controller;


import com.app.book.book.Entity.books;
import com.app.book.book.Entity.user;
import com.app.book.book.Model.JwtRequest;
import com.app.book.book.Model.JwtResponse;
import com.app.book.book.Repo.bookRepo;
import com.app.book.book.Security.JWTHelper;
import com.app.book.book.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class authController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTHelper helper;
    @Autowired
    private userService userService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){

        this.doAuthenticate(request.getEmail(),request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .user(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private void doAuthenticate(String email, String password){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        try {
            authenticationManager.authenticate(authenticationToken);
        }
        catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }

    @PostMapping("/create-user")
    public user createUser(@RequestBody user user){
        return userService.create(user);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(){
        return "Crendital invalid";
    }




}
