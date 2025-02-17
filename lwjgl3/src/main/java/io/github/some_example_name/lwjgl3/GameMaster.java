package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.some_example_name.lwjgl3.application.EntityManager;
import io.github.some_example_name.lwjgl3.Collision.CollisionManager;
import io.github.some_example_name.lwjgl3.IO.IOManager;
import io.github.some_example_name.lwjgl3.Scene.SceneManager;

public class GameMaster extends ApplicationAdapter {
    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private IOManager ioManager;
    private SceneManager sceneManager;

    @Override
    public void create() {
        entityManager = new EntityManager();
        collisionManager = new CollisionManager();
        ioManager = new IOManager();
        sceneManager = new SceneManager();
        sceneManager.initializeScenes(); // Initialize all scenes
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        sceneManager.renderScene(); // Renders the current scene
    }
}