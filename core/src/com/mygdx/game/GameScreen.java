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

    //Texture enemyTexture = enemyTexture = new Texture("");
    public GameScreen(core core){
        this.core = core;
        enemies = lvl1.getEnemyArray();
        core.rab.updatesprite();

    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(
        new InputAdapter(){
        //Inputs handled here :0
        public boolean keyDown(int keyCode){
            if (keyCode == Input.Keys.ENTER){
                core.setScreen(new TitleScreen(core));
                core.rab.setanimation("walking");
                core.rab.updatesprite();
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

        core.batch.begin();
        //should draw all the enemies declared in level1;
        for(int i=0; i<enemies.size(); i++){

            core.batch.draw(enemies.get(i).getEnemyTexture(), enemies.get(i).getX(), enemies.get(i).getY());
            enemies.get(i).setX(enemies.get(i).getX() + 10);
        }
        //enemies.clear();

        core.batch.draw(core.rab.getRabbitTexture(), core.rab.getX(), core.rab.getY());
        core.batch.end();

    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
        for(int i=0; i<enemies.size(); i++){
            enemies.get(i).dispose();
        }
        core.rab.dispose();
    }
}
