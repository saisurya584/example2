package com.travelbees.service;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.travelbees.dto.TouristDto;

public interface TouristService {
	
	public ResponseEntity<String> signUp(TouristDto touristDto );

    public ResponseEntity<String> login(String email,String password,String userRole);

 // public  ResponseEntity<List<TouristWrapper>> getAllTourists();
 
  public ResponseEntity<String> update(long Id, String firstName, String lastname, String email,String password,
		  String contactNumber);

//    ResponseEntity<String> checkToken();
//
    ResponseEntity<String> changePassword(String email,String password);

//    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);
	  
}
	   

