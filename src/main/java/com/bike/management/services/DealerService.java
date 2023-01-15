package com.bike.management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bike.management.models.Dealer;

public interface DealerService {
	
	Dealer createDealer(Dealer dealer);
	Dealer validateDealer(String username, String password) throws Exception;
	void cancelDealer(Long dealerId);
	Dealer updateDealer(Dealer dealer);
	Dealer editDealer(Long dealerId);
	List<Dealer> getDealers();
	Dealer findDealer(Long dealerId);

}
