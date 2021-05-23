package com.example.vareversat1.datgame.metier.entity;
import android.widget.ImageView;

import com.example.vareversat1.datgame.metier.collision.CollisionEnemy;

/**
 * Représente un ennemi
 */
public class Enemy extends TaskBlock {
    /**
     * Constructeur d'un ennemi, qui prend en paramètre certains renseignements
     * Attribue une collision et lance la tâche de l'ennemi
     */
    public Enemy(int speed, int lifePoints, int xAxis, int yAxis, ImageView imageView){
        super(speed, lifePoints, xAxis, yAxis, imageView, new CollisionEnemy());
    }
    /**
     * déplace l'ennemi et vérifie si il est entré en collision avec quelque chose
     */
    @Override
    public void move() {
        setyAxis(getyAxis()+getSpeed());
        change(this);
    }
    /**
     * Passe l'attribut isAlive à false pour spécifier que le block est mort
     */
    @Override
    public void killBlock(){
        isAlive = false;
    }
}
