package iiitb.ess201a7.r16061;

import iiitb.ess201a7.a7base.*;
import java.lang.*;

class Car16061 extends Car
{
	
	public Car16061(int id,int speed)
	{
		super(id,speed);
	}

	Location location;
	Location start;
	Location end;
	//Location previous;
	int status=1;
	@Override
	public int distSqrd(Location loc)
	{
		int x=loc.getX();
		int y=loc.getY();
		int answer=(int)(Math.pow(getLocation().getX()-x,2)+Math.pow(getLocation().getY()-y,2));
		return answer;
	}

	@Override
	public void setLocation(Location l)
	{
		location=l;
	}

	@Override
	public Location getLocation()
	{
		return location;
	}

	@Override
	public void setStatus(int s)
	{
		status=s;
	}

	@Override
	public int getStatus()
	{
		return status;
	}

	@Override
	public void assignTrip(Trip trip)
	{
		start=trip.getStart();
		end=trip.getDest();
		setStatus(2);
	}
	@Override
	public Trip getTrip()
	{
		Trip t=new Trip(getStart(),getDest());
		return t;
	}
	@Override
	public Location getStart()
	{
		return start;
	}

	@Override
	public Location getDest()
	{
		return end;
	}

	@Override
	public void updateLocation(double deltaT)
	{
		int prevy=location.getY();
        int prevx=location.getX();
        if(this.getStatus()==2)
        {
            Location l=getLocation();
            int a=l.getX();
            int b=l.getY();
            Location l1=getStart();
            int c=l1.getX();
            int d=l1.getY();
            double  x=(getSpeed()*deltaT);
            double dist=Math.sqrt(distSqrd(l1));
            double cos=(c-a)/dist;
            double sin=(d-b)/dist;
            int e = (int)(x*cos);
            int r= (int)(x*sin);
            l.set(a+e,b+r);
            if(prevy<l1.getY() && l.getY()>=l1.getY() && prevx<l1.getX() && l.getX()>=l1.getX())
            {
                status=3;
            }
            else if(prevy>l1.getY() && l.getY()<=l1.getY() && prevx>l1.getX() && l.getX()<=l1.getX())
            {
              status=3;
            }
            else if(prevy<l1.getY() && l.getY()>=l1.getY() && prevx>l1.getX() && l.getX()<=l1.getX())
            {
                status=3;
            }
            else if(prevy>l1.getY() && l.getY()<=l1.getY() && prevx<l1.getX() && l.getX()>=l1.getX())
            {
                status=3;
            }
            setLocation(l);
            
        }
        else if(this.getStatus()==3)
        {
            Location l=getLocation();
            int a=l.getX();
            int b=l.getY();
            Location l1=getDest();
            int c=l1.getX();
            int d=l1.getY();
            double  x=(getSpeed()*deltaT);
            double dist=Math.sqrt(distSqrd(l1));
            double cos=(c-a)/dist;
            double sin=(d-b)/dist;
            int e = (int)(x*cos);
            int r= (int)(x*sin);
            l.set(a+e,b+r);
            if(prevy<l1.getY() && l.getY()>=l1.getY() && prevx<l1.getX() && l.getX()>=l1.getX())
            {
                status=1;
            }
            else if(prevy>l1.getY() && l.getY()<=l1.getY() && prevx>l1.getX() && l.getX()<=l1.getX())
            {
              status=1;
            }
            else if(prevy<l1.getY() && l.getY()>=l1.getY() && prevx>l1.getX() && l.getX()<=l1.getX())
            {
                status=1;
            }
            else if(prevy>l1.getY() && l.getY()<=l1.getY() && prevx<l1.getX() && l.getX()>=l1.getX())
            {
                status=1;
            }
            setLocation(l);
            
        }
    }
}
