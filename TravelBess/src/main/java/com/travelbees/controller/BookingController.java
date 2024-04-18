package com.travelbees.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travelbees.dto.BookingDto;
import com.travelbees.dto.NavigatorDto;
import com.travelbees.entity.Booking;
import com.travelbees.entity.Navigator;
import com.travelbees.exception.InvalidBookingRequestException;
import com.travelbees.exception.ResourceNotFoundException;
import com.travelbees.service.BookingService;
import com.travelbees.service.NavigatorService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private NavigatorService navigatorService;
	
	//admin access.only admin can see
	 @GetMapping("/allbookings")
	 public ResponseEntity<List<BookingDto>> getAllBookings(){
	        List<Booking> bookings = bookingService.getAllBookings();
	        List<BookingDto> bookingDtos = new ArrayList<>();
	        for (Booking booking : bookings){
	            BookingDto bookingDto = getBookingDto(booking);
	            bookingDtos.add(bookingDto);
	        }
	        return ResponseEntity.ok(bookingDtos);
	    }
	 
	 private BookingDto getBookingDto(Booking booking) {
	       Navigator navigator = navigatorService.getNavigatorByNavigatorId(booking.getNavigator().getNavigatorId());
	        NavigatorDto navigatorDto = new NavigatorDto(
	        		navigator.getNavigatorId(),
	        		navigator.getPlace(),
	        		navigator.getNavigatorName(),
	        		navigator.getNavigatorEmail(),
	        		navigator.getContactNumber(),
	        		navigator.getPrice());
	        return new BookingDto(
	                booking.getBookingId(), booking.getStartDate(),
	                booking.getEndDate(),booking.getTouristFullName(),booking.getContactNumber(),
	                booking.getTouristEmail(), booking.getNumOfAdults(),
	                booking.getNumOfChildren(), booking.getTotalNumOfTourists(),
	                booking.getBookingConfirmationCode(), navigatorDto);
	    }


	 @PostMapping("/navigator/{navigatorId}/booking")
	    public ResponseEntity<?> saveBooking(@PathVariable Long navigatorId,
	    		@Valid @RequestBody Booking booking){
	        try{
	            String confirmationCode = bookingService.saveBooking(navigatorId, booking);
	           // ConformationDto conformationDto=new  ConformationDto();
	          //  conformationDto.setConformationDto("Navigator booked successfully, Your booking"
	            //		+ " confirmation code is :"+confirmationCode);
	            return ResponseEntity.ok(confirmationCode);

	        }catch (InvalidBookingRequestException e){
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }

	 //tourist can cancel the booking by providing booking id
	 @DeleteMapping("/booking/{bookingId}/delete")
	    public void cancelBooking(@PathVariable Long bookingId){
	        bookingService.cancelBooking(bookingId);
	    }
	 //tourist can search the booking by bookingconformation code
	 @GetMapping("/confirmation/{confirmationCode}")
	    public ResponseEntity<?> getBookingByConfirmationCode(@PathVariable String confirmationCode){
	        try{
	            Booking booking = bookingService.findByBookingConfirmationCode(confirmationCode);
	            BookingDto bookingDto = getBookingDto(booking);
	            return ResponseEntity.ok(bookingDto);
	        }catch (ResourceNotFoundException ex){
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	        }
	    }
	 
	 @GetMapping("/tourist/{email}/bookings")
	    public ResponseEntity<List<BookingDto>> getBookingsByTouristEmail(@PathVariable String email) {
	        List<Booking> bookings = bookingService.getBookingsByTouristEmail(email);
	        List<BookingDto> bookingResponses = new ArrayList<>();
	        for (Booking booking : bookings) {
	            BookingDto bookingResponse = getBookingDto(booking);
	            bookingResponses.add(bookingResponse);
	        }
	        return ResponseEntity.ok(bookingResponses);
	    }
	
}
	
