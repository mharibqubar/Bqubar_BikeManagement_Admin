package com.bike.management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bike.management.models.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer>{
	
	@Query(value="SELECT * FROM bikes b WHERE b.bike_id = ?1",nativeQuery = true)
	Optional<List<Bike>> findAllByBikeId(int bikeId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE bikes SET status = :status WHERE id = :bikeId", nativeQuery = true)
	int updateShareIdForBike(@Param("bikeId") int bikeId,@Param("status") int status);

	@Transactional
	@Query(value = "SELECT * FROM bikes b WHERE LOWER(b.brand) LIKE %:name% or b.bike_id IN (SELECT bike_id FROM bike_types WHERE LOWER(bike_name) LIKE %:name%)", nativeQuery = true)
	List<Bike> getBikesBySearch(@Param("name") String bikeName);

}
