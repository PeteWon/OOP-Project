package io.github.some_example_name.lwjgl3.IO.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Keyboard {
    private static boolean lastLeftPressed = false;
    private static boolean lastRightPressed = false;
    private static boolean lastUpPressed = false;
    private static boolean lastDownPressed = false;

    public static float getHorizontal() {

        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        // if (leftPressed && !lastLeftPressed) {
        // System.out.println("Left key pressed");
        // }
        // lastLeftPressed = leftPressed;
        if (leftPressed && !lastLeftPressed) {
            System.out.println("Keyboard: Moving Left");
        }
        if (rightPressed && !lastRightPressed) {
            System.out.println("Keyboard: Moving Right");
        }

        lastLeftPressed = leftPressed;
        lastRightPressed = rightPressed;

        if (leftPressed)
            return -1;
        if (rightPressed)
            return 1;
        return 0;
    }

    public static float getVertical() {
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (upPressed && !lastUpPressed) {
            System.out.println("Keyboard: Moving Up");
        }
        if (downPressed && !lastDownPressed) {
            System.out.println("Keyboard: Moving Down");
        }

        lastUpPressed = upPressed;
        lastDownPressed = downPressed;

        if (upPressed)
            return 1;
        if (downPressed)
            return -1;
        return 0;
    }

    public static boolean isKeyPressed(int key) {
        return Gdx.input.isKeyPressed(key);
    }
}
