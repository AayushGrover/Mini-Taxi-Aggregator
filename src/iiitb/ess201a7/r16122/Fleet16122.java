package iiitb.ess201a7.r16122;
import iiitb.ess201a7.a7base.*;
import java.util.*;
import java.lang.*;

public class Fleet16122 extends Fleet {
	private ArrayList<Car> carlist = new ArrayList<Car>();
	private int count;

	public Fleet16122(String colour){
		super(16122,colour);
	}

	@Override
	public void addCar(int speed){
		Car car = new Car16122(Integer.parseInt("16122"+count),speed);
		carlist.add(car);
		count++;
	}

	@Override
	/* ******** some changes here ******** */
	// I made some changes here. instead of assigning the first distance as the nearestcardistance blindly for comparison and then having count and all, we use Integer.MAX_VALUE for comparison.
	public Car findNearestCar(Location loc){
		int carform; int index = 0; int smallest = Integer.MAX_VALUE;
		for(int i=0; i<carlist.size(); i++){
			carform = carlist.get(i).distSqrd(loc);
				if(carlist.get(i).getStatus()==1){
					if(carform < smallest){
						smallest = carform;
						index = i;
					}
				}
			}
		return carlist.get(index);
	}

	public ArrayList<? extends Car> getCars(){
		return carlist;
	}
}
