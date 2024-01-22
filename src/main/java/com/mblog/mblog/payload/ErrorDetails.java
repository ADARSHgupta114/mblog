package com.mblog.mblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorDetails {
    private String Message;
    private Date date;
    private  String uri;
}
