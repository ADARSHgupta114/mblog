package com.mblog.mblog.service;

import com.mblog.mblog.payload.CommentDTO;

import java.util.List;

public interface commentService {
   CommentDTO createCommentServ(CommentDTO commentdto,long postId);

   void DeleteComment(long id);

    CommentDTO updateCommetService(long posid,long id, CommentDTO dto);

    List<CommentDTO> viewAllComments();
}
