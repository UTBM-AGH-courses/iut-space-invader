package com.example.vareversat1.datgame.metier.collision;

import android.graphics.Rect;
import android.util.Log;

import com.example.vareversat1.datgame.metier.ObserverGame;
import com.example.vareversat1.datgame.metier.entity.Enemy;
import com.example.vareversat1.datgame.metier.entity.TaskBlock;
import com.example.vareversat1.datgame.metier.AbstractGame;
import com.example.vareversat1.datgame.metier.Utils;

import java.util.Iterator;

public class CollisionShot implements ICollision {
    /**
     * Parcoure la liste des ennemis et verifie si il n'y a pas eu de collision avec shot
     * Si il y a eu collision, les deux blocks sont enlevés du jeu
     *
     * @param shot un tir du jeu
     * @param game le jeu
     * @return vrai si une collision s'est produite
     */
    @Override
    public synchronized boolean collision(TaskBlock shot, AbstractGame game) {
        Iterator<TaskBlock> itBlocks = game.getBlocks().iterator();
        TaskBlock block;
        while (itBlocks.hasNext()) {
            block = itBlocks.next();
            if (block instanceof Enemy
                    && Rect.intersects(block.getHitbox(), shot.getHitbox())) {
                if (block.getLifePoints() - 1 == 0) {

                    game.setCurrentScore(game.getCurrentScore() + 1);

                    if(game.getCurrentScore()%20 ==0 && game.getEnemyManager().getNbEnemies() < Utils.ENEMY_WAVE_LENGTH){
                        Log.i("e", "i");
                        game.getEnemyManager().setNbEnemies(game.getEnemyManager().getNbEnemies()+1);
                    }
                    game.getBlockManager().removeBlock(block, game.getGameActivity(), (ObserverGame)game);
                } else {
                    block.setLifePoints(block.getLifePoints() - 1);
                }
                game.getBlockManager().removeBlock(shot, game.getGameActivity(), (ObserverGame)game);
                return true;
            }
        }
        return false;
    }
}
