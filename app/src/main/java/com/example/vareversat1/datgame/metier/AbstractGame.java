package com.example.vareversat1.datgame.metier;

import android.os.AsyncTask;

import com.example.vareversat1.datgame.activity.GameActivity;
import com.example.vareversat1.datgame.metier.entity.Player;
import com.example.vareversat1.datgame.metier.entity.TaskBlock;
import com.example.vareversat1.datgame.metier.manager.BlockManager;
import com.example.vareversat1.datgame.metier.manager.CustomSensorManager;
import com.example.vareversat1.datgame.metier.manager.EnemyManager;
import com.example.vareversat1.datgame.metier.manager.MovementManager;
import com.example.vareversat1.datgame.metier.manager.ShotManager;

import java.util.Vector;
import java.util.concurrent.locks.Lock;

public abstract class AbstractGame {

    private boolean mainThreadIsAlive;
    public void setMainThreadIsAlive(boolean mainThreadIsAlive) { this.mainThreadIsAlive = mainThreadIsAlive; }
    public boolean isMainThreadIsAlive() { return mainThreadIsAlive; }

    protected AsyncTask<AbstractGame, Void, Void> timerTask;

    /**
     * Define the managers of the game
     */
    protected ShotManager shotManager;
    protected EnemyManager enemyManager;
    public EnemyManager getEnemyManager() { return enemyManager; }

    protected CustomSensorManager customSensorManager;

    protected BlockManager blockManager;
    public BlockManager getBlockManager() {return blockManager; }

    protected MovementManager movementManager;
    public MovementManager getMovementManager() { return movementManager; }


    /**
     * Activity of the game
     */
    private GameActivity gameActivity;
    public void setGameActivity(GameActivity activity){ gameActivity = activity;}
    public GameActivity getGameActivity() {
        return gameActivity;
    }

    /**
     * Playground of the game
     */
    protected Map map;
    public Map getMap() {
        return map;
    }

    /**
     * Player isPlaying
     */
    protected Player player;
    public Player getPlayer() {
        return player;
    }

    /**
     * Vector containing all blocks of the game
     */
    protected final Vector<TaskBlock> blocks = new Vector<>();
    public Vector<TaskBlock> getBlocks() {
        return blocks;
    }

    /**
     * Tell if yes or no the game is running
     */
    private boolean isPlaying = true;
    public boolean isPlaying() { return isPlaying;}
    public void setIsPlaying(boolean bool){ isPlaying = bool;}

    /**
     * Score of the player who is playing
     */
    private int currentScore;
    public int getCurrentScore() {
        return currentScore;
    }
    public void setCurrentScore(int score) {
        this.currentScore = score;
    }

    /**
     * Start the game
     */
    public abstract void startGame();

    /**
     * Stop the game and empty the list of blocks
     */
    public abstract void closeGame();

    public abstract void updateUI();
}