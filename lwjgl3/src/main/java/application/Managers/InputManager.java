package application.Managers;

import application.Classes.IO.ControllerInput;
import application.Classes.IO.Keyboard;

public class InputManager {
    private static boolean useGamepad = false;
    private static ControllerInput controllerInput = new ControllerInput();

    public static void detectInputType() {
        if (controllerInput.isControllerConnected()) {
            useGamepad = true; // Use controller if detected
        } else {
            useGamepad = false; // Default to keyboard if no controller
        }
    }

    public static float getMoveX() {
        detectInputType();
        // ? : is a shortcut for if/else
        // If useGamepad is true, return controllerInput, else return keyboard input
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
        // Nothing to dispose
    }

}
