package com.cst.CstLabs.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cst.CstLabs.entity.ResultsEntity;


@Repository
public interface ResultsRepository extends CrudRepository<ResultsEntity, Long> {
	
	 
@Query(value = " Select * from  Results where userid=:userid and result =:result and Courseid=:id", nativeQuery = true)	
public ResultsEntity getCertificateDetails(Long userid, String result,Long id);

}
