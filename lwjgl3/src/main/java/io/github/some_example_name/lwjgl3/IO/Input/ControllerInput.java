package io.github.some_example_name.lwjgl3.IO.Input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.ControllerAdapter;

public class ControllerInput extends ControllerAdapter {

    private Controller activeController;

    // Store movement axes
    private int leftStickXAxis = 0; // Default axis index
    private int leftStickYAxis = 1;

    private static final float DEAD_ZONE = 0.2f; // ✅ Stick drift fix

    public ControllerInput() {
        if (Controllers.getControllers().size > 0) {
            activeController = Controllers.getControllers().first();
            activeController.addListener(this);
            System.out.println("🎮 Controller connected: " + activeController.getName());

            // Detect controller type and assign correct axis mapping
            String controllerName = activeController.getName().toLowerCase();
            if (controllerName.contains("xbox")) {
                leftStickXAxis = 0; // Adjust if incorrect
                leftStickYAxis = 1;
            } else if (controllerName.contains("playstation") || controllerName.contains("ps")) {
                leftStickXAxis = 0; // Adjust if needed
                leftStickYAxis = 1;
            } else {
                leftStickXAxis = 0; // Default mapping
                leftStickYAxis = 1;
            }
        }
    }

    // ✅ Get X movement (left/right)
    public float getLeftStickX() {
        if (activeController == null)
            return 0f;
        float value = activeController.getAxis(leftStickXAxis);
        return Math.abs(value) > DEAD_ZONE ? value : 0f; // Apply dead zone
    }

    // ✅ Get Y movement (up/down)
    public float getLeftStickY() {
        if (activeController == null)
            return 0f;
        float value = activeController.getAxis(leftStickYAxis);
        return Math.abs(value) > DEAD_ZONE ? value : 0f;
    }

    // ✅ Check if a button is pressed
    public boolean isButtonPressed(int button) {
        if (activeController == null)
            return false;
        return activeController.getButton(button);
    }

    public Controller getActiveController() {
        return activeController;
    }

    public boolean isControllerConnected() {
        return activeController != null; // Returns true if a controller is connected
    }

    // private Controller activeController;

    // public ControllerInput() {
    // if (Controllers.getControllers().size > 0) {
    // activeController = Controllers.getControllers().first();
    // activeController.addListener(this);
    // System.out.println("✅ Controller connected: " + activeController.getName());
    // }
    // }

    // public boolean isControllerConnected() {
    // return activeController != null;
    // }

    // public float getLeftStickX() {
    // if (isControllerConnected()) {
    // return activeController.getAxis(1); // Change index based on controller
    // mapping
    // }
    // return 0;
    // }

    // public float getLeftStickY() {
    // if (isControllerConnected()) {
    // return activeController.getAxis(0); // Change index based on controller
    // mapping
    // }
    // return 0;
    // }

    // public boolean isButtonPressed(int buttonCode) {
    // if (isControllerConnected()) {
    // return activeController.getButton(buttonCode);
    // }
    // return false;
    // }
}
