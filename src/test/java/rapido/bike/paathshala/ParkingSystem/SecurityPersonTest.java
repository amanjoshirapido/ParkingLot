package rapido.bike.paathshala.ParkingSystem;

import org.mockito.Mockito;
import org.testng.annotations.Test;

public class SecurityPersonTest {

    @Test
    public void shouldNotifySecurityPersonWhenSlotIsFull() {
        int totalSlots = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots);
        SecurityPerson securityPerson= Mockito.spy(new SecurityPerson("aman"));
        parkingLot.addLotObserver(securityPerson);
        Vehicle vehicle = new Vehicle("UP 16 AB ");

        parkingLot.carParking(vehicle);

        Mockito.verify(securityPerson,Mockito.times(1)).notifyForFullLot();
    }

}