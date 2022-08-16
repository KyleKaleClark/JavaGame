/*
package com.mygdx.game;
import java.lang.Math;
import java.util.ArrayList;

public class targetting {
    ArrayList<enemy> enemyList = new ArrayList<enemy>();
    int targetSel = 0;
    //ArrayList<float> enemyDist = new ArrayList<float>(); //these are calculated in Java

    //actually would be of rabbit class ofc
    enemy rabbit = new enemy(10, 10, "notdog.png", 0);

    enemy e1 = new enemy(0, 0, "dog.png", 0);
    enemy e2 = new enemy(1, 0, "dog.png", 0);
    enemy e3 = new enemy(2, 5, "dog.png", 0);
    enemy e4 = new enemy(24, 1, "dog.png", 0);

    public targetting(float x, float y, String img_location){
        this.xLoc = x;
        this.yLoc = y;
        this.img = img_location;
    }
    public target(){
      //this can be done from a file and for loop once implemented
      enemyList.add(e1);
      enemyList.add(e2);
      enemyList.add(e3);
      enemyList.add(e4);

      //calculates distance for each enemy from rabbit, and returns it to them
      for(int i = 0; i<enemyList.size(); i++){
        enemyList.get(i).setDist((Math.sqrt( Math.pow((enemyList.get(i).getX()-rabbit.getX()), 2) + (Math.pow((enemyList.get(i).getY - rabbit.getY()), 2)))));
      }

      //sort based off distances, should be Insertion sort
      for(int j = 1; j<enemyList.size(); j++){
        int k = j-1;
        //enemy temp = enemyList.get(j);
        while((k > -1)&&(enemyList.get(k).getDist > enemyList.get(j).getDist)){
          enemyList.get(k+1) = enemyList.get(k);
          k--;
        }
        enemyList.set(k+1, enemyList.get(j));
      }
      //find angle and set direction of rabbit.
      if(Gdx.input.isKeyPressed(Input.Keys.Q)){ //this would releastically be the target button, Q is temp ofc
  			float theta = Math.atan(((enemyList.get(targetSel).getX()-rabbit.getX())) / (enemyList.get(targetSel).getY()-rabbit.getY()));
  		}
      if(Gdx.input.isKeyPressed(Input.Keys.R)){ //this would releastically be the target switch, R is temp ofc
  			targetSel += 1;
        if(targetSel > enemyList.size()){targetSel = 0;}//loop back
  		}

      //set Sprite Direction
      //between angles, and in Quadrant 1
      if(theta < 60 && theta > 30 && enemyList.get(targetSel).getX() > 0 && enemyList.get(targetSel) > 0){
        rabbit.setDir(1);
      }


}
*/
