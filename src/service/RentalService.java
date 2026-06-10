package service;

import model.Car;

import java.util.ArrayList;
import java.util.List;

public class RentalService {

    private List<Car> cars = new ArrayList<>();

    public RentalService() {
        cars.add(new Car("C101", "Toyota Corolla"));
        cars.add(new Car("C102", "Honda Civic"));
        cars.add(new Car("C103", "Suzuki Alto"));
    }

    public List<Car> getCars() {
        return cars;
    }

    public void rentCar(String carId) throws Exception {

        for (Car car : cars) {

            if (car.getCarId().equalsIgnoreCase(carId)) {

                if (!car.isAvailable()) {
                    throw new Exception("Car already rented.");
                }

                car.rentCar();
                return;
            }
        }

        throw new Exception("Car ID not found.");
    }

    public void returnCar(String carId) throws Exception {

        for (Car car : cars) {

            if (car.getCarId().equalsIgnoreCase(carId)) {

                if (car.isAvailable()) {
                    throw new Exception("Car is already available.");
                }

                car.returnCar();
                return;
            }
        }

        throw new Exception("Car ID not found.");
    }
}