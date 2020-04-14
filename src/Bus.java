import java.util.*;

public class Bus extends Thread {
    private int idBus;
    private int capacityBus;
    private int speed;
    private int position;
    private boolean isMoving;
    private boolean isMovingBack;
    private Map<Integer, Integer> listPass = new HashMap<>();
    private Station station;
    private Street street;

    public Bus(int idBus, int capacityBus, int speed) {
        this.idBus = idBus;
        this.capacityBus = capacityBus;
        this.speed = speed;
    }

    public void run() {
        isMoving = true;
        while (true) {
            if (isMoving) {
                move();
                System.out.printf("автобус№%d на позиции№%d\n", idBus, position);
                for (Station station : getStreet().getStations()) {
                    if (position == station.getPosition()) {
                        isMoving = false;
                        station.setBusy(true);
                        station.takePassenger(this);
                        station.sendPassenger(this);
                    }
                }
            }
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

    public Map<Integer, Integer> getListPass() {
        return listPass;
    }

    public void setListPass(Map<Integer, Integer> listPass) {
        this.listPass = listPass;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
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
                ", listPass=" + listPass +
                ", station=" + station +
                ", street=" + street +
                '}';
    }
}
