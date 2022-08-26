package ticketBooking;


public class Passenger {
	String name;
	int age;
	int seatNumber;
	String fromStation;
	String toStation;

	Passenger(String name,int age,String fromStation,String toStation){
		this.name=name;
		this.age=age;
		this.fromStation = fromStation;
		this.toStation = toStation;
	}
	
}
