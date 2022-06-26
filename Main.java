package ticketBooking;

import java.util.HashMap;
import java.util.Scanner;


public class Main {
	static Train vaigaiExpress=new Train();    ///vaigai expres
	static Train pandianExpress=new Train();  ///pandian express
	static Train quilonExpress=new Train();  ///quilon express
	static HashMap<Integer,Ticket> ticketList = new HashMap<>();
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("*******************************");
		System.out.println("Vaigai Express");
		showAvailabletickets(vaigaiExpress);
		System.out.println("Pandian Express");
		showAvailabletickets(pandianExpress);
		System.out.println("Quilon Express");
		showAvailabletickets(quilonExpress);
		System.out.println("*******************************");
		if((vaigaiExpress.waitingList-(vaigaiExpress.wlList.size()))!=0 && (pandianExpress.waitingList-(pandianExpress.wlList.size()))!=0 && (quilonExpress.waitingList-(quilonExpress.wlList.size()))!=0) {
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
				 if((express.waitingList-(express.wlList.size()))==0) {
					 System.out.println("No tickets available!!!");
					 System.out.println("Press y to book in other train or n to exit");
					 String selectTrain = input.next();
					 if(selectTrain.equals("y")) { 
						 if((vaigaiExpress.waitingList-(vaigaiExpress.wlList.size()))!=0) {
							 showAvailabletickets(vaigaiExpress);
							 System.out.println("Vaigai Express tickets are available");
						 }
						 if((pandianExpress.waitingList-(pandianExpress.wlList.size()))!=0) {
							 showAvailabletickets(pandianExpress);
							 System.out.println("Pandian Express tickets are available");
						 }
						 if((quilonExpress.waitingList-(quilonExpress.wlList.size()))!=0) {
							 showAvailabletickets(quilonExpress);
							 System.out.println("Quilon Express tickets are available");
						 }
						 System.out.println("Press v for vaigai express, p for pandian express, q for quilon express");
						 System.out.println("Enter Train name:");
						 String s=input.next();
						 if(s.equals("v")) {
							 express=vaigaiExpress;
							 if((express.waitingList-(express.wlList.size()))==0) {
								 break;
							 } 						 	 
						 }
						 else if(s.equals("p")) {
							 express=pandianExpress;
							 if((express.waitingList-(express.wlList.size()))==0) {
								 break;
							 } 	
						 }
						 else if(s.equals("q")){
							 express=quilonExpress;
							 if((express.waitingList-(express.wlList.size()))==0) {
								 break;
							 } 	
						 }
					 }
					 
					 else {
						 continue;
					 }	 
				 }
				 showAvailabletickets(express);
				 System.out.println("Starting Booking");
				 bookingTicket(express);
				 break;
			 case 2:
				 System.out.println("Starting Cancellation");
				 cancelTicket(express);
				 break;
			case 3:
				System.out.println("Checking Reservation Status");
				showReservationStatus(express);
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
				cancelFromTicket(express);
				break;
			 default:
				 System.out.println("Invalid input");
			 }
			 System.out.println("-------------------------------");			
		}	
	}	
		else {
			System.out.println("No tickets available!!!");
		}
		System.out.println("Thank you");
	 
	}	
	
	

	///Passenger Info and seat number allotment
	public static void bookingTicket(Train express){
		Scanner input =new Scanner(System.in);
		System.out.println("enter your name:");
		String name=input.nextLine();
		System.out.println("enter your age:");
		int age=input.nextInt();
		Passenger p=new Passenger(name,age);
		System.out.println("Press l for lower berth, m for middle berth, u for upper berth");
		System.out.println("enter your berth:");
		String berth=input.next();
		if(express.availableSeat.size()>0) {
			if(berth.equals("l") || berth.equals("m") || berth.equals("u") ){
				if(berth.equals("l")) {
					for(int i:express.availableSeat) {
						if(i%8==1 || i%8==4 || i%8==7) {
							p.seatNumber=i;
							booking(p,express);		
							Integer seatNumber = (Integer)i;
							express.availableSeat.remove(seatNumber);
							System.out.println("Passenger id for "+p.name+" is "+p.passengerId);
							System.out.println("Seat Number for "+p.name+" is "+p.seatNumber);
							System.out.println("Lower berth booked for "+p.name+" Successfully");
							addTicket(p,express);
							break;
						}
					}
			}
			if(berth.equals("m")) {
				for(int i:express.availableSeat) {
					if(i%8==2 || i%8==5) {
						p.seatNumber=i;
						booking(p,express);		
						Integer seatNumber = (Integer)i;
						express.availableSeat.remove(seatNumber);
						System.out.println("Passenger id for "+p.name+" is "+p.passengerId);
						System.out.println("Seat Number for "+p.name+" is "+p.seatNumber);
						System.out.println("Middle berth booked for "+p.name+" Successfully");
						addTicket(p,express);
						break;
					}
				}
			}
			if(berth.equals("u")) {
				for(int i:express.availableSeat) {
					if(i%8==0 || i%8==3 || i%8==6) {
						p.seatNumber=i;
						booking(p,express);		
						Integer seatNumber = (Integer)i;
						express.availableSeat.remove(seatNumber);
						System.out.println("Passenger id for "+p.name+" is "+p.passengerId);
						System.out.println("Seat Number for "+p.name+" is "+p.seatNumber);
						System.out.println("Upper berth booked for "+p.name+" Successfully");
						addTicket(p,express);
						break;
					}
				}
			}
			else if(express.availableSeat.size()>0 && p.seatNumber==0){
				System.out.println("Tickets not available for chosen berth");
				System.out.println("Press Y for Book Available berth Or Press N for exit");
				String option = input.next();
				if(option.equalsIgnoreCase("y")) {
					p.seatNumber=express.availableSeat.get(0);
					booking(p,express);			
					express.availableSeat.remove(0);
					System.out.println("Passenger id for "+p.name+" is "+p.passengerId);
					System.out.println("Seat Number for "+p.name+" is "+p.seatNumber);
					System.out.println("Berth booked for "+p.name+" Successfully");
					addTicket(p,express);
					}
			else {
					Passenger.id--;
			}
		}	
		}
		}
		else if((express.racListSeats-(express.racList.size()))>0) {            ///RACCOUNT
			System.out.println("RAC Tickets available press Y to book or press N to exit");
			String option=input.next();
			if(option.equalsIgnoreCase("y")) {
				racbooking(p,express);
				System.out.println("Passenger id for "+p.name+" is "+p.passengerId);
				System.out.println("Moved to RAC");
				addTicket(p,express);
			}
			else {
				Passenger.id--;
			}
		}
		else if((express.waitingList-(express.wlList.size()))>0) {
			System.out.println("Only Waiting List Tickets available press y to book");
			String option=input.next();
			if(option.equalsIgnoreCase("y")) {
				wlbooking(p,express);
				System.out.println("Passenger id for "+p.name+" is "+p.passengerId);
				System.out.println("Moved to Waiting List");
				addTicket(p,express);
			}
			else {
				Passenger.id--;
			}
		}
		System.out.println("-------------------------------");
	}
	
	
	///Booking 
	public static void booking(Passenger p,Train express) {
		express.passengersInfo.put(p.passengerId, p);
		express.bookedTicketList.add(p.passengerId);
	}

	public static void racbooking(Passenger p,Train express) {
		express.passengersInfo.put(p.passengerId, p);
		express.racList.add(p.passengerId);
	}


	public static void wlbooking(Passenger p,Train express) {
		express.passengersInfo.put(p.passengerId, p);
		express.wlList.add(p.passengerId);	
	}
	
	
	///Cancellation
	public static void cancelTicket(Train express) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your id:");
		int cancel = input.nextInt();
		Integer cancelId=cancel; 
		if(express.passengersInfo.containsKey(cancelId)) {
			Passenger p=express.passengersInfo.get(cancelId);
			System.out.println("*******************************");
			System.out.println("Ticket cancelled for "+p.name);
			System.out.println("-------------------------------");
			passengerFromTicket(express,cancelId);
			express.passengersInfo.remove(cancelId);
			if(express.bookedTicketList.contains(cancelId)) {
				express.bookedTicketList.remove(cancelId);
				if(express.racList.size()>0) {                                              ///seat number
						express.availableSeat.add(p.seatNumber);
						addRacTickets(express);
				}
				else{
					express.availableSeat.add(p.seatNumber);
				}
			}
			if(express.racList.contains(cancelId)) {
				express.racList.remove(cancelId);
				if(express.wlList.size()>0) {
					addWlToRac(express);
				}
			}
			if(express.wlList.contains(cancelId)) {
				express.wlList.remove(cancelId);
			}
			
		}
		else {
			System.out.println("Please check the passenger id");
		}
	}

	public static void addRacTickets(Train express) {
			Integer addRacid = express.racList.poll();
			Passenger p=express.passengersInfo.get(addRacid);
			p.seatNumber = express.availableSeat.get(0);
			express.availableSeat.remove(0);
			booking(p,express);
			if(express.wlList.size()>0) {
				addWlToRac(express);
			}
	}
	
	public static void addWlToRac(Train express) {
			Integer addWlId = express.wlList.poll();
			Passenger p=express.passengersInfo.get(addWlId);
			racbooking(p,express);
	}	
	
	
	///Show available ticket
	public static void showAvailabletickets(Train express) {
		int lowerBerthCount=0;
		int middleBerthCount=0;
		int upperBerthCount=0;
		for(int i:express.availableSeat)
		{
			if(i%8==1 || i%8==4 || i%8==7) {
				lowerBerthCount++;
			}
			if(i%8==2 || i%8==5) {
				middleBerthCount++;
			}
			if(i%8==3 || i%8==6 || i%8==0) {
				upperBerthCount++;
			}
		
		}
		System.out.println("Available Lower Berth:"+lowerBerthCount);
		System.out.println("Available Middle Berth:"+middleBerthCount);
		System.out.println("Available Upper Berth:"+upperBerthCount);
		System.out.println("Available RAC tickets:"+(express.racListSeats-(express.racList.size())));   ///RACCOUNT
		System.out.println("Available WaitingList tickets:"+(express.waitingList-(express.wlList.size())));
		if((express.waitingList-(express.wlList.size()))==0) {
			System.out.println("-----No tickets available!!!-----");
		}
	}	
	
	///Show Reservation status
	public static void showReservationStatus(Train express) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Passenger Id");
		int id=input.nextInt();
		if(express.passengersInfo.containsKey(id)) {
			Passenger p=express.passengersInfo.get(id);
			if(express.bookedTicketList.contains(p.passengerId)) {
				System.out.println("Name:"+p.name +" id:"+p.passengerId+" Seat Number:"+p.seatNumber);
			}
			if(express.racList.contains(p.passengerId)) {
				int racNumber = express.racList.indexOf(p.passengerId)+1;
				System.out.println("Name:"+p.name +" id:"+p.passengerId+" RAC Number:"+racNumber);
			}
			if(express.wlList.contains(p.passengerId)) { 
				int wlNumber = express.wlList.indexOf(p.passengerId)+1;
				System.out.println("Name:"+p.name +" id:"+p.passengerId+" Waiting List Number:"+wlNumber);
			}
		}
		else {
			System.out.println("Please enter correct passenger id");
		}
	}
	
	public static void showBookedDetails(Train express) {	
		System.out.println("*******************************");
		for(Passenger p:express.passengersInfo.values()) {
			if(express.bookedTicketList.contains(p.passengerId)) {
				System.out.println("Name:"+p.name+" Status:"+" id:"+p.passengerId+" Seat Number:"+p.seatNumber);
			}
			if(express.racList.contains(p.passengerId)) {
				int racNumber = express.racList.indexOf(p.passengerId)+1;
				System.out.println("Name:"+p.name+" Status:"+" id:"+p.passengerId+" RAC Number:"+racNumber);
			}
			if(express.wlList.contains(p.passengerId)) { 
				int wlNumber = express.wlList.indexOf(p.passengerId)+1;
				System.out.println("Name:"+p.name+" Status:"+" id:"+p.passengerId+" Waiting List Number:"+wlNumber);
			}
		}
		System.out.println("-------------------------------");
	}	
	
	///tickets
	///add ticket
	public static void addTicket(Passenger p,Train express) {
			Ticket lastBookedTicket=ticketList.get(Ticket.ticketNo);
			if(lastBookedTicket!=null && lastBookedTicket.passengers.size()<3 && (express.bookedTicketList.contains(lastBookedTicket.passengers.get(0).passengerId) ||
				express.racList.contains(lastBookedTicket.passengers.get(0).passengerId) || express.wlList.contains(lastBookedTicket.passengers.get(0).passengerId))) {
				lastBookedTicket.passengers.add(lastBookedTicket.passengers.size(),p);
			}
			else {
				Ticket.ticketNo++;
				Ticket newTicket=new Ticket();
				newTicket.passengers.add(newTicket.passengers.size(),p);	
				ticketList.put(Ticket.ticketNo,newTicket);	
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
	public static void cancelFromTicket(Train express) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter ticket Number");
		int cancelTicketNo = input.nextInt();
		Ticket ticket = ticketList.get(cancelTicketNo);
		for(Passenger p:ticket.passengers) {
			if(express.bookedTicketList.contains(p.passengerId)) {
				Integer cancelId=p.passengerId;
				express.bookedTicketList.remove(cancelId);
				express.passengersInfo.remove(cancelId);
				express.availableSeat.add(p.seatNumber);
				if(express.racList.size()!=0) {
					addRacTickets(express);  ///1
				}
			}
			else if(express.racList.contains(p.passengerId)) {
				Integer cancelId=p.passengerId;
				express.racList.remove(cancelId);
				express.passengersInfo.remove(cancelId);
			}
			else if(express.wlList.contains(p.passengerId)) {
				Integer cancelId=p.passengerId;
				express.wlList.remove(cancelId);
				express.passengersInfo.remove(cancelId);
			}
		}
		ticketList.remove(cancelTicketNo);
		
	}
	
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
	
	public static void passengerFromTicket(Train express,Integer PassengerId) {
		Passenger p=express.passengersInfo.get(PassengerId);
		if(ticketList.size()>0) {
		for(Ticket ticket:ticketList.values()) {
			for(Passenger passenger:ticket.passengers) {
				if(passenger==p) {
					ticket.passengers.remove(p);
					break;
				}
			}
		}
		}
		
	}
	
}
