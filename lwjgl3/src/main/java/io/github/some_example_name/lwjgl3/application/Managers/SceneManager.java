package io.github.some_example_name.lwjgl3.application.Managers;

import java.util.HashMap;
import java.util.Map;

import io.github.some_example_name.lwjgl3.abstract_classes.Scene;
import io.github.some_example_name.lwjgl3.application.Classes.IO.Audio;
import io.github.some_example_name.lwjgl3.application.Classes.Scene.GameScene;
import io.github.some_example_name.lwjgl3.application.Classes.Scene.MainMenuScene;
import io.github.some_example_name.lwjgl3.application.Classes.Scene.SettingsScene;
import io.github.some_example_name.lwjgl3.application.Classes.Scene.StopScene;

public class SceneManager {
    private Map<String, Scene> scenes; // Stores all scenes in a map with their names as keys
    private Scene currentScene;
    private String previousScene = "home";
    private Audio backgroundMusic; // Centralized background music

    public SceneManager() {
        scenes = new HashMap<>();
        currentScene = null;
        backgroundMusic = Audio.getInstance("Music/MainScreenMusic.mp3", 0.5f, true); // Load background music
    }

    // Calls renderScene with a default value frame time of 0.016s (60fps)
    public void renderScene() {
        renderScene(0.016f); // Calls the existing renderScene(float) with a default value
    }

    public void renderScene(float deltaTime) {
        if (currentScene != null) {
            currentScene.render(deltaTime);
        }
    }

    // Add scene too scene map
    public void addScene(String name, Scene scene) {
        if (!scenes.containsKey(name)) { // prevent adding duplicate scene
            scenes.put(name, scene);
        }
    }

    public void setScene(String sceneName) {
        setScene(sceneName, false); // Default to `restart = false`
    }

    public void setScene(String sceneName, boolean restart) {
        if (scenes.containsKey(sceneName)) {
            System.out.println("Switching to scene: " + sceneName);

            // Hide the previous scene before switching
            if (currentScene != null) {
                System.out.println("Hiding previous scene: " + currentScene.getClass().getSimpleName());
                currentScene.hide();
            }

            // Restart the "play" scene if required
            if (restart && sceneName.equals("play")) { // Reset GameScene when restarting
                scenes.put("play", new GameScene(this));
            }

            // Set and show new scene
            currentScene = scenes.get(sceneName);
            System.out.println("New current scene: " + currentScene.getClass().getSimpleName());
            currentScene.show(); // Make sure new scene is shown
        } else {
            System.out.println("Scene '" + sceneName + "' does not exist!");
        }
    }

    public void initializeScenes() {
        addScene("home", new MainMenuScene(this));
        addScene("play", new GameScene(this));
        addScene("stop", new StopScene(this));
        addScene("settings", new SettingsScene(this)); // Add SettingsScene
        setScene("home"); // Automatically start in MainMenuScene
    }

    // Music control methods
    public void playBackgroundMusic() {
        backgroundMusic.playMusic();
    }

    public void stopBackgroundMusic() {
        backgroundMusic.stopMusic();
    }

    public void setBackgroundMusicVolume(float volume) {
        backgroundMusic.setVolume(volume);
        backgroundMusic.setSoundEffectVolume("player", volume);
        backgroundMusic.setSoundEffectVolume("tree", volume);
    }

    public float getBackgroundMusicVolume() {
        return backgroundMusic.getVolume();
    }

    public String getPreviousScene() {
        return previousScene;
    }

    public void dispose() {
        backgroundMusic.dispose(); // Dispose of the music when the game exits
    }
}