package iiitb.ess201a7.r16061;

import iiitb.ess201a7.a7base.*;
import java.util.ArrayList;

public class Fleet16061 extends Fleet 
{
	
	public Fleet16061(String colour) 
	{
		super(16061,colour);
	}

	int i=0;
	ArrayList<Car> list_car=new ArrayList<>();

	@Override
	public void addCar(int speed) 
	{
		i=i+1;
		Car c=new Car16061(160610+i,speed);
		list_car.add(c);
	}

	@Override
	public Car findNearestCar(Location loc) 
	{
		int index=0;
		int i=-1;
		double dist;
		double min_dist=1000000;
		for(Car item:list_car)
		{
			Location l=item.getLocation();
			float x1=l.getX();
			float y1=l.getY();
			float x2=loc.getX();
			float y2=loc.getY();
			dist=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
			i=i+1;
			if(dist<min_dist && item.getStatus()==1)
			{
				min_dist=dist;
				index=i;
			}
		}
		return list_car.get(index);
	}
	
	@Override
	public  ArrayList<? extends Car> getCars()
	{
		return list_car;
	}
}
