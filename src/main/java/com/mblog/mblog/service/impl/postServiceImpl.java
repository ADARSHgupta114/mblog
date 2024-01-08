package com.mblog.mblog.service.impl;

import com.mblog.mblog.entites.Post;
import com.mblog.mblog.payload.postDTO;
import com.mblog.mblog.repository.postRepository;
import com.mblog.mblog.service.postService;
import org.springframework.stereotype.Service;

@Service
public class postServiceImpl implements postService
{
    private postRepository postrepo;

    public postServiceImpl(postRepository postrepo) {
        this.postrepo = postrepo;
    }

    @Override
    public postDTO createPost(postDTO postdto) {
        Post post = new Post();
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());
        Post savedPost = postrepo.save(post);

        postDTO dto = new postDTO();
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());

        return dto;
    }
}
