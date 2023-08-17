package com.projects.simplescript.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.projects.simplescript.model.biz.SubKriteriaNew;

@EnableJpaRepositories
@Repository
public interface SubKriteriaRepository  extends JpaRepository<SubKriteriaNew,Integer>{

    List<SubKriteriaNew> findByKriteriaId(Integer kriteriaId);
    List<SubKriteriaNew> findByKriteriaIdAndSubKriteriaName(Integer kriteriaId,String subKriteriaName);

}


