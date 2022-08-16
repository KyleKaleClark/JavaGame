package com.mygdx.game;

public class keys {

    private float xLoc, yLoc;
    private hitbox hitBx;
    private String img;
    public keys(float x, float y, String file, hitbox hitBox){
        this.xLoc = x;
        this.yLoc = y;
        this.img = file;
        this.hitBx = hitBox;
    }

    public float getX(){return xLoc;}
    public void setX(float updatedx){xLoc = updatedx;}
    public float getY(){return yLoc;}
    public void setY(float updatedy){yLoc = updatedy;}
    public String getfile(){return img;}
    public hitbox getHitBox(){return hitBx;}
    public void setHitbox(hitbox updatedHitbox){hitBx = updatedHitbox;}
    public void setImg(String updatedimg){img = updatedimg;}

}
