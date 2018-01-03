package iiitb.ess201a7.r16005;
import java.util.ArrayList;
import iiitb.ess201a7.a7base.*;

public class Fleet16005 extends Fleet {
	private int temp=0;
	private ArrayList<Car> cars = new ArrayList<>();
	public Fleet16005(String colour) {
		super(16005,colour);
	}

	@Override
	public void addCar(int speed) {
		Car tempCar = new Car16005(Integer.parseInt(16005 + "" + temp++),speed);
		cars.add(tempCar);
	}

	@Override
	public Car findNearestCar(Location loc) {
		double min = Double.POSITIVE_INFINITY;
		Car reqdCar = null;
		for (Car car: cars) {
			if(car.getStatus() == 1){
				double dist = Math.sqrt(car.distSqrd(loc));
				if(dist < min){ 
					min=dist;
					reqdCar=car;
				}
			}
		}
		return reqdCar;
	}

	@Override
	public ArrayList<? extends Car> getCars(){
		return cars;
	}
}
