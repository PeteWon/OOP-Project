package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.lwjgl3.Collision.CollisionManager;
import io.github.some_example_name.lwjgl3.IO.InputManager;
import io.github.some_example_name.lwjgl3.IO.OutputManager;
import io.github.some_example_name.lwjgl3.Scene.SceneManager;
import io.github.some_example_name.lwjgl3.application.EntityManager;

public class GameMaster extends ApplicationAdapter {
    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private InputManager inputManager;
    private OutputManager outputManager;
    private SceneManager sceneManager;

    @Override
    public void create() {
        entityManager = new EntityManager();
        collisionManager = new CollisionManager(entityManager);
        inputManager = new InputManager();
        outputManager = new OutputManager();
        sceneManager = new SceneManager();
        sceneManager.initializeScenes(); // Initialize all scenes
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        sceneManager.renderScene(); // Renders the current scene
    }

    @Override
    public void dispose() {
        entityManager.dispose();
        collisionManager.dispose();
        inputManager.dispose();
        outputManager.dispose();
        sceneManager.dispose();
    }
}