package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/*
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameScreen extends ScreenAdapter {
    core core;
    ArrayList<enemy> enemies = new ArrayList<enemy>();
    level1 lvl1 = new level1();
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;
    ArrayList<Drawable> drawingList;
    //Texture enemyTexture = enemyTexture = new Texture("");
    public GameScreen(core core){
        this.core = core;
        enemies = lvl1.getEnemyArray();
        core.rab.updatesprite();
        camera = new OrthographicCamera(480, 320);
        drawingList = new ArrayList<Drawable>();






        //this is only here to visualize hitboxes

        shapeRenderer = new ShapeRenderer();


    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(
        new InputAdapter(){
        //Inputs handled here :O
        public boolean keyDown(int keyCode){
            if (keyCode == Input.Keys.ENTER){
                core.setScreen(new TitleScreen(core));

            }
            if (keyCode == Input.Keys.K){

            }
            return true;
        }

    });
    }

    @Override
    public void render(float delta){
        //logic and such



        ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        core.elapse += Gdx.graphics.getDeltaTime();

        checkInputs();
        drawObjects();
        enemyMoves();

        //should draw all the enemies declared in level1;
        /*
        boolean drawingCut = true;
        for(int i=0; i<enemies.size(); i++){
            //core.batch.draw(enemies.get(i).getEnemyTexture(), enemies.get(i).getX(), enemies.get(i).getY());

            if(enemies.get(i).getY() >= core.rab.getY()){
                enemies.get(i).draw(core.batch, core.elapse);
                core.rab.draw(core.batch, core.elapse);

                //System.out.println(i);
            }

            else {
                core.rab.draw(core.batch, core.elapse);
                enemies.get(i).draw(core.batch, core.elapse);
                //enemies.get(i).move(); //babys first AI
            }
        }
        drawingCut = true;
        */
        //enemies.clear();
        //core.batch.draw(core.rab.getRabbitTexture(), core.rab.getX(), core.rab.getY());
        //core.batch.begin();
        //core.batch.end();
        //core.rab.draw(core.batch, core.elapse);
        //checkCollisions();

        //System.out.println(core.camera.position.toString());
        // I want to see how this increments, so i can Modulus it
        //to determine enemy movement every x frames.
        //System.out.println("Animation state: " + core.rab.getanimation() + ", Direction: " + core.rab.getDir());
    }


    //Check checkCollisions
    public void checkCollisions(float[] rabHitbox){
        for(int i=0; i <enemies.size(); i++){
            float[] hitbox = enemies.get(i).getHitbox();

            if(core.getCollision(
            enemies.get(i).getHitbox()[0], enemies.get(i).getHitbox()[1], enemies.get(i).getHitbox()[2],enemies.get(i).getHitbox()[3],
                    rabHitbox[0], rabHitbox[1], rabHitbox[2], rabHitbox[3]))
            {
                enemies.get(i).dispose();
                enemies.remove(i);
                //continue;
                break;
            }
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(255, 255, 255, 1);
            shapeRenderer.rect(enemies.get(i).getHitbox()[0], enemies.get(i).getHitbox()[1], enemies.get(i).getHitbox()[2],enemies.get(i).getHitbox()[3]);
            shapeRenderer.rect(core.rab.getHitbox()[0], core.rab.getHitbox()[1], core.rab.getHitbox()[2], core.rab.getHitbox()[3]);
            shapeRenderer.rect(rabHitbox[0], rabHitbox[1], rabHitbox[2], rabHitbox[3]);
            shapeRenderer.end();
            //System.out.println("x: " + core.rab.getHitbox()[0] + " y: " + core.rab.getHitbox()[1]
              //      + " width: " + core.rab.getHitbox()[2] + " height: " + core.rab.getHitbox()[3]);
        }

    }

    public void drawObjects(){
        for(int i=0; i<enemies.size(); i++){
            drawingList.add(enemies.get(i));
        }
        drawingList.add(core.rab);

        Collections.sort(drawingList, new Comparator<Drawable>() {

            @Override
            public int compare(Drawable lhs, Drawable rhs) {
                return lhs.getY() > rhs.getY() ? -1 : (lhs.getY() < rhs.getY()) ? 1: 0;
            }
        });

        for (int i = 0; i < drawingList.size(); i++){
            drawingList.get(i).draw(core.batch, core.elapse);
        }
        drawingList.clear();
    }

    public void enemyMoves(){
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).move();
        }
    }

//------------------------------------------------------------------------------------------------

    //Check Inputs
    public void checkInputs(){
        core.rab.setanimation("default");
        if(Gdx.input.isKeyPressed(Input.Keys.W)){ //North
            core.rab.updatesprite();
            core.rab.setY(core.rab.getY() + core.rab.getspd() * Gdx.graphics.getDeltaTime());
            core.rab.setDir(0);
            core.rab.setanimation("walking");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){ //South
            core.rab.updatesprite();
            core.rab.setY(core.rab.getY() - core.rab.getspd() * Gdx.graphics.getDeltaTime());
            core.rab.setDir(4);
            core.rab.setanimation("walking");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){ //East
            core.rab.updatesprite();
            core.rab.setX(core.rab.getX() + core.rab.getspd() * Gdx.graphics.getDeltaTime());
            core.rab.setDir(2);
            core.rab.setanimation("walking");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){ //West
            core.rab.updatesprite();
            core.rab.setX(core.rab.getX() - core.rab.getspd() * Gdx.graphics.getDeltaTime());
            core.rab.setDir(6);
            core.rab.setanimation("walking");
        }
        core.rab.setspeedBonus(1);
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            core.rab.setspeedBonus(3);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();

        //System.out.println("inAnimation?: " + core.rab.inAnimation());
        //System.out.println("inAnimation outside loop: " + core.rab.inAnimation());
        if(Gdx.input.isKeyJustPressed(Input.Keys.K) || core.rab.inAnimation()){
            float[] rabHitbox = core.rab.getSwordHitbox();
            checkCollisions(rabHitbox);
            if(!core.rab.inAnimation()){core.rab.setElapse(0);}
            //shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            //shapeRenderer.setColor(255, 255, 255, 1);

            //shapeRenderer.end();
        }
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
        for(int i=0; i<enemies.size(); i++){
            enemies.get(i).dispose();
        }
        core.rab.dispose(); //<- I dont think i need to dispose actually
        // since this isn't getting recreated every screen switch
    }
}
