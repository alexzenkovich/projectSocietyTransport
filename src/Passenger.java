import java.util.concurrent.locks.ReentrantLock;

public class Passenger extends Thread {
    private int idPas;
    private int currentStation;
    private int finishStation;
    private boolean isFinish;
    private Street street;
    private ReentrantLock locker;

    public Passenger(int idPas, int startStation, int finishStation, Street street, ReentrantLock locker) {
        this.idPas = idPas;
        this.currentStation = startStation;
        this.finishStation = finishStation;
        this.street = street;
        this.locker = locker;
    }

    public void run() {
        locker.lock();
        for (Station station : street.getStations()) {
            if (currentStation == station.getId()) {
                station.getWaiters().add(this);
                System.out.printf("Passenger# %d came to the bus stop# %d\n", idPas, station.getId());
            }
        }
        locker.unlock();
        while (street.getPassengers().contains(this)) {}
        System.out.printf("Passenger# %d came to himself bus stop# %d\n", idPas, finishStation);
    }



    public int getIdPas() {
        return idPas;
    }

    public void setIdPas(int id) {
        this.idPas = id;
    }

    public int getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(int startStation) {
        this.currentStation = startStation;
    }

    public int getFinishStation() {
        return finishStation;
    }

    public void setFinishStation(int finishStation) {
        this.finishStation = finishStation;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        if (finish) isFinish = true;
        isFinish = false;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "idPas=" + idPas +
                ", currentStation=" + currentStation +
                ", finishStation=" + finishStation +
                ", isFinish=" + isFinish +
                '}';
    }
}
