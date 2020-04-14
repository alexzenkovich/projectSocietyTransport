public class Passenger extends Thread{
    private int idPas;
    private int startStation;
    private int finishStation;
    private boolean isFinish;
    private Station station;
    private Street street;

    public Passenger(int idPas, int startStation, int finishStation){
        this.idPas = idPas;
        this.startStation = startStation;
        this.finishStation = finishStation;
    }

    public void run(){
        if (isFinish()){
            System.out.printf("Пассажир№ %d добрался до своей остановки№ %d", idPas, finishStation);
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
        return "Passenger{" +
                "idPas=" + idPas +
                ", startStation=" + startStation +
                ", finishStation=" + finishStation +
                ", isFinish=" + isFinish +
                ", station=" + station +
                ", street=" + street +
                '}';
    }
}
