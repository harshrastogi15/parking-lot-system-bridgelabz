package com.bridgelabz.service;

import com.bridgelabz.entity.Car;

import java.util.ArrayList;

public class ParkingService {
    ArrayList<Car> carArrayList;

    public ParkingService(){
        carArrayList = new ArrayList<>();
    }

    public boolean parkCar(Car car) {
        carArrayList.add(car);
        return true;
    }

    public boolean unParkCar(Car car) {
        carArrayList.remove(car);
        return true;
    }
}
