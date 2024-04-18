package com.travelbees.dto;

import java.time.LocalDate;

import com.travelbees.entity.Navigator;


public class BookingDto {

	private Long bookingId;
   
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String touristFullName;
	
    private String contactNumber;

    private String touristEmail;

    private int numOfAdults;

    private int numOfChildren;

    private int totalNumOfTourists;

    private String bookingConfirmationCode;

    private NavigatorDto navigatorDto;
    

	public BookingDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BookingDto(Long bookingId, LocalDate startDate, LocalDate endDate, String bookingConfirmationCode) {
		super();
		this.bookingId = bookingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bookingConfirmationCode = bookingConfirmationCode;
	}


	public BookingDto(Long bookingId, LocalDate startDate, LocalDate endDate, String touristFullName,
			String contactNumber, String touristEmail, int numOfAdults, int numOfChildren, int totalNumOfTourists,
			String bookingConfirmationCode, NavigatorDto navigatorDto) {
		super();
		this.bookingId = bookingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.touristFullName = touristFullName;
		this.contactNumber = contactNumber;
		this.touristEmail = touristEmail;
		this.numOfAdults = numOfAdults;
		this.numOfChildren = numOfChildren;
		this.totalNumOfTourists = totalNumOfTourists;
		this.bookingConfirmationCode = bookingConfirmationCode;
		this.navigatorDto = navigatorDto;
	}


	public Long getBookingId() {
		return bookingId;
	}


	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public String getTouristFullName() {
		return touristFullName;
	}


	public void setTouristFullName(String touristFullName) {
		this.touristFullName = touristFullName;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getTouristEmail() {
		return touristEmail;
	}


	public void setTouristEmail(String touristEmail) {
		this.touristEmail = touristEmail;
	}


	public int getNumOfAdults() {
		return numOfAdults;
	}


	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
	}


	public int getNumOfChildren() {
		return numOfChildren;
	}


	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}


	public int getTotalNumOfTourists() {
		return totalNumOfTourists;
	}


	public void setTotalNumOfTourists(int totalNumOfTourists) {
		this.totalNumOfTourists = totalNumOfTourists;
	}


	public String getBookingConfirmationCode() {
		return bookingConfirmationCode;
	}


	public void setBookingConfirmationCode(String bookingConfirmationCode) {
		this.bookingConfirmationCode = bookingConfirmationCode;
	}


	public NavigatorDto getNavigator() {
		return navigatorDto;
	}


	public void setNavigator(NavigatorDto navigatorDto) {
		this.navigatorDto = navigatorDto;
	}

    

}
