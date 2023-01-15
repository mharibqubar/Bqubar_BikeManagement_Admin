package com.bike.management.models;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Data
@Table(name="dealers")
public class Dealer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dealerId; 
	
	private String name;
	
	private String password;
	
	private String emailId;
	
	private Long mobileNo;
	
	private int dealerTypeId;
	
	private String images;
	
	private int role;
	
	private String adharCard;
	
	private String panCard;
	
	private String certificateImg;
	
	private String logo;
	
	private Instant creationDate;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	  @JoinColumn(name = "address_id", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Address> addresses;

}
