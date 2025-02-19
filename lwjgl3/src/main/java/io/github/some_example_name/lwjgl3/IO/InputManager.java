package io.github.some_example_name.lwjgl3.IO;

import io.github.some_example_name.lwjgl3.IO.Input.ControllerInput;
import io.github.some_example_name.lwjgl3.IO.Input.Keyboard;

public class InputManager {
    private static boolean useGamepad = false;
    private static ControllerInput controllerInput = new ControllerInput();

    public static void detectInputType() {
        if (controllerInput.isControllerConnected()) {
            useGamepad = true;
        } else {
            useGamepad = false;
        }
    }

    public static float getMoveX() {
        detectInputType();
        return useGamepad ? controllerInput.getLeftStickX() : Keyboard.getHorizontal();
    }

    public static float getMoveY() {
        detectInputType();
        return useGamepad ? controllerInput.getLeftStickY() : Keyboard.getVertical();
    }

    public static boolean isControllerButtonPressed(int buttonCode) {
        return controllerInput.isButtonPressed(buttonCode);
    }

    public void dispose() {
    }

}
