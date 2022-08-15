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
    Drawable[] control = {core.rab, core.frog};
    float[] movement = new float[8];
    level1 lvl1 = new level1();
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;
    ArrayList<Drawable> drawingList;
    //Texture enemyTexture = enemyTexture = new Texture("");
    public GameScreen(core core){
        this.core = core;
        enemies = lvl1.getEnemyArray();
        core.rab.updatesprite();
        core.frog.updatesprite();
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
            if(keyCode == Inputs.Keys.SHIFT_RIGHT){
                Collections.reverse(control);
            }
            return true;
        }

    });
    }

    @Override
    public void render(float delta){
        //Screen Clear
        ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        core.elapse += Gdx.graphics.getDeltaTime();

        //Logic and Art
        checkInputs();
        drawObjects();
        enemyMoves();
        checkCollisions();

    }


    //Check checkCollisions
        //need to see if i can switch hitbox back with direct calls,
        //would help implement other shit
    public void checkAttack(float[] rabHitbox){
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

            //bounceback


        }

    }

    public void checkCollisions(){
        float[][] bounceback = {
            //X                                 Y
            {core.rab.getX(), movement[core.rab.getDir()] * -1}, //N
            {movement[core.rab.getDir()] * -1, movement[core.rab.getDir()] * -1},
            {movement[core.rab.getDir()] * -1, core.rab.getY()}, //E
            {movement[core.rab.getDir()] * -1, movement[core.rab.getDir()] * -1},
            {core.rab.getX(), movement[core.rab.getDir()] * -1}, //S
            {movement[core.rab.getDir()] * -1, movement[core.rab.getDir()] * -1},
            {movement[core.rab.getDir()] * -1, core.rab.getY()}, //W
            {movement[core.rab.getDir()] * -1, movement[core.rab.getDir()] * -1}
        }

        if(core.getCollision(
        enemies.get(i).getHitbox()[0], enemies.get(i).getHitbox()[1], enemies.get(i).getHitbox()[2],enemies.get(i).getHitbox()[3],
        core.rab.getHitbox()[0], core.rab.getHitbox()[1], core.rab.getHitbox()[2], core.rab.getHitbox()[3])){
            core.rab.setX(bounceback[core.rab.getDir()][0]);
            core.rab.setY(bounceback[core.rab.getDir()][1]);
            core.rab.sethp(core.rab.gethp() - 1);
        }

    }

    public void drawObjects(){
        //build the drawing list
        for(int i=0; i<enemies.size(); i++){
            drawingList.add(enemies.get(i));
        }
        drawingList.add(core.rab);
        drawingList.add(core.frog);

        //Sort from farthest to closest
        Collections.sort(drawingList, new Comparator<Drawable>() {
            @Override
            public int compare(Drawable lhs, Drawable rhs) {
                //Magic from StackOverflow
                return lhs.getY() > rhs.getY() ? -1 : (lhs.getY() < rhs.getY()) ? 1: 0;
            }
        });
        //Actually draw stuff
        for (int i = 0; i < drawingList.size(); i++){
            drawingList.get(i).draw(core.batch, core.elapse);
        }
        //Hud drawing
        core.batch.begin();
        core.font.draw(core.batch, "Rabbit Health: " + (int)core.rab.gethp, 10, 10);
        core.font.draw(core.batch, "Frog Health:" + (int)core.frog.gethp, 10, 60);
        core.batch.end();

        //clear so you dont draw over stuff
        drawingList.clear();
    }

//------------------------------------------------------------------------------------------------
    public void enemyMoves(){
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).move();
        }
    }
