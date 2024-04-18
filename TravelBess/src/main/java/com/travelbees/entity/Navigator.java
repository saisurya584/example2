package com.travelbees.entity;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="navigators")
public class Navigator {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long navigatorId;
	
	//@Pattern(regexp = "^[A-Za-z]*[A-Za-z]{4,20}")
	@Column(name="place")
	private String place;
	
	//@Pattern(regexp = "^[A-Za-z]*[A-Za-z]{4,20}")
	@Column(name="navigator_name")
	private String navigatorName;
	
	//@Pattern(regexp ="^[A-Za-z]{4,20}@gmail.com$")
	@Column(name="navigator_email")
	private String navigatorEmail;
	
	//@Pattern(regexp = "^[6-9]*[0-9]{10}")
	@Column(name="contact_number")
	private String contactNumber;
    
	
	//@Pattern(regexp = "^[2-9][2-9]*[0-9]{10}")
	@Column(name="aadhar_number")
    private String aadharNumber;
    
	
	@Min(value =2000, message = "minimu 2000")
	@Column(name="price")
    private BigDecimal price;
    
	@Column(name="status")
    private boolean status=false;
	
	
    @Column(name="photo")
	private  Blob photo;
    
    @OneToMany(mappedBy="navigator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Booking> bookings;

	public Navigator() {
		super();
        this.bookings = new ArrayList<>();

	}

	public Navigator(Long navigatorId, String place, String navigatorName, String navigatorEmail, String contactNumber,
			String aadharNumber, BigDecimal price, boolean status, Blob photo, List<Booking> bookings) {
		super();
		this.navigatorId = navigatorId;
		this.place = place;
		this.navigatorName = navigatorName;
		this.navigatorEmail = navigatorEmail;
		this.contactNumber = contactNumber;
		this.aadharNumber = aadharNumber;
		this.price = price;
		this.status = status;
		this.photo = photo;
		this.bookings = bookings;
	}

	public Long getNavigatorId() {
		return navigatorId;
	}

	public void setNavigatorId(Long navigatorId) {
		this.navigatorId = navigatorId;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Navigator [NavigatorId=" + navigatorId + ", place=" + place + ", navigatorName=" + navigatorName
				+ ", NavigatorEmail=" + navigatorEmail + ", contactNumber=" + contactNumber + ", aadharNumber="
				+ aadharNumber + ", price=" + price + ", status=" + status + ", photo=" + photo + ", bookings="
				+ bookings + "]";
	}
	
	
    
	public void addBooking(Booking booking){
        if (bookings == null){
            bookings = new ArrayList<>();
        }
        bookings.add(booking);
        booking.setNavigator(this);
        status = true;
        String bookingCode = RandomStringUtils.randomNumeric(10);
        booking.setBookingConfirmationCode(bookingCode);
    }

}