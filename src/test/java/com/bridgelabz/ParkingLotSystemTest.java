package com.bridgelabz;

import com.bridgelabz.entity.Car;
import com.bridgelabz.entity.SecurityPersonal;
import com.bridgelabz.service.ParkingLotsService;
import com.bridgelabz.service.ParkingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ParkingLotSystemTest {

    ParkingService parkingService;
    SecurityPersonal securityPersonal;
    ParkingLotsService parkingLotsService;
    @Before
    public void setup(){
        securityPersonal = new SecurityPersonal();
        parkingService = new ParkingService(securityPersonal);
        parkingLotsService = new ParkingLotsService(securityPersonal);
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


    // UC-8
    @Test
    public void givenCarNumber_ShouldReturnBillToPay(){
        Car car1 = new Car("BH123edc", LocalTime.now().minusHours(2).minusMinutes(30));
        parkingService.parkCarReturnSlot(car1);
        int invoice = parkingService.getInvoice("BH123edc");
        Assert.assertEquals(180,invoice);

    }

    // UC-9
    @Test
    public void givenCar_ParkingAttendant_ShouldParkCarEvenly(){
        Car car1 = new Car("UP152wde");
        Car car2 = new Car("UP152wd2");
        Car car3 = new Car("UP152w12");
        Car car4 = new Car("UP151234");
        Car car5 = new Car("BH123edc");
        int lotNumber1 = parkingLotsService.parkCarEvenlyInLot(car1);
        Assert.assertEquals(1,lotNumber1);
        int lotNumber2 = parkingLotsService.parkCarEvenlyInLot(car2);
        Assert.assertEquals(2,lotNumber2);
        int lotNumber3 = parkingLotsService.parkCarEvenlyInLot(car3);
        Assert.assertEquals(3,lotNumber3);
        int lotNumber4 = parkingLotsService.parkCarEvenlyInLot(car4);
        Assert.assertEquals(1,lotNumber4);
        int lotNumber5 = parkingLotsService.parkCarEvenlyInLot(car5);
        Assert.assertEquals(2,lotNumber5);
    }


    // UC-10
    @Test
    public void givenHandicapCar_ParkingAttendant_ShouldParkCarNearestPlace(){
        Car car1 = new Car("UP152wde");
        Car car2 = new Car("UP152wd2");
        Car car3 = new Car("UP152w12");
        Car car4 = new Car("UP151234");
        Car car5 = new Car("BH123edc");
        int lotNumber1 = parkingLotsService.parkCarNearestForHandicapInLot(car1);
        Assert.assertEquals(1,lotNumber1);
        int lotNumber2 = parkingLotsService.parkCarNearestForHandicapInLot(car2);
        Assert.assertEquals(2,lotNumber2);
        int lotNumber3 = parkingLotsService.parkCarNearestForHandicapInLot(car3);
        Assert.assertEquals(3,lotNumber3);
        int lotNumber4 = parkingLotsService.parkCarNearestForHandicapInLot(car4);
        Assert.assertEquals(1,lotNumber4);
        int lotNumber5 = parkingLotsService.parkCarNearestForHandicapInLot(car5);
        Assert.assertEquals(2,lotNumber5);
    }

    // UC-11
    @Test
    public void givenLargeCar_ParkingAttendant_ShouldParkCarInLotWithHighestSpace(){
        Car car1 = new Car("UP152wde");
        Car car2 = new Car("UP152wd2");
        Car car3 = new Car("UP152w12");
        Car car4 = new Car("UP151234");
        Car car5 = new Car("BH123edc");
        int lotNumber1 = parkingLotsService.parkingLargeCar(car1);
        Assert.assertEquals(1,lotNumber1);
        int lotNumber2 = parkingLotsService.parkingLargeCar(car2);
        Assert.assertEquals(2,lotNumber2);
        int lotNumber3 = parkingLotsService.parkingLargeCar(car3);
        Assert.assertEquals(3,lotNumber3);
        int lotNumber4 = parkingLotsService.parkingLargeCar(car4);
        Assert.assertEquals(1,lotNumber4);
        int lotNumber5 = parkingLotsService.parkingLargeCar(car5);
        Assert.assertEquals(2,lotNumber5);
    }


    // UC-12
    @Test
    public void onRequestPolice_shouldReturnLocationOfWhiteCars(){
        Car car1 = new Car("UP152wde","BMW","White");
        Car car2 = new Car("UP152wd2","TOYOTA","White");
        Car car3 = new Car("UP152w12","BMW","Blue");
        Car car4 = new Car("UP151234","TOYOTA","Blue");
        Car car5 = new Car("BH123edc","TATA","Grey");
        parkingLotsService.parkCarEvenlyInLot(car1);
        parkingLotsService.parkCarEvenlyInLot(car2);
        parkingLotsService.parkCarEvenlyInLot(car3);
        parkingLotsService.parkCarEvenlyInLot(car4);
        parkingLotsService.parkCarEvenlyInLot(car5);

        ArrayList<Car> carList= parkingLotsService.searchCarByColor("White");
        ArrayList<Car> expectedCars = new ArrayList<>();
        expectedCars.add(car1);
        expectedCars.add(car2);
        carList.sort(Comparator.comparing(Car::getCarNumber));
        expectedCars.sort(Comparator.comparing(Car::getCarNumber));
        Assert.assertEquals(expectedCars,carList);
    }

}
