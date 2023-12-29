package com.bridgelabz.service;

import com.bridgelabz.entity.Car;
import com.bridgelabz.entity.SecurityPersonal;

import java.util.*;

public class ParkingService {

    private final int PARKING_LOT_CAPACITY = 5;
    private SecurityPersonal securityPersonal;
    private boolean isParkingLotFull = false;
    ArrayList<Car> carArrayList;
    Queue<Integer> freeSpace ;

    public ParkingService(SecurityPersonal securityPersonal){
        carArrayList = new ArrayList<>();
        this.securityPersonal = securityPersonal;
        freeSpace = new LinkedList<>();
        for(int i=0;i<PARKING_LOT_CAPACITY;i++){
            freeSpace.add(i+1);
        }
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

    public int parkCarReturnSlot(Car car) {
        if(isParkingLotFull || freeSpace.isEmpty()){
            return -1;
        }
        int val = freeSpace.remove();
        car.setParkingSlot(val);
        carArrayList.add(car);
        if(carArrayList.size() == PARKING_LOT_CAPACITY){
            isParkingLotFull = true;
            securityPersonal.updateMessage(isParkingLotFull);
        }
        return val;
    }

    public Car findCar(String carNumber) {
        return carArrayList.stream()
                .filter(car -> Objects.equals(carNumber, car.getCarNumber()))
                .findFirst()
                .orElse(null);
    }
}
