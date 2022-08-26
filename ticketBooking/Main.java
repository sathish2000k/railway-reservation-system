package ticketBooking;

import java.util.*;

public class Main {
	static Train vaigaiExpress=new Train();    ///vaigai expres
	static Train pandianExpress=new Train();  ///pandian express
	static Train quilonExpress=new Train();  ///quilon express
	static HashMap<Integer,Ticket> ticketList = new HashMap<>();
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("*******************************");
		int option=0;
		System.out.println("Press 1 for Booking\nPress 2 for cancellation\nPress 3 for Reservation status\nPress 4 for exit\nPress 5 for Passenger list\nPress 6 for Ticket list\nPress 7 for Cancel ticket");
		Train express=null;
		while(option!=4){
			 option = input.nextInt();
			 if(option!=4) {
				 System.out.println("Press v for vaigai express, p for pandian express, q for quilon express");
				 System.out.println("Enter Train name:");
				 String s=input.next();
				 if(s.equals("v")) {
					 express=vaigaiExpress;
				 }
				 else if(s.equals("p")) {
					 express=pandianExpress;
				 }
				 else if(s.equals("q")){
					 express=quilonExpress;
				 }
				 else {
					 System.out.println("Please check the train code");
					 System.out.println("Press 1 for Booking\nPress 2 for cancellation\nPress 3 for Reservation status\nPress 4 for exit\nPress 5 for Passenger list\nPress 6 for Ticket list\nPress 7 for Cancel ticket");
					 continue;
				 }
			 }
			 switch(option) {
			 case 1:
				 System.out.println("Starting Booking");
				 bookingTicket(express);
				 break;
			 case 2:
				 System.out.println("Starting Cancellation");
				 cancelFromTicket(express);
				 break;
			case 3:
				System.out.println("Checking Reservation Status");
				///showReservationStatus(express);
				break;
			case 4:
				break;
			case 5:
				showBookedDetails(express);
				break;
			case 6:
				showTicketDetails(express);
				break;
			case 7:
				System.out.println("Starting Cancellating tickt");
				cancelTicket(express);
				break;
			 default:
				 System.out.println("Invalid input");
			 }
			 System.out.println("-------------------------------");			
		}	
		System.out.println("Thank you");
	}		

	
	
	public static void bookingTicket(Train express) {
		
		
	}



	public static void booking(Passenger p, Train express) {
		express.bookedTicketList.add(p);
		
	}
	
	public static void racbooking(Passenger p,Train express) {
		express.racList.add(p);
	}


	public static void wlbooking(Passenger p,Train express) {
		express.wlList.add(p);	
	}
	
	
	///Print bookedlist
	public static void showBookedDetails(Train express) {
		System.out.println("*******************************");
		for(Passenger p:express.bookedTicketList) {
			System.out.println("Name:"+p.name+" Status:"+" Seat Number:"+p.seatNumber+" from:"+p.fromStation+" to:"+p.toStation);
		}
		
	}
	
	
	///tickets
	///add ticket
	public static void addTicket(Passenger p,Train express) {
			Ticket lastBookedTicket=ticketList.get(Ticket.ticketNo);
			if(lastBookedTicket!=null && lastBookedTicket.passengers.size()<3) {
				lastBookedTicket.passengers.add(lastBookedTicket.passengers.size(),p);
			}
			else {
				Ticket.ticketNo++;
				Ticket newTicket=new Ticket();
				newTicket.passengers.add(newTicket.passengers.size(),p);	
				ticketList.put(Ticket.ticketNo,newTicket);	
				newTicket.boardingPoint=p.fromStation;
				newTicket.destination=p.toStation;
				if(express.equals(vaigaiExpress)) {
					newTicket.trainName = "Vaigai express";
				}
				if(express.equals(pandianExpress)) {
					newTicket.trainName = "Pandian express";
				}
				if(express.equals(quilonExpress)) {
					newTicket.trainName = "Quilon express";
				}
			}
		}
	
	
	///cancel ticket
	public static void cancelTicket(Train express) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter ticket Number");
		int cancelTicketNo = input.nextInt();
		Ticket ticket = ticketList.get(cancelTicketNo);
		for(Passenger p:ticket.passengers) {
			if(express.bookedTicketList.contains(p)) {
				express.bookedTicketList.remove(p);
				express.availableSeat.add(p.seatNumber);
				if(express.racList.size()!=0) {
					addRacTickets(express);  ///1
				}
			}
			else if(express.racList.contains(p)) {
				express.racList.remove(p);
			}
			else if(express.wlList.contains(p)) {
				express.wlList.remove(p);
			}
		}
		ticketList.remove(cancelTicketNo);	
	}
	
	
	///Cancelling passenger from ticket
	public static void cancelFromTicket(Train express) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter ticket Number");
		int cancelTicketNo = input.nextInt();
		Ticket ticket = ticketList.get(cancelTicketNo);
		System.out.println("Enter passenger code");
		int cancelPassengerNo = input.nextInt();
		Passenger p=ticket.passengers.get(cancelPassengerNo);
		if(express.bookedTicketList.contains(p)) {
			express.bookedTicketList.remove(p);
			express.availableSeat.add(p.seatNumber);
			if(express.racList.size()!=0) {
				addRacTickets(express);  ///1
			}
		}
		else if(express.racList.contains(p)) {
			express.racList.remove(p);
		}
		else if(express.wlList.contains(p)) {
			express.wlList.remove(p);
		}
	}
	


	///Print ticket details
	public static void showTicketDetails(Train express) {
		for(Integer ticket:ticketList.keySet()) {
			int ticketNo = ((int)ticket);
			if(ticketList.size()>0) {
			System.out.println("Ticket No:"+ticketNo);	
			System.out.println(ticketList.get(ticket).trainName);
			for(Passenger passenger:ticketList.get(ticket).passengers) {
				System.out.println(ticketList.get(ticket).passengers.indexOf(passenger)+" "+passenger.name+" "+passenger.seatNumber);
				}
			}
		}	
	}
	
	public static void addRacTickets(Train express) {
		
		
	}
			
}