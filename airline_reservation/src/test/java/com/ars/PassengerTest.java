package com.ars;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.ars.config.HibernateUtil;
import com.ars.entity.Passenger;
import com.ars.model.PassengerDTO;
import com.ars.service.PassengerService;
import com.ars.serviceimpl.PassengerServiceImpl;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class PassengerTest {
	PassengerService passengerService=new PassengerServiceImpl();
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

//testing for passenger registration
@Test
@Order(1)
void testRegisterPassenger()
{
	Transaction tx=session.beginTransaction();
	Passenger passenger=Passenger.builder().name("Priya Singh").email("priya112@gmail.com").UserName("pri123").password("p123").role("user").phno("9823564515").build();
	Integer t=(Integer)session.save(passenger);
	tx.commit();
	assertThat(t>0).isTrue();
	}
//testing for get passenger
@Test
@Order(2)
void testGetPassengerById()
{
	PassengerDTO pdto=passengerService.getPassengerById(3);
	assertThat(pdto.getName()).isEqualTo("Priya Singh");
	}
//testing for update passenger
@Test
@Order(3)
void testUpdatePassengerUsingService()
{
	Transaction tx=session.beginTransaction();
	Passenger pd=new Passenger();
	pd.setName("Priya Singh");
	PassengerDTO pdto=passengerService.updatePassenger(3, pd);
	tx.commit();
	assertThat(pdto.getName()).isEqualTo("Priya Singh");
	}
// testing for delete passenger
@Test
@Order(4)
@DisplayName(value="Negetive Test case")
void testDeletePassenger()
{
	passengerService.deletePassenger(3);
	assertThrows(HibernateException.class,()->passengerService.getPassengerById(3));
	}


}
