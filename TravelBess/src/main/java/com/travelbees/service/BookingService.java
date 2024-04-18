package com.travelbees.service;

import java.util.List;

import com.travelbees.entity.Booking;


public interface BookingService {

	
	  public List<Booking> getAllBookingsByNavigatorId(Long navigatorId);
	  
	  
	   void cancelBooking(Long bookingId);

	    String saveBooking(Long navigatorId, Booking booking);

	    Booking findByBookingConfirmationCode(String confirmationCode);

	    List<Booking> getAllBookings();

	    List<Booking> getBookingsByTouristEmail(String touristEmail);
}
