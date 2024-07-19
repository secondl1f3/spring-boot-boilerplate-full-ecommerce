package com.sakanlabs.badal.repository;

import com.sakanlabs.badal.entity.Role;
import com.sakanlabs.badal.util.Constants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(Constants.RoleEnum name);
}
