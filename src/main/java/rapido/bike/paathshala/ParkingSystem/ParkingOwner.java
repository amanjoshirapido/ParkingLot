package rapido.bike.paathshala.ParkingSystem;

public class ParkingOwner {
    private final String ownerName;



    public ParkingOwner(String ownerName) {
        this.ownerName = ownerName;
    }

    public String checkForfullSlot(ParkingSystem parkingSystem) {
        return parkingSystem.checkForfullSlot();
    }

}