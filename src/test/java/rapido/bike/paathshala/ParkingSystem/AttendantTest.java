package rapido.bike.paathshala.ParkingSystem;

import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AttendantTest {
    void fillTheParkingLotFully(ParkingLot parkingLot) {

            for(int index = 0; index<parkingLot.getTotalNumberOfSlots();index++)
            {
                Vehicle vehicle = new Vehicle("1237"+index);
                parkingLot.carParking(vehicle);
            }

    }

    @Test
    public void shouldAllowParkingInFirstLotIfAllLotsAreVacant() {

        Attendant attendant = new Attendant(5,5);

        String assignedLot = attendant.VehicleAllocatingToLot();

        assertEquals("Slot 1", assignedLot);
    }

    @Test
    public void shouldAllowParkingInVacantLotIfOtherLotsAreFilled() {

        Attendant attendant = new Attendant(5,5);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        fillTheParkingLotFully(parkingLots.get(0));

        String assignedLot = attendant.VehicleAllocatingToLot();

        assertEquals("Slot 2", assignedLot);

    }

    @Test
    public void shouldNotAllowParkingIfAllSlotsAreFilled() {

        Attendant attendant = new Attendant(2,2);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        fillTheParkingLotFully(parkingLots.get(0));
        fillTheParkingLotFully(parkingLots.get(1));


        String unassignedLot = attendant.VehicleAllocatingToLot();

        assertEquals("Slot not available", unassignedLot);

    }

    @Test
    public void shouldAllowToUnParkIfVehicleIsPresentInAnyParkingLot() {

        Attendant attendant = new Attendant(2,2);
        String vehicle = "12370";
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        fillTheParkingLotFully(parkingLots.get(0));

        String slotToUnParkFrom = attendant.VehicleDeAllocatingFromLot(vehicle);

        assertEquals("You can un park from slot 1", slotToUnParkFrom);


    }

    @Test
    public void shouldNotAllowToUnParkIfVehicleIsNotPresentInAnyParkingLot() {

        Attendant attendant = new Attendant(2,2);
        String vehicle = "12375";
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        fillTheParkingLotFully(parkingLots.get(0));

        String slotToUnParkFrom = attendant.VehicleDeAllocatingFromLot(vehicle);

        assertEquals("Vehicle Not Present in any lot", slotToUnParkFrom);


    }

    @Test
    public void shouldParkEvenlyIfThreeCarsAreInQueueAndAllLotsAreEmpty()
    {
        Attendant attendant = new Attendant(3,3);

        attendant.parkVehicleEvenly(new Vehicle("UP"+Math.random()));
        attendant.parkVehicleEvenly(new Vehicle("UP"+Math.random()));
        attendant.parkVehicleEvenly(new Vehicle("UP"+Math.random()));
        int[] expectedLotAllocation = new int []{1,1,1};

        Assert.assertArrayEquals(expectedLotAllocation, attendant.numberOfCarsParkedInEveryLot);
    }
    @Test
    public void shouldParkEvenlyIfTwoCarsAreAlreadyParked(){
        Attendant attendant = new Attendant(3,3);

        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLots.get(0).carParking(new Vehicle("XYZ"));
        parkingLots.get(1).carParking(new Vehicle("XYZ"));
        int[] expectedLotAllocation = new int []{1,1,1};

        attendant.parkVehicleEvenly(new Vehicle("UP"));

        Assert.assertArrayEquals(expectedLotAllocation, attendant.numberOfCarsParkedInEveryLot);
    }
    @Test
    public void shouldParkEvenlyIfFourCarsAreAlreadyParked(){
        Attendant attendant = new Attendant(3,3);

        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLots.get(0).carParking(new Vehicle("XYZ"));
        parkingLots.get(1).carParking(new Vehicle("XYZ"));
        parkingLots.get(2).carParking(new Vehicle("XYZ"));
        parkingLots.get(1).carParking(new Vehicle("ABC"));
        int[] expectedLotAllocation = new int []{2,2,1};


        attendant.parkVehicleEvenly(new Vehicle("UP16"));


        Assert.assertArrayEquals(expectedLotAllocation, attendant.numberOfCarsParkedInEveryLot);
    }

    @Test
    public void shouldParkEvenlyIfTwoCarsAreAlreadyPresentInASingleLotAndRestAreVacant()
    {
        Attendant attendant = new Attendant(3,3);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLots.get(0).carParking(new Vehicle("XYZ"+Math.random()));
        parkingLots.get(0).carParking(new Vehicle("XYZ"+Math.random()));
        int[] expectedLotAllocation = new int []{2,1,1};

        attendant.parkVehicleEvenly(new Vehicle("UP16"+Math.random()));
        attendant.parkVehicleEvenly(new Vehicle("UP16"+Math.random()));

        Assert.assertArrayEquals(expectedLotAllocation, attendant.numberOfCarsParkedInEveryLot);


    }



}
