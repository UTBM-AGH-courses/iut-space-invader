package com.example.vareversat1.datgame.metier.entity;

import android.widget.ImageView;
import com.example.vareversat1.datgame.metier.Shifting;

/**
 * Représente le joueur
 */
public class Player extends Block {
    /**
     * Constructeur d'un joueur
     * @param speed vitesse du block
     * @param lifePoints nombre de vie du block
     * @param abs position du block sur l'axe des abscisses
     * @param ord position du block sur l'axe des ordonnées
     * @param imageView image du block
     */
    public Player(int speed, int lifePoints, int abs, int ord, ImageView imageView){
        super(speed, lifePoints, abs, ord, imageView);
    }
    /**
     * Déplace le joueur en fonction de shift
     * @param shift direction du déplacement
     */
    public void move(Shifting shift) {
        switch (shift){
            case RIGHT:
                setxAxis(getxAxis()+getSpeed());
                break;
            case LEFT :
                setxAxis(getxAxis()-getSpeed());
                break;
        }
    }
    /**
     * @return un boolean indiquant si le joueur a perdu toutes ses vies ou non
     */
    public Boolean isAlive(){
        return getLifePoints() > 0;
    }
}