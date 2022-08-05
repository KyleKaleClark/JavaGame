package com.mygdx.game;

public class Rabbit {

    private float xLoc, yLoc, health, healthBonus, magicPoints, magicPointsBonus, attack, attackBonus, magicAttack, magicAttackBonus;
    private float speed, speedBonus;
    private int dir;
    private String img, animation;

    public Rabbit(float x, float y, int direction, float hp, float hpBonus, float mp, float mpBonus, float atk,
                  float atkBonus, float magicAtk, float magicAtkBonus, float spd, float spdBonus, String animationType){
        this.xLoc = x;
        this.yLoc = y;
        this.dir = direction;
        this.img = "link_up.png";
        this.health = hp;
        this.healthBonus = hpBonus;
        this.magicPoints = mp;
        this.magicPointsBonus = mpBonus;
        this.attack = atk;
        this.attackBonus = atkBonus;
        this.magicAttack = magicAtk;
        this.speed = spd * spdBonus;
        this.speedBonus = spdBonus;
        this.animation = animationType;
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
    public String getanimation(){return animation;}
    public void setanimation(String newanimation){animation = newanimation;}

    public void updatesprite(){
        if (dir == 0 && animation == "default"){img = "link_up.png";} //North
        else if (dir == 1){img = "link_up.png";}
        else if (dir == 2){img = "link_right.png";} //East
        else if (dir == 3){img = "link_right.png";}
        else if (dir == 4){img = "link_down.png";} //South
        else if (dir == 5){img = "link_down.png";}
        else if (dir == 6){img = "link_left.png";} //West
        else if (dir == 7){img = "link_left.png";}
        if (dir == 4 && animation == "attack"){img = "link_sword_down.png";}
    }
    public void setDir(int updatedDir){
        dir = updatedDir;
    }
    public int getDir(){
        return dir;
    }

    public float gethp(){return health;}
    public void sethp(float updatedhp){health = updatedhp;}

    public float gethpbonus(){return healthBonus;}
    public void sethpbonus(float updatedhpbonus){healthBonus = updatedhpbonus;}

    public float getmp(){return magicPoints;}
    public void setmp(float updatedmp){magicPoints = updatedmp;}

    public float getmpbonus(){return magicPointsBonus;}
    public void setmpbonus(float updatedmpbonus){magicPointsBonus = updatedmpbonus;}

    //need to do the other ones, will do it at work B)

    public float getspd(){return speed * speedBonus;}
    public void setspeed(float updatedspeed){speed = updatedspeed;}

    public float getspdbonus(){return speedBonus;}
    public void setspeedBonus(float updatedspeedBonus){speedBonus = updatedspeedBonus;}

}
