import java.util.LinkedList;
import java.util.Queue;

public class Station {
    private int id;
    private int position;
    private boolean isBusy;
    private Queue<Passenger> waiters;
    private Street street;

    public Station(int id, int position, Street street) {
        this.id = id;
        this.position = position;
        this.street = street;
        waiters = new LinkedList<>();
    }

    public synchronized void sendPassenger(Bus bus) {
        int count = 0;
        if (waiters.size() > 0) {
            Passenger passenger;
            while (bus.getListPas().size() < bus.getCapacityBus() || !waiters.isEmpty()) {
                passenger = waiters.poll();
                if (passenger != null) {
                    bus.getListPas().add(passenger.getFinishStation());
                    System.out.printf("Пассажир№%d сел в автобус№%d\n", passenger.getId() - 11, bus.getIdBus());
                    System.out.println("on the station: " + waiters);
                    System.out.println("in the bus: " + bus.getListPas());
                    count++;
                } else {
                    break;
                }
            }
            if (count>0) {
                System.out.printf("автобус№%d взял %d пассажиров на остановке№%d\n", bus.getIdBus(), count, id);
            }
        }
        isBusy = false;
        bus.setMoving(true);
    }

    public synchronized void takePassenger(Bus bus) {
        int count = 0;
        if (bus.getListPas().size() > 0) {
            for (int i = 0; i < bus.getListPas().size(); i++) {
                for (Passenger passenger : street.getPassengers()) {
                    if (bus.getListPas().get(i) == passenger.getFinishStation()) {
                        passenger.setStartStation(id);
                    }
                }
                if (bus.getListPas().get(i) == id) {
                    bus.getListPas().remove(bus.getListPas().get(i));
                    System.out.println("in the bus: " + bus.getListPas());
                    count++;
                }
            }
            if (count>0) {
                System.out.printf("автобус№%d высадил %d пассажиров на остановке№%d\n", bus.getIdBus(), count, id);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        if (busy) isBusy = true;
        else isBusy = false;
    }

    public Queue<Passenger> getWaiters() {
        return waiters;
    }

    public void setWaiters(Queue<Passenger> waiters) {
        this.waiters = waiters;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", position=" + position +
                ", isBusy=" + isBusy +
                ", waiters=" + waiters +
                ", street=" + street +
                '}';
    }
}
