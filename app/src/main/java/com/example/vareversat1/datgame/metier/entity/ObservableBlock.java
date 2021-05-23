package com.example.vareversat1.datgame.metier.entity;

import com.example.vareversat1.datgame.metier.ObserverGame;
import java.util.HashSet;
import java.util.Set;

/**
 * Permet à un ObservableBlock de notifier ses observeurs, dont il gère lui-même la liste, d'un changement
 */
public class ObservableBlock {
    protected Set<ObserverGame> observers = new HashSet<>();
    /**
     * Ajoute un observeur à la liste
     * @param observer le nouvel observeur
     * @return
     */
    public boolean attach(ObserverGame observer){
        return observers.add(observer);
    }
    /**
     * Retire un observeur de la liste
     * @param observer l'observer à enlever
     * @return
     */
    public boolean detach(ObserverGame observer){
        return observers.remove(observer);
    }
    /**
     * Prévient tous les observeurs de l'ObservableBlock qu'il a changé
     * @param block le bloc qui a changé
     */
    protected void change(TaskBlock block){
        for (ObserverGame obs : observers)
            obs.update(block);
    }
}