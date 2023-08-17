package com.projects.simplescript.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.projects.simplescript.model.biz.KriteriaNew;

@Repository
@EnableJpaRepositories
public interface KriteriaRepository extends JpaRepository<KriteriaNew,Integer>{
    
}
