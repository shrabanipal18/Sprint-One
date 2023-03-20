package com.ars.model;

import java.time.LocalDate;
import java.util.Date;

import com.ars.entity.Airline;
import com.ars.entity.Flight;
import com.ars.entity.Passenger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TicketBookingDTO {
	private int ticketId;
	private int no_of_passenger;
	private LocalDate date;
	private Flight id;
	private Airline aid;
	private Passenger pid;

}
