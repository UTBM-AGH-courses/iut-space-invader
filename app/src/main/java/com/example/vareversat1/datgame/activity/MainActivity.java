package com.example.vareversat1.datgame.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vareversat1.datgame.R;
import com.example.vareversat1.datgame.metier.manager.DataManager;

/**
 * Created by vareversat1 on 24/01/18.
 */

public class MainActivity extends AppCompatActivity {

    private TextView highScore;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataManager = new DataManager(this);
        Intent intent = getIntent();
        if( intent.getStringExtra(Intent.EXTRA_TEXT) != null){
            String score = intent.getStringExtra(Intent.EXTRA_TEXT);
            Toast toast = Toast.makeText(this, "You loose !", Toast.LENGTH_SHORT);
            toast.show();
            if(Integer.parseInt(score)>dataManager.readHighScore())
                dataManager.writeHighScore(Integer.parseInt(score));
        }

        //Test de l'orientation
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                setContentView(R.layout.main_window_portrait);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                setContentView(R.layout.main_window_land);
                break;
        }

        //Get du bouton
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openGameActivity();
            }
        });

        highScore = findViewById(R.id.highScore);
        highScore.setText(String.valueOf(dataManager.readHighScore()));

    }



    private void openGameActivity() {
        Intent myIntent = new Intent(this, GameActivity.class);
        RadioGroup difficulty = findViewById(R.id.difficulty);
        RadioButton radioButton = findViewById(difficulty.getCheckedRadioButtonId());
        myIntent.putExtra(Intent.EXTRA_TEXT, radioButton.getText());
        this.startActivityForResult(myIntent, 0);
    }

}
