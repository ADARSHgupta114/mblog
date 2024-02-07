package com.mblog.mblog.controllers;

import com.mblog.mblog.payload.postDTO;
import com.mblog.mblog.service.postService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/post")
public class postController {

    private postService postserv;

    public postController(postService postserv) {
        this.postserv = postserv;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-post")
    public ResponseEntity<postDTO> createPost(@RequestBody postDTO dto){
       postDTO ndto =  postserv.createPost(dto);
        return new ResponseEntity<>(ndto, HttpStatus.CREATED);
    }
    @GetMapping("/by")
    public ResponseEntity<postDTO> getPostById(@RequestParam long id){
      postDTO dto =  postserv.getPostById(id);
      return  new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //https://localhost:8080/api/post?pageno=0&pagesize=3
    @GetMapping
    public List<postDTO> getAllPost(
            @RequestParam(name="pageno",required = false,defaultValue = "0") int pageno,
            @RequestParam(name="pagesize",required = false,defaultValue = "3") int pagesize,
            @RequestParam(name="sortby",required = false,defaultValue = "id") String sortby,
            @RequestParam(name="sortbydir",required = false,defaultValue = "id") String sortbydir

    ){
        List<postDTO> postdtos = postserv.getAllPost(pageno,pagesize,sortby,sortbydir);
        return  postdtos;
    }
    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<String> DeletePostById(@PathVariable long id){
        postserv.deletePostServ(id);
        return new ResponseEntity<>("Post Deleted SuccessFully",HttpStatus.OK);
    }
    @PutMapping("/update-post/{id}")
    public ResponseEntity<postDTO> UpdatePostById(@PathVariable long id,@RequestBody postDTO update){
       postDTO UpdatedPost =  postserv.UpdatePostServ(id,update);
       return new ResponseEntity<>(UpdatedPost,HttpStatus.OK);
    }
}
