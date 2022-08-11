package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class enemy {

    private float xLoc, yLoc, dist;
    private String img;
    public Texture enemyTexture;
    public SpriteBatch batch;
    public TextureRegion[] regions;
    public Animation<TextureRegion> animation;
    public Random rand = new Random();
    private int n;
    public enemy(float x, float y, String img_location){
        this.xLoc = x;
        this.yLoc = y;
        this.img = img_location;
        this.enemyTexture = new Texture(img);
        //this.dist = distance;
        batch = new SpriteBatch();
        regions = TextureRegion(enemyTexture, enemyTexture.getWidth()/1, enemyTexture.getHeight());
        animation = new Animation<>(0.25f, regions);
    }
    public void draw(float elapse){
        batch.begin();
        batch.draw(animation.getKeyFrame(elapse, `loop` : true), xLoc, yLoc, 25, 25);
        batch.end();
    }

    public void move(float elapse){
        //babys first AI
        if (elapse%60==0){ //every 60 frames
            n = rand.nextInt(8);
            if(n==0){yLoc += 20;}
            else if(n==2){xLoc += 20;}
            else if(n==4){yLoc -= 20;}
            else if (n==6){xLoc -=20;}
        }
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
