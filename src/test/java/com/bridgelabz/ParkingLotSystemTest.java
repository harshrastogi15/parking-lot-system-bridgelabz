package com.bridgelabz;

import com.bridgelabz.entity.Car;
import com.bridgelabz.service.ParkingService;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotSystemTest {

    // UC-1
    @Test
    public void givenCar_ShouldAbleToParkCar(){
        Car car = new Car();
        ParkingService parkingService = new ParkingService();
        boolean isAbleToPark = parkingService.parkCar(car);
        Assert.assertTrue(isAbleToPark);
    }


    // UC-2
    @Test
    public void unParkCar_ShouldVerify(){
        Car car = new Car();
        ParkingService parkingService = new ParkingService();
        parkingService.parkCar(car);
        boolean isAbleToUnPark = parkingService.unParkCar(car);
        Assert.assertTrue(isAbleToUnPark);
    }

}
