package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import sun.awt.AWTThreading;

public class enemy implements Drawable{

    private float xLoc, yLoc, dist;
    private String img;
    public Texture enemyTexture;
    public TextureRegion[] regions;
    public Animation<TextureRegion> animation;
    public Random rand = new Random();
    private int n;
    private int elapse;
    private float width;
    private float height;
    public enemy(float x, float y, String img_location){
        this.xLoc = x;
        this.yLoc = y;
        this.img = img_location;
        this.enemyTexture = new Texture(img);
        //this.dist = distance;
        elapse = 0;
        n = rand.nextInt(8);
        regions = TextureRegion.split(enemyTexture, enemyTexture.getWidth()/1, enemyTexture.getHeight())[0];
        animation = new Animation<>(0.25f, regions);
        width = 150;
        height = 100;
    }
    public void draw(SpriteBatch batch, float elapse){
        batch.begin();
        batch.draw(animation.getKeyFrame(elapse, true), xLoc, yLoc, width, height);
        batch.end();
    }

    public void move(){
        //babys first AI
        elapse += 1;

        if ((int)elapse<=120){ //every 60 frames
            if(n==0){yLoc++;}
            else if(n==1){yLoc++; xLoc++;}
            else if(n==2){xLoc++;}
            else if(n==3){xLoc++; yLoc--;}
            else if(n==4){yLoc--;}
            else if(n==5){yLoc--; xLoc--;}
            else if (n==6){xLoc--;}
        }
        //System.out.println("System Time: " + elapse);
        if(elapse >= 240){elapse = 0;
            n = rand.nextInt(9); //9 because 8 directions + 1 inaction state
        }

    }

    public float getWidth(){return width;}
    public float getHeight(){return height-40;}
    public float[] getHitbox(){
        return new float[] {xLoc, yLoc, getWidth(), getHeight()};
    }

    public float getX(){
        return xLoc;
    }
    public void setX(float updatedx){
        xLoc = updatedx;
    }
    public float getY(){
        return yLoc;
    }
    public void setY(float updatedy){
        yLoc = updatedy;
    }
    public String fileLoc(){
        return img;
    }
    public void updatefile(String updatedimg){
        img = updatedimg;
    }
    public float getDist(){
        return dist;
    }
    public void setDist(float updatedDist){
      dist = updatedDist;
    }

    public void draw(){

    }
    public Texture getEnemyTexture(){
        return enemyTexture;
    }
    public void dispose(){
        enemyTexture = null;
        System.gc();
    }
}
