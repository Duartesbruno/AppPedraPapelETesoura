package com.example.pedrapapeletesoura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

public class HelpActivity extends AppCompatActivity {

    ImageView botaoVoltar;
    Switch modeDarkSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        botaoVoltar = (ImageView) findViewById(R.id.btnVoltar);
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

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("switch", modeDarkSwitch.isChecked());
                startActivity(intent);
                finish();

            }
        });
    }
}