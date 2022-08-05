package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ParkingSystem {

    private int totalNumberOfSlots;
    private int noOfCarsAlreadyParked = 0;
    private HashSet<Vehicle> ParkedCarSet = new HashSet<Vehicle>();
    private List<ParkingLotObserver> lotObserverList=new ArrayList<>();

    public ParkingSystem(int totalNumberOfSlots) {
        this.totalNumberOfSlots = totalNumberOfSlots;
    }

    public String carParking(Vehicle vehicle) {
        if (isSlotAvailable() && !ParkedCarSet.contains(vehicle)) {
            ParkedCarSet.add(vehicle);
            noOfCarsAlreadyParked += 1;
            if(isSlotFull()){
                notifyAllObserverForFullParking();
            }
            return "Car Parked!";
        } else {
            return "Cannot Park !";
        }
    }

    private void notifyAllObserverForFullParking() {
        for(ParkingLotObserver parkingLotObserver:lotObserverList){
            parkingLotObserver.notifyForFullLot();
        }
    }

    private void notifyAllObserverForParkingAvailableAgain() {
        for(ParkingLotObserver parkingLotObserver:lotObserverList){
            parkingLotObserver.notifyForAvailableSlot();
        }
    }

    private boolean isSlotAvailable() {
        return totalNumberOfSlots - noOfCarsAlreadyParked > 0;
    }

    public String carUnparking(Vehicle vehicle) {
        if (ParkedCarSet.contains(vehicle)) {
            ParkedCarSet.remove(vehicle);
            noOfCarsAlreadyParked -= 1;
            if (noOfCarsAlreadyParked == totalNumberOfSlots - 1){
                notifyAllObserverForParkingAvailableAgain();
            }
            return "Unparked";
        } else {
            return "Cannot unpark a vehicle which is not already parked";
        }


    }
    public boolean isSlotFull(){
        if(totalNumberOfSlots==noOfCarsAlreadyParked){
            return true;
        }
        else{
            return false;
        }
    }


    public void addLotObserver(ParkingLotObserver parkingLotObserver) {
        lotObserverList.add(parkingLotObserver);
    }
}
