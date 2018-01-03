package iiitb.ess201a7.r16035;

import iiitb.ess201a7.a7base.*;


import java.util.ArrayList;

public class Fleet16035 extends Fleet{

    ArrayList<Car16035> cars;

    public Fleet16035(String col){
        super(16035, col);
        cars = new ArrayList<Car16035>();
    }

    // creates a new car (consistent with its derived type) and adds it to its list of cars
    public void addCar(int speed){
        Car16035.id = Integer.parseInt(Integer.toString(this.getId()) + Integer.toString(cars.size() + 1));
        Car16035 car = new Car16035(speed);
        cars.add(car);
    }

    public Car findNearestCar(Location loc){
        Car16035 nearestCar = null;
        int min = 1000;
        for(int i = 0; i < cars.size(); i++){
            if(cars.get(i).getStatus() == 1){
                min = cars.get(i).distSqrd(loc);
            }
        }
        for(int i = 0; i < cars.size(); i++){
            if(cars.get(i).distSqrd(loc) <= min && cars.get(i).getStatus() == 1){
                min = cars.get(i).distSqrd(loc);
                nearestCar = cars.get(i);
            }
        }
        return nearestCar;
    }

    // v3 - added
    public ArrayList<Car16035> getCars(){
        return cars;
    }

}
