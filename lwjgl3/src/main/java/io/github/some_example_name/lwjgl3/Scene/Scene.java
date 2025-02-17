// package io.github.some_example_name.lwjgl3.Scene;

// import com.badlogic.gdx.Game;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
// import com.badlogic.gdx.scenes.scene2d.ui.Skin;
// import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
// import com.badlogic.gdx.scenes.scene2d.InputEvent;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.scenes.scene2d.ui.Image;
// public abstract class Scene implements Screen {
//     protected final SceneManager game;
//     protected SpriteBatch batch;
//     protected Texture tex;

//     public Scene(SceneManager game, String texturePath){
//         this.game = game;
//         tex = new Texture(texturePath);
//         batch = new SpriteBatch();

//     }
//     @Override
//     public abstract void render(float delta);
//     @Override
//     public void dispose(){
//         batch.dispose();
//         tex.dispose();
//     }
// }
package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    public void show() {
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
