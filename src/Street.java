import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Street {
    public static final int LENGTH = 3000;
    private List<Station> stations;
    private List<Bus> buses;
    private List<Passenger> passengers;

    public Street() {
    }

    public void initBuses() throws InterruptedException {
        System.out.println("Start buses...");
        for (Bus bus : buses) {
            TimeUnit.SECONDS.sleep(3);
            bus.start();
        }
    }

    public void initPassengers() {
        System.out.println("start passengers...");
        for (Passenger passenger : passengers) {
            for (Station station : stations) {
                if (passenger.getStartStation() == station.getId()) {
                    station.getWaiters().add(passenger);
                }
            }
            passenger.start();
            System.out.println(passenger);
        }
    }

    public void checkPassengers() {
        passengers.removeIf(Passenger::isFinish);
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
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
}
