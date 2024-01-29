package com.mblog.mblog.service;

import com.mblog.mblog.payload.CommentDTO;

public interface commentService {
   CommentDTO createCommentServ(CommentDTO commentdto,long postId);
}
