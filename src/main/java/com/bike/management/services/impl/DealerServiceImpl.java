package com.bike.management.services.impl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bike.management.configurations.GlobalConstants;
import com.bike.management.exceptions.InvalidCredentialException;
import com.bike.management.exceptions.NoDataFoundException;
import com.bike.management.models.Dealer;
import com.bike.management.repositories.DealersRepository;
import com.bike.management.services.DealerService;

@Service
public class DealerServiceImpl implements DealerService{
	
	@Autowired
	private DealersRepository dealersRepository;

	@Override
	public Dealer createDealer(Dealer dealer) {
		this.dealerExistOrNot(dealer.getDealerId());
		return dealersRepository.save(dealer);
	}
	
	@Override
	public Dealer validateDealer(String username, String password) {
		Optional<Dealer> dealer = dealersRepository.validateUsrenameOrPassword(username, password);
		if(!dealer.isPresent())
		{
			throw new InvalidCredentialException(GlobalConstants.INVALID_CREDENTIALS);
		}
		
		return dealer.get();
	}

	@Override
	public void cancelDealer(Long dealerId) {
		Dealer dealer = this.findDealer(dealerId);
		 dealersRepository.deleteById(dealerId);
	}

	@Override
	public Dealer updateDealer(Dealer dealer) {
		Dealer dealerExist = this.findDealer(dealer.getDealerId());
		return dealersRepository.saveAndFlush(dealer);
	}

	@Override
	public Dealer editDealer(Long dealerId) {
		Dealer dealer = this.findDealer(dealerId);
		return dealer;
	}

	@Override
	public List<Dealer> getDealers() {
		return dealersRepository.findAll();
	}

	public Dealer findDealer(Long dealerId) {
		Optional<Dealer> dealer = dealersRepository.findById(dealerId);
		if(!dealer.isPresent())
		{
			throw new NoDataFoundException(GlobalConstants.DEALER_MSG);
		}
		return dealer.get();
	}
	
	private void dealerExistOrNot(Long dealerId) {
		Optional<Dealer> dealer = dealersRepository.findById(dealerId);
		if(dealer.isPresent())
		{
			throw new NoDataFoundException(GlobalConstants.DEALER_EXIST);
		}
	}

}
