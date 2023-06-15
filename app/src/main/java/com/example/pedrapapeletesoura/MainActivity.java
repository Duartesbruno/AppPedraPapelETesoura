package com.example.pedrapapeletesoura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    EditText userName;
    Button enterButton, helpButton;
    ImageView logo;
    Switch modeDarkSwitch;
    ConstraintLayout mainScreen;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        mainScreen = findViewById(R.id.clMainScreen);
        logo = findViewById(R.id.ivLogo);
        helpButton = findViewById(R.id.btnAjuda);
        modeDarkSwitch = findViewById(R.id.swDarkMode);
        userName = findViewById(R.id.etUserName);
        enterButton = findViewById(R.id.btnEnter);

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

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(userName.getText())) {
                    userName.setError(getResources().getString(R.string.error_name));
                } else {
                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                    intent.putExtra("name", userName.getText().toString());
                    startActivity(intent);
                }

            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
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

        mainScreen.setBackgroundResource(R.color.white);
        logo.setImageResource(R.drawable.logo);
        helpButton.setBackgroundResource(R.drawable.ripple_effect_raised);
        helpButton.setTextColor(getResources().getColor(R.color.white));
        userName.setBackgroundResource(R.drawable.edit_text_background);
        userName.setTextColor(getResources().getColor(R.color.black));
        enterButton.setBackgroundResource(R.drawable.ripple_effect_raised);
        enterButton.setTextColor(getResources().getColor(R.color.white));
    }

    private void activateDarkMode() {
        SharedPreferences.Editor preferencesEdit = preferences.edit();
        preferencesEdit.putString("darkMode", "activated");
        preferencesEdit.apply();

        mainScreen.setBackgroundResource(R.color.blackModern);
        logo.setImageResource(R.drawable.logo2);
        helpButton.setBackgroundResource(R.drawable.ripple_effect_raised2);
        helpButton.setTextColor(getResources().getColor(R.color.black));
        userName.setBackgroundResource(R.drawable.edit_text_background2);
        userName.setTextColor(getResources().getColor(R.color.grayHint));
        enterButton.setBackgroundResource(R.drawable.ripple_effect_raised2);
        enterButton.setTextColor(getResources().getColor(R.color.black));
    }

}