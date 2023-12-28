package com.bridgelabz.service;

import com.bridgelabz.entity.Car;
import com.bridgelabz.entity.SecurityPersonal;

import java.util.ArrayList;

public class ParkingService {

    private final int PARKING_LOT_CAPACITY = 5;
    private SecurityPersonal securityPersonal;
    private boolean isParkingLotFull = false;
    ArrayList<Car> carArrayList;

    public ParkingService(SecurityPersonal securityPersonal){
        carArrayList = new ArrayList<>();
        this.securityPersonal = securityPersonal;
    }

    public boolean parkCar(Car car) {

        if(isParkingLotFull){
            return false;
        }
        carArrayList.add(car);
        if(carArrayList.size() == PARKING_LOT_CAPACITY){
            isParkingLotFull = true;
            securityPersonal.updateMessage(isParkingLotFull);
        }
        return true;
    }

    public boolean unParkCar(Car car) {
        carArrayList.remove(car);
        isParkingLotFull = false;
        securityPersonal.updateMessage(isParkingLotFull);
        return true;
    }

    public boolean checkParkingLotIsFull() {
        return isParkingLotFull;
    }
}
