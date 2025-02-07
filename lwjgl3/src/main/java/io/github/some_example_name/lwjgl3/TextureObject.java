package io.github.some_example_name.lwjgl3;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public abstract class TextureObject extends Entity{
	private Texture tex;

	public TextureObject(String textureFileName) {
		super(0,0,0);
		tex = new Texture(Gdx.files.internal(textureFileName));
	}
	

	public TextureObject(String textureFileName, float xAxis, float yAxis,float s) {
		super(xAxis,yAxis,s);
		tex = new Texture(Gdx.files.internal(textureFileName));
	}
	
	public Texture getTexture() {
		return tex;
	}
	
    public void setTexture(String textureFileName) {
        if (tex != null) {
            tex.dispose();
        }
        this.tex = new Texture(Gdx.files.internal(textureFileName));
    }
	

	@Override
    public void draw(SpriteBatch batch) {
    	batch.draw(tex, getX(), getY()); 
    }
	
    @Override
    public void moveAIControlled() {}

    @Override
    public void moveUserControlled() {}

    @Override
    public void update() {
        System.out.println("In TextureObject of " + tex.toString() + " at " + getX() + "," + getY() + " position");
        moveAIControlled();
        moveUserControlled();
    }

    
    @Override
    public void dispose() {
        if (tex != null) {
            tex.dispose();
        }
    }


}