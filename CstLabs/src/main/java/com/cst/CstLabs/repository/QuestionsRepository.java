package com.cst.CstLabs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cst.CstLabs.entity.QuestionsEntity;


@Repository
public interface QuestionsRepository extends CrudRepository<QuestionsEntity, Long>{

}
