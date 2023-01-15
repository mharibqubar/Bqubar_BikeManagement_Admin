package com.bike.management.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bike.management.models.Bike;
import com.bike.management.models.BikeType;

@Service
public interface BikeService {

	Bike createBike(Bike bike);
	Bike updateBike(String bike);
	String deleteBike(String bike);
	List<Bike> getBikes(int bikeId);
	Bike getOneBike(int id);
	Map<String,List<String>> validateBike(Bike bike);
	List<BikeType> getBikeTypes();
	String shareBikeToCustomers(String shareId);
	List<Bike> searchBike(String bikeName);
}
