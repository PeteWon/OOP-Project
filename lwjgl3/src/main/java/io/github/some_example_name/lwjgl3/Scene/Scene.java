package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
public abstract class Scene implements Screen {
    protected final SceneManager game;
    protected SpriteBatch batch;
    protected Texture tex;

    public Scene(SceneManager game, String texturePath){
        this.game = game;
        tex = new Texture(texturePath);
        batch = new SpriteBatch();

    }
    @Override
    public abstract void render(float delta);
    @Override
    public void dispose(){
        batch.dispose();
        tex.dispose();
    }
}