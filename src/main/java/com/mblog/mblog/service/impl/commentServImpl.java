package com.mblog.mblog.service.impl;

import com.mblog.mblog.entites.Comments;
import com.mblog.mblog.entites.Post;
import com.mblog.mblog.exception.ResourceNotFoundException;
import com.mblog.mblog.payload.CommentDTO;
import com.mblog.mblog.repository.commentRepository;
import com.mblog.mblog.repository.postRepository;
import com.mblog.mblog.service.commentService;
import org.springframework.stereotype.Service;

@Service
public class commentServImpl implements commentService {

    private postRepository postrepository;
    private commentRepository commentrepository;

    public commentServImpl(postRepository postrepository, commentRepository commentrepository) {
        this.postrepository = postrepository;
        this.commentrepository = commentrepository;
    }

    @Override
    public CommentDTO createCommentServ(CommentDTO commentdto, long postId){
        Post post=postrepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Not Found with id:"+postId));
        Comments comment = new Comments();
        comment.setEmail(commentdto.getEmail());
        comment.setText(commentdto.getText());
        comment.setPost(post);
        Comments savedComment = commentrepository.save(comment);
        CommentDTO dto = new CommentDTO();
        dto.setText(savedComment.getText());
        dto.setId(savedComment.getId());
        dto.setEmail(savedComment.getEmail());
        return dto;
    }
}
