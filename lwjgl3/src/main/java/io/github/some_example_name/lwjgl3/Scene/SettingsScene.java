package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.github.some_example_name.lwjgl3.Scene.Scene;
import io.github.some_example_name.lwjgl3.Scene.SceneManager;
import io.github.some_example_name.lwjgl3.IO.IOManager;

public class SettingsScene extends Scene {
    private Stage stage;
    private Skin skin;
    private Slider volumeSlider;
    private TextButton backButton;

    public SettingsScene(SceneManager game) {
        super(game, "background.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // ✅ Volume Slider
        volumeSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeSlider.setValue(IOManager.getVolume()); // ✅ Load saved volume
        volumeSlider.setPosition(Gdx.graphics.getWidth() / 2f - 100, 300);

        volumeSlider.addListener(event -> {
            IOManager.setVolume(volumeSlider.getValue()); // ✅ Update volume
            return false;
        });

        // ✅ Back Button
        backButton = new TextButton("Back", skin);
        backButton.setPosition(Gdx.graphics.getWidth() / 2f - 50, 200);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("🔙 Returning to Main Menu...");
                game.setScene("home");
            }
        });

        // ✅ Add UI elements to stage
        stage.addActor(volumeSlider);
        stage.addActor(backButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        System.out.println("✅ Main menu shown, input processor set");

        if (!stage.getActors().contains(backButton, true)) {
            stage.addActor(backButton);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
    }
}
