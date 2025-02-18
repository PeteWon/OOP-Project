package io.github.some_example_name.lwjgl3.IO.Input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.ControllerAdapter;

public class ControllerInput extends ControllerAdapter {

    private Controller activeController;
    private static final float DEAD_ZONE = 0.2f; // Stick drift fix

    // Default axis mapping
    private int leftStickXAxis = 0;
    private int leftStickYAxis = 1;

    public ControllerInput() {
        if (Controllers.getControllers().size > 0) {
            activeController = Controllers.getControllers().first();
            activeController.addListener(this);
            System.out.println("🎮 Controller connected: " + activeController.getName());
        }
        // Listen for new controllers being connected/disconnected
        Controllers.addListener(this);
    }

    public float getLeftStickX() {
        if (activeController == null)
            return 0f;

        float value = activeController.getAxis(leftStickXAxis);
        if (Math.abs(value) > DEAD_ZONE) {
            if (value > 0) {
                System.out.println("Controller: Moving Right");
            } else {
                System.out.println("Controller: Moving Left");
            }
            return value;
        }
        return 0f;
    }

    public float getLeftStickY() {
        if (activeController == null)
            return 0f;

        float value = -activeController.getAxis(leftStickYAxis); // Invert Y-Axis Fix
        if (Math.abs(value) > DEAD_ZONE) {
            if (value > 0) {
                System.out.println("Controller: Moving Up");
            } else {
                System.out.println("Controller: Moving Down");
            }
            return value;
        }
        return 0f;
    }

    @Override
    public void connected(Controller controller) {
        if (activeController == null) {
            activeController = controller;
            System.out.println("New Controller Connected: " + controller.getName());
        }
    }

    @Override
    public void disconnected(Controller controller) {
        if (activeController == controller) {
            System.out.println("Controller Disconnected: " + controller.getName());
            activeController = null; // Reset active controller
        }
    }

    // Check if a button is pressed
    public boolean isButtonPressed(int button) {
        if (activeController == null)
            return false;
        return activeController.getButton(button);
    }

    public Controller getActiveController() {
        return activeController;
    }

    public boolean isControllerConnected() {
        return activeController != null;
    }

}
