package com.travelbees.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;


import com.travelbees.entity.Navigator;


import jakarta.validation.Valid;

public interface NavigatorService 
{
	public Navigator addNavigator( MultipartFile photo, String place,String navigatorName,
			String navigatorEmail,String contactNumber,String aadharNumber,BigDecimal price)throws SQLException, IOException;
	// public Navigator addNavigator(NavigatorResponseDto navigatorResponsedto,MultipartFile photo)throws SQLException, IOException;
	public List<String> getAllPlaces();

    public List<Navigator> getAllNavigators();

    public byte[] getNavigatorPhotoByNavigatorId(Long navigatorId) throws SQLException;

    public void deleteNavigator(Long navigatorId);

    public Navigator updateNavigator(Long navigatorId,String place,String navigatorName,String navigatorEmail,
			String contactNumber,String aadharNumber,BigDecimal Price,byte[] photo);

    public Navigator getNavigatorByNavigatorId(Long navigatorId);

    public List<Navigator> getAvailableNavigators(LocalDate startDate, LocalDate endDate, String place);
    

}