package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Rabbit {

    private float xLoc, yLoc, health, healthBonus, magicPoints, magicPointsBonus, attack, attackBonus, magicAttack, magicAttackBonus;
    private float speed, speedBonus;
    private int dir;
    private String img, animation;
    public Texture rabbitTexture;
    public TextureRegion[] regions;
    public Animation<TextureRegion> animatonAnimation;
    int textureDivWidth, textureDivHeight;
    float frameDivider;

    Texture[] walkingSprites = {new Texture("rab_walk_up.png"), new Texture("rab_walk_up.png"),
            new Texture("rab_right_walk.png"), new Texture("rab_right_walk.png"),
            new Texture("rab_walk_down.png"), new Texture("rab_walk_down.png"),
            new Texture("rab_left_walk.png"), new Texture("rab_left_walk.png")};
    Texture[] standingSprites = {new Texture("rab_stand_up1.png"), new Texture("rab_stand_up1.png"),
            new Texture("link_right_stand.png"), new Texture("link_right_stand.png"),
            new Texture("rab_stand1.png"), new Texture("rab_stand1.png"),
            new Texture("link_left_stand.png"), new Texture("link_left_stand.png")};

    public Rabbit(float x, float y, int direction, float hp, float hpBonus, float mp, float mpBonus, float atk,
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



}
