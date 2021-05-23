package com.example.vareversat1.datgame.metier.entity;

import android.widget.ImageView;

import com.example.vareversat1.datgame.metier.Utils;
import com.example.vareversat1.datgame.metier.collision.CollisionShot;

/**
 * Représente un tir
 */
public class Shot extends TaskBlock {
    /**
     * Constructeur d'un shot, qui prend en paramètre certains renseignements
     * Attribue une collision et lance la tâche du shot
     * Constructeur d'un block
     * @param speed vitesse du block
     * @param lifePoints nombre de vie du block
     * @param abs position du block sur l'axe des abscisses
     * @param ord position du block sur l'axe des ordonnées
     * @param imageView image du block
     */
    public Shot(int speed, int lifePoints, int abs, int ord, ImageView imageView){
        super(speed, lifePoints, abs, ord, imageView, new CollisionShot());
    }
    /**
     * Déplace le tir et vérifie si il est entré en collision avec quelque chose
     */
    @Override
    public void move() {
        setyAxis(getyAxis()- getSpeed());
        change(this);
    }
    /**
     * Passe l'attribut isAlive à false pour spécifier que le block est mort
     */
    @Override
    public void killBlock() {
        isAlive = false;
    }
}
