package io.github.some_example_name.lwjgl3.Scene;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Map<String, Scene> scenes;
    private Scene currentScene;

    public SceneManager() {
        scenes = new HashMap<>();
        currentScene = null;
    }

    public void addScene(String name, Scene scene) {
        if (!scenes.containsKey(name)) {
            scenes.put(name, scene);
        }
    }

    public void setScene(String sceneName) {
        if (scenes.containsKey(sceneName)) {
            System.out.println("✅ Switching to scene: " + sceneName);
            if (currentScene != null) {
                System.out.println("🔄 Hiding previous scene: " + currentScene.getClass().getSimpleName());
                currentScene.hide();
            }
            currentScene = scenes.get(sceneName);
            System.out.println("🎬 New current scene: " + currentScene.getClass().getSimpleName());
            currentScene.show();  // ✅ Make sure new scene is shown
        } else {
            System.out.println("❌ Scene '" + sceneName + "' does not exist!");
        }
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
        setScene("home"); // ✅ Automatically start in MainMenuScene
    }
}