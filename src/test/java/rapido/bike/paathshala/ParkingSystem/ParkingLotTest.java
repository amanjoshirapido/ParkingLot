package rapido.bike.paathshala.ParkingSystem;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class ParkingSystemTest {


    @Test
    public void shouldAllowParkingWhenParkingNotFull() {
        int totalSlots = 1;

        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        Vehicle vehicle = new Vehicle("DL AB 1237");
        String parkStatus = parkingSystem.carParking(vehicle);

        assertEquals("Car Parked!", parkStatus);
    }

    @Test
    public void shouldNotAllowParkingWhenSlotIsFull() {
        int totalSlots = 1;

        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        Vehicle vehicle1 = new Vehicle("DL AB 1237");
        parkingSystem.carParking(vehicle1);
        Vehicle vehicle2 = new Vehicle("DL AB 1234");
        String parkStatus = parkingSystem.carParking(vehicle2);

        assertEquals("Cannot Park !", parkStatus);
    }

    @Test
    public void shouldAllowUnparkIfCarIsParked() {
        int totalSlots = 6;

        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        Vehicle vehicle = new Vehicle("UP 16 AB 6638");
        parkingSystem.carParking(vehicle);
        String unParkStatus = parkingSystem.carUnparking(vehicle);

        assertEquals("Unparked", unParkStatus);
    }

    @Test
    public void shouldNotAllowUnparkIfCarIsNotAlreadyParked() {
        int totalSlots = 6;

        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        Vehicle vehicle = new Vehicle("UP 16 AB 6638");
        String unParkStatus = parkingSystem.carUnparking(vehicle);

        assertEquals("Cannot unpark a vehicle which is not already parked", unParkStatus);

    }

    @Test
    public void shouldNotifyParkingOwnerWhenSlotIsFull() {
        int totalSlots = 1;

        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        ParkingOwner parkingOwner=new ParkingOwner("ojasvi");
        parkingSystem.addLotObserver(parkingOwner);
        Vehicle vehicle = new Vehicle("DL AB 723");
        parkingSystem.carParking(vehicle);

        assertEquals(true,parkingOwner.isLotFull);

    }

    @Test
    public void shouldNotifySecurityPersonWhenSlotIsFull() {
        int totalSlots = 1;

        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        SecurityPerson securityPerson=new SecurityPerson("aman");
        parkingSystem.addLotObserver(securityPerson);
        Vehicle vehicle = new Vehicle("DL AB 723");
        parkingSystem.carParking(vehicle);

        assertEquals(true,securityPerson.isLotFull);

    }

    @Test
    public void shouldNotifyOwnerWhenParkingAvailableAgain(){
        int totalSlots = 1;

        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        ParkingOwner parkingOwner = new ParkingOwner("Shashwat");
        parkingSystem.addLotObserver(parkingOwner);
        Vehicle vehicle = new Vehicle("DL AB 123");
        parkingSystem.carParking(vehicle);
        parkingSystem.carUnparking(vehicle);

        assertEquals(false, parkingOwner.isLotFull);
    }

    @Test
    public void shouldAllowFirstLotIfAllLotsAreVacant()
    {
        ParkingSystem[] parkingSystem;
        parkingSystem = new ParkingSystem[5];
        
    }
}



