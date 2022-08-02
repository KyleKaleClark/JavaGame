package com.mygdx.game;

public class Rabbit {

    private float xLoc, yLoc;
    private String img;

    public Rabbit(float x, float y, String img_location){
        this.xLoc = x;
        this.yLoc = y;
        this.img = img_location;
    }

    public float returnX(){
        return xLoc;
    }
    public void setX(float updatedx){
        xLoc = updatedx;
    }
    public float returnY(){
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
}
