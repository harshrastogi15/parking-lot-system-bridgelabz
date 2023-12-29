package com.bridgelabz.entity;

import java.time.LocalTime;
import java.util.Date;

public class Car {
    private int parkingSlot;
    private String carNumber;

    private LocalTime arrivalTime;

    public Car(){}
    public Car(String carNumber){
        this.carNumber = carNumber;
        this.arrivalTime = LocalTime.now();
    }

    public Car(String carNumber,LocalTime time){
        this.carNumber = carNumber;
        this.arrivalTime = time;
    }

    public int getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(int parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

}
