package com.example.vareversat1.datgame.metier.manager;

import com.example.vareversat1.datgame.activity.GameActivity;
import com.example.vareversat1.datgame.metier.ObserverGame;
import com.example.vareversat1.datgame.metier.entity.TaskBlock;

import java.util.List;

/**
 * Created by gulivet1 on 21/03/18.
 */

public class BlockManager {

    /**
     * Add a Block at the list of blocks of the game
     * @param block block to add
     */
    public synchronized void addBlock(final TaskBlock block, List<TaskBlock> blockList, GameActivity gameActivity, ObserverGame game){
        if (block.attach(game)){
            blockList.add(block);
            gameActivity.removeBlock(block);
            gameActivity.addBlock(block);
        }
    }

    /**
     * Remove a Block at the list of blocks of the game
     * @param block block to delete
     */
    public synchronized void removeBlock(final TaskBlock block, GameActivity gameActivity, ObserverGame game){
        if (block.detach(game)){
            block.killBlock();
            gameActivity.removeBlock(block);
        }
    }
}
