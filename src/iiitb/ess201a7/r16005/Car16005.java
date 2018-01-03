package iiitb.ess201a7.r16005;

import iiitb.ess201a7.a7base.*;
import java.util.Random;

class Car16005 extends Car {
	private Location myLocation;
	private int status;
	private Trip myTrip;

	public Car16005(int fid, int speed) {
		super(fid,speed);
		status=Idle;
		Random r = new Random();
		//myLocation=new Location(r.nextInt(1001),r.nextInt(1001));
		myLocation=new Location(240,60);
	}

	@Override
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
		myLocation = l;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return myLocation;
	}

	@Override
	public void setStatus(int s) {
		// TODO Auto-generated method stub
		status=s;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public void assignTrip(Trip trip) {
		// TODO Auto-generated method stub
		if(status==Idle) {
			setStatus(Booked);
			myTrip=trip;
		}
	}

	@Override
	public Trip getTrip()
	{
		return myTrip;
	}

	@Override
	public Location getStart() {
		// TODO Auto-generated method stub
		if(myTrip!=null)
			return myTrip.getStart();
		return null;
	}

	@Override
	public Location getDest() {
		// TODO Auto-generated method stub
		if(myTrip!=null)
			return myTrip.getDest();
		return null;
	}

	@Override
	public int distSqrd(Location loc)
	{
		int delX = myLocation.getX()-loc.getX();
		int delY = myLocation.getY()-loc.getY();
		return (delX*delX) + (delY*delY);
	}

	@Override
	public void updateLocation(double deltaT) {
		// TODO Auto-generated method stub
		int x=myLocation.getX();
		int y=myLocation.getY();
		if(status==Idle){
			return;
		}

		else if (status==Booked) {
			double d=Math.sqrt(distSqrd(myTrip.getStart()));
			if(maxSpeed*deltaT >= d){
				setStatus(OnTrip);
				x=myTrip.getStart().getX();
				y=myTrip.getStart().getY();
			}
			else {
				x+=((myTrip.getStart().getX()-x)*maxSpeed*deltaT)/d;
				y+=((myTrip.getStart().getY()-y)*maxSpeed*deltaT)/d;
			}
		}

		else if(status==OnTrip){
			double d=Math.sqrt(distSqrd(myTrip.getDest()));
			if(maxSpeed*deltaT >= d){
				setStatus(Idle);
				x=myTrip.getDest().getX();
				y=myTrip.getDest().getY();
			}
			else {
				x+=(((myTrip.getDest().getX()-x)*maxSpeed*deltaT)/d);
				y+=(((myTrip.getDest().getY()-y)*maxSpeed*deltaT)/d);
			}
		}

		myLocation.set(x,y);
	}
}