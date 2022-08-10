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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class GameScreen extends ScreenAdapter{
    core core;
    ArrayList<enemy> enemies = new ArrayList<enemy>();
    level1 lvl1 = new level1();
    public GameScreen(core_replacement core){
        this.core = core;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(
        new InputAdapter(){
        //Inputs handled here :0
        public boolean keyDown(int keyCode){
            if (keyCode == Input.Keys.ENTER){
                core.setScreen(new GameScreen(core));
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
        enemies = lvl1.getEnemyArray();
        core.batch.begin();
        //should draw all the enemies declared in level1;
        for(int i=0; i<enemies.size(); i++){
            core.batch.draw(enemies.get(i).fileLoc(), enemies.get(i).getX(), enemies.get(i).getY());
        }
        core.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
        enemies.clear();
    }
}