//------------------------------------------------------------------------------------------------

    //Check Inputs

    //If the control List works then we can do as an example below for inputs/Logic
    /*
        control[1].updatesprite();
        control[1].setY(control[0].getY());
        control[1].setX(control[0].getX());
        control[1].setDir(control[0].getDir());
        control[0].setanimation("walking");

        control[0].updateSprite();
        control[0].setY(control[0].getY() + control[0].getspd() * Gdx.graphics.getDeltaTime);
        control[0].setDir(0);
        control[0].setanimation("walking");

    */
    public void checkInputs(){
        movement[] = {core.rab.getY() + core.rab.getspd() * Gdx.graphics.getDeltaTime(),
            core.rab.getY() + core.rab.getspd() * Gdx.graphics.getDeltaTime(),
            core.rab.getX() + core.rab.getspd() * Gdx.graphics.getDeltaTime(),
            core.rab.getX() + core.rab.getspd() * Gdx.graphics.getDeltaTime(),
            core.rab.getY() - core.rab.getspd() * Gdx.graphics.getDeltaTime(),
            core.rab.getY() - core.rab.getspd() * Gdx.graphics.getDeltaTime(),
            core.rab.getY() - core.rab.getspd() * Gdx.graphics.getDeltaTime(),
            core.rab.getY() - core.rab.getspd() * Gdx.graphics.getDeltaTime()}


        core.rab.setanimation("default");
        if(Gdx.input.isKeyPressed(Input.Keys.W)){ //North
            //Update Frog first because it'll use the Rabbits info from last frame.
            core.frog.updatesprite();
            core.frog.setY(core.rab.getY());
            core.frog.setX(core.rab.getX());
            core.frog.setDir(core.rab.getDir());
            core.frog.setanimation("walking");

            core.rab.updatesprite();
            //core.rab.setY(core.rab.getY() + core.rab.getspd() * Gdx.graphics.getDeltaTime());
            core.rab.setY(movement[core.rab.getDir()]);
            core.rab.setDir(0);
            core.rab.setanimation("walking");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){ //South
            core.frog.updatesprite();
            core.frog.setY(core.rab.getY());
            core.frog.setX(core.rab.getX());
            core.frog.setDir(core.rab.getDir());
            core.frog.setanimation("walking");

            core.rab.updatesprite();
            //core.rab.setY(core.rab.getY() - core.rab.getspd() * Gdx.graphics.getDeltaTime());
            core.rab.setY(movement[core.rab.getDir()]);
            core.rab.setDir(4);
            core.rab.setanimation("walking");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){ //East
            core.frog.updatesprite();
            core.frog.setY(core.rab.getY());
            core.frog.setX(core.rab.getX());
            core.frog.setDir(core.rab.getDir());
            core.frog.setanimation("walking");

            core.rab.updatesprite();
            //core.rab.setX(core.rab.getX() + core.rab.getspd() * Gdx.graphics.getDeltaTime());
            core.rab.setY(movement[core.rab.getDir()]);
            core.rab.setDir(2);
            core.rab.setanimation("walking");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){ //West
            core.frog.updatesprite();
            core.frog.setY(core.rab.getY());
            core.frog.setX(core.rab.getX());
            core.frog.setDir(core.rab.getDir());
            core.frog.setanimation("walking");

            core.rab.updatesprite();
            //core.rab.setX(core.rab.getX() - core.rab.getspd() * Gdx.graphics.getDeltaTime());
            core.rab.setY(movement[core.rab.getDir()]);
            core.rab.setDir(6);
            core.rab.setanimation("walking");
        }
        core.rab.setspeedBonus(1);
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            core.rab.setspeedBonus(3);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();


        if(Gdx.input.isKeyJustPressed(Input.Keys.K) || core.rab.inAnimation()){
            float[] rabHitbox = core.rab.getSwordHitbox();
            checkAttack(rabHitbox);
            if(!core.rab.inAnimation()){core.rab.setElapse(0);}
        }

        //empty it
        movement = null;
    }


    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
        for(int i=0; i<enemies.size(); i++){
            enemies.get(i).dispose();
        }
        core.rab.dispose();
        core.frog.dispose(); //<- I dont think i need to dispose actually
        // since this isn't getting recreated every screen switch
    }
}
