
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class enemy {

    private float xLoc, yLoc, dist;
    private String img;
    Texture enemyTexture;

    public enemy(float x, float y, String img_location){
        this.xLoc = x;
        this.yLoc = y;
        this.img = img_location;
        this.enemyTexture = new Texture(img);
        //this.dist = distance;
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
