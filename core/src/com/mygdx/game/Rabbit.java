package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Rabbit {

    private float xLoc, yLoc, health, healthBonus, magicPoints, magicPointsBonus, attack, attackBonus, magicAttack, magicAttackBonus;
    private float speed, speedBonus;
    private int dir;
    private String img, animation;
    public Texture rabbitTexture;
    public SpriteBatch batch;
    public TextureRegion[] regions;
    public Animation<TextureRegion> animation;
    //All Sprite file locations
    String[] walkingSprites = {"rab_stand_up.png", "rab_stand_up.png",
            "rab_stand_right.png", "rab_stand_right.png",
            "rab_stand.png", "rab_stand.png",
            "rab_stand_left.png", "rab_stand_left.png"};

    public Rabbit(float x, float y, int direction, float hp, float hpBonus, float mp, float mpBonus, float atk,
                  float atkBonus, float magicAtk, float magicAtkBonus, float spd, float spdBonus, String animationType){
        this.xLoc = x;
        this.yLoc = y;
        this.dir = direction;
        this.img = "rab_stand.png";
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
        batch = new SpriteBatch();
        regions = TextureRegion(rabbitTexture, rabbitTexture.getWidth()/2, rabbitTexture.getHeight());
        animation = new Animation<>(0.25f, regions);
    }
    public void draw(float elapse){
        batch.begin();
        batch.draw(animation.getKeyFrame(elapse, `loop` : true), xLoc, yLoc, 25, 25);
        batch.end();
    }
    public void updatesprite(){
        //rabbitTexture.dispose();
        //consider implementing Maps to have File and Files Rows/Columns?
        //also maybe consider switching to a Switch statement HUH
        if(animation == "default"){
            /*
            if (dir == 0){img = "rab_stand_up.png";} //North
            else if (dir == 1){img = "rab_stand_up.png";}
            else if (dir == 2){img = "rab_stand_right.png";} //East
            else if (dir == 3){img = "rab_stand_right.png";}
            else if (dir == 4){img = "rab_stand.png";} //South
            else if (dir == 5){img = "rab_stand.png";}
            else if (dir == 6){img = "rab_stand_left.png";} //West
            else if (dir == 7){img = "rab_stand_left.png";}
            */
            img = walkingSprites[dir];//should be faster way to implement ^^

        }
        else if (animation == "walking"){
            if (dir == 0){img = "rab_walk_up.png";} //North
            else if (dir == 1){img = "rab_walk_up.png";}
            else if (dir == 2){img = "rab_right_walk.png";} //East
            else if (dir == 3){img = "rab_right_walk.png";}
            else if (dir == 4){img = "rab_walk_down.png";} //South
            else if (dir == 5){img = "rab_walk_down.png";}
            else if (dir == 6){img = "rab_left_walk.png";} //West
            else if (dir == 7){img = "rab_left_walk.png";}
        }
        rabbitTexture = new Texture(img);
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
