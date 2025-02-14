package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.lwjgl3.Entity.Entity;
import io.github.some_example_name.lwjgl3.Entity.EntityManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

	
public class GameMaster extends ApplicationAdapter {


	@Override
	public void create() {

		// batch = new SpriteBatch();
		// shape = new ShapeRenderer();
        // entityManager = new EntityManager();
		}
	
	@Override
	public void render() {

		ScreenUtils.clear(0, 0, 0.2f, 1);
		
	}
	
    @Override
    public void dispose() {


    }
}



