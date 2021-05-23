package com.example.vareversat1.datgame.metier.collision;

import com.example.vareversat1.datgame.metier.entity.TaskBlock;
import com.example.vareversat1.datgame.metier.AbstractGame;
import com.example.vareversat1.datgame.metier.entity.TaskBlock;

public interface ICollision {
    boolean collision(TaskBlock block, AbstractGame game);
}
