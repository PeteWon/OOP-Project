package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class Triangle extends Entity{
	private Color color;
	private float x1,x2,x3,y1,y2,y3;
	private String constructorUsed;
	
	public Triangle() {
		super();
		this.color = Color.WHITE;
	}
	
	public Triangle(Color color, float x, float y, float speed) {
		super(x,y,speed);
		this.color = color;	
		constructorUsed = "1";
	}
	
	public Triangle(Color color, float x, float y, float speed, float x1, 
			float y1, float x2, float y2, float x3, float y3) {
		super(x,y,speed);
		this.color = color;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		constructorUsed = "2";

	}
	
    public String getConstructorUsed() {
        return constructorUsed;
    }
	
	@Override
	public void draw(ShapeRenderer shape) {
		shape.setColor(color);
		
		if (constructorUsed.equals("1")) {
		    float size = 50; // Example size, can adjust as needed
		    float x1 = getX() - size;
		    float y1 = getY() - size;
		    float x2 = getX() + size;
		    float y2 = getY() - size;
		    float x3 = getX();
		    float y3 = getY() + size;
			shape.triangle(x1,y1,x2,y2,x3,y3);

		}
		else if (constructorUsed.equals("2")) {
			shape.triangle(x1,y1,x2,y2,x3,y3);
		}
	}
	
	@Override
    public void moveUserControlled() {

		
		if (Gdx.input.isKeyPressed(Keys.D)) {
			setX(getX() + getSpeed());
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			setX(getX() - getSpeed());
		}
		
	    if (constructorUsed.equals("2")) {
	        float dx = getX() - (x1 + x2 + x3) / 3; // Calculate the change in x
	        x1 += dx;
	        x2 += dx;
	        x3 += dx;
	    }
		
        if (getX() > Gdx.graphics.getWidth()) {
            setX(0); // Appears on the left
        }
        if (getX() < 0) {
            setX(Gdx.graphics.getWidth()); // Appears on the right
        }
	}
	
	@Override
	public void update() {
	    moveUserControlled();  // Ensures movement happens on update
	}
}