package com.mygdx.game;

public class Rabbit {

    private float xLoc, yLoc;
    private int dir;
    private String img;

    public Rabbit(float x, float y, int direction, String img_location){
        this.xLoc = x;
        this.yLoc = y;
        this.img = img_location;
        this.dir = direction;
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
    public void setDir(int updatedDir){
        dir = updatedDir;
    }
    public void getDir(){
        return dir;
    }

}
