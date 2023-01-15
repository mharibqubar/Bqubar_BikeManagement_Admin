package com.bike.management.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bike.management.models.Dealer;


@Repository
public interface DealersRepository extends JpaRepository<Dealer, Long>{

	@Query(value="SELECT * FROM dealers WHERE email_id = ?1 and password = ?2",nativeQuery = true)
	Optional<Dealer> validateUsrenameOrPassword(String email, String pwd);
}
