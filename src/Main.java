import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberBuses = 1;               //количество автобусов - 5;
        int busCapacity = 5;              //вместимость автобуса - 50;
        int speedBus = 10;                 //скорость автобуса - 60;
        int interval = 2000;               //интервал между автобусами - 1000;
        int numberOfStations = 5;         //количество остановок - 10;
        int numberPassengers = 3;        //общее число пассажиров - 300;
//---------------------------------------------------------------------------------------------------------------------
        Street street = new Street();
        ReentrantLock locker = new ReentrantLock();

        List<Station> stations = new ArrayList<Station>() {            //создаем остановки
            {
                int pos = 0;
                int interval = Street.LENGTH / (numberOfStations);
                for (int i = 0; i < numberOfStations; i++) {
                    add(new Station(i, pos += interval, street));
                }
            }
        };
        List<Bus> buses = new ArrayList<Bus>(){                        //создаем автобусы
            {
                for (int i = 0; i < numberBuses; i++) {
                    add(new Bus(i, busCapacity, speedBus, street, locker));
                }
            }
        };

        List<Passenger> passengers = new ArrayList<Passenger>(){        //создаем пассажиров
            {
                for (int i = 0; i < numberPassengers; i++){
                    add(new Passenger(i, new Random().nextInt(stations.size()), new Random().nextInt(stations.size()), street));
                }
            }
        };
        street.setStations(stations);
        street.setBuses(buses);
        street.setPassengers(passengers);
        for (Station station : street.getStations()){
            System.out.println(station);
        }
        street.initPassengers();
        street.checkPassengers();
        for (Station station : street.getStations()) {
            System.out.println(station.getWaiters());
        }
        street.initBuses();
        while (passengers.size()>0){
            street.checkPassengers();
        }

/*        System.out.println(buses);
        System.out.println(passengers);
        System.out.println(stations);*/
    }
}
