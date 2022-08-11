package com.mygdx.game;

public class level1 {

    public level1(){
    }

    public ArrayList<enemy> getEnemyArray(){
        ArrayList<enemy> enemies = new ArrayList<enemy>();
        enemies.add(new enemy(0, 50, "enemy.png"));
        enemies.add(new enemy(50, 50, "enemy.png"));
        enemies.add(new enemy(100, 50, "enemy.png"));
        enemies.add(new enemy(150, 50, "enemy.png"));
        enemies.add(new enemy(200, 50, "enemy.png"));
        enemies.add(new enemy(250, 50, "enemy.png"));
        enemies.add(new enemy(0, 100, "enemy.png"));
        enemies.add(new enemy(0, 150, "enemy.png"));
        enemies.add(new enemy(0, 200, "enemy.png"));

        return(enemies);
    }

}
