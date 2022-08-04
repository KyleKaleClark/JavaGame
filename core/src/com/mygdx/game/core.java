package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

//import java.awt.Shape;

public class core extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, link;
	BitmapFont font;
	ShapeRenderer shapeRenderer;
	OrthographicCamera camera;
	Rabbit rab;
	float X = 200;
	float Y = 200;
	float xSpeed = 240;
	float ySpeed = 240;
	String link_sprite = "LADX_Link_Sprite.png";

	@Override
	public void create () {
		rab = new Rabbit(X, Y, 0, link_sprite);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();

		camera = new OrthographicCamera(480, 320);

	}

	@Override
	public void render () { //Where the game should be drawn
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		link = new Texture(rab.fileLoc());
		camera.update();

		draw();
		gameLogic();
		camera.position.set(rab.getX(), rab.getY(), 0); //this camera set has to be after gamelogic to keep it smooth :^)




	}


	public void draw(){
		//Sprite Batch shit
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(link, rab.getX(), rab.getY(), 100, 100);
		batch.draw(link, 100, 100);
		batch.end();


		System.out.println("(X, Y) = (" + rab.getX() + ", " + rab.getY()+")");
		System.out.println("1");

	}
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		shapeRenderer.dispose();

	}
	@Override
	public void resize(int width, int height){

	}


	public void gameLogic(){
		ArrayList<enemy> enemyList = new ArrayList<enemy>();
		int targetSel = 0;
		double theta = 0;
















		//Input reading
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			rab.setY(rab.getY() + ySpeed * Gdx.graphics.getDeltaTime());
			//camera.translate(0, ySpeed  * Gdx.graphics.getDeltaTime());
			rab.updatefile("link_up.png");
		}
		else if(Gdx.input.isKeyPressed((Input.Keys.S))){
			rab.setY(rab.getY() - ySpeed * Gdx.graphics.getDeltaTime());
			//camera.translate(0, -ySpeed  * Gdx.graphics.getDeltaTime());
			rab.updatefile("LADX_Link_Sprite.png");
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			rab.setX(rab.getX() + xSpeed * Gdx.graphics.getDeltaTime());
			//camera.translate(0, ySpeed  * Gdx.graphics.getDeltaTime());
			rab.updatefile("link_right.png");
		}
		else if(Gdx.input.isKeyPressed((Input.Keys.A))){
			rab.setX(rab.getX() - xSpeed * Gdx.graphics.getDeltaTime());
			//camera.translate(0, -ySpeed  * Gdx.graphics.getDeltaTime());
			rab.updatefile("link_left.png");
		}
	}
}
