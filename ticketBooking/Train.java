package ticketBooking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Train {
	int racListSeats=1;
	int waitingList=1;
	int totalSeats=3;
	
	ArrayList<String> stations =new ArrayList<String>(Arrays.asList("Madurai","Dindigul","Trichy","Chennai"));
	
	ArrayList<Passenger> bookedTicketList =new ArrayList<>();
	LinkedList<Passenger> racList = new LinkedList<>();
	LinkedList<Passenger> wlList = new LinkedList<>();

	ArrayList<Integer> availableSeat = new ArrayList<Integer>();
	
	HashMap<Integer,Ticket> stationAvailableSeatInfo = new HashMap<>(); ///ticket
	
	
	Train() {
		for(int seatNumber=1;seatNumber<=totalSeats;seatNumber++) {
			availableSeat.add(seatNumber);
		}
	}
	
}	
	
