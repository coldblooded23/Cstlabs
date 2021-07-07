package com.cst.CstLabs.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cst.CstLabs.entity.VideoCountEntity;


@Repository
public interface VideoCountRepository extends CrudRepository<VideoCountEntity,Long> {
	
	
	@Query(value = "SELECT * FROM videocount WHERE emailid = :email and courseId=:id", nativeQuery = true)
	public VideoCountEntity getVideoWatchedByEmail(String email,Long id);
	
	
	
	@Modifying
	@Transactional
	@Query(value = "update videocount set count=:count,watched=:watch where emailid=:email and courseId=:id", nativeQuery = true)
	public int UpdateVedioCountByEmail(String email,int count,boolean watch,Long id);
	
	
	@Modifying
	@Transactional
	@Query(value = "update videocount set watched=:watch where emailid=:email and courseId=:id", nativeQuery = true)
	public int UpdateVedioWatchByEmail(String email,boolean watch,Long id);

}
