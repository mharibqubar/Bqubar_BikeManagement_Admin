package com.bike.management.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="bike_types")
public class BikeType {

	@Id
	@Column(name="bike_id")
	private int bikeId;
	
	@Column(name="bike_name")
	private String bikeName;
}
