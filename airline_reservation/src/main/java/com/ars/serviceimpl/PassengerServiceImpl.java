package com.ars.serviceimpl;
import com.ars.dao.PassengerDAO;
import com.ars.daoimpl.PassengerDAOImpl;
import com.ars.entity.Passenger;
import com.ars.exception.GlobalException;
import com.ars.model.PassengerDTO;
import com.ars.service.PassengerService;

import javax.persistence.PersistenceException;

import org.modelmapper.ModelMapper;
public class PassengerServiceImpl implements PassengerService{
	PassengerDAO passengerDao=new PassengerDAOImpl();
	@Override
	public void savePassenger(Passenger passenger) {
		passengerDao.savePassenger(passenger);
		
	}

	@Override
	public boolean login(String userName, String password) {
		
		return passengerDao.login(userName, password);
	}
	@Override
	public PassengerDTO getPassengerById(int id) throws GlobalException {
		Passenger  passenger1=passengerDao.getPassenger(id);
		
		return new ModelMapper().map(passenger1, 
				PassengerDTO.class);
	}

	@Override
	public PassengerDTO updatePassenger(int id, Passenger passenger) {
		Passenger p=passengerDao.updatePassenger(id, passenger);
		return new ModelMapper().map(p, PassengerDTO.class);
	}

	@Override
	public void deletePassenger(int id) throws PersistenceException {
		passengerDao.deletePassenger(id);
		
		
	}

}
