package com.travelbees.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelbees.entity.Booking;

import jakarta.transaction.Transactional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	 @Transactional
	 void deleteByBookingId(Long bookingId);
	 
	 List<Booking> findByNavigatorNavigatorId(Long navigatorId);
	 
	 Booking findByBookingConfirmationCode(String confirmationCode);

	 List<Booking> findByTouristEmail(String email);
}
