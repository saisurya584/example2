package com.travelbees.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travelbees.entity.Navigator;
import com.travelbees.entity.Tourist;

import jakarta.transaction.Transactional;

@Repository
public interface NavigatorRepository  extends JpaRepository<Navigator, Long> {

	  @Query("SELECT DISTINCT t.place FROM Navigator t")
	   public  List<String> findDistinctPlaces();
	  
	  public Navigator findByNavigatorId(Long navigatorId);
	  
	  
	 
	  @Query("SELECT n FROM Navigator n " +
		       "WHERE n.place LIKE %:place% " +
		       "AND n.navigatorId NOT IN (" +
		       "    SELECT b.navigator.navigatorId FROM Booking b " +
		       "    WHERE ((b.startDate <= :endDate) AND (b.endDate >= :startDate))" +
		       ")")
		List<Navigator> findAvailableNavigatorsByPlaceAndDates(
		        @Param("place") String place,
		        @Param("startDate") LocalDate startDate,
		        @Param("endDate") LocalDate endDate);

 
	    @Transactional
		 void deleteByNavigatorId(Long navigatorId);
		 
}