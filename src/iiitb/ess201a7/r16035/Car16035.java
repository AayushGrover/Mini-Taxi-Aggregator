package iiitb.ess201a7.r16035;

import iiitb.ess201a7.a7base.Car;
import iiitb.ess201a7.a7base.Location;
import iiitb.ess201a7.a7base.Trip;

public class Car16035 extends Car{

    Location current;
    int status;
    Trip trip;
    public static int id = 0;

    public Car16035(int speed){
        super(id, speed);
        status = 1;
    }

    public int distSqrd(Location loc) {
        return (int)(Math.pow(current.getX() - loc.getX(), 2) + Math.pow(current.getY() - loc.getY(), 2));
    }

    public void setLocation(Location l){
        this.current = l;
    }

    public Location getLocation(){
        return this.current;
    }

    public void setStatus(int s){
        this.status = s;
    }

    public int getStatus(){
        return this.status;
    }

    public void assignTrip(Trip trip){
        this.trip = trip;
        this.setStatus(2);
    }

    public Trip getTrip(){
        return this.trip;
    }

    // return location of start of trip (where customer is to be picked up)
    // null if idle
    public Location getStart(){
        return this.trip.getStart();
    }

    // return location of end of trip (where customer is to be dropped off)
    // null if idle

    public Location getDest(){
        return this.trip.getDest();
    }

    // v3 - parameter is now double instead of int
    public void updateLocation(double deltaT){
        Location l1, l2;
        double X, Y, d, D, cosT, sinT;
        if(this.getStatus() == 0){
            this.setStatus(1);
            return;
        }

        if(this.getStatus() == 2) {
            l1 = new Location(this.getLocation().getX(), this.getLocation().getY());
            l2 = new Location(this.getLocation().getX(), this.getLocation().getY());
            d = maxSpeed * deltaT;
            X = this.trip.getStart().getX() - this.current.getX();
            Y = this.trip.getStart().getY() - this.current.getY();
            D = Math.sqrt(Math.pow(X, 2) + Math.sqrt(Math.pow(Y, 2)));

            cosT = X/D;
            sinT = Y/D;

            l2.set((int) (l2.getX() + d * cosT), (int) (l2.getY() + d * sinT));
            this.setLocation(l2);

            if((l1.getX() <= trip.getStart().getX() && trip.getStart().getX() <= l2.getX())
                    || (l1.getX() >= trip.getStart().getX() && trip.getStart().getX() >= l2.getX())){
                this.setLocation(trip.getStart());
                this.setStatus(3);
            }
            return;
        }

        else if(this.getStatus() == 3){
            l1 = new Location(this.getLocation().getX(), this.getLocation().getY());
            l2 = new Location(this.getLocation().getX(), this.getLocation().getY());
            d = maxSpeed * deltaT;
            X = this.trip.getDest().getX() - this.current.getX();
            Y = this.trip.getDest().getY() - this.current.getY();
            D = Math.sqrt(Math.pow(X, 2) + Math.sqrt(Math.pow(Y, 2)));

            cosT = X/D;
            sinT = Y/D;

            l2.set((int) (l2.getX() + d * cosT), (int) (l2.getY() + d * sinT));
            this.setLocation(l2);

            if((l1.getX() <= trip.getDest().getX() && trip.getDest().getX() <= l2.getX())
                    || (l1.getX() >= trip.getDest().getX() && trip.getDest().getX() >= l2.getX())){
                this.setLocation(trip.getDest());
                this.setStatus(0);
            }
        }
    }
}
