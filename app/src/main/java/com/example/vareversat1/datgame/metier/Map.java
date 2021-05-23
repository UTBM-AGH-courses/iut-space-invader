package com.example.vareversat1.datgame.metier;

import android.util.Log;

import com.example.vareversat1.datgame.metier.entity.Block;
import com.example.vareversat1.datgame.metier.entity.Shot;

/**
 * Représente le terrain de jeu sur lequel se déroule le jeu. Elle a une hauteur, une largeur et vérifie si la
 * position des blocks est valide.
 */
public class Map {
    private final int height;
    public int getHeight() {
        return height-1;
    }

    private final int width;
    public int getWidth() {
        return width-1;
    }

    public Map(int height, int width){
        this.height = height;
        this.width = width;
    }
    /**
     * Verifie si un block est sorti du terrain de jeu
     * @param block à vérifier
     * @return vrai si le block est sorti du jeu
     */
    public boolean isOutOfMap(Block block){
        return block.getyAxis() < Utils.MAP_LIMIT || block.getyAxis() > getHeight();
    }
    /**
     * Verifie si un block peut se déplacer à gauche sans sortir du terrain
     * @param block à vérifier
     * @return vrai si il peut
     */
    public boolean canGoLeft(Block block){
        return block.getxAxis() > Utils.MAP_LIMIT;
    }
    /**
     * Verifie si un block peut se déplacer à droite sans sortir du terrain
     * @param block à vérifier
     * @return vrai si il peut
     */
    public boolean canGoRight(Block block){
        return block.getxAxis()+Utils.IMG_WIDTH < getWidth();
    }
}
