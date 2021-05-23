package com.example.vareversat1.datgame.metier.manager;

import com.example.vareversat1.datgame.activity.GameActivity;
import com.example.vareversat1.datgame.metier.Map;
import com.example.vareversat1.datgame.metier.Shifting;
import com.example.vareversat1.datgame.metier.entity.Player;
import com.example.vareversat1.datgame.metier.entity.TaskBlock;

import java.util.Iterator;
import java.util.List;

/**
 * Created by gulivet1 on 21/03/18.
 */

public class MovementManager {

    /**
     * Set the coordinates of the player and apply them in the view while the player is not dead
     * @param shift direction of the moving
     */
    public synchronized void movePlayer(Shifting shift, Player player, Map map, GameActivity gameActivity){
        if (player == null)
            return;
        switch (shift){
            case LEFT:
                if (map.canGoLeft(player) )
                    player.move(shift);
                break;
            case RIGHT:
                if (map.canGoRight(player) )
                    player.move(shift);
                break;
        }
        gameActivity.moveBlock(player);
    }

    /**
     * Move a block into the playground
     * @param blockList list of Blocks of the game
     */
    public synchronized void moveBlocks(List<TaskBlock> blockList){
        Iterator<TaskBlock> itBlocks = blockList.iterator();
        while (itBlocks.hasNext()) {
            TaskBlock b = itBlocks.next();
            b.move();
            if(!b.getIsAlive()){
                itBlocks.remove();
            }
        }
    }
}
