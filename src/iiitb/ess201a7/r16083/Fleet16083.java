package iiitb.ess201a7.r16083;
import iiitb.ess201a7.a7base.*;
import java.util.ArrayList;


public class Fleet16083 extends Fleet {

	private ArrayList<Car> carList; //Stores the list of cars in the fleet
	private static int uCarId; //uCarId stores the id that needs to be assinged to the car. It is incremented after each car is added.
	
	public Fleet16083(String colour) {
		super(16083, colour); //16083 is the last 5 digits of my roll no. (IMT2016083)
		this.uCarId = 1; //To append to the end of the fleet id
		this.carList = new ArrayList<>();
	}

	@Override
	public void addCar(int speed) {
		int idOfCar = Integer.parseInt(Integer.toString(getId()) + Integer.toString(uCarId));
		Car newCar = new Car16083(idOfCar, speed);
		this.uCarId++; //increasing the uCarId by 1
		this.carList.add(newCar);
		System.out.println("Added new Car with ID = " + newCar.getId());
		
	}

	@Override
	public Car findNearestCar(Location loc) {
		int minDistSqrd = Integer.MAX_VALUE; //Set int minDistSqrd to the max possible to find the nearest car to the location.
		Car nearestCar = null;

		for(Car car: this.carList){
			if(car.getStatus() == Car.Idle && (car.distSqrd(loc) < minDistSqrd)){
				minDistSqrd = car.distSqrd(loc);
				nearestCar = car;
			}
		}
		return nearestCar; //If the car is null, it is handled in the higher classes.(platform)
	}

	@Override
	public ArrayList<? extends Car> getCars(){
		return this.carList;
	}

	/*public ArrayList<Car> getListOfCars(){
		return this.carList;
	}*/

	private float distBwLocs(Location a, Location b) { //utility function
		return (float)(Math.sqrt((a.getX()-b.getX())*(a.getX()-b.getX()) + (a.getY()-b.getY())*(a.getY()-b.getY())));
	}
}