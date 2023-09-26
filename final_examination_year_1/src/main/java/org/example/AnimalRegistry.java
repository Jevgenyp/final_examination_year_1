package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AnimalRegistry {
    private static final String FILENAME = "animals.json";
    private List<Animal> animals = new ArrayList<>();

    // Initialize the Gson object once and reuse it
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Animal.class, new AnimalAdapter())
            .setPrettyPrinting()
            .create();

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public void registerAnimal(Animal animal) {
        animals.add(animal);
    }

    public void listAnimals() {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-12s | %-12s | %-12s | %-12s | %-20s | %-20s%n", "No.", "ID", "Species", "Type", "Category", "Birth Date", "Name");
        System.out.println("------------------------------------------------------------------------------------------------");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            String formattedBirthDate = sdf.format(animal.getBirthDate());
            System.out.printf("%-5d | %-12s | %-12s | %-12s | %-12s | %-20s | %-20s%n", i+1, animal.getId(), animal.getSpecies(), animal.getType(), animal.getCategory(), formattedBirthDate, animal.getName());
        }

        System.out.println("------------------------------------------------------------------------------------------------");
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter(FILENAME)) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("lastUpdated", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            data.put("totalAnimals", animals.size());
            data.put("animals", animals);
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try (FileReader reader = new FileReader(FILENAME)) {
            Type mapType = new TypeToken<HashMap<String, Object>>(){}.getType();
            HashMap<String, Object> data = gson.fromJson(reader, mapType);

            if (data != null && data.containsKey("animals")) {
                animals = gson.fromJson(gson.toJson(data.get("animals")), new TypeToken<List<Animal>>(){}.getType());
            } else {
                animals = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
