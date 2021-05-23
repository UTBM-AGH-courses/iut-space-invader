package com.example.vareversat1.datgame.activity;

import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vareversat1.datgame.R;
import com.example.vareversat1.datgame.metier.AbstractGame;
import com.example.vareversat1.datgame.metier.Game;
import com.example.vareversat1.datgame.metier.Utils;
import com.example.vareversat1.datgame.metier.entity.Block;

/**
 * Created by vareversat1 on 31/01/18.
 */

public class GameActivity extends AppCompatActivity {


    private FrameLayout frameLayout;
    private Button pauseButton;

    private AbstractGame game;

    private TextView scoreText;
    private TextView vieText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /**
         * Set the window to display
         */
        setContentView(R.layout.game_window);
        frameLayout = findViewById(R.id.frameLayout);

        /**
         * Get the accelerometer sensor
         */
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor =  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        /**
         * Get the screen size
         */
        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);

        /**
         * Set correct values of speed according to the siez of the screen
         */
        setCorrectValues(screenSize);

        /**
         * Get the difficulty the player chose
         */
        Intent intent = getIntent();
        String difficulty = (intent.getStringExtra(Intent.EXTRA_TEXT));

        /**
         * Get the device type (phone or tablet)
         */
        getTheDeviceType();

        /**
         * Creation of the game
         */
        game = new Game(this, screenSize.y, screenSize.x, difficulty, sensor, sensorManager);

        /**
         * Get the window content
         */
        pauseButton = findViewById(R.id.pauseButton);
        scoreText = findViewById(R.id.scoreText);
        vieText = findViewById(R.id.vieText);

        /**
         * Set the action of the pause button
         */
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pauseActionButton();
            }
        });


    }


    /**
     * Allows the user to pause the game
     */
    private void pauseActionButton(){
        if(game.isPlaying()) {
            game.setIsPlaying(false);
            pauseButton.setText(R.string.resumeText);
        }
        else {
            pauseButton.setText(R.string.pauseText);
            game.setIsPlaying(true);

        }
    }

    /**
     * Set correct values according to the screen size
     * @param screenSize the screen size
     */
    private void setCorrectValues(Point screenSize){
        Utils.IMG_WIDTH = screenSize.x / 6;
        Utils.IMG_HEIGHT = screenSize.y / 7;

        Utils.SPEED_ENEMY = screenSize.y / 85;
        Utils.SPEED_SHOT = screenSize.y / 128;

        Utils.SPEED_PLAYER = screenSize.x / 57;
    }

    /**
     * Get the device type (Phone or Tablet)
     */
    private void getTheDeviceType(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        if (diagonalInches>=6.5){
            Utils.DEVICE_TYPE = Utils.TABLET;
        }
        else{
            Utils.DEVICE_TYPE = Utils.PHONE;
        }
    }

    /**
     * Life cycle management
     */
    @Override
    protected void onStop(){
        super.onStop();
        game.setIsPlaying(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.setIsPlaying(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.setIsPlaying(false);
    }

    /**
     * Update the life TextView
     */
    public void updatePlayerLife(){
        vieText.setText(String.format(getString(R.string.lifeText),game.getPlayer().getLifePoints()));
    }

    /**
     * Update the score TextView
     */
    public void updateScore(){
        scoreText.setText(String.format(getString(R.string.scoreText),game.getCurrentScore()));
    }

    /**
     * Stop the game and display the main menu
     */
    public void defeat(){
        game.setIsPlaying(false);
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(game.getCurrentScore()));
        this.startActivityForResult(myIntent, 0);
    }

    /**
     * Add a block to the playground
     * @param block block to add
     */
    public void addBlock(Block block){
        if (block.getxAxis() >= 0){
            block.getImageView().getHitRect(block.getHitbox());
            frameLayout.addView(block.getImageView(), new FrameLayout.LayoutParams(Utils.IMG_WIDTH, Utils.IMG_WIDTH));
            moveBlock(block);
        }
    }
    /**
     * Move a block in the playground
     * @param block block to move
     */
    public void moveBlock(Block block){
        block.getImageView().getHitRect(block.getHitbox());
        block.getImageView().setX(block.getxAxis());
        block.getImageView().setY(block.getyAxis());
    }
    /**
     * Remove a block of the playground
     * @param block to delete
     */
    public void removeBlock(Block block){
        frameLayout.removeView(block.getImageView());
    }


}