package org.example;

import java.util.Date;

public abstract class PackAnimals extends Animal {
    public PackAnimals(String id, String species, Date birthDate, String name, String type) {
        super(id, type, species, birthDate, name); // Use the provided "type" parameter
    }

    @Override
    public String getCategory() {
        return "PackAnimals";
    }

    public static class Donkey extends PackAnimals {
        public Donkey(String id, String species, Date birthDate, String name) {
            super(id, species, birthDate, name, "Donkey"); // Provide "Donkey" as the "type"
        }

        @Override
        public String getType() {
            return "Donkey";
        }
    }

    public static class Horse extends PackAnimals {
        public Horse(String id, String species, Date birthDate, String name) {
            super(id, species, birthDate, name, "Horse"); // Provide "Horse" as the "type"
        }

        @Override
        public String getType() {
            return "Horse";
        }
    }

    public static class Camel extends PackAnimals {
        public Camel(String id, String species, Date birthDate, String name) {
            super(id, species, birthDate, name, "Camel"); // Provide "Camel" as the "type"
        }

        @Override
        public String getType() {
            return "Camel";
        }
    }
}
