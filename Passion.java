package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Shape;

public class Passion extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	ShapeRenderer shapeRenderer;

	float circX = 200;
	float circY = 100;
	float xSpeed = 240;
	float ySpeed = 240;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () { //Where the game should be drawn
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			circY += ySpeed * Gdx.graphics.getDeltaTime();
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.S)){
			circY -= ySpeed * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			circX += xSpeed * Gdx.graphics.getDeltaTime();
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.A)){
			circX -= xSpeed * Gdx.graphics.getDeltaTime();
		}

		//circY += ySpeed * Gdx.graphics.getDeltaTime();

		if(circX-75 < 0 || circX+75 > Gdx.graphics.getWidth()){
			xSpeed = 0;
			circX -= 1;
		}
		else{
			xSpeed = 240;
		}
		if(circY-75 < 0 || circY+75 > Gdx.graphics.getHeight()){
			circY = circY;
		}
		//System.out.println(xSpeed + "< x }} " + ySpeed + "< y");
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.circle(circX, circY, 75);
		shapeRenderer.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		shapeRenderer.dispose();

	}
}
