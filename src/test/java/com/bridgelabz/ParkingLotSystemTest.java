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

    // UC-6
    @Test
    public void givenCar_AttendantParkCar_ShouldReturnParkingLotNumber(){
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        int parkingLotNumber1 = parkingService.parkCarReturnSlot(car1);
        Assert.assertEquals(1,parkingLotNumber1);
        int parkingLotNumber2 = parkingService.parkCarReturnSlot(car2);
        Assert.assertEquals(2,parkingLotNumber2);
        int parkingLotNumber3 = parkingService.parkCarReturnSlot(car3);
        Assert.assertEquals(3,parkingLotNumber3);
        int parkingLotNumber4 = parkingService.parkCarReturnSlot(car4);
        Assert.assertEquals(4,parkingLotNumber4);
        int parkingLotNumber5 = parkingService.parkCarReturnSlot(car5);
        Assert.assertEquals(5,parkingLotNumber5);

    }

    // UC-7
    @Test
    public void givenCarNumber_findCarSlot_ShouldReturnParkingSlotNumber(){
        Car car1 = new Car("UP152wde");
        Car car2 = new Car("UP152wd2");
        Car car3 = new Car("UP152w12");
        Car car4 = new Car("UP151234");
        Car car5 = new Car("BH123edc");
        parkingService.parkCarReturnSlot(car1);
        parkingService.parkCarReturnSlot(car2);
        parkingService.parkCarReturnSlot(car3);
        parkingService.parkCarReturnSlot(car4);
        parkingService.parkCarReturnSlot(car5);

        Car getCar = parkingService.findCar("UP152w12");
        Assert.assertEquals(3,getCar.getParkingSlot());
    }
}
