package com.example.vareversat1.datgame.metier;

import android.widget.ImageView;

import com.example.vareversat1.datgame.activity.GameActivity;

/**
 * Permet de stocker les informations de l'application pour
 * qu'elles soient accessibles dans tout le code facilement
 */
public class Utils {
    public static final int ENEMY_WAVES = 20;
    public static final int MAP_LIMIT = 0;
    /**
     * Taille des entités du jeu
     */
    public static final int IMG_SIZE = 60;
    public static int IMG_WIDTH = 250;
    public static int IMG_HEIGHT = 320;
    /**
     * Paramètres des entités du jeu de base
     */
    public static final int ENEMY_WAVE_LENGTH = 10;
    public static int SPEED_PLAYER = 0;
    public static int SPEED_ENEMY = 1;
    public static int SPEED_SHOT = 3;
    public static final int LIFE_PLAYER = 3;
    public static final int LIFE_ENEMY = 1;
    public static final int LIFE_ENEMY2 = 2;
    public static final int LIFE_SHOT = 1;
    /**
     * Difficulty
     */
    public static final int ENEMY2_EASY = 2;
    public static final int ENEMY2_NORMAL = 6;
    public static final int ENEMY2_HARD = 8;

    /**
     * Device type
     */
    public static final int TABLET = 1;
    public static final int PHONE = 0;
    public static int DEVICE_TYPE = 0;


    public static final int MAIN_THREAD_SLEEP = 35;
    public static final int HIGH_SCORE_KEY = 0;

    /**
     * Crée une image utilisable dans la vue à partir de la source
     * @param source url de l'image à créer
     * @return une image utilisable pour l'affichage
     */
    public static ImageView createImage(int source, GameActivity game){
        ImageView imageView = new ImageView(game);
        imageView.setImageResource(source);
        imageView.setMaxHeight(IMG_SIZE);
        return imageView;
    }
}
