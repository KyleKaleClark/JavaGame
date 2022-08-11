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

public class TitleScreen extends ScreenAdapter {
    core core;

    public TitleScreen(core core){
        this.core = core;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(
        new InputAdapter(){
            //Input reading
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
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        core.batch.begin();
        core.font.draw(core.batch, "Lets START (press enter)", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        core.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }


}
