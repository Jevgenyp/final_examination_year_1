package org.example;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    protected String id;
    protected String species;
    protected Date birthDate;
    protected String name;
    protected List<String> commands = new ArrayList<>();

    public Animal(String id, String species, Date birthDate, String name) {
        this.id = id;
        this.species = species;
        this.birthDate = birthDate;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public abstract String getType();

    public abstract String getCategory();  // New abstract method

    public void teachCommand(String command) {
        commands.add(command);
    }

    public List<String> getCommands() {
        return commands;
    }

    public String displayInfo() {
        return "ID: " + id + ", Species: " + species + ", Birth Date: " + birthDate.toString() + ", Name: " + name;
    }
}
