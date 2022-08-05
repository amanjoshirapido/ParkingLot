package rapido.bike.paathshala.ParkingSystem;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ParkingLotTest {


    @Test
    public void shouldAllowParkingWhenParkingNotFull() {
        int totalSlots = 1;

        ParkingLot parkingLot = new ParkingLot(totalSlots);
        Vehicle vehicle = new Vehicle("DL AB 1237");
        String parkStatus = parkingLot.carParking(vehicle);

        assertEquals("Car Parked!", parkStatus);
    }

    @Test
    public void shouldNotAllowParkingWhenSlotIsFull() {
        int totalSlots = 1;

        ParkingLot parkingLot = new ParkingLot(totalSlots);
        Vehicle vehicle1 = new Vehicle("DL AB 1237");
        parkingLot.carParking(vehicle1);
        Vehicle vehicle2 = new Vehicle("DL AB 1234");
        String parkStatus = parkingLot.carParking(vehicle2);

        assertEquals("Cannot Park !", parkStatus);
    }

    @Test
    public void shouldAllowUnparkIfCarIsParked() {
        int totalSlots = 6;

        ParkingLot parkingLot = new ParkingLot(totalSlots);
        Vehicle vehicle = new Vehicle("UP 16 AB 6638");
        parkingLot.carParking(vehicle);
        String unParkStatus = parkingLot.carUnparking(vehicle);

        assertEquals("Unparked", unParkStatus);
    }

    @Test
    public void shouldNotAllowUnparkIfCarIsNotAlreadyParked() {
        int totalSlots = 6;

        ParkingLot parkingLot = new ParkingLot(totalSlots);
        Vehicle vehicle = new Vehicle("UP 16 AB 6638");
        String unParkStatus = parkingLot.carUnparking(vehicle);

        assertEquals("Cannot unpark a vehicle which is not already parked", unParkStatus);

    }

    @Test
    public void shouldNotifyParkingOwnerWhenSlotIsFull() {
        int totalSlots = 1;

        ParkingLot parkingLot = new ParkingLot(totalSlots);
        ParkingOwner parkingOwner=new ParkingOwner("ojasvi");
        parkingLot.addLotObserver(parkingOwner);
        Vehicle vehicle = new Vehicle("DL AB 723");
        parkingLot.carParking(vehicle);

        assertEquals(true,parkingOwner.isLotFull);

    }

    @Test
    public void shouldNotifySecurityPersonWhenSlotIsFull() {
        int totalSlots = 1;

        ParkingLot parkingLot = new ParkingLot(totalSlots);
        SecurityPerson securityPerson=new SecurityPerson("aman");
        parkingLot.addLotObserver(securityPerson);
        Vehicle vehicle = new Vehicle("DL AB 723");
        parkingLot.carParking(vehicle);

        assertEquals(true,securityPerson.isLotFull);

    }



    @Test
    public void shouldNotifyOwnerWhenParkingAvailableAgain(){
        int totalSlots = 1;

        ParkingLot parkingLot = new ParkingLot(totalSlots);
        ParkingOwner parkingOwner = new ParkingOwner("Shashwat");
        parkingLot.addLotObserver(parkingOwner);
        Vehicle vehicle = new Vehicle("DL AB 123");
        parkingLot.carParking(vehicle);
        parkingLot.carUnparking(vehicle);

        assertEquals(false, parkingOwner.isLotFull);
    }


}



