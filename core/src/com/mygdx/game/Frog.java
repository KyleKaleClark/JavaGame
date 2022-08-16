package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Frog implements Drawable{

    private float xLoc, yLoc, health, healthBonus, magicPoints, magicPointsBonus, attack, attackBonus, magicAttack, magicAttackBonus;
    private float speed, speedBonus;
    private int dir;
    private String img, animation;
    public Texture rabbitTexture;
    public TextureRegion[] regions;
    public Animation<TextureRegion> animatonAnimation;
    int textureDivWidth, textureDivHeight;
    float frameDivider;
    float width;
    float height;
    int elapse;
    boolean inAnimation;

    Texture[] walkingSprites = {new Texture("frog_walk_up.png"), new Texture("frog_walk_up.png"),
            new Texture("frog_walk_right.png"), new Texture("frog_walk_right.png"),
            new Texture("frog_walk_down.png"), new Texture("frog_walk_down.png"),
            new Texture("frog_walk_left.png"), new Texture("frog_walk_left.png")};
    Texture[] standingSprites = {new Texture("frog_stand_up.png"), new Texture("frog_stand_up.png"),
            new Texture("frog_stand_right.png"), new Texture("frog_stand_right.png"),
            new Texture("frog_stand_down.png"), new Texture("frog_stand_down.png"),
            new Texture("frog_stand_left.png"), new Texture("frog_stand_left.png")};



    public Frog(float x, float y, int direction, float hp, float hpBonus, float mp, float mpBonus, float atk,
                  float atkBonus, float magicAtk, float magicAtkBonus, float spd, float spdBonus, String animationType){
        this.xLoc = x;
        this.yLoc = y;
        this.dir = direction;
        this.img = "rab_walk_down.png";
        this.health = hp;
        this.healthBonus = hpBonus;
        this.magicPoints = mp;
        this.magicPointsBonus = mpBonus;
        this.attack = atk;
        this.attackBonus = atkBonus;
        this.magicAttack = magicAtk;
        this.magicAttackBonus = magicAtkBonus;
        this.speed = spd * spdBonus;
        this.speedBonus = spdBonus;
        this.animation = animationType;
        rabbitTexture = new Texture(img);
        regions = TextureRegion.split(rabbitTexture, rabbitTexture.getWidth()/2, rabbitTexture.getHeight())[0];
        animatonAnimation = new Animation<>(frameDivider, regions);
        textureDivWidth = 2;
        textureDivHeight = 1;
        frameDivider = 0.25f;
        width = 100;
        height = 64;
        elapse = 0;
        inAnimation = false;
    }
    public void draw(SpriteBatch batch, float elapse){
        batch.begin();
        batch.draw(animatonAnimation.getKeyFrame(elapse, true), xLoc, yLoc, 100, 100);
        batch.end();
    }
    public void updatesprite(){
        if(animation == "default"){
            textureDivWidth = 1; textureDivHeight = 1;
            rabbitTexture = standingSprites[dir];
        }
        else if (animation == "walking"){
            textureDivWidth = 2; textureDivHeight = 1;
            rabbitTexture = walkingSprites[dir];//should be faster way to implement ^^
        }
        regions = TextureRegion.split(rabbitTexture, rabbitTexture.getWidth()/textureDivWidth, rabbitTexture.getHeight()/textureDivHeight)[0];
        animatonAnimation = new Animation<>(frameDivider, regions);
        //System.out.println("rabbitTexture" + rabbitTexture.toString());
    }
    public Texture getRabbitTexture(){
        return rabbitTexture;
    }

    public void dispose(){
        rabbitTexture = null;
        System.gc();
    }
    public String fileLoc(){return img;}

    public float getX(){return xLoc;}
    public void setX(float updatedx){xLoc = updatedx;}

    public float getY(){return yLoc;}
    public void setY(float updatedy){yLoc = updatedy;}

    public float getWidth(){return width;}
    public float getHeight(){return height;}
    public float[] getHitbox(){
        return new float[] {xLoc, yLoc, getWidth(), getHeight()};
    }

    public String getanimation(){return animation;}
    public void setanimation(String newanimation){animation = newanimation; updatesprite();}

    public void setDir(int updatedDir){dir = updatedDir;}
    public int getDir(){return dir;}

    public float gethp(){return health;}
    public void sethp(float updatedhp){health = updatedhp;}

    public float gethpbonus(){return healthBonus;}
    public void sethpbonus(float updatedhpbonus){healthBonus = updatedhpbonus;}

    public float getmp(){return magicPoints;}
    public void setmp(float updatedmp){magicPoints = updatedmp;}

    public float getmpbonus(){return magicPointsBonus;}
    public void setmpbonus(float updatedmpbonus){magicPointsBonus = updatedmpbonus;}

    public float getAtk(){return attack  * attackBonus;}
    public void setAtk(float updatedAtk){attack = updatedAtk;}

    public float getAtkBonus(){return attackBonus;}
    public void setAtkBonus(float updatedAtkBonus){attackBonus = updatedAtkBonus;}

    public float getMg(){return magicAttack * magicAttackBonus;}
    public void setMg(float updatedMg){magicAttack = updatedMg;}

    public float getMgAtkBonus(){return magicAttackBonus;}
    public void setMgAtkBonus(float updatedMgAtkBonus){magicAttackBonus = updatedMgAtkBonus;}

    public float getspd(){return speed * speedBonus;}
    public void setspeed(float updatedspeed){speed = updatedspeed;}

    public float getspdbonus(){return speedBonus;}
    public void setspeedBonus(float updatedspeedBonus){speedBonus = updatedspeedBonus;}

    public boolean inAnimation(){return inAnimation;}
    public void setInAnimation(boolean updatedAnim){inAnimation = updatedAnim;}

    public void setElapse(int updatedElapse){elapse = updatedElapse;}

    public void innerTimerReset(){elapse = 0;}
    public float[] getSwordHitbox(){
        float[][][] swordHitbox = {
                {{xLoc-100, yLoc, 100, 50},{xLoc-50, yLoc+25, 100, 75},{xLoc, yLoc+65, 100, 75}},//Up
                {{xLoc+100, yLoc, 100, 50},{xLoc+50, yLoc-25, 100, 50},{xLoc, yLoc-50, 100, 50}},
                {{xLoc, yLoc+65, 100, 50},{xLoc+50, yLoc+25, 100, 75},{xLoc+100, yLoc, 100, 50}},//Right
                {{xLoc, yLoc+65, 100, 50},{xLoc+50, yLoc+25, 100, 75},{xLoc+100, yLoc, 100, 50}},
                {{xLoc+100, yLoc, 100, 50},{xLoc+50, yLoc-25, 100, 50},{xLoc, yLoc-50, 100, 50}},//Down
                {{xLoc+100, yLoc, 100, 50},{xLoc+50, yLoc-25, 100, 50},{xLoc, yLoc-50, 100, 50}},
                {{xLoc, yLoc-50, 50, 100},{xLoc-50, yLoc-50, 100, 100},{xLoc-100, yLoc, 100, 50}},//Left
                {{xLoc+100, yLoc, 100, 50},{xLoc+50, yLoc-25, 100, 50},{xLoc, yLoc-50, 100, 50}}
        };
        elapse++;
        if(elapse < 10){
            inAnimation = true;
            return swordHitbox[getDir()][0];
        }
        else if(elapse >=10 && elapse <=15){
            inAnimation = true;
            return swordHitbox[getDir()][1];
        }
        else if(elapse >= 15 && elapse <25){
            inAnimation = true;
            return swordHitbox[getDir()][2];
        }
        inAnimation = false;
        swordHitbox = null;
        System.gc();
        return new float[]{0f, 0f, 0f, 0f};
    }

}
