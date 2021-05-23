package com.example.vareversat1.datgame.metier.manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.vareversat1.datgame.metier.Utils;

/**
 * Created by gulivet1 on 21/03/18.
 */

public class DataManager {

    private Activity activity;

    public DataManager(Activity activity) {
        this.activity = activity;
    }


    /**
     * Get the saved high score of the game
     */
    public int readHighScore(){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        int defaultValue = 0;
        return sharedPref.getInt(String.valueOf(Utils.HIGH_SCORE_KEY), defaultValue);
    }


    /**
     * Write a new high score
     */
    public void writeHighScore(int score){

        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(String.valueOf(Utils.HIGH_SCORE_KEY), score);
        editor.apply();



    }






}
