package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class StopScene extends Scene {
    private Stage stage;

    public StopScene(SceneManager game) {
        super(game, "background.png");  // Change to your actual image
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        System.out.println("Game has stopped");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

        batch.begin();
        batch.draw(tex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScene("home");  // Return to Main Menu
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }
}

