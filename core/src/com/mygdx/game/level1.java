package com.mygdx.game;

import java.util.ArrayList;

public class level1 {

    public level1(){
    }

    public ArrayList<enemy> getEnemyArray(){
        ArrayList<enemy> enemies = new ArrayList<enemy>();
        enemies.add(new enemy(0, 50, "enemy.png"));
        enemies.add(new enemy(200, 500, "enemy.png"));
        enemies.add(new enemy(100, 500, "enemy.png"));
        enemies.add(new enemy(150, 50, "enemy.png"));
        enemies.add(new enemy(1000, 50, "enemy.png"));
        enemies.add(new enemy(0, 750, "enemy.png"));
        enemies.add(new enemy(25, 800, "enemy.png"));
        enemies.add(new enemy(0, 0, "enemy.png"));
        enemies.add(new enemy(0, 200, "enemy.png"));

        return(enemies);
    }

}
