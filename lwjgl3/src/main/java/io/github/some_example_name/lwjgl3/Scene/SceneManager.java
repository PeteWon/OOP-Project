package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Screen;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
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

    public void setScene(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            if (currentScene != null) {
                currentScene.dispose();  // Dispose of previous scene resources
            }
            currentScene = scene;
            System.out.println("Current scene set to: " + name);
            currentScene.show();  // Call show() for initialization
        } else {
            System.out.println("Scene not found: " + name);
        }
    }

    public void renderScene(float delta) {
        if (currentScene != null) {
            currentScene.render(delta);
        } else {
            System.out.println("No scene is currently set.");
        }
    }

    public void initializeScenes() {
        addScene("home", new MainMenuScene(this));
        addScene("play", new GameScene(this));
        addScene("stop", new StopScene(this));
        setScene("home");  // Start at home scene
    }
}
