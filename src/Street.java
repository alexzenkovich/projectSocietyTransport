import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Street {
    public static final int LENGTH = 3000;
    private int interval;
    private List<Station> stations;
    private List<Bus> buses;
    private List<Passenger> passengers;

    public Street(int interval) {
        this.interval = interval;
    }

    public void initBuses() throws InterruptedException {
        System.out.println("Start buses...");
        for (Bus bus : buses) {
            TimeUnit.MILLISECONDS.sleep(interval);
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
 //           System.out.println(passenger);
        }
    }

    public synchronized void checkAndSetFinishPassenger(int idPassenger, int idStation) {
        for (Station station : stations) {
            Passenger passenger;
            Iterator<Passenger> iterator = passengers.iterator();
            while (iterator.hasNext()) {
                passenger = iterator.next();
                if (passenger.getIdPas() == idPassenger && station.getId() == idStation) {
                    System.out.println(passenger.getState());
                    passenger.setFinish(true);
                    System.out.println(passenger.getState());
                    System.out.printf("pass-r#%d finish state: %b \n", passenger.getIdPas(), passenger.isFinish());
                    iterator.remove();
                }
            }
        }
        printList(passengers);
    }

    public synchronized void printList(List<Passenger> list) {
        System.out.println("number of all passangers: " + list.size());
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
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
