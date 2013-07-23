package com.punchline.microspace;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.punchline.javalib.utils.SoundManager;
import com.punchline.javalib.utils.Units;
import com.punchline.microspace.entities.SpaceWorld;

public class MicroSpace implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private SpaceWorld world;
	
	@Override
	public void create() {
		Units.setPixelsPerMeter(8);
		
		float w = (float)Gdx.graphics.getWidth() / Units.getPixelsPerMeter();
		float h = (float)Gdx.graphics.getHeight() / Units.getPixelsPerMeter();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		
		batch = new SpriteBatch();
		
		world = new SpaceWorld(camera);
	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
		SoundManager.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		world.process();
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		//sprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}