package iiitb.ess201a7.r16052;

import iiitb.ess201a7.a7base.*;

class Car16052 extends Car {

	private Location myLocation;
	private int myStatus;
	private Trip myTrip;

	private static int turnx,turny;
	private static double myDeltaT;
	static
	{
		turnx = 0;//this is to ensure the car moves around the actual line
		turny = 0;
	}

	private double getDistance(Location l1,Location l2){
		int y = l2.getY() - l1.getY();
		int x = l2.getX() - l1.getX();

		return Math.sqrt((y*y) + (x*x));
	}

	private boolean reachedPoint(Location l)//also used in update status,but only after updateLocation
	{
		//it might never touch the end point-depends on deltaT
		if(getDistance(l,myLocation) <= this.getSpeed()*myDeltaT)//if in proximit,mark reached
		{
			myLocation = l;//move myself to that point
			return true;
		}
		else
			return false;
	}//this is to check if came within the end point

	private void updateStatus()
	{
		if(myStatus == Booked)
		{
			if(reachedPoint(myTrip.getStart()))
				setStatus(OnTrip);//using function since then all possible changes can happen in one place
		}
		else if(myStatus == OnTrip)
		{
			if(reachedPoint(myTrip.getDest()))
				setStatus(Idle);
		}
	}

	public Car16052(int fid,int speed) {
		super(fid,speed);

		myStatus = Idle;
		myLocation = new Location(0,0);//default location until its set
		myTrip = null;//since its not on a trip
	}

	@Override
	public void setLocation(Location l) {
		myLocation = l;
	}

	@Override
	public Location getLocation() {
		return myLocation;
	}

	@Override
	public void setStatus(int s) {
		myStatus = s;
	}

	@Override
	public int getStatus() {
		return myStatus;
	}

	@Override
	public void assignTrip(Trip trip) {
		myTrip = trip;
		myStatus = Booked;
	}

	@Override
	public Trip getTrip() {
		return myTrip;
	}

	@Override
	public Location getStart() {
		if(myStatus == Idle)
			return null;
		else
			return myTrip.getStart();
	}

	@Override
	public Location getDest() {
		if(myStatus == Idle)
			return null;
		else
			return myTrip.getDest();
	}

	@Override
	public int distSqrd(Location loc){
		int x = loc.getX() - myLocation.getX();
		int y = loc.getY() - myLocation.getY();

		return (x*x) + (y*y);
	}

	@Override
	public void updateLocation(double deltaT) {//this is new location after time deltaT
		Location s,e;
		double a,b,h,cos,sin;
		myDeltaT = deltaT;

		if(myStatus == Idle)
			return;
		else if(myStatus == Booked)//moving towards start location of trip
		{
			s = myLocation;  //now car is moving towards the start of the trip
			e = myTrip.getStart();
		}
		else//moving towards end location of trip
		{
			s = myTrip.getStart();
			e = myTrip.getDest();
		}

		a = e.getY() - s.getY();
		b = e.getX() - s.getX();
		h = getDistance(s,e);

		cos = b/h;
		sin = a/h;

		int x,y;
		double val;

		val = (getSpeed())*deltaT*cos;
		if(Math.abs(val) < 1 && val != 0)//this is to ensure movement happens even if amount to move is less than 1
		{
			//else location won't be updated
			val = turnx;//alternating between 0 and 1
			turnx = (turnx + 1)%2;//this is to update x every alternate turn(to avoid over-movement)
		}
		x = (int)(myLocation.getX() + val);

		val = (getSpeed())*deltaT*sin;
		if(Math.abs(val) < 1 && val != 0)//same as for x
		{
			//else location won't be updated
			val = turny;
			turny = (turny + 1)%2;
		}
		y = (int)(myLocation.getY() + val);

		// System.out.println("b = "+b+"\ncos ="+cos+"\nval = "+(getSpeed())*deltaT*cos);

		myLocation.set(x,y);

		updateStatus();
	}

}
