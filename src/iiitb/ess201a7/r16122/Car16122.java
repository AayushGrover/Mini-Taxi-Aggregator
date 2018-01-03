package iiitb.ess201a7.r16122;
import java.lang.*;
import iiitb.ess201a7.a7base.*;

class Car16122 extends Car{
	/* ******** some changes here ******** */
	//when we create a new car, we automatically set the status as idle(1).
	private int status=1;
	private Location lxy;
	private Trip currenttrip;

	/* ******** some changes here ******** */
	//we are gonna have int fid as a parameter here for passing the car id. this will be provided in the fleet class.
	public Car16122(int fid, int speed){
		super(fid,speed);
		/* ******** some changes here ******** */
		//we set the default location of the car to (0,0) when we create a new car.
		lxy = new Location(0,0);
	}

	@Override
	public void setLocation(Location l){
		lxy = l;
	}

	@Override
	public Trip getTrip(){
		return currenttrip;
	}

	@Override
	public int distSqrd(Location loc){
		/* ******** some changes here ******** */
		// apparently dissqrd is the square of the distance between two points.
		// it doesnt matter but we better change it or else we'll have conflicts when we use this class with other people's code.
		int dist = 1; dist*=Math.pow(loc.getX()-lxy.getX(),2) + Math.pow(loc.getY()-lxy.getY(),2);
		return dist;
	}

	@Override
	public Location getLocation(){
		return lxy;
	}

	@Override
	public void setStatus(int s){
		this.status = s;
	}

	@Override
	public int getStatus(){
		return status;
	}

	@Override
	public void assignTrip(Trip trip){
		currenttrip = trip;
		/* ******** some changes here ******** */
		//when we assign a trip, we change the car status automatically to 2.
		status=2;
	}

	@Override
	public Location getStart(){
		return currenttrip.getStart();
	}

	@Override
	public Location getDest(){
		return currenttrip.getDest();
	}
 /* ******** some changes here ******** */
 /* lot of changes in the updateLocation method.
 	1) first thing to notice is that maxSpeed is in m/s and the time deltaT is in millisecs so we conflict with units. so we divide maxSpeed*deltaT with 1000.
	2) another thing is that apparently atan() function can return angles from 0 to pi (not greater than that) so we can't use the concept of slopes. What we do is use the concept of similar triangles. Its hard typing the explanation here but maybe if you go through the codde you'll understand. Ping me if you want to! (because of this, lot of changes to the code)
	3) one other thing we missed is that we wrote the code with only the car going from the start point to destination but we didn't think of the car going for the pickup (from its Location to the booking location.) so we use if-else conditions for checking car status. Another thing is, if suppose the distance it travels in deltaT time, is greater than the actual distance between the car and the startpoint of trip or endpoint, then the updateLocation method will set the wrong locations. so we have if-else conditions for that too.
	*/
	@Override
	public void updateLocation(double deltaT){
		int xdist = lxy.getX(); int ydist = lxy.getY();
		// if the car is idle (status=1), just come out of the loop with return (no need to update the location)
		if(getStatus()==1){
			return;
		}
		//if status is 2, it has been booked, so it has to go to the pickup point which is the current location to the startpoint of the trip.
		else if(getStatus()==2){
			// this is to make sure that the hop made by the car (the distance it travels in deltaT time is less than the actual distance left)
			if(maxSpeed*deltaT<Math.sqrt(distSqrd(currenttrip.getStart()))){
				xdist+=(currenttrip.getStart().getX()-lxy.getX())*maxSpeed*(deltaT)/Math.sqrt(distSqrd(currenttrip.getStart()));
				ydist+=(currenttrip.getStart().getY()-lxy.getY())*maxSpeed*(deltaT)/Math.sqrt(distSqrd(currenttrip.getStart()));
				lxy.set(xdist,ydist);
			}
			else{
				//this is when the hop is greater than the actual distance. we blindly set the location to the start location.
				lxy.set(currenttrip.getStart().getX(), currenttrip.getStart().getY());
				// as the car has reached the pickup point, the trip will start, so the status is changed to 3.
				status=3;
			}

		}
		//if status is 3, it is on trip,  so it has to go from the startpoint of the trip to the destination.
		else if(getStatus()==3){
				// this is to make sure that the hop made by the car (the distance it travels in deltaT time is less than the actual distance left)
			if(maxSpeed*(deltaT)<Math.sqrt(distSqrd(currenttrip.getDest()))){
				xdist+=(currenttrip.getDest().getX()-lxy.getX())*maxSpeed*(deltaT)/Math.sqrt(distSqrd(currenttrip.getDest()));
				ydist+=(currenttrip.getDest().getY()-lxy.getY())*maxSpeed*(deltaT)/Math.sqrt(distSqrd(currenttrip.getDest()));
				lxy.set(xdist,ydist);
			}
			else{
				//this is when the hop is greater than the actual distance. we blindly set the location to the destination location.
				lxy.set(currenttrip.getDest().getX(), currenttrip.getDest().getY());
				// as the car has finished the trip, we set the status to idle.
				status=1;
			}
		}
	}
}
