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
	Texture rabbitAnimation;
	TextureRegion[] walkFrames;
	hitbox rabHitbox;
	//SpriteBatch spriteBatch;
	float X = 200;
	float Y = 200;
	float xSpeed = 200;
	float ySpeed = 200;
	float stateTime;
	int spriteRows = 1;
	int spriteColumns = 2;
	String link_sprite = "link_down.png";


	@Override
	public void create () {
		rab = new Rabbit(X, Y, 4, 20, 1, 1, 1, 2, 1, 1, 1, 1, 1, "default");
		//height width

		batch = new SpriteBatch();

		//I should be switching out the animation.png depending on whats going on
		System.out.println("Here?");

		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();

		camera = new OrthographicCamera(480, 320);

	}

	@Override
	public void render () { //Where the game should be drawn
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		rabbitAnimation = new Texture(rab.fileLoc());

		TextureRegion[][] tmp = TextureRegion.split(rabbitAnimation, rabbitAnimation.getWidth() / spriteColumns, rabbitAnimation.getHeight()/spriteRows);

		walkFrames = new TextureRegion[spriteColumns];
		int index = 0;
		for(int i = 0; i < spriteRows; i++){
			for(int j = 0; j < spriteColumns; j++){
				walkFrames[index++] = tmp[i][j];
			}
		}
		walk = new Animation<TextureRegion>(0.25f, walkFrames);

		link = new Texture(rab.fileLoc());

		enemy = new Texture("enemy.png");
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walk.getKeyFrame(stateTime, true);
		camera.update();

		draw();
		gameLogic();
		camera.position.set(rab.getX(), rab.getY(), 0); //this camera set has to be after gamelogic to keep it smooth :^)


		//setting these up for garbage collection so i'm not burning memory
		link.dispose(); enemy.dispose(); rabbitAnimation.dispose();
		walk = null; link = null; enemy = null; walkFrames = null; rabbitAnimation = null;
		System.gc();


	}


	public void draw(){
		//Sprite Batch shit



		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(currentFrame, rab.getX(), rab.getY());
		batch.draw(enemy, 50, 50);
		batch.end();
		rab.updatesprite();

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
		rabbitAnimation.dispose();

	}
	@Override
	public void resize(int width, int height){

	}


	public void gameLogic(){
		ArrayList<enemy> enemyList = new ArrayList<enemy>();
		rabHitbox = new hitbox(rab.getX(), rab.getY(), 100, 50);

		//testing collisions
		boolean collide = rabHitbox.collision(50, 50, 50, 50);
		//if (collide){System.out.println("HIT HIT HIT");}

		//Input reading
		if(Gdx.input.isKeyPressed(Input.Keys.W)){ //North
			rab.setY(rab.getY() + ySpeed * rab.getspd() * Gdx.graphics.getDeltaTime());
			rab.setDir(0);
			walkAnimation();
		}
		else if(Gdx.input.isKeyPressed((Input.Keys.S))){ //South
			rab.setY(rab.getY() - ySpeed * rab.getspd() * Gdx.graphics.getDeltaTime());
			rab.setDir(4);
			walkAnimation();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){ //East
			rab.setX(rab.getX() + xSpeed * rab.getspd() * Gdx.graphics.getDeltaTime());
			rab.setDir(2);
			walkAnimation();
		}
		else if(Gdx.input.isKeyPressed((Input.Keys.A))){ //West
			rab.setX(rab.getX() - xSpeed * rab.getspd() * Gdx.graphics.getDeltaTime());
			rab.setDir(6);
			walkAnimation();
			System.out.println(rab.getanimation());
			System.out.println(rab.getDir());
			System.out.println(rab.fileLoc());
			//animate();
		}

		rab.setanimation("default");
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
			rab.setanimation("attack");
		}

		rab.setspeedBonus(1);
		if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
			rab.setspeedBonus(3);
			//System.out.println("Fuck");
		}

		//rab.updatesprite(); //<- Moving to draw function??

		//easy quit
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();
	}
	public void walkAnimation(){
		rab.setanimation("walking");
		rab.updatesprite();
	}
}