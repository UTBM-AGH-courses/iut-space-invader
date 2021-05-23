package com.example.vareversat1.datgame.metier.manager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.vareversat1.datgame.metier.AbstractGame;
import com.example.vareversat1.datgame.metier.Shifting;
import com.example.vareversat1.datgame.metier.Utils;

/**
 * Created by Valentin on 23/03/2018.
 */

public class CustomSensorManager implements SensorEventListener {

    private AbstractGame abstractGame;
    private Sensor sensor;
    private SensorManager sensorManager;

    public CustomSensorManager(AbstractGame abstractGame, Sensor sensor, SensorManager sensorManager) {
        this.abstractGame = abstractGame;
        this.sensor = sensor;
        this.sensorManager = sensorManager;

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(abstractGame.isPlaying()) {
            if ((int) sensorEvent.values[Utils.DEVICE_TYPE] > 0) {
                abstractGame.getMovementManager().movePlayer(Shifting.LEFT, abstractGame.getPlayer(), abstractGame.getMap(), abstractGame.getGameActivity());
            } else if ((int) sensorEvent.values[Utils.DEVICE_TYPE] < 0) {
                abstractGame.getMovementManager().movePlayer(Shifting.RIGHT, abstractGame.getPlayer(), abstractGame.getMap(), abstractGame.getGameActivity());
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
