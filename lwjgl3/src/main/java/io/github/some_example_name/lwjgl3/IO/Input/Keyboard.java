package io.github.some_example_name.lwjgl3.IO.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Keyboard {
    public static float getHorizontal() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            return -1;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            return 1;
        return 0;
    }

    public static float getVertical() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            return 1;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            return -1;
        return 0;
    }
}
