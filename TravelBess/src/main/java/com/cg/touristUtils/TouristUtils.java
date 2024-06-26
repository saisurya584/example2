package com.cg.touristUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TouristUtils {
	
	 private TouristUtils() {

	    }

	    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
	        return new ResponseEntity<String>("{\"message\": \"" + responseMessage + "\"}", httpStatus);
	    }
}
