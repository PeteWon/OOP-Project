package io.github.some_example_name.lwjgl3;

public class Drop extends TextureObject{

	public Drop(String textureFileName) {
		super(textureFileName);
	}
	public Drop(String textureFileName, float x, float y, float speed) {
		super(textureFileName, x, y, speed);
	}
	
	@Override
	public void moveAIControlled() {
		setY(getY() - getSpeed());
    	if (getY()<= 0) {
    		setY(400);
        	if (getSpeed() <=10) {
        		setSpeed(getSpeed() + 2);
        	}
    	}
	}
}