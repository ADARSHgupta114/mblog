package com.mblog.mblog.controllers;

import com.mblog.mblog.entites.Comments;
import com.mblog.mblog.payload.CommentDTO;
import com.mblog.mblog.service.commentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class commentController {
    private commentService commnmetservice;

    public commentController(commentService commnmetservice) {
        this.commnmetservice = commnmetservice;
    }

    @PostMapping("/save-comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentdto, @RequestParam("postId") Long postId){
        CommentDTO dto = commnmetservice.createCommentServ(commentdto,postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping("/view-comments")
    public ResponseEntity<List<CommentDTO>> getAllComments(){
        List<CommentDTO> dto = commnmetservice.viewAllComments();
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping("/update-comment{postid}-{id}")
    public  ResponseEntity<CommentDTO> updateComments(@PathVariable long postid,@PathVariable long id,@RequestBody CommentDTO dto){
        CommentDTO updatedComment =   commnmetservice.updateCommetService(postid,id,dto);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }
    @DeleteMapping("/delete-comments/{id}")
    public ResponseEntity<String> deleteComments(@PathVariable long id){
        commnmetservice.DeleteComment(id);
        return new ResponseEntity<String>("Comment Deleted",HttpStatus.OK);
    }

}
