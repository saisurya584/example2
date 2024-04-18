package com.travelbees.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.constants.TouristConstants;
import com.cg.touristUtils.TouristUtils;

import com.travelbees.dto.TouristDto;
import com.travelbees.service.TouristService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TouristController {

@Autowired
private TouristService touristService;
	
@PostMapping(path ="/signup")
public ResponseEntity<String> signUp(@Valid @RequestBody TouristDto touristDto) {
    try {
    	System.out.println(touristDto);
        return touristService.signUp(touristDto);
    } catch (Exception e) {
        e.printStackTrace();
    }

    return TouristUtils.getResponseEntity(TouristConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
}

@GetMapping("/logining")
public ResponseEntity<String> login(@RequestParam("email")String email,
        @RequestParam("password") String password,
        @RequestParam("role") String role) {
	
	

    try {
        return touristService.login(email,password,role);
        
    } catch (Exception e) {
        e.printStackTrace();
    }

    return TouristUtils.getResponseEntity(TouristConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
}
	
//@GetMapping(path = "/get")
//public ResponseEntity<List<TouristWrapper>> getAllTourists(){
//	 try {
//         return touristService.getAllTourists();
//     } catch (Exception e) {
//         e.printStackTrace();
//     }
//
//     return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
// }
@PatchMapping(path="/updateTourist/{Id}")
public ResponseEntity<String> update(@PathVariable Long Id,
        @RequestParam(required = false)  String firstName,
        @RequestParam(required = false)  String lastName,
        @RequestParam(required = false)  String touristEmail,
        @RequestParam(required = false)  String password,
        @RequestParam(required = false)  String contactNumber
        ) {
    try {
    	System.out.println(contactNumber);
        return touristService.update(Id,firstName,lastName,touristEmail,password,contactNumber);
    } catch (Exception e) {
        e.printStackTrace();
    }

    return TouristUtils.getResponseEntity(TouristConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
}
@PatchMapping(path="/forgotPassword")
public ResponseEntity<String> forgotPassword( @RequestParam(required = false) String email,
        @RequestParam(required = false) String password) {
    try {
        return touristService.changePassword(email,password);
    } catch (Exception e) {
        e.printStackTrace();
    }

    return TouristUtils.getResponseEntity(TouristConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
}


}



