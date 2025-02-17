package io.github.some_example_name.lwjgl3.IO;

import io.github.some_example_name.lwjgl3.IO.Input.ControllerInput;
import io.github.some_example_name.lwjgl3.IO.Input.Keyboard;

public class IOManager {
    private static float volume = 1.0f; // Default volume (1.0 = max)
    private static Keyboard keyboard = new Keyboard();

    public static float getHorizontal() {
        float controllerX = controllerInput.getLeftStickX();
        float keyboardX = keyboard.getHorizontal();
        return controllerX != 0 ? controllerX : keyboardX; // ✅ Prioritizes controller if available
    }

    public static float getVertical() {
        float controllerY = controllerInput.getLeftStickY();
        float keyboardY = keyboard.getVertical();
        return controllerY != 0 ? controllerY : keyboardY; // ✅ Prioritizes controller if available
    }

    public static boolean isButtonPressed(int buttonCode) {
        return controllerInput.isButtonPressed(buttonCode);
    }

    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float newVolume) {
        volume = Math.max(0, Math.min(newVolume, 1.0f)); // ✅ Ensure 0.0 to 1.0 range
        System.out.println("🎵 Volume set to: " + volume);
    }

    private static boolean useGamepad = false;
    private static ControllerInput controllerInput;

    public IOManager() {
        controllerInput = new ControllerInput();
    }

    public static void detectInputType() {
        if (controllerInput.isControllerConnected()) {
            useGamepad = true;
        } else {
            useGamepad = false;
        }
    }

    public static float getMoveX() {
        detectInputType();

        if (useGamepad) {
            return controllerInput.getLeftStickX();
        } else {
            return Keyboard.getHorizontal();
        }
    }

    public static float getMoveY() {
        detectInputType();

        if (useGamepad) {
            return controllerInput.getLeftStickY();
        } else {
            return Keyboard.getVertical();
        }
    }

    public static boolean isControllerButtonPressed(int buttonCode) {
        return controllerInput.isButtonPressed(buttonCode);
    }
}
