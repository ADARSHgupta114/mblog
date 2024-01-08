package com.mblog.mblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class postDTO {
    private long id;
    private String title;
    private String description;
    private String content;
}
