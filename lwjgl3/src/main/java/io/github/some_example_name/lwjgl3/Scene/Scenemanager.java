package io.github.some_example_name.lwjgl3.Scene;

import java.util.HashMap;
import java.util.Map;

public class Scenemanager {
    private Map<String, Scene> scenes;
    private Scene currentScene;

    public Scenemanager() {
        scenes = new HashMap<>();
        currentScene = null;
    }

    public void addScene(String name, Scene scene) {
        if (!scenes.containsKey(name)) {
            scenes.put(name, scene);
        }
    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }

    public void setScene(String name) {
        Scene scene = getScene(name);
        if (scene != null) {
            currentScene = scene;
            System.out.println("Current scene set to: " + scene.getName());
        } else {
            System.out.println("Scene not found: " + name);
        }
    }

    public void renderScene() {
        if (currentScene != null) {
            System.out.println("Rendering current scene: " + currentScene.getName());
            currentScene.displayScene();
        } else {
            System.out.println("No scene is currently set.");
        }
    }

    // Initializes the "home" scene
    public void initializeHomeScene() {
        if (!scenes.containsKey("home")) {
            Scene homeScene = new Scene("home", "This is the home screen.");
            addScene("home", homeScene);
        }
        setScene("home");
    }

    // Initializes the "play" scene
    public void initializePlayScene() {
        if (!scenes.containsKey("play")) {
            Scene playScene = new Scene("play", "This is the gameplay scene.");
            addScene("play", playScene);
        }
        setScene("play");
    }

    // Initializes the "stop" scene
    public void initializeStopScene() {
        if (!scenes.containsKey("stop")) {
            Scene stopScene = new Scene("stop", "The game has stopped.");
            addScene("stop", stopScene);
        }
        setScene("stop");
    }
}
