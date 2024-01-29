package com.mblog.mblog.service;

import  com.mblog.mblog.payload.postDTO;

import java.util.List;

public interface postService {

    postDTO createPost(postDTO postdto);
    postDTO getPostById(long id);

    List<postDTO> getAllPost(int pageno, int pagesize, String sortby, String sortbydir);
}
