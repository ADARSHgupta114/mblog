package com.mblog.mblog.service.impl;

import com.mblog.mblog.entites.Post;
import com.mblog.mblog.exception.ResourceNotFoundException;
import com.mblog.mblog.payload.postDTO;
import com.mblog.mblog.repository.postRepository;
import com.mblog.mblog.service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class postServiceImpl implements postService
{
    private ModelMapper modelmapper;
    private postRepository postrepo;
    public postServiceImpl(postRepository postrepo,ModelMapper modelmapper) {
        this.postrepo = postrepo;
        this.modelmapper = modelmapper;
    }

    @Override//Service Layer to Create A Post
    public postDTO createPost(postDTO postdto) {

        Post post = maptToEntity(postdto);
        Post savedPost = postrepo.save(post);
        postDTO dto = maptToDTO(savedPost);
        return dto;
    }
    //Service Layer to get a Post Data
    public postDTO getPostById(long id){
       Post post = postrepo.findById(id).orElseThrow(
               ()->new ResourceNotFoundException("Post Not Found With Id: "+id)
        );
       postDTO dto = new postDTO();
       dto.setTitle(post.getTitle());
       dto.setDescription(post.getDescription());
       dto.setContent(post.getContent());
       return  dto;
    }
    //Service Layer to get all Post Data
    @Override
    public List<postDTO> getAllPost(int pageno, int pagesize, String sortby, String sortbydir) {
        Sort sort = (sortbydir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortby).ascending():Sort.by(sortby).descending();
        Pageable pagable = PageRequest.of(pageno, pagesize, sort);
        Page<Post> pageposts = postrepo.findAll(pagable);
        List<Post> posts = pageposts.getContent();
        List<postDTO> dtos =  posts.stream().map(post->maptToDTO(post)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public void deletePostServ(long id) {
        postrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post not found with Id: "+id));
        postrepo.deleteById(id);
    }

    @Override
    public postDTO UpdatePostServ(long id, postDTO update) {
        Post Oldpost = postrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post Not Found With Id" + id));
        Post UpdatedPost = modelmapper.map(update, Post.class);
        UpdatedPost.setId(Oldpost.getId());
        Post NewPost = postrepo.save(UpdatedPost);
        postDTO NewDTO = modelmapper.map(NewPost,postDTO.class);
        return NewDTO;
    }

    postDTO maptToDTO(Post post){
       postDTO dto = modelmapper.map(post,postDTO.class);
        return dto;
    }
    Post maptToEntity(postDTO postdto){
        Post post = modelmapper.map(postdto,Post.class);
        return post;
    }

}
