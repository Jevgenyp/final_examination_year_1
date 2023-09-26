package org.example;

import java.util.Date;

public abstract class Domestic extends Animal {
    public Domestic(String id, String species, Date birthDate, String name, String type) {
        super(id, type, species, birthDate, name); // Use the provided "type" parameter
    }

    @Override
    public String getCategory() {
        return "Domestic";
    }

    public static class Dog extends Domestic {
        public Dog(String id, String species, Date birthDate, String name) {
            super(id, species, birthDate, name, "Dog"); // Provide "Dog" as the "type"
        }

        @Override
        public String getType() {
            return "Dog";
        }
    }

    public static class Cat extends Domestic {
        public Cat(String id, String species, Date birthDate, String name) {
            super(id, species, birthDate, name, "Cat"); // Provide "Cat" as the "type"
        }

        @Override
        public String getType() {
            return "Cat";
        }
    }

    public static class Hamster extends Domestic {
        public Hamster(String id, String species, Date birthDate, String name) {
            super(id, species, birthDate, name, "Hamster"); // Provide "Hamster" as the "type"
        }

        @Override
        public String getType() {
            return "Hamster";
        }
    }
}
