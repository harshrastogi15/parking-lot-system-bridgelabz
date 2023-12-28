package com.bridgelabz;

import com.bridgelabz.entity.Car;
import com.bridgelabz.entity.SecurityPersonal;
import com.bridgelabz.service.ParkingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {

    ParkingService parkingService;
    SecurityPersonal securityPersonal;
    @Before
    public void setup(){
        securityPersonal = new SecurityPersonal();
        parkingService = new ParkingService(securityPersonal);
    }

    // UC-1
    @Test
    public void givenCar_ShouldAbleToParkCar(){
        Car car = new Car();
        boolean isAbleToPark = parkingService.parkCar(car);
        Assert.assertTrue(isAbleToPark);
    }


    // UC-2
    @Test
    public void unParkCar_ShouldVerify(){
        Car car = new Car();
        parkingService.parkCar(car);
        boolean isAbleToUnPark = parkingService.unParkCar(car);
        Assert.assertTrue(isAbleToUnPark);
    }


    // UC-3
    @Test
    public void onRequest_ShouldReturnParkingLotIsFullOrEmpty(){
        boolean isLotFull = parkingService.checkParkingLotIsFull();
        Assert.assertFalse(isLotFull);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        parkingService.parkCar(car1);
        parkingService.parkCar(car2);
        parkingService.parkCar(car3);
        parkingService.parkCar(car4);
        parkingService.parkCar(car5);
        isLotFull = parkingService.checkParkingLotIsFull();
        Assert.assertTrue(isLotFull);
    }

    // UC-4
    @Test
    public void OnSecurityPersonalRequest_ShouldReturnMessage(){
        String message = securityPersonal.message;
        Assert.assertEquals("Parking Lot is not full",message);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        parkingService.parkCar(car1);
        parkingService.parkCar(car2);
        parkingService.parkCar(car3);
        parkingService.parkCar(car4);
        parkingService.parkCar(car5);
        message = securityPersonal.message;
        Assert.assertEquals("Parking Lot is full",message);
    }

}
