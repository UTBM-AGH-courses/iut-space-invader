package com.example.vareversat1.datgame.metier;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.util.Log;

import com.example.vareversat1.datgame.activity.GameActivity;
import com.example.vareversat1.datgame.R;
import com.example.vareversat1.datgame.metier.entity.Enemy;
import com.example.vareversat1.datgame.metier.entity.Player;
import com.example.vareversat1.datgame.metier.entity.TaskBlock;
import com.example.vareversat1.datgame.metier.manager.BlockManager;
import com.example.vareversat1.datgame.metier.manager.CustomSensorManager;
import com.example.vareversat1.datgame.metier.manager.EnemyManager;
import com.example.vareversat1.datgame.metier.manager.MovementManager;
import com.example.vareversat1.datgame.metier.manager.ShotManager;

import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe concrète faisant la médiation entre les différents blocks
 * du jeu, qui sont modifiés grâce aux actions de l'utilisateur
 */
public class Game extends AbstractGame implements ObserverGame{
    /**
     * Let us know where are we in the refresh loop
     */
    private int timer = 0;

    public Game(GameActivity gameActivity, int height, int width, String difficulty, Sensor sensor, SensorManager manager){

        timerTask = new TimerTask();
        /**
         * Creation of all the managers
         */
        shotManager = new ShotManager();
        blockManager = new BlockManager();
        enemyManager = new EnemyManager();
        movementManager = new MovementManager();
        customSensorManager = new CustomSensorManager(this, sensor, manager);

        setGameActivity(gameActivity);
        enemyManager.setDifficulty(difficulty);

        /**
         * Creation of the playground
         */
        map = new Map(height, width);

        /**
         * We create add add the player to the playground
         */
        player = new Player(Utils.SPEED_PLAYER,
                Utils.LIFE_PLAYER,
                map.getWidth()/2,
                map.getHeight()-Utils.IMG_HEIGHT,
                Utils.createImage(R.drawable.player, gameActivity));

        gameActivity.addBlock(player);


        /**
         * The game start
         */
        startGame();
    }

    @Override
    public synchronized void startGame(){
        setMainThreadIsAlive(true);
        timerTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,this);
    }


    /**
     * Stop the game and empty the list of blocks
     */
    @Override
    public synchronized void closeGame(){
        if (isPlaying()){
            player = null;
            Iterator<TaskBlock> itBlocks = blocks.iterator();
            while (itBlocks.hasNext()) {
                itBlocks.next().killBlock();
            }
            setIsPlaying(false);
            setMainThreadIsAlive(false);
        }
    }
    /**
     * Call whenever an ObservableBlock notify a modification
     * This method test the potential different events for a block and apply the correct behave
     * @param block block we must update
     */
    @Override
    public synchronized void update(final TaskBlock block) {
        if ( getMap().isOutOfMap(block) ){
            blockManager.removeBlock(block, getGameActivity(), this);
            if (block instanceof Enemy){
                player.setLifePoints(player.getLifePoints()-1);
                getGameActivity().updatePlayerLife();
                if ( !player.isAlive() )
                    closeGame();
            }
        }
        else if (!block.getCollision().collision(block,this))
            getGameActivity().moveBlock(block);
    }

    /**
     * Method call every time the TimerTask do a TICK
     * Allows to set the refresh rate of the game
     */
    @Override
    public synchronized void updateUI(){

        if(isPlaying()) {
            timer += 1;
            if (timer % 35 == 0) {
                TaskBlock taskBlock = enemyManager.createEnemyInvasion(getGameActivity(), getMap());
                if(taskBlock != null){
                    blockManager.addBlock(taskBlock, getBlocks(), getGameActivity(), this);
                }
            }
            if (timer % 20 == 0) {
                blockManager.addBlock(shotManager.createShot(player, getGameActivity()), getBlocks(), getGameActivity(), this);
            }
            getGameActivity().updateScore();
            getGameActivity().updatePlayerLife();
            movementManager.moveBlocks(getBlocks());
        }
    }
}