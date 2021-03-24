package com.undeadbigunicorn.demo.repository;

import com.undeadbigunicorn.demo.domain.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
}
