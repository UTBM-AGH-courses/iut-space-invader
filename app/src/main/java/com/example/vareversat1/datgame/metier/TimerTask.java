package com.example.vareversat1.datgame.metier;

import android.os.AsyncTask;

/**
 * Created by gulivet1 on 15/03/18.
 */
public class TimerTask extends AsyncTask<AbstractGame, Void, Void> {
    @Override
    protected Void doInBackground(AbstractGame... abstractGame) {
        if (abstractGame[0] != null) {
            final AbstractGame game = abstractGame[0];
            while (game.isMainThreadIsAlive()) {
                game.getGameActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(game.isPlaying()){
                            game.updateUI();
                        }
                    }
                });
                try {
                    Thread.sleep(Utils.MAIN_THREAD_SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            game.getGameActivity().defeat();
            return null;
        }
        else
            return null;
    }
}