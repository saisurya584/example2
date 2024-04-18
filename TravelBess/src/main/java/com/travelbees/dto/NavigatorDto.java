package com.travelbees.dto;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import com.travelbees.entity.Booking;


  public class NavigatorDto {
	
  private Long NavigatorId;

  private String place;
	
  private String navigatorName;
	
  private String navigatorEmail;
	
  private String contactNumber;

  private String aadharNumber;
    
  private BigDecimal price;
    
  private boolean status;
	
  private String photo;
    
  private List<BookingDto> bookings;

  
public NavigatorDto() {
	super();
	// TODO Auto-generated constructor stub
}


public NavigatorDto(Long navigatorId, String place, String navigatorName, String navigatorEmail, String contactNumber,BigDecimal price) {
	super();
	NavigatorId = navigatorId;
	this.place = place;
	this.navigatorName = navigatorName;
	this.navigatorEmail = navigatorEmail;
	this.contactNumber = contactNumber;
	this.price = price;
}



public NavigatorDto(Long navigatorId, String place, String navigatorName, String navigatorEmail, String contactNumber,
		String aadharNumber, BigDecimal price, boolean status, byte[] photo, List<BookingDto> bookings) {
	super();
	NavigatorId = navigatorId;
	this.place = place;
	this.navigatorName = navigatorName;
	this.navigatorEmail = navigatorEmail;
	this.contactNumber = contactNumber;
	this.aadharNumber = aadharNumber;
	this.price = price;
	this.status = status;
	this.photo = photo!= null ? Base64.encodeBase64String(photo) : null;
	this.bookings = bookings;
}


public Long getNavigatorId() {
	return NavigatorId;
}


public void setNavigatorId(Long navigatorId) {
	NavigatorId = navigatorId;
}


public String getPlace() {
	return place;
}


public void setPlace(String place) {
	this.place = place;
}


public String getNavigatorName() {
	return navigatorName;
}


public void setNavigatorName(String navigatorName) {
	this.navigatorName = navigatorName;
}


public String getNavigatorEmail() {
	return navigatorEmail;
}


public void setNavigatorEmail(String navigatorEmail) {
	this.navigatorEmail = navigatorEmail;
}


public String getContactNumber() {
	return contactNumber;
}


public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}


public String getAadharNumber() {
	return aadharNumber;
}


public void setAadharNumber(String aadharNumber) {
	this.aadharNumber = aadharNumber;
}


public BigDecimal getPrice() {
	return price;
}


public void setPrice(BigDecimal price) {
	this.price = price;
}


public boolean isStatus() {
	return status;
}


public void setStatus(boolean status) {
	this.status = status;
}


public String getPhoto() {
	return photo;
}


public void setPhoto(String photo) {
	this.photo = photo;
}


public List<BookingDto> getBookings() {
	return bookings;
}


public void setBookings(List<BookingDto> bookings) {
	this.bookings = bookings;
}

  
  
}
