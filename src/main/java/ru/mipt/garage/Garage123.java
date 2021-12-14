package ru.mipt.garage;
import java.util.*;

public class Garage123 implements Garage{
    private final Map<Owner, HashSet<Car>> carsOfOwners = new HashMap<>();
    private final Map<String, HashSet<Car>> carsOfBrands = new HashMap<>();
    private final TreeSet<Car> velocityTop = (TreeSet<Car>)(new TreeSet<>(Comparator.comparing(Car::getMaxVelocity))).descendingSet();
    private final TreeSet<Car> powerTop = new TreeSet<>(Comparator.comparing(Car::getPower));
    private HashSet<Owner> owners = new HashSet<>();

    @Override
    public Set<Owner> allCarsUniqueOwners() {
        return carsOfOwners.keySet();
    }

    @Override
    public Set<Car> topThreeCarsByMaxVelocity() {
        Set topThree = new HashSet();
        int num = 0;
        for (Car car: velocityTop) {
            if (num == 3) break;

            topThree.add(car);
            ++num;
        }
        return topThree;
    }

    @Override
    public Set<Car> allCarsOfBrand(String brand) {
        return carsOfBrands.get(brand);
    }

    @Override
    public Set<Car> carsWithPowerMoreThan(int power) {
        Car toComp = new Car(10, "Mine", "Car", 0, power, -1);
        return powerTop.tailSet(toComp);
    }

    @Override
    public Set<Car> allCarsOfOwner(Owner owner){
        return carsOfOwners.get(owner);
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        int sum = 0;
        int num = 0;

        for (Owner owner: owners) {
            Set<Car> carsOfOwner = carsOfOwners.get(owner);
            for (Car car : carsOfOwner) {
                if (car.getBrand().equals(brand)) {
                    num += 1;
                    sum += owner.getAge();
                }
            }
        }

        return num == 0 ? 0 : (sum / num);
    }

    @Override
    public int meanCarNumberForEachOwner() {
        int carNum = 0;
        for (Owner owner: owners) {
            carNum += carsOfOwners.get(owner).size();
        }
        return carNum / carsOfOwners.size();
    }

    @Override
    public Car removeCar(int carId) {
        boolean isFound = false;
        Car found = null;
        outter:
        for (Owner owner : owners){
            HashSet<Car> cars = carsOfOwners.get(owner);
            for (Car car: cars) {
                if (car.getCarId() == carId) {
                    found = car;
                    isFound = true;
                    cars.remove(car);
                    if (carsOfOwners.get(owner).isEmpty()) {
                        owners.remove(owner);
                    }
                    break outter;
                }
            }
        }

        if (isFound) {
            Set<String> brands = carsOfBrands.keySet();
            outter:
            for (String brand: brands) {
                HashSet<Car> cars = carsOfBrands.get(brand);
                for (Car car: cars) {
                    if (car.getCarId() == carId) {
                        cars.remove(car);
                        break outter;
                    }
                }
            }
            powerTop.remove(found);
            velocityTop.remove(found);
        }

        return found;
    }

    @Override
    public void addNewCar(Car car, Owner owner) {
        if (owners.contains(owner)) {
            carsOfOwners.get(owner).add(car);
        } else {
            HashSet<Car> cars = new HashSet<>();
            cars.add(car);
            carsOfOwners.put(owner, cars);
            owners.add(owner);
        }

        if (carsOfBrands.containsKey(car.getBrand())) {
            carsOfBrands.get(car.getBrand()).add(car);
        } else {
            HashSet<Car> cars = new HashSet<>();
            cars.add(car);
            carsOfBrands.put(car.getBrand(), cars);
        }

        powerTop.add(car);
        velocityTop.add(car);
    }
}
