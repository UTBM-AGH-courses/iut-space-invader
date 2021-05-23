package com.example.vareversat1.datgame.metier;

import com.example.vareversat1.datgame.metier.entity.TaskBlock;

/**
 * Représentant un objet capable de mettre à jour un block si celui-çi notifie un changement
 */
public interface ObserverGame {
    /**
     * Permet d'effectuer les changements necessaires pour le bon
     * déroulement du jeu quand un block se manifeste
     * @param block le block qui doit être mis à jour
     */
    void update(TaskBlock block);
}