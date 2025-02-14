package io.github.some_example_name.lwjgl3.Scene;

public class Scene {
    // Class attributes
    private String name;
    private String description;

    // Constructor
    public Scene(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Display scene details
    public void displayScene() {
        System.out.println("Scene: " + name);
        System.out.println("Description: " + description);
    }

    // Predefined game scenes
    public static Scene startScene() {
        return new Scene("Start", "The game is about to begin.");
    }

    public static Scene pauseScene() {
        return new Scene("Pause", "The game is paused.");
    }

    public static Scene endScene() {
        return new Scene("End", "The game has ended.");
    }
}
