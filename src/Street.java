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
        System.out.println("Start passengers...");
        for (Passenger passenger : passengers) {
            passenger.start();
//            System.out.println(passenger);
        }
    }

    public synchronized boolean checkAndSetFinishPassenger(int idPassenger, int idStation) {
        for (Station station : stations) {
            Passenger passenger;
            Iterator<Passenger> iterator = passengers.iterator();
            while (iterator.hasNext()) {
                passenger = iterator.next();
                if (passenger.getIdPas() == idPassenger && station.getId() == idStation &&
                        passenger.getFinishStation()==idStation) {
                    passenger.setFinish(true);
                    passenger.setCurrentStation(station.getId());
//                    System.out.printf("pass-r#%d finish state: %b \n", passenger.getIdPas(), passenger.isFinish());
                    System.out.printf("Passenger# %d got out at the bus stop# %d\n",
                            passenger.getIdPas(), passenger.getFinishStation());
/*                    try{
                        TimeUnit.MILLISECONDS.sleep(10);
                    }catch(InterruptedException e){}*/
                    iterator.remove();
                    passenger.interrupt();
                    System.out.println(passenger.getIdPas() + " " + passenger.getState());
                    printList(passengers);
                    return true;
                }
            }
        }
        return false;
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
