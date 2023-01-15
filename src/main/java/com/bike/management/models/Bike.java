package com.bike.management.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.NumberFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="bikes")
public class Bike {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int bikeId;
	@NotEmpty(message = "Brand is required")
	private String brand;
	@NotEmpty(message = "Bike model is required")
	private String model;
	
    @Column(name = "price", columnDefinition="double(10,2) default '0.00'")
	private double price;

    @Column(columnDefinition=" varchar(100) default ''")
	private String  bikeImg;
	@NotEmpty(message = "Fuel type is required")
	private String fuelType;

	@Column(columnDefinition=" varchar(50) default 'black'")
	private String colors;  // list of colors
	@NotEmpty(message = "Version is required")
	private String version;
	@NotEmpty(message = "About is required")
	private String about;
	@NotEmpty(message = "WebLink is required")
	private String link;
	@NotEmpty(message = "Wheels is required")
	private String wheels;

	@NumberFormat
	private int seatCapacity;
	@NotEmpty(message = "Safety is required")
	private String safety;

	@NumberFormat
	private int mileage;
	
	@NotEmpty(message = "Engine is required")
	private String engine;

	@NotEmpty(message = "Transmissoion is required")
	private String transmission;
	
	@Column(columnDefinition="int(1) default '1'")
	private int status = 1;  // 1 -> create, 2 -> update, 3 -> share, 4 -> delete
	
	private Instant modifyAt;
	
}
