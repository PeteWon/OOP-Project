package abstract_classes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import application.Managers.SceneManager;

public abstract class Scene implements Screen {
    protected final SceneManager game;
    protected SpriteBatch batch;
    protected Texture tex;

    public Scene(SceneManager game, String texturePath) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.tex = new Texture(texturePath);
    }

    @Override
    public void render(float delta) {
        if (batch != null) {
            batch.begin();
            batch.draw(tex, 0, 0);
            batch.end();
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        // Optional: Handle resizing if needed
    }

    @Override
    public void pause() {
        // Optional: Handle pausing
    }

    @Override
    public void resume() {
        // Optional: Handle resuming
    }

    @Override
    public void hide() {
        // Default implementation (can be overridden by subclasses)
    }

    @Override
    public void dispose() {
        batch.dispose();
        tex.dispose();
    }
}
