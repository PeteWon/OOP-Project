package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import io.github.some_example_name.lwjgl3.IO.IOManager;

public class SettingsScene extends Scene {
    private Stage stage;
    private Skin skin;
    private Slider volumeSlider;
    private TextButton backButton;
    private float lastPrintedVolume = -1f; // ✅ Prevent duplicate prints

    public SettingsScene(SceneManager game) {
        super(game, "background2.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // ✅ Volume Slider (Corrected)
        volumeSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeSlider.setValue(IOManager.getVolume()); // ✅ Load saved volume
        volumeSlider.setPosition(Gdx.graphics.getWidth() / 2f - 100, 300);

        volumeSlider.addListener(new ChangeListener() { // ✅ Always fires on change
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                float volume = volumeSlider.getValue();

                // ✅ Prevent duplicate printing (Only log on noticeable change)
                if (Math.abs(volume - lastPrintedVolume) >= 0.05f) { // Adjust threshold if needed
                    IOManager.setVolume(volume); // ✅ Save volume correctly
                    game.setBackgroundMusicVolume(volume); // ✅ Update global music volume
                    System.out.println("🔊 Volume set to: " + volume);
                    lastPrintedVolume = volume; // ✅ Store last printed value
                }
            }
        });

        // ✅ Back Button
        backButton = new TextButton("Back", skin);
        backButton.setPosition(Gdx.graphics.getWidth() / 2f - 50, 200);

        backButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
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
        System.out.println("✅ Settings menu shown, input processor set");

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
