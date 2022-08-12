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

public class GameScreen extends ScreenAdapter {
    core core;
    ArrayList<enemy> enemies = new ArrayList<enemy>();
    level1 lvl1 = new level1();
    OrthographicCamera camera;
    //Texture enemyTexture = enemyTexture = new Texture("");
    public GameScreen(core core){
        this.core = core;
        enemies = lvl1.getEnemyArray();
        core.rab.updatesprite();
        camera = new OrthographicCamera(480, 320);

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




        //System.out.println(core.camera.toString());


        //should draw all the enemies declared in level1;
        for(int i=0; i<enemies.size(); i++){
            //core.batch.draw(enemies.get(i).getEnemyTexture(), enemies.get(i).getX(), enemies.get(i).getY());
            enemies.get(i).draw(core.batch, core.elapse);
            enemies.get(i).move(); //babys first AI
        }
        //enemies.clear();
        //core.batch.draw(core.rab.getRabbitTexture(), core.rab.getX(), core.rab.getY());
        core.batch.begin();
        core.batch.end();
        core.rab.draw(core. batch, core.elapse);


        //System.out.println(core.camera.position.toString());
        // I want to see how this increments, so i can Modulus it
        //to determine enemy movement every x frames.
        //System.out.println("Animation state: " + core.rab.getanimation() + ", Direction: " + core.rab.getDir());
    }

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
            //System.out.println("FUCK");
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
            //System.out.println("Fuck");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
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
