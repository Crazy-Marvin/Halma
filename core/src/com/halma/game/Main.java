package com.halma.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Handler handler;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		handler = new Handler(this);
		handler.init();
	}

	@Override
	public void render () {
		//Update process
		handler.update(Gdx.graphics.getDeltaTime());


		//Rendering process
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		handler.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		handler.dispose();
	}
}
