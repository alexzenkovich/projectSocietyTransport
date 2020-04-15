import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Street {
    public static final int LENGTH = 3000;
    private static List<Station> stations;
    private List<Bus> buses;
    private List<Passenger> passengers;

    public Street(List<Bus> buses, List<Passenger> passengers, List<Station> stations) {
        this.buses = buses;
        this.passengers = passengers;
        this.stations = stations;
    }

    public void initBuses() throws InterruptedException {
        initPassengers();
        System.out.println("Start buses...");
        for (Bus bus : buses) {
            TimeUnit.SECONDS.sleep(3);
            bus.start();
        }
    }

    public void initPassengers(){
        System.out.println("start passengers...");
        for (Passenger passenger : passengers){
            passenger.start();
        }
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public static List<Station> getStations() {
        return stations;
    }
}
