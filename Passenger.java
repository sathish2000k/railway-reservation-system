package ticketBooking;


public class Passenger {
	static int id=0;
	int passengerId;
	String name;
	int age;
	int seatNumber;

	Passenger(String name,int age){
		this.name=name;
		this.age=age;
		this.passengerId=++id;
	}
	
}
