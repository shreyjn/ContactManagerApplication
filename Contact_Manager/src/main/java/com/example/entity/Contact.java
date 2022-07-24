package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CONTACT")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	@Column(nullable = false)
	private String mobileNumber1;
	private String mobileNumber2;
	private String imageUrl;
	private String description;

	@ManyToOne
	private User user;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber1() {
		return mobileNumber1;
	}

	public void setMobileNumber1(String mobileNumber1) {
		this.mobileNumber1 = mobileNumber1;
	}

	public String getMobileNumber2() {
		return mobileNumber2;
	}

	public void setMobileNumber2(String mobileNumber2) {
		this.mobileNumber2 = mobileNumber2;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public boolean equals(Object obj) {
		return this.cId==((Contact)obj).getcId();
	}

	@Override
	public String toString() {
		return "Contact [cId=" + cId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobileNumber1=" + mobileNumber1 + ", mobileNumber2=" + mobileNumber2 + ", imageUrl=" + imageUrl
				+ ", description=" + description + ", user=" + user + "]";
	}

}