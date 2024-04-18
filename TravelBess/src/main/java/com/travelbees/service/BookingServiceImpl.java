package com.travelbees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelbees.entity.Booking;
import com.travelbees.entity.Navigator;
import com.travelbees.exception.InvalidBookingRequestException;
import com.travelbees.exception.ResourceNotFoundException;
import com.travelbees.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private NavigatorService navigatorService;
	
    public List<Booking> getAllBookingsByNavigatorId(Long navigatorId) {
    	 return bookingRepository.findByNavigatorNavigatorId(navigatorId);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public String saveBooking(Long navigatorId, Booking booking) {
        if (booking.getEndDate().isBefore(booking.getStartDate())){
            throw new InvalidBookingRequestException("start date must come before end-date");
        }
        Navigator navigator = navigatorService.getNavigatorByNavigatorId(navigatorId);
        List<Booking> existingBookings = navigator.getBookings();
        boolean navigatorIsAvailable = navigatorIsAvailable(booking,existingBookings);
        if (navigatorIsAvailable){
           navigator.addBooking(booking);
            bookingRepository.save(booking);
        }else{
            throw  new InvalidBookingRequestException("Sorry, This navigator is not available for the selected dates;");
        }
        return booking.getBookingConfirmationCode();
    }

 
    private boolean navigatorIsAvailable(Booking bookingRequest, List<Booking> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getStartDate().equals(existingBooking.getStartDate())
                                || bookingRequest.getEndDate().isBefore(existingBooking.getEndDate())
                                || (bookingRequest.getStartDate().isAfter(existingBooking.getStartDate())
                                && bookingRequest.getStartDate().isBefore(existingBooking.getEndDate()))
                                || (bookingRequest.getStartDate().isBefore(existingBooking.getStartDate())

                                && bookingRequest.getEndDate().equals(existingBooking.getEndDate()))
                                || (bookingRequest.getStartDate().isBefore(existingBooking.getStartDate())

                                && bookingRequest.getEndDate().isAfter(existingBooking.getEndDate()))

                               || (bookingRequest.getStartDate().equals(existingBooking.getEndDate())
                                && bookingRequest.getEndDate().equals(existingBooking.getStartDate()))

                                || (bookingRequest.getStartDate().equals(existingBooking.getEndDate())
                                && bookingRequest.getEndDate().equals(bookingRequest.getStartDate()))
                );
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteByBookingId(bookingId);
    }

    public Booking findByBookingConfirmationCode(String confirmationCode) {
    	
    Booking  booking = bookingRepository.findByBookingConfirmationCode(confirmationCode);
    if(booking==null) {
    	
          throw new ResourceNotFoundException("No booking found with booking code :"+confirmationCode);
          

    }
    else{
	return booking;
}
	
	
}
	@Override
	public List<Booking> getBookingsByTouristEmail(String touristEmail) {
		
		        return bookingRepository.findByTouristEmail(touristEmail);
		    
	}
}