package com.mblog.mblog.controllers;

import com.mblog.mblog.payload.postDTO;
import com.mblog.mblog.service.postService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/post")
public class postController {

    private postService postserv;

    public postController(postService postserv) {
        this.postserv = postserv;
    }

    @PostMapping
    public ResponseEntity<postDTO> createPost(@RequestBody postDTO dto){
       postDTO ndto =  postserv.createPost(dto);
        return new ResponseEntity<>(ndto, HttpStatus.CREATED);
    }
}
