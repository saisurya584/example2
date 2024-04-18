package com.travelbees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travelbees.entity.Tourist;

import jakarta.transaction.Transactional;

@Repository
public interface TouristRepository extends JpaRepository<Tourist, Long> {

	 public Tourist findByEmail(String email);
	 
	 public Tourist findByEmailAndPasswordAndRole(String email, String password,String role);
	
	 
//	 @Query("SELECT new com.cg.wrapper.TouristWrapper(t.id, t.firstName, t.lastName, t.email, t.contactNumber, t.status) FROM Tourist t")
//	    List<TouristWrapper> getAllTourists();
//	 
	 
	 
	 
	}


