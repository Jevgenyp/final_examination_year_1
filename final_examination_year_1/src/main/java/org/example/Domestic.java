package org.example;

import java.util.Date;

public abstract class Domestic extends Animal {
    public Domestic(String id, String species, Date birthDate, String name) {
        super(id, species, birthDate, name);
    }

    @Override
    public String getCategory() {
        return "Domestic";
    }

    public static class Dog extends Domestic {
        public Dog(String id, String species, Date birthDate, String name) {
            super(id, species, birthDate, name);
        }

        @Override
        public String getType() {
            return "Dog";
        }
    }

    public static class Cat extends Domestic {
        public Cat(String id, String species, Date birthDate, String name) {
            super(id, species, birthDate, name);
        }

        @Override
        public String getType() {
            return "Cat";
        }
    }

    public static class Hamster extends Domestic {
        public Hamster(String id, String species, Date birthDate, String name) {
            super(id, species, birthDate, name);
        }

        @Override
        public String getType() {
            return "Hamster";
        }
    }


}
