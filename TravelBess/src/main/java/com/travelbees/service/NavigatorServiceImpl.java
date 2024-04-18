package com.travelbees.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.travelbees.dto.NavigatorDto;
import com.travelbees.entity.Navigator;

import com.travelbees.exception.InternalServerException;
import com.travelbees.exception.NavigatorException;
import com.travelbees.exception.ResourceNotFoundException;
import com.travelbees.repository.NavigatorRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@Service
public class NavigatorServiceImpl implements NavigatorService
{
    @Autowired
	private NavigatorRepository navigatorRepository;

    public Navigator addNavigator( MultipartFile photo, String place,String navigatorName,
			String navigatorEmail,String contactNumber,String aadharNumber,BigDecimal price)throws SQLException, IOException{

	try {
		  Navigator navigator = new Navigator();
	        navigator.setPlace(place);
	        navigator.setNavigatorName(navigatorName);
	        navigator.setNavigatorEmail(navigatorEmail);
	        navigator.setContactNumber(contactNumber);
	        navigator.setAadharNumber(aadharNumber);
	        navigator.setPrice(price);
	        if (!photo.isEmpty()){
	            byte[] photoBytes = photo.getBytes();
	            Blob photoBlob = new SerialBlob(photoBytes);
	            navigator.setPhoto(photoBlob);
	        }
	return navigatorRepository.save(navigator);
    
    }	catch(Exception e) {
    	//throw new NavigatorException("validatio failed"+e.getMessage());
    	 if (e instanceof DataIntegrityViolationException) {
    	        // Handle database integrity violation exception separately if needed
    	        throw new NavigatorException("Database integrity violation: " + e.getMessage());
    	    }

    	    if (e instanceof ConstraintViolationException) {
    	        ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;

    	        // Extract and concatenate the error messages
    	        String errorMessage = constraintViolationException.getConstraintViolations().stream()
    	                .map(ConstraintViolation::getMessage)
    	                .collect(Collectors.joining(", "));

    	        throw new NavigatorException("Validation failed: " + errorMessage);
    	    }

    	    throw new NavigatorException("An unexpected error occurred: " + e.getMessage());
    }}
	@Override
	public List<String> getAllPlaces() {

		        return navigatorRepository.findDistinctPlaces();
		    }

	@Override
	public List<Navigator> getAllNavigators() {
		// TODO Auto-generated method stub
		return navigatorRepository.findAll();
	}

	@Override
	public byte[] getNavigatorPhotoByNavigatorId(Long navigatorId) throws SQLException {
	
		  Navigator navigator = navigatorRepository.findByNavigatorId(navigatorId);
	        if(navigator==null){
	            throw new ResourceNotFoundException("Sorry, navigators not found!");	        }
	        Blob photoBlob = navigator.getPhoto();
	        if(photoBlob != null){
	            return photoBlob.getBytes(1, (int) photoBlob.length());
	        }
	        return null;
	}

	@Override
	public void deleteNavigator(Long navigatorId) {
		
		        Navigator navigator = navigatorRepository.findByNavigatorId(navigatorId);
		        if(navigator!=null){
		            navigatorRepository.deleteByNavigatorId(navigatorId);
		        }
		    }

	@Override
	public Navigator updateNavigator(Long navigatorId,String place,String navigatorName,String navigatorEmail,
			String contactNumber,String aadharNumber,BigDecimal price,byte[] photo) {
		// TODO Auto-generated method stub
		 Navigator navigator = navigatorRepository.findByNavigatorId(navigatorId);
	        if (place != null) navigator.setPlace(place);
	        if (navigatorName != null) navigator.setNavigatorName(navigatorName);
	        if (navigatorEmail != null) navigator.setNavigatorEmail(navigatorEmail);
	        if (contactNumber != null) navigator.setContactNumber(contactNumber);
	        if(aadharNumber != null) navigator.setAadharNumber(aadharNumber);
	        if (price != null) navigator.setPrice(price);
	        if (photo != null && photo.length > 0) {
	            try {
	                navigator.setPhoto(new SerialBlob(photo));
	            } catch (SQLException ex){
	                throw new InternalServerException("Fail updating navigator");
	            }
	        }
	       return navigatorRepository.save(navigator);
	    }

	@Override
	public Navigator getNavigatorByNavigatorId(Long navigatorId) {
		// TODO Auto-generated method stub
		 
		        return navigatorRepository.findByNavigatorId(navigatorId);
		    }

	@Override
	public List<Navigator> getAvailableNavigators(LocalDate startDate, LocalDate endDate, String place) {
		// TODO Auto-generated method stub
		return navigatorRepository.findAvailableNavigatorsByPlaceAndDates(place,startDate,endDate);
    }
	}

	
	
	
	


	
