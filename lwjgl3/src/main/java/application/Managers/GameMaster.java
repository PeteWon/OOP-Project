package application.Managers;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

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