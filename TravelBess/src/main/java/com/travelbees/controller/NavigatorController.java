package com.travelbees.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.constants.TouristConstants;
import com.cg.touristUtils.TouristUtils;
import com.travelbees.dto.BookingDto;
import com.travelbees.dto.NavigatorDto;
import com.travelbees.entity.Booking;
import com.travelbees.entity.Navigator;

import com.travelbees.exception.PhotoRetrievalException;
import com.travelbees.service.BookingService;
import com.travelbees.service.NavigatorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//@RequestMapping("/navigators")
public class NavigatorController {

@Autowired
public NavigatorService navigatorService;

@Autowired
public BookingService bookingService;

//admin can add navigator
@PostMapping("/addnavigator")
public ResponseEntity<NavigatorDto> addNavigator( @RequestParam("photo") MultipartFile photo,
		@RequestParam("place") String place,
		@RequestParam("navigatorName") String navigatorName,
		@RequestParam("navigatorEmail") String navigatorEmail,
		@RequestParam("contactNumber") String contactNumber,
		@RequestParam("aadharNumber") String aadharNumber,
		@RequestParam("price") BigDecimal price) throws SQLException, IOException {
	

	      Navigator savedNavigator= navigatorService.addNavigator(photo,place,navigatorName,navigatorEmail,contactNumber,
			    		aadharNumber,price);
		
	        NavigatorDto navigatorDto = new NavigatorDto(savedNavigator.getNavigatorId(), savedNavigator.getPlace(),
	        		savedNavigator.getNavigatorName(),savedNavigator.getNavigatorEmail(),savedNavigator.getContactNumber(),
	        		savedNavigator.getPrice());
	        return ResponseEntity.ok(navigatorDto);


}

//toursitfirst select places.in order to  get navigators at that place.for select he has to chooose places.
@GetMapping("/place")
public List<String> getAllPlaces() {
    return navigatorService.getAllPlaces();
}
//after tourist sellect places he will get all navigators that place.
//admin also get to edit navigators with thieir bookings
@GetMapping("/allnavigators")
public ResponseEntity<List<NavigatorDto>> getAllNavigators() throws SQLException {
    List<Navigator> navigators = navigatorService.getAllNavigators();
    List<NavigatorDto> navigatorDtos = new ArrayList<>();
    for (Navigator navigator : navigators) {
        byte[] photoBytes = navigatorService.getNavigatorPhotoByNavigatorId(navigator.getNavigatorId());
        if (photoBytes != null && photoBytes.length > 0) {
            String base64Photo = Base64.encodeBase64String(photoBytes);
            NavigatorDto navigatorDto = getNavigatorDto(navigator);
            navigatorDto.setPhoto(base64Photo);
            navigatorDtos.add(navigatorDto);
        }
    }
    return ResponseEntity.ok(navigatorDtos);

}
private NavigatorDto getNavigatorDto(Navigator navigator) {
    List<Booking> bookings = getAllBookingsByNavigatorId(navigator.getNavigatorId());
   List<BookingDto> bookingInfo = null;
//		   bookings .stream()
//            .map(booking -> new BookingDto(booking.getBookingId(),
//                    booking.getStartDate(),
//                    booking.getEndDate(), booking.getBookingConfirmationCode())).toList();
    byte[] photoBytes = null;
    Blob photoBlob = navigator.getPhoto();
    if (photoBlob != null) {
        try {
            photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
        } catch (SQLException e) {
            throw new PhotoRetrievalException("Error retrieving photo");
        }
    }
    return new NavigatorDto(navigator.getNavigatorId(),navigator.getPlace(),navigator.getNavigatorName(),navigator.getNavigatorEmail(),
    		navigator.getContactNumber(),navigator.getAadharNumber(),
             navigator.getPrice(),
            navigator.getStatus(), photoBytes, bookingInfo);
}

private List<Booking> getAllBookingsByNavigatorId(Long navigatorId) {
    return bookingService.getAllBookingsByNavigatorId(navigatorId);

}

//admin can delete navigator.
@DeleteMapping("/deletenavigator/{navigatorId}")
public ResponseEntity<Void> deleteNavigator(@PathVariable Long navigatorId){
    navigatorService.deleteNavigator(navigatorId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

//admin can update navigator details
@PutMapping("/update/{navigatorId}")
public ResponseEntity<NavigatorDto> updateNaigator(@PathVariable Long navigatorId,
                                               @RequestParam(required = false)  String place,
                                               @RequestParam(required = false)  String navigatorName,
                                               @RequestParam(required = false)  String navigatorEmail,
                                               @RequestParam(required = false)  String contactNumber,
                                               @RequestParam(required = false)  String aadharNumber,
                                               @RequestParam(required = false) BigDecimal Price,
                                               @RequestParam(required = false) MultipartFile photo) throws SQLException, IOException {
    byte[] photoBytes = photo != null && !photo.isEmpty() ?
            photo.getBytes() : navigatorService.getNavigatorPhotoByNavigatorId(navigatorId);
    Blob photoBlob = photoBytes != null && photoBytes.length >0 ? new SerialBlob(photoBytes): null;
    Navigator navigators = navigatorService.updateNavigator(navigatorId,place,navigatorName,navigatorEmail,contactNumber ,aadharNumber,Price, photoBytes);
    navigators.setPhoto(photoBlob);
    NavigatorDto navigatorDto = getNavigatorDto(navigators);
    return ResponseEntity.ok(navigatorDto);
}
//toursit and admin can get details of navigator by providing id
@GetMapping("/navigator/{navigatorId}")
public ResponseEntity<NavigatorDto> getNavigatorByNavigatorId(@PathVariable Long navigatorId){
    Navigator navigators = navigatorService.getNavigatorByNavigatorId(navigatorId);
    
        NavigatorDto navigatorDto = getNavigatorDto(navigators);
        return  ResponseEntity.ok(navigatorDto);
    }
//admini and tourist can see available navigators in the dates
@GetMapping("/availablenavigators")
public ResponseEntity<List<NavigatorDto>> getAvailableNavigators(
        @RequestParam("startDate")LocalDate startDate,
        @RequestParam("endDate") LocalDate endDate,
        @RequestParam("place") String place) throws SQLException {
    List<Navigator> availableNavigators = navigatorService.getAvailableNavigators(startDate, endDate, place);
    List<NavigatorDto> navigatorDtos = new ArrayList<>();
    for (Navigator navigator: availableNavigators){
        byte[] photoBytes = navigatorService.getNavigatorPhotoByNavigatorId(navigator.getNavigatorId());
        if (photoBytes != null && photoBytes.length > 0){
            String photoBase64 = Base64.encodeBase64String(photoBytes);
            NavigatorDto navigatorDto = getNavigatorDto(navigator);
            navigatorDto.setPhoto(photoBase64);
            navigatorDtos.add(navigatorDto);
        }
    }
    if(navigatorDtos.isEmpty()){
        return ResponseEntity.noContent().build();
    }else{
        return ResponseEntity.ok(navigatorDtos);
    }
}





}






