package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Attendant {
    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }
    private ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    public int numberOfCarsParkedInEveryLot[];
    int sizeOfParkingLots;
    int numberOfParkingLots;

    int lastVehicleParkedAt;
    Attendant(int sizeOfParkingLots,int numberOfParkingLots)
    {
       this.sizeOfParkingLots = sizeOfParkingLots;
       this.numberOfParkingLots = numberOfParkingLots;
       this.lastVehicleParkedAt = -1;
       for(int index=0;index<numberOfParkingLots;index++)
       {
           this.parkingLots.add(new ParkingLot(sizeOfParkingLots));
       }
        numberOfCarsParkedInEveryLot = new int[numberOfParkingLots];
       for(int index=0;index<numberOfParkingLots;index++)
       {
           numberOfCarsParkedInEveryLot[index]=0;
       }
    }

    String VehicleAllocatingToLot()
    {
        for(int index=0;index<this.parkingLots.size();index++)
        {
            if(this.parkingLots.get(index).isSlotAvailable())
            {
                this.parkingLots.get(index).carParking(new Vehicle("XYZ"+index));
                numberOfCarsParkedInEveryLot[index]=numberOfCarsParkedInEveryLot[index]+1;
                return "Slot "+(index+1);
            }
        }
        return "Slot not available";
    }

    String VehicleDeAllocatingFromLot(String LicenseNumber)
    {
        Vehicle vehicle = new Vehicle(LicenseNumber);
        for(int index=0;index<parkingLots.size();index++)
        {
            if(parkingLots.get(index).getParkedCarSet().contains(vehicle))
            {
                return "You can un park from slot "+(index+1);
            }
        }
        return "Vehicle Not Present in any lot";
    }

    int findIndexOfMinimumValueInArray(int[] numberOfCarsParkedInEveryLot )
    {
        int minIndex = 0;
        int minValue=Integer.MAX_VALUE;
        for(int index=0;index<numberOfCarsParkedInEveryLot.length;index++)
        {

        if(numberOfCarsParkedInEveryLot[index]<minValue)
        {
            minValue = numberOfCarsParkedInEveryLot[index];
            minIndex=index;
        }

        }
        return minIndex;
    }
    int [] getUpdatedValueOfAvailableleSlotsInParkingLots(int []numberOfCarsParkedInEveryLot)
    {
        for(int index=0;index<numberOfCarsParkedInEveryLot.length;index++)
        {
            numberOfCarsParkedInEveryLot[index]=parkingLots.get(index).noOfCarsAlreadyParked;
        }
        return numberOfCarsParkedInEveryLot;
    }
    void parkVehicleEvenly(Vehicle vehicle)
    {
        getUpdatedValueOfAvailableleSlotsInParkingLots(numberOfCarsParkedInEveryLot);
        int index = findIndexOfMinimumValueInArray(numberOfCarsParkedInEveryLot);
        parkingLots.get(index).carParking(vehicle);
        numberOfCarsParkedInEveryLot[index]++;
    }

}
