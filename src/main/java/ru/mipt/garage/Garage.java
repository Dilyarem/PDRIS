package ru.mipt.garage;
import java.util.Set;

public interface Garage {

    Set<Owner> allCarsUniqueOwners();

    /**
     * Complexity should be less than O(n)
     */
    Set<Car> topThreeCarsByMaxVelocity();

    /**
     * Complexity should be O(1)
     */
    Set<Car> allCarsOfBrand(String brand);

    /**
     * Complexity should be less than O(n)
     */
    Set<Car> carsWithPowerMoreThan(int power);

    /**
     * Complexity should be O(1)
     */
    Set<Car> allCarsOfOwner(Owner owner);

    /**
     * @return mean value of owner age that has cars with given brand
     */
    int meanOwnersAgeOfCarBrand(String brand);

    /**
     * @return mean value of cars for all owners
     */
    int meanCarNumberForEachOwner();

    /**
     * Complexity should be less than O(n)
     * @return removed car
     */
    Car removeCar(int carId);

    /**
     * Complexity should be less than O(n)
     */
    void addNewCar(Car car, Owner owner);
}
