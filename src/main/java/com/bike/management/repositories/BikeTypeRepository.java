package com.bike.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bike.management.models.BikeType;
public interface BikeTypeRepository  extends JpaRepository<BikeType, Integer>{
	
	

}
