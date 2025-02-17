package io.github.some_example_name.lwjgl3.IO.Input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.ControllerAdapter;

public class ControllerInput extends ControllerAdapter {
    private Controller activeController;

    public ControllerInput() {
        if (Controllers.getControllers().size > 0) {
            activeController = Controllers.getControllers().first();
            activeController.addListener(this);
            System.out.println("✅ Controller connected: " + activeController.getName());
        }
    }

    public boolean isControllerConnected() {
        return activeController != null;
    }

    public float getLeftStickX() {
        if (isControllerConnected()) {
            return activeController.getAxis(1); // Change index based on controller mapping
        }
        return 0;
    }

    public float getLeftStickY() {
        if (isControllerConnected()) {
            return activeController.getAxis(0); // Change index based on controller mapping
        }
        return 0;
    }

    public boolean isButtonPressed(int buttonCode) {
        if (isControllerConnected()) {
            return activeController.getButton(buttonCode);
        }
        return false;
    }
}
