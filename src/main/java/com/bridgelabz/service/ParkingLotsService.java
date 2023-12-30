package com.bridgelabz.service;

import com.bridgelabz.entity.Car;
import com.bridgelabz.entity.SecurityPersonal;

import java.util.ArrayList;

public class ParkingLotsService {

    private final int TOTAL_PARKING_LOTS = 3;
    ParkingService[] parkingServices;

    public ParkingLotsService(SecurityPersonal securityPersonal){
            parkingServices = new ParkingService[TOTAL_PARKING_LOTS];
            for (int i=0; i<TOTAL_PARKING_LOTS; i++){
                parkingServices[i] = new ParkingService(securityPersonal);
            }
    }

    public ArrayList<Car> searchCarByColor(String color) {
        ArrayList<Car> carList = new ArrayList<>();
        for(int i=0;i<TOTAL_PARKING_LOTS;i++){
            ArrayList<Car> tempCarList = parkingServices[i].searchCarByColor(color);
            if(tempCarList != null)
                carList.addAll(tempCarList);
        }
        return carList;
    }

    public int parkCarEvenlyInLot(Car car) {
        int parkingLot = 1;
        for(int i=0;i<TOTAL_PARKING_LOTS;i++){
            if(parkingServices[i].totalNumberOfCarParked()<parkingServices[parkingLot-1].totalNumberOfCarParked()){
                parkingLot = i+1;
            }
        }
        car.setParkingLotNo(parkingLot);
        parkingServices[parkingLot-1].parkCarReturnSlot(car);
        return parkingLot;
    }

    public int parkCarNearestForHandicapInLot(Car car) {
        int parkingLot = 1;
        for(int i=0;i<TOTAL_PARKING_LOTS;i++){
            if(parkingServices[i].nearestSlotForParking()<parkingServices[parkingLot-1].nearestSlotForParking()){
                parkingLot = i+1;
            }
        }
        parkingServices[parkingLot-1].parkCarReturnSlot(car);
        return parkingLot;
    }

    public int parkingLargeCar(Car car) {
        int parkingLot = 1;
        for(int i=0;i<TOTAL_PARKING_LOTS;i++){
            if(parkingServices[i].totalFreeSpace()>parkingServices[parkingLot-1].totalFreeSpace()){
                parkingLot = i+1;
            }
        }
        parkingServices[parkingLot-1].parkCarReturnSlot(car);
        return parkingLot;
    }
}
