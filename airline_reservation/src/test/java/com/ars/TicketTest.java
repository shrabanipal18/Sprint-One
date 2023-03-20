package com.ars;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.ars.config.HibernateUtil;
import com.ars.model.TicketBookingDTO;
import com.ars.service.TicketService;
import com.ars.serviceimpl.TicketServiceImpl;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TicketTest {
	TicketService tService=new TicketServiceImpl();
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

	//testing method for book ticket
@Test
@Order(1)
void testBookFlight()
{
	TicketBookingDTO tDto=tService.bookFlight(1,1,LocalDate.parse("2023-03-18"),"priya112@gmail.com","Indigo");
	assertNotNull(tDto);
}
//testing method for cancel booking
@Test
@Order(2)
void testCancelBooking()
{
	tService.cancelBooking(86186);
	assertThrows(HibernateException.class,()->tService.getTicket(86186));
	}
//testing method for get ticket
@Test
@Order(3)
void testGetTicket()
{
	TicketBookingDTO tDto=tService.getTicket(86186);
	assertNotNull(tDto);
}
}
