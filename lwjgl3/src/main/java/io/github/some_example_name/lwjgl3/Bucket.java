package io.github.some_example_name.lwjgl3;
//huhyhuy

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Bucket extends TextureObject{
	
	public Bucket(String textureFileName) {
		super(textureFileName);
	}
	public Bucket(String textureFileName, float x, float y, float speed) {
		super(textureFileName, x, y, speed);
	}
	
	@Override
	public void moveUserControlled() {
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			setX(getX() - 200 * Gdx.graphics.getDeltaTime());
		}
	
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			setX(getX() + 200 * Gdx.graphics.getDeltaTime());
		}
		
	    if (getX() > Gdx.graphics.getWidth()) {
	        setX(0); // Appears on the left
	    }
	    if (getX() < 0) {
	        setX(Gdx.graphics.getWidth()); // Appears on the right
	    }
}
}