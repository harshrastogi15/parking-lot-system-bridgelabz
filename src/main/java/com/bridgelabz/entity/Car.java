package com.bridgelabz.entity;

public class Car {
    private int parkingSlot;
    private String carNumber;

    public Car(){}
    public Car(String carNumber){
        this.carNumber = carNumber;
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
}
