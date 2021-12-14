import org.junit.jupiter.api.Test;
import ru.mipt.garage.Car;
import ru.mipt.garage.Garage123;
import ru.mipt.garage.Owner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class Garage123Test {
    Garage123 garage = new Garage123();

    Owner petr = new Owner("Petr", "Petrov", 18, 2);
    Owner sanya = new Owner("Alexander", "Belov", 37, 3);

    Car lastocka = new Car(0, "Lada", "Samara", 100, 10, 3);
    Car nova = new Car(1, "Ferari", "F8", 300, 50, 2);
    Car aventador = new Car(2, "Lamborghini", "Aventador", 280, 40, 2);
    Car beloved = new Car(3, "Lada", "2114", 150, 20, 2);

    void addAll() {
        garage.addNewCar(aventador, petr);
        garage.addNewCar(nova, petr);
        garage.addNewCar(lastocka, sanya);
        garage.addNewCar(beloved, petr);
    }

    @Test
    public void testAllOwners() {
        garage.addNewCar(aventador, petr);
        garage.addNewCar(nova, petr);
        Set<Owner> owners = new HashSet<>();
        owners.add(petr);
        assertEquals(owners, garage.allCarsUniqueOwners());
        garage.addNewCar(lastocka, sanya);
        garage.addNewCar(beloved, sanya);
        owners.add(sanya);
        assertEquals(owners, garage.allCarsUniqueOwners());
    }

    @Test
    public void testCarsOfBrand(){
        addAll();

        Set<Car> ladaCars = new HashSet<>();
        ladaCars.add(lastocka);
        ladaCars.add(beloved);
        Set<Car> feraCars = new HashSet<>();
        feraCars.add(nova);

        assertEquals(ladaCars, garage.allCarsOfBrand("Lada"));
        assertEquals(feraCars, garage.allCarsOfBrand("Ferari"));
    }

    @Test
    public void testPowerCars() {
        addAll();

        Set<Car> cars = new HashSet<>();
        cars.add(nova);
        cars.add(aventador);
        assertEquals(cars, garage.carsWithPowerMoreThan(30));
        assertEquals(new HashSet<Car>(), garage.carsWithPowerMoreThan(130));
    }

    @Test
    public void testCarsOfOwner() {
        addAll();

        Set<Car> cars = new HashSet<>();
        cars.add(nova);
        cars.add(aventador);
        cars.add(beloved);


        assertEquals(cars, garage.allCarsOfOwner(petr));
    }

    @Test
    public void testMeanAge() {
        addAll();

        assertEquals((petr.getAge() + sanya.getAge())/2, garage.meanOwnersAgeOfCarBrand("Lada"));

        garage.removeCar(3);
        assertEquals(sanya.getAge(), garage.meanOwnersAgeOfCarBrand("Lada"));
    }

    @Test
    public void testVelTop(){
        addAll();

        Set<Car> cars = new HashSet<>();
        cars.add(nova);
        cars.add(aventador);
        cars.add(beloved);

        assertEquals(cars, garage.topThreeCarsByMaxVelocity());
    }

    @Test
    public void testMeanCarNum() {
        addAll();
        assertEquals(2, garage.meanCarNumberForEachOwner());

        garage.removeCar(3);
        assertEquals(1, garage.meanCarNumberForEachOwner());
    }

    @Test
    public void testRemove() {
        addAll();

        Set<Car> cars = new HashSet<>();
        cars.add(nova);
        cars.add(aventador);

        garage.removeCar(3);

        assertEquals(cars, garage.allCarsOfOwner(petr));
    }

    @Test
    public void testAdd() {
        garage.addNewCar(beloved, petr);
        Set<Car> cars = new HashSet<>();
        cars.add(beloved);

        assertEquals(cars, garage.allCarsOfOwner(petr));
    }

}