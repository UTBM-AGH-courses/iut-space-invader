package com.example.vareversat1.datgame.metier.entity;

import android.widget.ImageView;

import com.example.vareversat1.datgame.metier.collision.ICollision;

/**
 * Un TaskBlock est un block spécial qui peut entrer en collision et mourir
 */
public abstract class TaskBlock extends Block {
    protected Boolean isAlive = true;
    public boolean getIsAlive(){
        return isAlive;
    }

    private final ICollision collision;
    public ICollision getCollision() {
        return collision;
    }
    /**
     * Constructeur de TaskBlock, qui peut en plus des usages des Block, entrer en collision
     * @param speed vitesse du block
     * @param lifePoints nombre de vie du block
     * @param abs position du block sur l'axe des abscisses
     * @param ord position du block sur l'axe des ordonnées
     * @param imageView image du block
     * @param collision ajoute la façon sont le block doit entrer en collision
     */
    public TaskBlock(int speed, int lifePoints, int abs, int ord, ImageView imageView, ICollision collision){
        super(speed, lifePoints, abs, ord, imageView);
        this.collision = collision;
    }
    /**
     * Définie comment un TaskBlock doit mourir
     */
    public abstract void killBlock();

    /**
     * Définie la manière dont un block doit bouger
     */
    public abstract void move();
}