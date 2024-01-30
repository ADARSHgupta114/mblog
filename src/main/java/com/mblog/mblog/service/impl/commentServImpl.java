package com.mblog.mblog.service.impl;

import com.mblog.mblog.entites.Comments;
import com.mblog.mblog.entites.Post;
import com.mblog.mblog.exception.ResourceNotFoundException;
import com.mblog.mblog.payload.CommentDTO;
import com.mblog.mblog.repository.commentRepository;
import com.mblog.mblog.repository.postRepository;
import com.mblog.mblog.service.commentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class commentServImpl implements commentService {

    private postRepository postrepository;
    private commentRepository commentrepository;
    private ModelMapper modelmapper;


    public commentServImpl(postRepository postrepository, commentRepository commentrepository,ModelMapper modelmapper) {
        this.postrepository = postrepository;
        this.commentrepository = commentrepository;
        this.modelmapper = modelmapper;
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

    @Override
    public void DeleteComment(long id) {
        commentrepository.deleteById(id);
    }

    @Override
    public CommentDTO updateCommetService(long postid,long id, CommentDTO dto) {
        Post post = postrepository.findById(postid).orElseThrow(() -> new ResourceNotFoundException("Post Not Found of Id" + postid));
        Comments comment = commentrepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Comment Not Found of Id" + id));
        Comments Updatedcomment = modelmapper.map(dto,Comments.class);
        Updatedcomment.setId(comment.getId());
        Updatedcomment.setPost(comment.getPost());
        Comments SavedComment = commentrepository.save(Updatedcomment);
        CommentDTO UpdatedDTO = modelmapper.map(SavedComment,CommentDTO.class);
        return UpdatedDTO;
    }

    @Override
    public List<CommentDTO> viewAllComments() {
        List<Comments> comments = commentrepository.findAll();
        List<CommentDTO> dto = comments.stream().map(c->modelmapper.map(c, CommentDTO.class)).collect(Collectors.toList());
        return dto;
    }
}
