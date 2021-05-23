package com.example.vareversat1.datgame.metier.entity;

import android.graphics.Rect;
import android.widget.ImageView;

import com.example.vareversat1.datgame.metier.Shifting;

/**
 * Représente un block, c'est à dire une entité du jeu, qui recense toutes les entitées possibles du jeu
 */
public abstract class Block extends ObservableBlock {
    private int speed;
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private int lifePoints;
    public int getLifePoints() {
        return lifePoints;
    }
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    private int xAxis;
    public int getxAxis() {
        return xAxis;
    }
    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    private int yAxis;
    public int getyAxis() {
        return yAxis;
    }
    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    private int index=0;
    public int getIndex(){return index;}
    public void setIndex(int index){ this.index = index; }

    private ImageView imageView;
    public ImageView getImageView() {
        return imageView;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    private Rect hitbox;
    public Rect getHitbox() {return hitbox;}
    /**
     * Constructeur d'un block
     * @param speed vitesse du block
     * @param lifePoints vie du block
     * @param xAxis xAxis du block
     * @param yAxis yAxis du block
     * @param imageView image représantant le block
     */
    public Block(int speed, int lifePoints, int xAxis, int yAxis, ImageView imageView){
        setSpeed(speed);
        setLifePoints(lifePoints);
        setxAxis(xAxis);
        setyAxis(yAxis);
        setImageView(imageView);
        hitbox = new Rect();
    }
}
