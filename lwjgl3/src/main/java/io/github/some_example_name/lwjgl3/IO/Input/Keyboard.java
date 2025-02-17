package io.github.some_example_name.lwjgl3.IO.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Keyboard {
    public static float getHorizontal() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            System.out.println("⌨️ Keyboard: Moving Left");
            return -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            System.out.println("⌨️ Keyboard: Moving Right");
            return 1;
        }
        return 0;
    }

    public static float getVertical() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            System.out.println("⌨️ Keyboard: Moving Up");
            return 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            System.out.println("⌨️ Keyboard: Moving Down");
            return -1;
        }
        return 0;
    }
}
