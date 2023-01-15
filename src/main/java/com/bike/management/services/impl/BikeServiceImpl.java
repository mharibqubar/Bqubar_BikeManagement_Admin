package com.bike.management.services.impl;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bike.management.configurations.GlobalConstants;
import com.bike.management.exceptions.NoDataFoundException;
import com.bike.management.models.Bike;
import com.bike.management.models.BikeType;
import com.bike.management.repositories.BikeRepository;
import com.bike.management.repositories.BikeTypeRepository;
import com.bike.management.services.BikeService;


@Service
public class BikeServiceImpl implements BikeService{
	
	@Autowired
	private BikeRepository bikeRepository;
	
	@Autowired
	private BikeTypeRepository bikeTypeRepository;

	@Override
	public Bike createBike(Bike bike) {
		bike.setModifyAt(Instant.now());
		return bikeRepository.save(bike);
	}

	@Override
	public Bike updateBike(String bike) {
		return null;
	}

	@Override
	public String deleteBike(String bike) {
		return null;
	}

	@Override
	public List<Bike> getBikes(int bikeId) {
		Optional<List<Bike>> bikes = bikeRepository.findAllByBikeId(bikeId); 
		if(!bikes.isPresent()) {
			throw new NoDataFoundException(GlobalConstants.NO_DATA);
		}
		return bikes.get();
	}

	@Override
	public Bike getOneBike(int id) {
		Optional<Bike> bikeData = bikeRepository.findById(id);
		Bike bike = bikeData.isPresent() ? bikeData.get() : new Bike();
		bike.setBikeImg(bike.getId() +"_"+ bike.getBikeImg());
		return bike; 
	}

	@Override
	public Map<String, List<String>> validateBike(Bike bike) {
		Map<String, List<String>> errors = new HashMap<>();
		return null;
	}

	@Override
	public List<BikeType> getBikeTypes() {
		System.out.println(bikeTypeRepository.findAll());
		return bikeTypeRepository.findAll();
	}

	@Override
	public String shareBikeToCustomers(String shareId) {
		Bike bike = bikeRepository.findById(Integer.valueOf(shareId)).orElseThrow(() -> new NoDataFoundException(GlobalConstants.NO_DATA));
		int count = bikeRepository.updateShareIdForBike(bike.getId(),bike.getStatus());	
		
		return bike.getBrand() + GlobalConstants.CUSTOMER_MSG;
	}

	@Override
	public List<Bike> searchBike(String bikeName) {
	List<Bike> duplicateBikes = bikeRepository.getBikesBySearch(bikeName.toLowerCase()); 
	List<Bike> bikes = duplicateBikes.stream().distinct().collect(Collectors.toList());
	if(bikes != null && bikes.isEmpty())
	{
		throw new NoDataFoundException(GlobalConstants.NO_DATA);
	}
		return bikes;
	}

}
