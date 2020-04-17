import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Bus extends Thread {
    private final int idBus;
    private final int capacityBus;
    private final int speed;
    private int position;
    private boolean isMoving;
    private boolean isMovingBack;
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
        while (street.getPassengers().size() > 0) {
            if (isMoving()) {
                move();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    checkStation(street.getStations());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //           System.out.printf("автобус№%d на позиции№%d\n", idBus, position);
        }
        System.out.printf("автобус№%d закончил рейс\n", idBus);
    }

    private void move() {
        if (!isMovingBack()) {
            position += speed;
            if (position == Street.LENGTH) isMovingBack = true;
        } else {
            position -= speed;
            if (position == 0) isMovingBack = false;
        }
    }

    private void checkStation(List<Station> stations) throws InterruptedException {
        for (Station station : stations) {
            if (position == station.getPosition() && !station.isBusy()) {
                isMoving = false;
                locker.lock();
                station.setBusy(true);
 //               System.out.printf("автобус№%d приехал на остановку№%d \n", idBus, station.getId());
                station.takePassenger(this);
                station.sendPassenger(this);
//                System.out.printf("автобус№%d уехал с остановки№%d \n", idBus, station.getId());
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

    @Override
    public String toString() {
        return "Bus{" +
                "idBus=" + idBus +
                ", capacityBus=" + capacityBus +
                ", listPas=" + listPas +
                '}';
    }
}
