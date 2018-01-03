package iiitb.ess201a7.r16083;

import iiitb.ess201a7.a7base.*;

class Car16083 extends Car {

	Location currLocation; //Stores the current lcation of the car
	int status; //Stores the status of the car (Idle, Booked, or OnTrip)
	Trip currTrip; //When a trip is assigned to the car, it is stored in currTrip

	public Car16083(int cid, int speed) {
		super(cid, speed);
		setStatus(this.Idle);
		currLocation = new Location(0, 0); //Setting the currLocation to (0, 0)
	}

	@Override
	public int distSqrd(Location loc) {
		return (currLocation.getX() - loc.getX())*(currLocation.getX() - loc.getX()) + (currLocation.getY() - loc.getY())*(currLocation.getY() - loc.getY());
	}

	@Override
	public void setLocation(Location l) {
		this.currLocation = l;
	}

	@Override
	public Location getLocation() {
		return this.currLocation;
	}

	@Override
	public void setStatus(int s) {
		this.status = s;
		
	}

	@Override
	public int getStatus() {
		return this.status;
	}

	@Override
	public void assignTrip(Trip trip) {
		setStatus(Booked);
		this.currTrip = trip;
	}

	@Override
	public Trip getTrip(){
		return this.currTrip;
	}

	@Override
	public Location getStart() {
		if(this.status == this.Idle)	return null; //If the car is Idle, no trip is assigned, as a result no StartLocation for trip
		return this.currTrip.getStart();
	}

	@Override
	public Location getDest() {
		if(this.status == this.Idle)	return null; //Same as the previous case.
		return this.currTrip.getDest();
	}

	@Override
	public void updateLocation(double deltaT) {
		if(this.status == Idle)	return;

		else if(this.status == Booked){

			if(getSpeed()*deltaT >= distFrom(getStart())){	
				System.out.println("Car has reached the customer");
				this.currLocation.set(getStart().getX(), getStart().getY());
				setStatus(OnTrip);
				return;
			}
			else{
				int xSq = (this.currLocation.getX()-getStart().getX())*(this.currLocation.getX()-getStart().getX());
				int ySq = (this.currLocation.getY()-getStart().getY())*(this.currLocation.getY()-getStart().getY());

				int distanceToDest = (int)Math.sqrt(xSq + ySq);

				int incX = (int)(getSpeed()*deltaT*(getStart().getX()-this.currLocation.getX()))/distanceToDest;
				int incY = (int)(getSpeed()*deltaT*(getStart().getY()-this.currLocation.getY()))/distanceToDest;

				this.currLocation.set(this.currLocation.getX() + incX,
									  this.currLocation.getY() + incY);
			}
		}

		else{
			if(getSpeed()*deltaT >= distFrom(getDest())){
				System.out.println("Car has reached the destination");
				this.currLocation.set(getDest().getX(), getDest().getY());
				setStatus(Idle);
				return;
			}
			else{
				int xSq = (this.currLocation.getX()-getDest().getX())*(this.currLocation.getX()-getDest().getX());
				int ySq = (this.currLocation.getY()-getDest().getY())*(this.currLocation.getY()-getDest().getY());

				int distanceToDest = (int)Math.sqrt(xSq + ySq);

				int incX = (int)((getSpeed()*deltaT*(getDest().getX()-this.currLocation.getX()))/distanceToDest);
				int incY = (int)((getSpeed()*deltaT*(getDest().getY()-this.currLocation.getY()))/distanceToDest);

				this.currLocation.set(this.currLocation.getX() + incX, this.currLocation.getY() + incY);
			}
		}
	}

	private double distFrom(Location a){
		int xSq = (this.currLocation.getX()-a.getX())*(this.currLocation.getX()-a.getX());
		int ySq = (this.currLocation.getY()-a.getY())*(this.currLocation.getY()-a.getY());
		
		return Math.sqrt(xSq + ySq);
	}
}