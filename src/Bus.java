import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Bus extends Thread {
    private int idBus;
    private int capacityBus;
    private int speed;
    private int position;
    private boolean isMoving;
    private boolean isMovingBack;
    private int numberPeopleInBus;
    private List<Integer> listPas = new LinkedList<>();
    private Street street;
    ReentrantLock locker;

    public Bus(int idBus, int capacityBus, int speed, Street street, ReentrantLock locker) {
        this.idBus = idBus;
        this.capacityBus = capacityBus;
        this.speed = speed;
        this.street = street;
        this.locker = locker;
    }

    public void run() {
        isMoving = true;
        while (true) {
            if (isMoving) {
                move();
                checkStation(street.getStations());
            }
 //           System.out.printf("автобус№%d на позиции№%d\n", idBus, position);
        }
    }

    public void move() {
        if (!isMovingBack()) {
            position += speed;
            if (position == Street.LENGTH) isMovingBack = true;
        } else {
            position -= speed;
            if (position == 0) isMovingBack = false;
        }
    }

    public void checkStation(List<Station> stations) {
        for (Station station : stations) {
            locker.lock();
            if (position == station.getPosition() && !station.isBusy()) {
                isMoving = false;
                station.setBusy(true);
                station.takePassenger(this);
                station.sendPassenger(this);
            }
            locker.unlock();
        }
    }

    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    public int getCapacityBus() {
        return capacityBus;
    }

    public void setCapacityBus(int capacityBus) {
        this.capacityBus = capacityBus;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public void setMovingBack(boolean movingBack) {
        isMovingBack = movingBack;
    }

    public int getNumberPeopleInBus() {
        return numberPeopleInBus;
    }

    public void setNumberPeopleInBus(int numberPeopleInBus) {
        this.numberPeopleInBus = numberPeopleInBus;
    }

    public List<Integer> getListPas() {
        return listPas;
    }

    public void setListPas(List<Integer> listPas) {
        this.listPas = listPas;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "idBus=" + idBus +
                ", capacityBus=" + capacityBus +
                ", speed=" + speed +
                ", position=" + position +
                ", isMoving=" + isMoving +
                ", isMovingBack=" + isMovingBack +
                ", numberPeopleInBus=" + numberPeopleInBus +
                '}';
    }
}
