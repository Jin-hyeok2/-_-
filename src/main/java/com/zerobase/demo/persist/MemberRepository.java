package com.zerobase.demo.persist;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerobase.demo.model.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
    
    Optional<MemberEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
