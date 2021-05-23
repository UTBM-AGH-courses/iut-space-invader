package com.example.vareversat1.datgame.metier.collision;

import android.graphics.Rect;
import android.util.Log;

import com.example.vareversat1.datgame.metier.ObserverGame;
import com.example.vareversat1.datgame.metier.Utils;
import com.example.vareversat1.datgame.metier.entity.Shot;
import com.example.vareversat1.datgame.metier.entity.TaskBlock;
import com.example.vareversat1.datgame.metier.AbstractGame;

import java.util.Iterator;

public class CollisionEnemy implements ICollision {
    /**
     * Parcours la liste des tirs et verifie si il n'y a pas eu de collision avec annemi
     * Si il y a eu collision, les deux blocks sont enlevés du jeu
     * @param ennemi un ennemi du jeu
     * @param game le jeu
     * @return vrai si une collision s'est produite
     */
    @Override
    public synchronized boolean collision(TaskBlock ennemi, AbstractGame game){
        Iterator<TaskBlock> itBlocks = game.getBlocks().iterator();
        TaskBlock block;
        while (itBlocks.hasNext()) {
            block = itBlocks.next();
            if (block instanceof Shot
                    && Rect.intersects(block.getHitbox(), ennemi.getHitbox())) {
                if(ennemi.getLifePoints()-1 ==0) {

                    game.setCurrentScore(game.getCurrentScore() + 1);

                    if(game.getCurrentScore()%20 ==0 && game.getEnemyManager().getNbEnemies() < Utils.ENEMY_WAVE_LENGTH){
                        Log.i("e", "i");
                        game.getEnemyManager().setNbEnemies(game.getEnemyManager().getNbEnemies()+1);
                    }
                    game.getBlockManager().removeBlock(ennemi, game.getGameActivity(), (ObserverGame)game);

                }
                else{
                    ennemi.setLifePoints(ennemi.getLifePoints()-1);
                }
                game.getBlockManager().removeBlock(block, game.getGameActivity(), (ObserverGame)game);
                return true;
            }
        }
        return false;
    }
}