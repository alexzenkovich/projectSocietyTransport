import java.util.List;

public class Passenger extends Thread {
    private int idPas;
    private int startStation;
    private int finishStation;
    private boolean isFinish;
    private Street street;

    public Passenger(int idPas, int startStation, int finishStation, Street street) {
        this.idPas = idPas;
        this.startStation = startStation;
        this.finishStation = finishStation;
        this.street = street;
    }

    public void run() {
        while (!isFinish()) {
            if (isFinish) {
                System.out.printf("Пассажир№ %d добрался до своей остановки№ %d\n", idPas, finishStation);
            }

        }
    }

    public int getIdPas() {
        return idPas;
    }

    public void setIdPas(int id) {
        this.idPas = id;
    }

    public int getStartStation() {
        return startStation;
    }

    public void setStartStation(int startStation) {
        this.startStation = startStation;
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
        isFinish = finish;
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
                ", startStation=" + startStation +
                ", finishStation=" + finishStation +
                ", isFinish=" + isFinish +
                '}';
    }
}
