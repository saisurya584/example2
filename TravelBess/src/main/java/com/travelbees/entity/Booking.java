package com.travelbees.entity;
 
import java.time.LocalDate;
import java.util.Date;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
 
@Entity
@Table(name="navigator_booking")
public class Booking {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long bookingId;
	
	@Column(name="start_date")
	@NotNull(message = "Required")
	private LocalDate startDate;
	@Column(name="end_date")
	@NotNull(message = "Required")
	private LocalDate endDate;
	@Column(name="tourist_fullname")
	@Pattern(regexp = "[a-zA-Z]*[a-zA-Z]{3,20}")
	private String touristFullName;
	
    @Column(name="contact_number")
    @Pattern(regexp = "^[6-9]*[0-9]{9}")
    private String contactNumber;

 
    @Column(name = "tourist_email")
    private String touristEmail;
 
    @Column(name = "adults")
    @Min(value = 1, message = "Total number of tourists must be at least 1")
    @Max(value = 9, message = "Total number of tourists cannot exceed 9")
    private int numOfAdults;
 
    @Column(name = "children")
    @Min(value = 0, message = "Total number of tourists must be at least 0")
    @Max(value = 9, message = "Total number of tourists cannot exceed 9 ")
    private int numOfChildren;
 
    @Column(name = "total_tourists")
    private int totalNumOfTourists;
 
    @Column(name = "confirmation_Code")
    private String bookingConfirmationCode;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "navigator_id")
    private Navigator navigator;
 
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public Booking(Long bookingId, LocalDate startDate, LocalDate endDate, String touristFullName, String contactNumber,
			String touristEmail, int numOfAdults, int numOfChildren, int totalNumOfTourists,
			String bookingConfirmationCode, Navigator navigator) {
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
		this.navigator = navigator;
	}
 
	public void calculateTotalNumberOfTourists(){
        this.totalNumOfTourists = this.numOfAdults + numOfChildren;
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
		calculateTotalNumberOfTourists();
	}
 
	public int getNumOfChildren() {
		return numOfChildren;
	}
 
	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
		 calculateTotalNumberOfTourists();
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
 
	public Navigator getNavigator() {
		return navigator;
	}
 
	public void setNavigator(Navigator navigator) {
		this.navigator = navigator;
	}
 
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", touristFullName=" + touristFullName + ", contactNumber=" + contactNumber + ", touristEmail="
				+ touristEmail + ", numOfAdults=" + numOfAdults + ", numOfChildren=" + numOfChildren
				+ ", totalNumOfTourists=" + totalNumOfTourists + ", bookingConfirmationCode=" + bookingConfirmationCode
				+ ", navigator=" + navigator + "]";
	}
 
	
}
