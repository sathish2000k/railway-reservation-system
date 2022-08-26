package ticketBooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Train {
	
	int racListSeats=100;
	int waitingList=100;
	int totalSeats=150;
	
	ArrayList<Integer> bookedTicketList =new ArrayList<Integer>();
 	HashMap<Integer,Passenger> passengersInfo = new HashMap<Integer,Passenger>();
	LinkedList<Integer> racList = new LinkedList<Integer>();
	LinkedList<Integer> wlList = new LinkedList<Integer>();

	ArrayList<Integer> availableSeat = new ArrayList<Integer>();
	
	

	Train() {
		for(int seatNumber=1;seatNumber<=totalSeats;seatNumber++) {
			availableSeat.add(seatNumber);
		}
	}
	
}	
	
