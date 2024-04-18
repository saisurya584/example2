package com.travelbees.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.constants.TouristConstants;
import com.cg.touristUtils.TouristUtils;
import com.travelbees.dto.TouristDto;
import com.travelbees.entity.Navigator;
import com.travelbees.entity.Tourist;
import com.travelbees.repository.TouristRepository;

@Service
public class TouristServiceImpl implements TouristService {

@Autowired
private TouristRepository touristRepository;
	

public ResponseEntity<String> signUp(TouristDto touristDto) {
    
    try {
    	System.out.println(touristDto);
            Tourist tourist = touristRepository.findByEmail(touristDto.getEmail());
            System.out.println(tourist);
            if (Objects.isNull(tourist)) {
            	touristRepository.save(getTouristFromMap(touristDto));
                return TouristUtils.getResponseEntity("Successfully registered.", HttpStatus.OK);
            } else {
                return TouristUtils.getResponseEntity("Email already exists.", HttpStatus.BAD_REQUEST);
            }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return TouristUtils.getResponseEntity(TouristConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
}

private Tourist getTouristFromMap(TouristDto touristDto) {
    Tourist tourist = new Tourist();
    System.out.println(touristDto);
    tourist.setFirstName(touristDto.getFirstName());
    tourist.setLastName(touristDto.getLastName());
    tourist.setContactNumber(touristDto.getContactNumber());
    tourist.setEmail(touristDto.getEmail());
    tourist.setPassword(touristDto.getPassword());
    tourist.setStatus("false");
    tourist.setRole("user");
    System.out.println(tourist);
    return tourist;
}


@Override
public ResponseEntity<String> login(String email,String password,String role) {
	// TODO Auto-generated method stub
	 
     
	Tourist tourist=touristRepository.findByEmailAndPasswordAndRole(email,password,role);
	 System.out.println(tourist);
     
	 if( tourist!=null)
	 {
		 return TouristUtils.getResponseEntity("Successfully login.", HttpStatus.OK);
	 }
	 else {
		 return TouristUtils.getResponseEntity(TouristConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
	 }
}


@Override
//public ResponseEntity<List<TouristWrapper>> getAllTourists() {
//	  try {
//          if(true) {//here we check authontication whether he is admin or not
//        	  
//              return new ResponseEntity<>(touristRepository.getAllTourists(), HttpStatus.OK);
//          } else {
//              return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
//          }
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//
//      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  }



	public ResponseEntity<String> update(long Id, String firstName, String lastName, String email,String password,
			  String contactNumber) {
	

        try {
            if(true) //if it is admin it will allow you to updaate
            {
                Tourist tourist = touristRepository.findById(Id).get();
                System.out.println(contactNumber);
                if(tourist !=null) {
               	
         	        if (firstName != null) tourist.setFirstName(firstName);
         	        if (lastName != null) tourist.setLastName(lastName);
         	        if (email != null) tourist.setEmail(email);
         	        if (password != null) tourist.setPassword(password);
         	       System.out.println(contactNumber);
         	        if(contactNumber != null) tourist.setContactNumber(contactNumber);
         	       System.out.println(contactNumber);
         	       touristRepository.save(tourist);
         	      System.out.println(contactNumber);
                    return TouristUtils.getResponseEntity("Tourist status updated successfully.", HttpStatus.OK);
                } else {
                    return TouristUtils.getResponseEntity("Tourist id doesn't exist", HttpStatus.OK);
                }
            } else {
                return TouristUtils.getResponseEntity(TouristConstants.UNATHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TouristUtils.getResponseEntity(TouristConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


@Override
	public ResponseEntity<String> changePassword(String email,String password) {
        try {
            Tourist tourist = touristRepository.findByEmail(email);//after login normally  we have to cuurent password through jwt token.
            //now i am normal email id

            if(tourist != null) {
                
                    tourist.setPassword(password);
                    touristRepository.save(tourist);
                    return TouristUtils.getResponseEntity("Password updated successfully.", HttpStatus.OK);
                } else {
                    return TouristUtils.getResponseEntity("Incorrect old password.", HttpStatus.BAD_REQUEST);
                }
        

          
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TouristUtils.getResponseEntity(TouristConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



