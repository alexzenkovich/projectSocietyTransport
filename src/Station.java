import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Station {
    private int id;
    private int position;
    private boolean isBusy;
    private List<Passenger> waiters;
    private Bus bus;
    private Street street;

    public Station(int id, int position) {
        this.id = id;
        this.position = position;
        waiters = new ArrayList<>();
    }

    public synchronized void sendPassenger(Bus bus) {
        int start = bus.getListPass().size();
        int count = 0;
        while (isBusy) {
            try{
                wait();
            }catch (InterruptedException e){ }
        }
        if (bus.getListPass().size() <= bus.getCapacityBus() || !waiters.isEmpty()) {
            bus.getListPass().put((int) waiters.get(0).getId(), waiters.get(0).getFinishStation());
            System.out.printf("Пассажир№%d сел в автобус№%d", waiters.get(0).getId(), bus.getIdBus());
            waiters.remove(waiters.get(0));
            count++;
        }
        int result = start + count;
        System.out.printf("автобус№%d взял %d пассажиров на остановке№%d\n", bus.getIdBus(), result, id);
        isBusy = false;
        bus.setMoving(true);
        notify();
    }

    public synchronized void takePassenger(Bus bus) {
        int start = bus.getListPass().size();
        int count = 0;
        while (isBusy) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        if (bus.getListPass().size() > 0) {
            for (Map.Entry<Integer, Integer> pair : bus.getListPass().entrySet()) {
                for (Passenger passenger : street.getPassengers()){
                    if (passenger.getFinishStation()==pair.getValue()){
                        passenger.setFinish(true);
                    }
                }
                if (pair.getValue() == id) {
                    bus.getListPass().remove(pair.getKey(), pair.getValue());
                    count++;
                }
            }
        }
        int result = start - count;
        System.out.printf("автобус№%d высадил %d пассажиров на остановке№%d\n", bus.getIdBus(), result, id);
        notify();
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

    public List<Passenger> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<Passenger> waiters) {
        this.waiters = waiters;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", position=" + position +
                ", isBusy=" + isBusy +
                ", waiters=" + waiters +
                ", bus=" + bus +
                ", street=" + street +
                '}';
    }
}
