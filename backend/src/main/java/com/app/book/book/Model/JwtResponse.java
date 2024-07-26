package com.app.book.book.Model;


import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
public class JwtResponse {

    private String jwtToken;
    private String user;
}
