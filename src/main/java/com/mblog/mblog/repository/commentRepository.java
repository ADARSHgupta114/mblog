package com.mblog.mblog.repository;

import com.mblog.mblog.entites.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepository extends JpaRepository<Comments,Long> {
}
