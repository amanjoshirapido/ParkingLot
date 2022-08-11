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

        int assignedLot = attendant.VehicleAllocatingToLot();

        assertEquals(1, assignedLot);
    }

    @Test
    public void shouldAllowParkingInVacantLotIfOtherLotsAreFilled() {

        Attendant attendant = new Attendant(5,5);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        fillTheParkingLotFully(parkingLots.get(0));

        int assignedLot = attendant.VehicleAllocatingToLot();

        assertEquals(2, assignedLot);

    }

    @Test
    public void shouldNotAllowParkingIfAllSlotsAreFilled() {

        Attendant attendant = new Attendant(2,2);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        fillTheParkingLotFully(parkingLots.get(0));
        fillTheParkingLotFully(parkingLots.get(1));


        int assignedLot = attendant.VehicleAllocatingToLot();

        assertEquals(-1, assignedLot);

    }

    @Test
    public void shouldAllowToUnParkIfVehicleIsPresentInAnyParkingLot() {

        Attendant attendant = new Attendant(2,2);
        String vehicle = "12370";
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        fillTheParkingLotFully(parkingLots.get(0));

        int slotToUnParkFrom = attendant.VehicleDeAllocatingFromLot(vehicle);

        assertEquals(1, slotToUnParkFrom);


    }

    @Test
    public void shouldNotAllowToUnParkIfVehicleIsNotPresentInAnyParkingLot() {

        Attendant attendant = new Attendant(2,2);
        String vehicle = "12375";
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        fillTheParkingLotFully(parkingLots.get(0));

        int slotToUnParkFrom = attendant.VehicleDeAllocatingFromLot(vehicle);

        assertEquals(-1, slotToUnParkFrom);


    }

    @Test
    public void shouldParkEvenlyIfThreeCarsAreInQueueAndAllLotsAreEmpty()
    {
      ParkingOwner parkingOwner = new ParkingOwner("Aman");
      Attendant attendant = new Attendant(3,3);
      parkingOwner.useParkingStrategy(attendant,new EvenlyCarDistributionStrategy(attendant.getParkingLots()));

      attendant.carPark(new Vehicle("XYZ"));
      attendant.carPark(new Vehicle("abc"));
      int carParkedAtLot = attendant.carPark(new Vehicle("DAS"));

      assertEquals(3,carParkedAtLot);



    }
    @Test
    public void shouldParkEvenlyIfTwoCarsAreAlreadyParked(){
        ParkingOwner parkingOwner = new ParkingOwner("Aman");
        Attendant attendant = new Attendant(3,3);
        parkingOwner.useParkingStrategy(attendant,new EvenlyCarDistributionStrategy(attendant.getParkingLots()));
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLots.get(0).carParking(new Vehicle("XYZ"));
        parkingLots.get(1).carParking(new Vehicle("XYZ"));
        int expectedLotAllocation = 3;

        int carParkedAtLot = attendant.carPark(new Vehicle("UP"));

        Assert.assertEquals(expectedLotAllocation, carParkedAtLot);
    }

    @Test
    public void shouldParkEvenlyIfTwoCarsAreAlreadyPresentInASingleLotAndRestAreVacant()
    {
        ParkingOwner parkingOwner = new ParkingOwner("Aman");
        Attendant attendant = new Attendant(3,3);
        parkingOwner.useParkingStrategy(attendant,new EvenlyCarDistributionStrategy(attendant.getParkingLots()));
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLots.get(0).carParking(new Vehicle("XYZ"+Math.random()));
        parkingLots.get(0).carParking(new Vehicle("XYZ"+Math.random()));

        int expectedLotAllocation = 2;

        int carParkedAtLot = attendant.carPark(new Vehicle("UP16"+Math.random()));

        Assert.assertEquals(expectedLotAllocation, carParkedAtLot);


    }

    @Test
    public void shouldDirectCarsToLotOneUntilItIsFilled()
    {
        ParkingOwner parkingOwner = new ParkingOwner("Aman");
        Attendant attendant = new Attendant(3,3);
        parkingOwner.useParkingStrategy(attendant,new FillUntilFullStrategy(attendant.getParkingLots()));
        int expectedParkedLot = 1;

        attendant.getParkingLots().get(0).carParking(new Vehicle("UP"));
        attendant.getParkingLots().get(0).carParking(new Vehicle("NO"));

        int lotWhereThirdVehicleIsParked = attendant.carPark(new Vehicle("C"));

        assertEquals(expectedParkedLot,lotWhereThirdVehicleIsParked);

    }

    @Test
    public void shouldDirectCarsToLotTwoIfLotOneIsFilled()
    {
        ParkingOwner parkingOwner = new ParkingOwner("Aman");
        Attendant attendant = new Attendant(3,3);
        parkingOwner.useParkingStrategy(attendant,new FillUntilFullStrategy(attendant.getParkingLots()));
        int expectedParkedLot = 2;

        attendant.getParkingLots().get(0).carParking(new Vehicle("UP"));
        attendant.getParkingLots().get(0).carParking(new Vehicle("NO"));
        attendant.getParkingLots().get(0).carParking(new Vehicle("YES"));


        int lotWhereThirdVehicleIsParked = attendant.carPark(new Vehicle("C"));

        assertEquals(expectedParkedLot,lotWhereThirdVehicleIsParked);


    }

    @Test
    public void shouldNotAllowToParkIfAllLotsAreFull()
    {
        ParkingOwner parkingOwner = new ParkingOwner("Aman");
        Attendant attendant = new Attendant(2,2);
        parkingOwner.useParkingStrategy(attendant,new FillUntilFullStrategy(attendant.getParkingLots()));
        fillTheParkingLotFully(attendant.getParkingLots().get(0));
        fillTheParkingLotFully(attendant.getParkingLots().get(1));
        int expectedParkedLot = -1;


        int lotOfFifthVehicleParked = attendant.carPark(new Vehicle("D"));

        assertEquals(expectedParkedLot,lotOfFifthVehicleParked);


    }


}