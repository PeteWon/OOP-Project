package io.github.some_example_name.lwjgl3.Scene;

import java.util.HashMap;
import java.util.Map;

import io.github.some_example_name.lwjgl3.IO.Output.Audio;

public class SceneManager {
    private Map<String, Scene> scenes;
    private Scene currentScene;
    private String previousScene = "home";
    private Audio backgroundMusic; // ✅ Centralized background music

    public SceneManager() {
        scenes = new HashMap<>();
        currentScene = null;
        backgroundMusic = new Audio("Music/MainScreenMusic.mp3", 0.1f, true); // ✅ Load background music
    }

    public void addScene(String name, Scene scene) {
        if (!scenes.containsKey(name)) {
            scenes.put(name, scene);
        }
    }

    public void setScene(String sceneName) {
        setScene(sceneName, false); // ✅ Default to `restart = false`
    }

    public void setScene(String sceneName, boolean restart) {
        if (scenes.containsKey(sceneName)) {
            System.out.println("✅ Switching to scene: " + sceneName);
            if (currentScene != null) {
                System.out.println("🔄 Hiding previous scene: " + currentScene.getClass().getSimpleName());
                currentScene.hide();
            }
            if (restart && sceneName.equals("play")) { // ✅ Reset GameScene when restarting
                scenes.put("play", new GameScene(this));
            }
            currentScene = scenes.get(sceneName);
            System.out.println("🎬 New current scene: " + currentScene.getClass().getSimpleName());
            currentScene.show(); // ✅ Make sure new scene is shown
        } else {
            System.out.println("❌ Scene '" + sceneName + "' does not exist!");
        }
    }

    public String getPreviousScene() {
        return previousScene;
    }

    public void renderScene() {
        renderScene(0.016f); // ✅ Calls the existing renderScene(float) with a default value
    }

    public void renderScene(float deltaTime) {
        if (currentScene != null) {
            currentScene.render(deltaTime);
        }
    }

    public void initializeScenes() {
        addScene("home", new MainMenuScene(this));
        addScene("play", new GameScene(this));
        addScene("stop", new StopScene(this));
        addScene("settings", new SettingsScene(this)); // ✅ Add SettingsScene
        setScene("home"); // ✅ Automatically start in MainMenuScene
    }

    // ✅ Music control methods
    public void playBackgroundMusic() {
        backgroundMusic.playMusic();
    }

    

    public void stopBackgroundMusic() {
        backgroundMusic.stopMusic();
    }

    public void setBackgroundMusicVolume(float volume) {
        backgroundMusic.setVolume(volume);
    }

    public void dispose() {
        backgroundMusic.dispose(); // ✅ Dispose of the music when the game exits
    }
}