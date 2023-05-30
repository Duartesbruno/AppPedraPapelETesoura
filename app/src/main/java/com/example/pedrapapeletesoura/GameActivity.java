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

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ImageView backButton;
    TextView userName, userName2, scoreText, cpuText, cpuText2, resultText, roundText, cpuScore, UserScore, roundNumber;
    int countCpuScore, countUserScore, countRounds = 0;
    Switch modeDarkSwitch;
    ConstraintLayout gameScreen;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        gameScreen = findViewById(R.id.clGameScreen);
        backButton = findViewById(R.id.btnVoltar);
        userName = findViewById(R.id.tvUserName);
        userName2 = findViewById(R.id.tvUserName2);
        modeDarkSwitch = findViewById(R.id.swDarkMode);
        scoreText = findViewById(R.id.tvScore);
        cpuText = findViewById(R.id.tvCpuText);
        cpuText2 = findViewById(R.id.tvCpuText2);
        resultText = findViewById(R.id.tvResultado);
        roundText = findViewById(R.id.tvRound);
        cpuScore = findViewById(R.id.tvScoreCpu);
        UserScore = findViewById(R.id.tvScoreUser);
        roundNumber = findViewById(R.id.tvRoundNumber);

        String valor = getIntent().getStringExtra("name");
        userName.setText(valor);
        userName2.setText(valor);

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

        if(preferences.getString("darkMode", null).equals("activated")) {
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

        gameScreen.setBackgroundResource(R.color.white);
        backButton.setImageResource(R.drawable.back_icon);
        userName.setTextColor(getResources().getColor(R.color.black));
        userName2.setTextColor(getResources().getColor(R.color.black));
        scoreText.setTextColor(getResources().getColor(R.color.black));
        cpuText.setTextColor(getResources().getColor(R.color.black));
        cpuText2.setTextColor(getResources().getColor(R.color.black));
        resultText.setTextColor(getResources().getColor(R.color.black));
        roundText.setTextColor(getResources().getColor(R.color.black));
        cpuScore.setTextColor(getResources().getColor(R.color.black));
        UserScore.setTextColor(getResources().getColor(R.color.black));
        roundNumber.setTextColor(getResources().getColor(R.color.black));
    }

    private void activateDarkMode() {
        SharedPreferences.Editor preferencesEdit = preferences.edit();
        preferencesEdit.putString("darkMode", "activated");
        preferencesEdit.apply();

        gameScreen.setBackgroundResource(R.color.blackModern);
        backButton.setImageResource(R.drawable.back_icon2);
        userName.setTextColor(getResources().getColor(R.color.white));
        userName2.setTextColor(getResources().getColor(R.color.white));
        scoreText.setTextColor(getResources().getColor(R.color.white));
        cpuText.setTextColor(getResources().getColor(R.color.white));
        cpuText2.setTextColor(getResources().getColor(R.color.white));
        resultText.setTextColor(getResources().getColor(R.color.white));
        roundText.setTextColor(getResources().getColor(R.color.white));
        cpuScore.setTextColor(getResources().getColor(R.color.white));
        UserScore.setTextColor(getResources().getColor(R.color.white));
        roundNumber.setTextColor(getResources().getColor(R.color.white));
    }

    //metodos de disparo do botao/imagem(onClick)
    public void selectedRock(View view) {
        //System.out.println("Pedra foi selecionado");
        this.selectedOption("rock");
    }

    public void selectedPaper(View view) {
        //System.out.println("Papel foi selecionado");
        this.selectedOption("paper");
    }

    public void selectedScissors(View view) {
        //System.out.println("Tesoura foi selecionado");
        this.selectedOption("scissors");
    }

    public void selectedOption(String selectedOption) {
        ImageView imgResultadoCpu = findViewById(R.id.ivCpuResultImg);
        ImageView imgOpcaoSelecionada = findViewById(R.id.ivUserSelectImg);
        TextView tvResultado = findViewById(R.id.tvResultado);
        TextView tvScoreCpu = findViewById(R.id.tvScoreCpu);
        TextView tvScoreUser = findViewById(R.id.tvScoreUser);
        TextView tvRoundNumber = findViewById(R.id.tvRoundNumber);

        int number = new Random().nextInt(3); //0 1 2
        String[] options = {"rock", "paper", "scissors"};
        String cpuOption = options[number];

        switch (cpuOption) {
            case "rock":
                imgResultadoCpu.setImageResource(R.drawable.rock_left);
                break;

            case "paper":
                imgResultadoCpu.setImageResource(R.drawable.paper_left);
                break;

            case "scissors":
                imgResultadoCpu.setImageResource(R.drawable.scissors_left);
                break;

        }

        switch (selectedOption) {
            case "rock":
                imgOpcaoSelecionada.setImageResource(R.drawable.rock_right);
                break;

            case "paper":
                imgOpcaoSelecionada.setImageResource(R.drawable.paper_right);
                break;

            case "scissors":
                imgOpcaoSelecionada.setImageResource(R.drawable.scissors_right);
                break;

        }

        if ((cpuOption == "rock" && selectedOption == "scissors") ||
                (cpuOption == "paper" && selectedOption == "rock") ||
                (cpuOption == "scissors" && selectedOption == "paper")) {
            tvResultado.setText(getResources().getString(R.string.tvLost));
            countCpuScore++;
            tvScoreCpu.setText(String.valueOf(countCpuScore));
        }else if ((selectedOption == "rock" && cpuOption == "scissors") ||
                (selectedOption == "paper" && cpuOption == "rock") ||
                (selectedOption == "scissors" && cpuOption == "paper") ) {
            tvResultado.setText(getResources().getString(R.string.tvWon));
            countUserScore++;
            tvScoreUser.setText(String.valueOf(countUserScore));
        }else{
            tvResultado.setText(getResources().getString(R.string.tvDraw));
        }

        countRounds++;
        tvRoundNumber.setText(String.valueOf(countRounds));
    }
}