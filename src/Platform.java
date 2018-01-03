import java.util.ArrayList;

import iiitb.ess201a7.a7base.*;

public class Platform {
	private ArrayList<Fleet> fleets = new ArrayList<>();

	public Platform() {
		
	}
	
	public void addFleet(Fleet f) {	
		fleets.add(f);
	}
	
	// for a request defined as a Trip, find the best car by checking each of its fleets
	// and assigns the car to this trip
	public Car assignCar(Trip trip) {
		double min = Double.POSITIVE_INFINITY;
		Car reqdCar = null;
		for (Fleet fleet : fleets) {
			Car c = fleet.findNearestCar(trip.getStart());	
			if(c!=null) {
				double dist = Math.sqrt(c.distSqrd(trip.getStart()));
				if(dist < min){
					min = dist;
					reqdCar = c;
				}
			}
		}
		if(reqdCar!=null) reqdCar.assignTrip(trip);
		return reqdCar;
	}
	
	// returns list of all cars (in all the fleets) managed by this platform 
	public ArrayList<Car> findCars() {
		ArrayList<Car> cars = new ArrayList<Car>();
		for (Fleet fleet : fleets) {
			for (Car car: fleet.getCars()) {
				cars.add(car);
			}
		}
		return cars;
	}
}
