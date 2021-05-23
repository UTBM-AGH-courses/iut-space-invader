package com.example.vareversat1.datgame.metier.manager;

import android.util.Log;

import com.example.vareversat1.datgame.R;
import com.example.vareversat1.datgame.activity.GameActivity;
import com.example.vareversat1.datgame.metier.AbstractGame;
import com.example.vareversat1.datgame.metier.Map;
import com.example.vareversat1.datgame.metier.Utils;
import com.example.vareversat1.datgame.metier.entity.Enemy;
import com.example.vareversat1.datgame.metier.entity.TaskBlock;

import java.util.Random;

/**
 * Created by gulivet1 on 21/03/18.
 */

public class EnemyManager {

    Random randomize = new Random();

    /**
     * Total numbers of enemies
     */
    private int nbEnemies;
    public int getNbEnemies() {
        return nbEnemies;
    }
    public void setNbEnemies(int nbEnemies) {
        this.nbEnemies = nbEnemies;
    }

    /**
     * Enemy number of the first type
     */
    private int nbEnemy1;
    public int getNbEnemy1() { return nbEnemy1; }
    public void setNbEnemy1(int nbEnemy1) { this.nbEnemy1 = nbEnemy1; }

    /**
     * Enemy number of the first type
     */
    private int nbEnemy2;
    public int getNbEnemy2() { return nbEnemy2;}
    public void setNbEnemy2(int nbEnemy2) { this.nbEnemy2 = nbEnemy2; }

    /**
     * Set the difficulty of the game
     * @param difficulty Get from the radio button of the main view
     */
    public synchronized void setDifficulty(String difficulty){
        this.setNbEnemies(Utils.ENEMY_WAVE_LENGTH);

        if(difficulty.equals("HARD")){
            setNbEnemy2(Utils.ENEMY2_HARD);
        }
        else if(difficulty.equals("NORMAL")){
            setNbEnemy2(Utils.ENEMY2_NORMAL);
        }
        else{
            setNbEnemy2(Utils.ENEMY2_EASY);
        }
        setNbEnemy1(getNbEnemies()- getNbEnemy2());
        this.setNbEnemies(getNbEnemy2());
    }

    /**
     * Create a first type of enemy
     */
    private Enemy createEnemy1(int imageResource, Map map, GameActivity gameActivity){


        return new Enemy(Utils.SPEED_ENEMY,
                Utils.LIFE_ENEMY,
                randomize.nextInt((map.getWidth() - Utils.IMG_WIDTH)+1),
                Utils.MAP_LIMIT,
                Utils.createImage(imageResource, gameActivity));
    }

    /**
     * Create a second type of enemy
     */
    private Enemy createEnemy2(int imageResource, Map map, GameActivity gameActivity){


        return new Enemy(Utils.SPEED_ENEMY,
                Utils.LIFE_ENEMY2,
                randomize.nextInt((map.getWidth() - Utils.IMG_WIDTH)+1),
                Utils.MAP_LIMIT,
                Utils.createImage(imageResource, gameActivity));
    }

    /**
     * Create a new wave of enemies
     */
    public synchronized TaskBlock createEnemyInvasion(GameActivity gameActivity, Map map) {
        if (getNbEnemy1() == 0 && getNbEnemy2() == 0) {
            setNbEnemy2(getNbEnemies());
            setNbEnemy1(Utils.ENEMY_WAVE_LENGTH - getNbEnemy2());

        }
        if (getNbEnemy1() > 0) {
            setNbEnemy1(getNbEnemy1() - 1);
            return createEnemy1(R.drawable.enemy1, map, gameActivity);
        }
        else if (getNbEnemy2() > 0) {
            setNbEnemy2(getNbEnemy2() - 1);
            return createEnemy2(R.drawable.enemy2, map, gameActivity);
        }

        return null;
    }
}
