package com.mblog.mblog.repository;

import com.mblog.mblog.payload.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface roleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);

}
