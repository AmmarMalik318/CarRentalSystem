package model;

public class Car {
    private String carId;
    private String model;
    private boolean available;

    public Car(String carId, String model) {
        this.carId = carId;
        this.model = model;
        this.available = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rentCar() {
        available = false;
    }

    public void returnCar() {
        available = true;
    }

    @Override
    public String toString() {
        return carId + " - " + model +
                (available ? " (Available)" : " (Rented)");
    }
}