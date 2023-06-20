package com.example.pedrapapeletesoura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    ImageView backButton;
    Switch modeDarkSwitch;
    ConstraintLayout helpScreen;
    TextView title1, title2, text1, text2, text3, titleRock, titlePaper, titleScissors;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        backButton = findViewById(R.id.btnBack);
        modeDarkSwitch = findViewById(R.id.swDarkMode);
        helpScreen = findViewById(R.id.clHelpScreen);
        title1 = findViewById(R.id.tvTitle1);
        title2 = findViewById(R.id.tvTitle2);
        text1 = findViewById(R.id.tvText1);
        text2 = findViewById(R.id.tvText2);
        text3 = findViewById(R.id.tvText3);
        titleRock = findViewById(R.id.tvRock);
        titlePaper = findViewById(R.id.tvPaper);
        titleScissors = findViewById(R.id.tvScissors);

        modeDarkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    activateDarkMode();
                } else {
                    disableDarkMode();
                }
            }
        });

        if(preferences.getString("darkMode", "disabled").equals("activated")) {
            modeDarkSwitch.setChecked(true);
        } else {
            modeDarkSwitch.setChecked(false);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        if(preferences.getString("darkMode",null) == null) {
            SharedPreferences.Editor preferencesEdit = preferences.edit();
            preferencesEdit.putString("darkMode", "disabled");
            preferencesEdit.apply();
        }

        if(preferences.getString("darkMode", null).equals("activated")) {
            activateDarkMode();
        } else {
            disableDarkMode();
        }
    }

    private void disableDarkMode() {
        SharedPreferences.Editor preferencesEdit = preferences.edit();
        preferencesEdit.putString("darkMode", "disabled");
        preferencesEdit.apply();

        helpScreen.setBackgroundResource(R.color.white);
        backButton.setImageResource(R.drawable.back_icon);
        title1.setTextColor(getResources().getColor(R.color.black));
        title2.setTextColor(getResources().getColor(R.color.black));
        text1.setTextColor(getResources().getColor(R.color.black));
        text2.setTextColor(getResources().getColor(R.color.black));
        text3.setTextColor(getResources().getColor(R.color.black));
        titleRock.setTextColor(getResources().getColor(R.color.black));
        titlePaper.setTextColor(getResources().getColor(R.color.black));
        titleScissors.setTextColor(getResources().getColor(R.color.black));
    }

    private void activateDarkMode() {
        SharedPreferences.Editor preferencesEdit = preferences.edit();
        preferencesEdit.putString("darkMode", "activated");
        preferencesEdit.apply();

        helpScreen.setBackgroundResource(R.color.blackModern);
        backButton.setImageResource(R.drawable.back_icon2);
        title1.setTextColor(getResources().getColor(R.color.white));
        title2.setTextColor(getResources().getColor(R.color.white));
        text1.setTextColor(getResources().getColor(R.color.white));
        text2.setTextColor(getResources().getColor(R.color.white));
        text3.setTextColor(getResources().getColor(R.color.white));
        titleRock.setTextColor(getResources().getColor(R.color.white));
        titlePaper.setTextColor(getResources().getColor(R.color.white));
        titleScissors.setTextColor(getResources().getColor(R.color.white));
    }
}