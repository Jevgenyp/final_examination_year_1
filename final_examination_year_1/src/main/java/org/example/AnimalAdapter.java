package org.example;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnimalAdapter extends TypeAdapter<Animal> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy, hh:mm:ss a");

    @Override
    public void write(JsonWriter out, Animal animal) throws IOException {
        out.beginObject();

        out.name("type").value(animal.getType());
        out.name("id").value(animal.getId());
        out.name("species").value(animal.getSpecies());
        out.name("birthDate").value(dateFormat.format(animal.getBirthDate()));
        out.name("name").value(animal.getName());
        out.name("commands");
        out.beginArray();
        for (String command : animal.getCommands()) {
            out.value(command);
        }
        out.endArray();

        out.endObject();
    }

    @Override
    public Animal read(JsonReader in) throws IOException {
        in.beginObject();

        String type = null;
        String id = "";
        String species = "";
        Date birthDate = null;
        String name = "";
        List<String> commands = new ArrayList<>();

        while (in.hasNext()) {
            switch (in.nextName()) {
                case "type":
                    type = in.nextString();
                    break;
                case "id":
                    id = in.nextString();
                    break;
                case "species":
                    species = in.nextString();
                    break;
                case "birthDate":
                    try {
                        birthDate = dateFormat.parse(in.nextString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "name":
                    name = in.nextString();
                    break;
                case "commands":
                    in.beginArray();
                    while (in.hasNext()) {
                        commands.add(in.nextString());
                    }
                    in.endArray();
                    break;
            }
        }

        Animal animal;
        if (type == null) {
            throw new IllegalArgumentException("Animal type is missing in the JSON data.");
        }

        switch (type) {
            case "Dog":
                animal = new Domestic.Dog(id, species, birthDate, name);
                break;
            case "Cat":
                animal = new Domestic.Cat(id, species, birthDate, name);
                break;
            case "Hamster":
                animal = new Domestic.Hamster(id, species, birthDate, name);
                break;
            case "Donkey":
                animal = new PackAnimals.Donkey(id, species, birthDate, name);
                break;
            case "Horse":
                animal = new PackAnimals.Horse(id, species, birthDate, name);
                break;
            case "Camel":
                animal = new PackAnimals.Camel(id, species, birthDate, name);
                break;
            default:
                throw new IllegalArgumentException("Unknown animal type: " + type);
        }

        for (String command : commands) {
            animal.teachCommand(command);
        }

        in.endObject();
        return animal;
    }
}
