package ru.mipt.garage;
import java.util.Objects;

public class Owner {
    private final String name;
    private final String lastName;
    private final int age;
    private final int ownerId;

    public Owner(String name, String lastName, int age, int ownerId) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.ownerId = ownerId;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return ownerId == owner.ownerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId);
    }
}
