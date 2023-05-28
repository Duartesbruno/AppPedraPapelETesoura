package com.example.pedrapapeletesoura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    EditText userName;
    Button enterButton;
    Button helpButton;
    Switch modeDarkSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.etUserName);
        enterButton = (Button) findViewById(R.id.btnEnter);
        helpButton = (Button) findViewById(R.id.btnAjuda);
        modeDarkSwitch = (Switch) findViewById(R.id.swDarkMode);

        boolean valor2 = getIntent().getBooleanExtra("switch",false);
        modeDarkSwitch.setChecked(valor2);

        modeDarkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {      //estrutura para verificar se esta clicado ou nao
                if(isChecked) {
                    //System.out.println("selecionado");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                } else {
                    //System.out.println("nao selecionado");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                if (TextUtils.isEmpty(userName.getText())) {
                    userName.setError(getResources().getString(R.string.error_name));  //resources+getstring serve para pegar string nos arquivos de string
                } else {
                    intent.putExtra("nome", userName.getText().toString());
                    intent.putExtra("switch", modeDarkSwitch.isChecked());
                    startActivity(intent);
                    finish();
                }

            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("switch", modeDarkSwitch.isChecked());
                startActivity(intent);
                finish();
            }
        });
    }


}