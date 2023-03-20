package com.ars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.ars.config.HibernateUtil;
import com.ars.dao.AirlineDAO;
import com.ars.daoimpl.AirlineDAOImpl;
import com.ars.entity.Admin;
import com.ars.entity.Airline;
import com.ars.entity.Flight;
import com.ars.model.AdminDTO;
import com.ars.model.FlightDTO;
import com.ars.model.TicketBookingDTO;
import com.ars.service.AirlineService;
import com.ars.service.FlightService;
import com.ars.serviceimpl.AirlineServiceImpl;
import com.ars.serviceimpl.FlightServiceImpl;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class FlightTest {
	FlightService fService=new FlightServiceImpl();
	AirlineService airlineService=new AirlineServiceImpl();
	AirlineDAO airDao=new AirlineDAOImpl();
	private static SessionFactory sessionFactory;
	private Session session;
	
	@BeforeAll
	static void setUp()
	{
		sessionFactory=HibernateUtil.getSessionFactory();
	}
	@BeforeEach
	void openSession()
	{
		session=sessionFactory.openSession();
	}
	
@AfterEach
void closeSession()
{
	if(session!=null)
	session.close();
	System.out.println("Session closed");
	}
@AfterAll
static void tearDown()
{
	if(sessionFactory!=null)
		sessionFactory.close();
	System.out.println("Session factory colosed");
	}
//testing for save flight
@Test
@Order(1)
void testSaveFlight()
{
	Transaction tx=session.beginTransaction();
	Flight flight=Flight.builder().flight_id(1).avilableSeats(54).totalSeats(90).travellerClass("Business").time("06:10").date(LocalDate.of(2023, 03, 18)).destination("delhi").source("kolkata").build();
	Integer i=(Integer)session.save(flight);
	tx.commit();
	assertThat(i>0).isTrue();
	}
//testing for update flight
@Test
@Order(2)
void testUpdateFlight()
{
	Transaction tx=session.beginTransaction();
	Flight ad=new Flight();
	ad.setFlight_id(1);
	FlightDTO fDto=fService.updateFlight(1, ad);
	tx.commit();
	assertThat(fDto.getFlight_id()).isEqualTo(1);
}
//testing for get flight
@Test
@Order(3)
void testGetFlight()
{
	FlightDTO fDto=fService.getFlight(1);
	assertThat(fDto.getFlight_id()).isEqualTo(1);
}
//testing for delete flight
@Test
@Order(4)
void testDeleteFlight()
{
	fService.deleteFlight(1);
	assertThrows(HibernateException.class,()->fService.getFlight(1));
}

}
