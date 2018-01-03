package iiitb.ess201a7.r16052;

import iiitb.ess201a7.a7base.*;

import java.util.*;//this was added later...will it affect combination

public class Fleet16052 extends Fleet {

	private ArrayList<Car16052> myCars;
	private int carCount;
	private String id_string;

	private double getDistance(Location l1,Location l2){
		int y = l2.getY() - l1.getY();
		int x = l2.getX() - l1.getX();

		return Math.sqrt((y*y) + (x*x));
	}


	public Fleet16052(String colour) {
		super(16052,colour);//since expect only one instance of this class
		carCount = 0;
		id_string = new String("16052");//assuming only one instance of every fleet

		myCars = new ArrayList<Car16052>();
	}

	@Override
	public void addCar(int speed) {
		carCount++;
		String serialNum = Integer.toString(carCount);

		int newId = Integer.parseInt(id_string + serialNum);

		myCars.add(new Car16052(newId,speed));//made a new car and added it
	}

	@Override
	public Car findNearestCar(Location loc) {//finds nearest car in the fleet
		if(myCars.size() == 0)
			return null;//returns null if no car is around
		else
		{
			int free_car;//this is the index of the first free car
			for(free_car = 0 ; free_car < myCars.size() ; free_car++)
			{
				if(myCars.get(free_car).getStatus() == Car.Idle)
					break;
			}//checking only Idle cars
			if(free_car == myCars.size())
				return null;//this happens only if all cars in this fleet are busy
			else
			{
				//if come here,there is at least one free car
				double min = getDistance(loc,myCars.get(free_car).getLocation());
				int min_index = free_car;
				double temp;
				for(int i = free_car+1 ; i < myCars.size() ; i++)
				{
					if(myCars.get(i).getStatus() == Car.Idle)
					{
						temp = getDistance(loc,myCars.get(i).getLocation());
						if(temp < min)
						{
							min = temp;
							min_index = i;
						}
					}
				}
				// System.out.println("min_index = " + min_index);
				return myCars.get(min_index);
			}
		}
	}

	@Override
	public ArrayList<? extends Car> getCars(){
		return myCars;
	}
}
