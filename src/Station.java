import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Station {
    private final int id;
    private final int position;
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
                    System.out.printf("Passenger# %d got into the bus# %d, his finish bus stop is %d. " +
                                    "On the busstop# %d left %d, in the bus: %d\n",
                            passenger.getIdPas(), bus.getIdBus(), passenger.getFinishStation(), id,
                            waiters.size(), bus.getListPas().size());
                    count++;
                    bus.setNumberOfPeople(bus.getNumberOfPeople() + 1);
                    if (bus.getListPas().size() == bus.getCapacityBus()) break;
                } else {
                    break;
                }
            }
            if (count > 0) {
                System.out.printf("Bus# %d took %d passengers on the bus stop# %d\n", bus.getIdBus(), count, id);
            } else {
                System.out.printf("There was nobody at the bus stop# %d. The bus# %d goes on.\n", id, bus.getIdBus());
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
                if (street.checkAndSetFinishPassenger(temp, id)) {
                    iterator.remove();
                    count++;
                }
            }
            if (count > 0) {
                System.out.printf("The bus# %d dropped off %d passengers at the bus stop# %d.\n",
                        bus.getIdBus(), count, id);
                System.out.println("At the bus stop# " + id + ". In the bus# " + bus.getIdBus() +
                        " " + bus.getListPas());
            }
        }
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Queue<Passenger> getWaiters() {
        return waiters;
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
