package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

//import java.awt.Shape;

public class core extends ApplicationAdapter {
	SpriteBatch batch;
	Texture enemy, link;
	BitmapFont font;
	ShapeRenderer shapeRenderer;
	OrthographicCamera camera;
	Rabbit rab;
	Animation<TextureRegion> walk;
	TextureRegion currentFrame;
	Texture walkSheet;
	TextureRegion[] walkFrames;
	//SpriteBatch spriteBatch;
	float X = 200;
	float Y = 200;
	float xSpeed = 200;
	float ySpeed = 200;
	float stateTime;
	String link_sprite = "link_down.png";

	@Override
	public void create () {
		rab = new Rabbit(X, Y, 4, 20, 1, 1, 1, 2, 1,
				1, 1, 1, 1, "default");
		batch = new SpriteBatch();
		walkSheet = new Texture(Gdx.files.internal("animation.png"));

		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 2, walkSheet.getHeight()/1);

		walkFrames = new TextureRegion[2];
		int index = 0;
		for(int i = 0; i < 1; i++){
			for(int j = 0; j < 2; j++){
				walkFrames[index++] = tmp[i][j];
			}
		}
		walk = new Animation<TextureRegion>(0.25f, walkFrames);
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
		enemy = new Texture("enemy.png");
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walk.getKeyFrame(stateTime, true);
		camera.update();

		draw();
		gameLogic();
		camera.position.set(rab.getX(), rab.getY(), 0); //this camera set has to be after gamelogic to keep it smooth :^)




	}


	public void draw(){
		//Sprite Batch shit
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(link, rab.getX(), rab.getY());
		batch.draw(enemy, 50, 50);
		batch.end();


		//System.out.println("(X, Y) = (" + rab.getX() + ", " + rab.getY()+")");
		//System.out.println("1");

	}
	public void animate(){
		batch.begin();
		batch.draw(currentFrame, rab.getX(), rab.getY());
		batch.end();
	}
	@Override
	public void dispose () {
		batch.dispose();
		enemy.dispose();
		shapeRenderer.dispose();
		//spriteBatch.dispose();
		walkSheet.dispose();

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
			rab.setY(rab.getY() + ySpeed * rab.getspd() * Gdx.graphics.getDeltaTime());
			rab.setDir(0);
		}
		else if(Gdx.input.isKeyPressed((Input.Keys.S))){
			rab.setY(rab.getY() - ySpeed * rab.getspd() * Gdx.graphics.getDeltaTime());
			rab.setDir(4);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			rab.setX(rab.getX() + xSpeed * rab.getspd() * Gdx.graphics.getDeltaTime());
			rab.setDir(2);
		}
		else if(Gdx.input.isKeyPressed((Input.Keys.A))){
			rab.setX(rab.getX() - xSpeed * rab.getspd() * Gdx.graphics.getDeltaTime());
			rab.setDir(6);
			animate();
		}

		rab.setanimation("default");
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
			rab.setanimation("attack");
		}

		rab.setspeedBonus(1);
		if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
			rab.setspeedBonus(3);
			System.out.println("Fuck");
		}

		rab.updatesprite();
	}
}
