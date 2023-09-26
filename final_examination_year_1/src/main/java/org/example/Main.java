package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static AnimalRegistry registry = new AnimalRegistry();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static Counter animalCounter = new Counter();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        registry.loadFromFile();

        while (continueProgram) {
            System.out.println("\nMenu:");
            System.out.println("1. Register a new animal");
            System.out.println("2. Teach command to an animal");
            System.out.println("3. List animal's commands");
            System.out.println("4. List all animals");
            System.out.println("5. Save data");
            System.out.println("6. Exit");

            String choiceStr = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter animal type (Dog, Cat, etc.):");
                    String type = scanner.nextLine();

                    System.out.println("Enter animal name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter animal ID:");
                    String id = scanner.nextLine();

                    System.out.println("Enter animal species:");
                    String species = scanner.nextLine();

                    System.out.println("Enter animal birth date (dd/MM/yyyy):");
                    Date birthDate;
                    try {
                        birthDate = dateFormat.parse(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid date format! Using current date.");
                        birthDate = new Date();
                    }

                    Animal animal;
                    switch (type.toLowerCase()) {
                        case "dog":
                            animal = new Domestic.Dog(id, species, birthDate, name);
                            break;
                        case "cat":
                            animal = new Domestic.Cat(id, species, birthDate, name);
                            break;
                        case "hamster":
                            animal = new Domestic.Hamster(id, species, birthDate, name);
                            break;
                        case "donkey":
                            animal = new PackAnimals.Donkey(id, species, birthDate, name);
                            break;
                        case "horse":
                            animal = new PackAnimals.Horse(id, species, birthDate, name);
                            break;
                        case "camel":
                            animal = new PackAnimals.Camel(id, species, birthDate, name);
                            break;
                        default:
                            System.out.println("Unknown animal type.");
                            continue;
                    }

                    registry.registerAnimal(animal);
                    animalCounter.add();
                    System.out.println(name + " has been registered. Total animals registered this session: " + animalCounter.getCount());
                    break;

                case 2:
                    registry.listAnimals();
                    System.out.println("Enter the number of the animal you want to teach:");
                    int index = Integer.parseInt(scanner.nextLine()) - 1;

                    if (index >= 0 && index < registry.getAnimals().size()) {
                        Animal selectedAnimal = registry.getAnimals().get(index);
                        System.out.println("Enter a new command for " + selectedAnimal.getName() + ":");
                        String command = scanner.nextLine();
                        selectedAnimal.teachCommand(command);
                        System.out.println(selectedAnimal.getName() + " has learned the command: " + command);
                    } else {
                        System.out.println("Invalid selection.");
                    }
                    break;

                case 3:
                    registry.listAnimals();
                    System.out.println("Enter the number of the animal to view its commands:");
                    index = Integer.parseInt(scanner.nextLine()) - 1;
                    if (index >= 0 && index < registry.getAnimals().size()) {
                        Animal selectedAnimal = registry.getAnimals().get(index);
                        System.out.println(selectedAnimal.getName() + "'s commands: " + selectedAnimal.getCommands());
                    } else {
                        System.out.println("Invalid selection.");
                    }
                    break;

                case 4:
                    registry.listAnimals();
                    break;

                case 5:
                    registry.saveToFile();
                    System.out.println("Data saved successfully.");
                    break;

                case 6:
                    continueProgram = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        registry.saveToFile();
        animalCounter.close();
    }
}
