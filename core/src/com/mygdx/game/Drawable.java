package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Drawable {

    void draw(SpriteBatch batch, float elapse);
    void updatesprite();
    void dispose();

    float getX();
    float getY();
    void setX(float updatedx);
    void setY(float updatedy);

    void setDir(int updatedDir);
    int getDir();

    float gethp();
    void sethp(float updatedhp);

    float gethpbonus();
    void sethpbonus(float updatedhpbonus);

    float getmp();
    void setmp(float updatedmp)

    float getmpbonus()
    void setmpbonus(float updatedmpbonus);

    float getAtk();
    void setAtk(float updatedAtk);

    float getAtkBonus();
    void setAtkBonus(float updatedAtkBonus);

    float getMg();
    void setMg(float updatedMg);

    float getMgAtkBonus();
    void setMgAtkBonus(float updatedMgAtkBonus);

    float getspd();
    void setspeed(float updatedspeed);

    float getspdbonus();
    void setspeedBonus(float updatedspeedBonus);

    boolean inAnimation();
    void setInAnimation(boolean updatedAnim);

    void setElapse(int updatedElapse);
    void innerTimerReset();
}
