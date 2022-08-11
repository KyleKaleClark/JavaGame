package com.mygdx.game;

public class hitbox {

    private float xLoc, yLoc, hitboxWidth, hitboxHeight;
    public hitbox(float x, float y, float width, float height){
        this.xLoc = x;
        this.yLoc = y;
        this.hitboxWidth = width;
        this.hitboxHeight = height;
    }

    public boolean collision(float otherX, float otherY, float otherW, float otherH){
      if(xLoc < otherX + otherW && xLoc + hitboxWidth > otherX && otherY < otherY + otherH && hitboxHeight + yLoc > otherY){
        return true;
      }
      return false;
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
    public float getWidth(){
        return hitboxWidth;
    }
    public void setWidth(float updatedWidth){
        hitboxWidth = updatedWidth;
    }
    public float getHeight(){
        return hitboxHeight;
    }
    public void setHeight(float updatedHeight){
        hitboxHeight = updatedHeight;
    }
}
