package com.bridgelabz.service;

import com.bridgelabz.entity.Car;
import com.bridgelabz.entity.SecurityPersonal;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class ParkingService {

    private static final int CHARGE_PER_HOUR = 60;
    private final int PARKING_LOT_CAPACITY = 5;
    private SecurityPersonal securityPersonal;
    private boolean isParkingLotFull = false;
    ArrayList<Car> carArrayList;
    PriorityQueue<Integer> freeSpace ;

    public ParkingService(SecurityPersonal securityPersonal){
        carArrayList = new ArrayList<>();
        this.securityPersonal = securityPersonal;
        freeSpace = new PriorityQueue<>();
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

    public int getInvoice(String carNumber) {
        Car car = this.findCar(carNumber);
        if(car == null){
            return -1;
        }
        Duration duration = Duration.between(car.getArrivalTime(),LocalTime.now());
        return (int)(duration.toHours()*CHARGE_PER_HOUR) + (duration.toMinutesPart()%60 >0 ?CHARGE_PER_HOUR:0);
    }

    public int totalNumberOfCarParked() {
        return carArrayList.size();
    }

    public int nearestSlotForParking() {
        Integer peek = freeSpace.peek();
        if(peek == null){
            return 10000009;
        }
        return peek;
    }

    public int totalFreeSpace() {
        return PARKING_LOT_CAPACITY - carArrayList.size();
    }
}
