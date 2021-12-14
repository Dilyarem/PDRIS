package ru.mipt.garage;
import java.util.Objects;

public class Car{
    private final int carId;
    private final String brand;
    private final String modelName;
    private final int maxVelocity;
    private final int power;
    private final int ownerId;

    public Car(int carId, String brand, String modelName, int maxVelocity, int power, int ownerId) {
        this.carId = carId;
        this.brand = brand;
        this.modelName = modelName;
        this.maxVelocity = maxVelocity;
        this.power = power;
        this.ownerId = ownerId;
    }

    public int getPower() {
        return power;
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public String getBrand() {
        return brand;
    }

    public int getCarId() {
        return carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId);
    }
}

