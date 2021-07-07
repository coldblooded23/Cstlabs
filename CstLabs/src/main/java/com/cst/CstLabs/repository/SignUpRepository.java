package com.cst.CstLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cst.CstLabs.entity.SignUpEntity;


public interface SignUpRepository extends JpaRepository<SignUpEntity, Long> {
	
	@Query(value = "SELECT * FROM UserDetails WHERE EmailId = ?1", nativeQuery = true)
	public SignUpEntity getUserByEmail(String email);
	//  update UserDetails set Password='123456' where EmailId ='rak@gmail.com'
	
	@Query(value = "SELECT userid FROM UserDetails WHERE EmailId = ?1", nativeQuery = true)
	public Long getUserIdByEmail(String email);
	
    @Transactional
	@Modifying
	@Query(value = "update UserDetails set Password=:password where EmailId =:email", nativeQuery = true)
	public int updatePassword(String password ,String email);
}
