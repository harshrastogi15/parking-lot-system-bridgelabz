package com.bridgelabz.entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class Car {
    private int parkingSlot;
    private String carNumber;
    private int parkingLotNo;
    private LocalTime arrivalTime;
    public String color;
    public String companyName;
    public String parkingAttendantName;


    public Car(){}
    public Car(String carNumber){
        this.carNumber = carNumber;
        this.arrivalTime = LocalTime.now();
    }

    public Car(String carNumber,LocalTime time){
        this.carNumber = carNumber;
        this.arrivalTime = time;
    }

    public Car(String carNumber,String companyName,String color){
        this(carNumber);
        this.companyName = companyName;
        this.color = color;
    }

    public Car(String carNumber,String companyName,String color, String parkingAttendantName){
        this(carNumber,companyName,color);
        this.parkingAttendantName = parkingAttendantName;
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

    public int getParkingLotNo() {
        return parkingLotNo;
    }

    public void setParkingLotNo(int parkingLotNo) {
        this.parkingLotNo = parkingLotNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return parkingSlot == car.parkingSlot && parkingLotNo == car.parkingLotNo && Objects.equals(carNumber, car.carNumber) && Objects.equals(color, car.color) && Objects.equals(companyName, car.companyName);
    }

}
