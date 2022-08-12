package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
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



public class core extends Game {
    SpriteBatch batch;
    Texture enemy, link;
    BitmapFont font;
    OrthographicCamera camera;
    Rabbit rab;
    Animation<TextureRegion> walk;
    float elapse;

    @Override
    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        rab = new Rabbit(100, 100, 4, 20, 1, 1, 1, 2, 1, 1, 1, 200, 1, "default");
        elapse = 0f;
        //camera = new OrthographicCamera(480, 320);
        //camera.update();
        setScreen(new TitleScreen(this));
    }

    @Override
    public void dispose(){
        batch.dispose();
        font.dispose();
    }

    public boolean getCollision(float x1, float y1, float height1, float width1,
    float x2, float y2, float height2, float width2){
        return(x1 < x2+width2 && x1 + width1 > x2 && y1 < y2+height2 &&
        height1 + y1 > y2);
    }


}
