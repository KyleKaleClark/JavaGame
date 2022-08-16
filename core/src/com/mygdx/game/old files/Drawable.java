package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Drawable {

    void draw(SpriteBatch batch, float elapse);
    float getX();
    float getY();
}
