package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;

public class FillUntilFullStrategy implements ParkingStrategy{
    private ArrayList<ParkingLot> parkingLots ;

    public FillUntilFullStrategy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public int parkTheCar(Vehicle vehicle) {

        for(int index=0;index<parkingLots.size();index++)
        {
            if(parkingLots.get(index).isSlotAvailable()==true)
            {
                parkingLots.get(index).carParking(vehicle);
                return index+1;
            }
        }
        return -1;
    }
}
