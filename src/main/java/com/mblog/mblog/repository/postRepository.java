package com.mblog.mblog.repository;

import com.mblog.mblog.entites.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postRepository extends JpaRepository<Post,Long> {

}
