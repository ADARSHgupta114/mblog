package com.mblog.mblog.controllers;

import com.mblog.mblog.entites.Comments;
import com.mblog.mblog.payload.CommentDTO;
import com.mblog.mblog.service.commentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class commentController {
    private commentService commnmetservice;

    public commentController(commentService commnmetservice) {
        this.commnmetservice = commnmetservice;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentdto, @RequestParam("postId") Long postId){
        CommentDTO dto = commnmetservice.createCommentServ(commentdto,postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
