package com.cst.CstLabs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cst.CstLabs.entity.CourseEntity;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Long> {
	
	@Query(value = "select * from Course where CourseId =:id",nativeQuery = true)	
	public CourseEntity getCoursesDetails(Long id);
	
	@Query(value = "select * from Course where CourseId Not in (select CourseId from Results  where Result='pass' and UserId=:id)",nativeQuery = true)
	public List<CourseEntity> getUncompleatedCourses(Long id);
	
	@Query(value ="  select * from Course where CourseId in (select CourseId from Results  where Result='pass' and UserId=:id)", nativeQuery = true)
	public List<CourseEntity> getCompleatedCourse(Long id);
	
	
	
	
}
