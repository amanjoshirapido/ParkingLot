package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;

public class EvenlyCarDistributionStrategy implements ParkingStrategy {
    private ArrayList<ParkingLot> parkingLots ;
    public int numberOfCarsParkedInEveryLot[];


    public EvenlyCarDistributionStrategy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.numberOfCarsParkedInEveryLot = new int[parkingLots.size()];
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
    @Override
    public int parkTheCar(Vehicle vehicle) {
        getUpdatedValueOfAvailableleSlotsInParkingLots(numberOfCarsParkedInEveryLot);
        int index = findIndexOfMinimumValueInArray(numberOfCarsParkedInEveryLot);
        parkingLots.get(index).carParking(vehicle);
        numberOfCarsParkedInEveryLot[index]++;
        return index+1;
    }
}
