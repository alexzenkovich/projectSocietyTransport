import java.util.Iterator;
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

    public void sendPassenger(Bus bus) {
        int count = 0;
        if (!waiters.isEmpty() && bus.getListPas().size() < bus.getCapacityBus()) {
            Passenger passenger;
            while (bus.getListPas().size() < bus.getCapacityBus() || !waiters.isEmpty()) {
                passenger = waiters.poll();
                if (passenger != null) {
                    bus.getListPas().add(passenger.getIdPas());
                    System.out.printf("Пассажир№%d сел в автобус№%d на остановке#%d осталось %d человек, в автобусе %d\n",
                            passenger.getIdPas(), bus.getIdBus(), id, waiters.size(), bus.getListPas().size());
                    count++;
                    if (bus.getListPas().size() == bus.getCapacityBus()) break;
                } else {
                    break;
                }
            }
            if (count > 0) {
//                System.out.printf("автобус№%d взял %d пассажиров на остановке№%d\n", bus.getIdBus(), count, id);
            } else {
//                System.out.printf("на остановке№%d никого не было автобус№%d едет дальше\n", id, bus.getIdBus());
            }
        }
        isBusy = false;
        bus.setMoving(true);
    }

    public void takePassenger(Bus bus) {
        int count = 0;
        if (bus.getListPas().size() > 0) {
            Iterator<Integer> iterator = bus.getListPas().iterator();
            while (iterator.hasNext()) {
                int temp = iterator.next();
                if (temp == id) {
                    street.checkAndSetFinishPassenger(temp, id);
                    iterator.remove();
                    count++;
                }
            }
            if (count > 0) {
                System.out.printf("автобус№%d высадил %d пассажиров на остановке№%d\n", bus.getIdBus(), count, id);
                System.out.println("after station: " + id + " in the bus: " + bus.getListPas());
            }
        }
    }

    public int getId() {
        return id;
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
