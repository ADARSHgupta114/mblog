package com.mblog.mblog.payload;

import lombok.*;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String usernameOrEmail;
    private String password;
}
