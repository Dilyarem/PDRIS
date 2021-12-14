package ru.mipt.garage;

public class Application {

    public static void main(String[] args) {
        Garage123 garage = new Garage123();

        Owner petr = new Owner("Petr", "Petrov", 18, 2);
        Owner sanya = new Owner("Alexander", "Belov", 37, 3);

        Car lastocka = new Car(0, "Lada", "Samara", 100, 10, 3);
        Car nova = new Car(1, "Ferari", "F8", 300, 50, 2);
        Car aventador = new Car(2, "Lamborghini", "Aventador", 280, 40, 2);
        Car beloved = new Car(3, "Lada", "2114", 150, 20, 2);

        garage.addNewCar(aventador, petr);
        garage.addNewCar(nova, petr);
        garage.addNewCar(lastocka, sanya);
        garage.addNewCar(beloved, petr);

        garage.removeCar(3);

        System.out.println("Hello world!");
    }
}
