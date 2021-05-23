package com.example.vareversat1.datgame.metier.manager;

import com.example.vareversat1.datgame.R;
import com.example.vareversat1.datgame.activity.GameActivity;
import com.example.vareversat1.datgame.metier.Utils;
import com.example.vareversat1.datgame.metier.entity.Player;
import com.example.vareversat1.datgame.metier.entity.Shot;
import com.example.vareversat1.datgame.metier.entity.TaskBlock;

/**
 * Created by Valentin on 23/03/2018.
 */

public class ShotManager {

    /**
     * Create a new shot with and add it to the game
     */
    public synchronized TaskBlock createShot(Player player, GameActivity gameActivity){
        if(player.getxAxis() <= Utils.MAP_LIMIT ){
            return new Shot(Utils.SPEED_SHOT,
                    Utils.LIFE_SHOT,
                    Utils.MAP_LIMIT,
                    player.getyAxis()-Utils.IMG_HEIGHT,
                    Utils.createImage(R.drawable.missile, gameActivity));
        }
        return new Shot(Utils.SPEED_SHOT,
                Utils.LIFE_SHOT,
                player.getxAxis(),
                player.getyAxis()-Utils.IMG_HEIGHT,
                Utils.createImage(R.drawable.missile, gameActivity));

    }
}
