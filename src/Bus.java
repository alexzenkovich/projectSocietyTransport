import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Bus extends Thread {
    private final int idBus;
    private final int capacityBus;
    private final int speed;
    private int position;
    private int numberOfPeople;
    private boolean isMoving;
    private boolean isMovingBack;
    private List<Integer> listPas = new LinkedList<>();
    private Street street;
    private ReentrantLock locker;

    public Bus(int idBus, int capacityBus, int speed, Street street, ReentrantLock locker) {
        this.idBus = idBus;
        this.capacityBus = capacityBus;
        this.speed = speed;
        this.street = street;
        this.locker = locker;
        this.numberOfPeople = 0;
    }

    public void run() {
        isMoving = true;
        while (street.getPassengers().size() > 0) {
            if (isMoving()) {
                move();
                try {
                    System.out.printf("Bus# %d on position# %d\n", idBus, position);
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkStation(street.getStations());
            }
        }
        System.out.printf("Bus№ %d finished route. The bus transported %d people\n", idBus, numberOfPeople);
    }

    private void move() {
        locker.lock();
        if (!isMovingBack()) {
            position += speed;
            if (position == Street.LENGTH) isMovingBack = true;
        } else {
            position -= speed;
            if (position == 0) isMovingBack = false;
        }
        locker.unlock();
    }

    private void checkStation(List<Station> stations) {
        for (Station station : stations) {
            if (position == station.getPosition() && !station.isBusy()) {
                isMoving = false;
                locker.lock();
                station.setBusy(true);
                System.out.printf("Bus# %d arrived to the bus stop# %d \n", idBus, station.getId());
                station.takePassenger(this);
                station.sendPassenger(this);
                System.out.printf("Bus# %d departured from the bus stop# %d\n", idBus, station.getId());
                locker.unlock();
            }
        }
    }

    public int getIdBus() {
        return idBus;
    }

    public int getCapacityBus() {
        return capacityBus;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isMovingBack() {
        return isMovingBack;
    }

    public List<Integer> getListPas() {
        return listPas;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "idBus=" + idBus +
                ", capacityBus=" + capacityBus +
                ", listPas=" + listPas +
                '}';
    }
}
