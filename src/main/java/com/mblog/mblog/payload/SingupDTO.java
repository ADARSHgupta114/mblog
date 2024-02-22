package com.mblog.mblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingupDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private Boolean verifiedEmail;
}
