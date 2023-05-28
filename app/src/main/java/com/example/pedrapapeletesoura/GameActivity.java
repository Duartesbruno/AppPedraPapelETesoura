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
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ImageView botaoVoltar;
    TextView txUserName;
    TextView txUserName2;
    int pontuacaoCpu = 0;
    int pontuacaoUser = 0;

    int numeroRound = 0;
    Switch modeDarkSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        botaoVoltar = (ImageView) findViewById(R.id.btnVoltar);
        txUserName = (TextView) findViewById(R.id.etUserName);
        txUserName2 = (TextView) findViewById(R.id.txUserName2);
        modeDarkSwitch = (Switch) findViewById(R.id.swDarkMode);


        String valor = getIntent().getStringExtra("nome");
        txUserName.setText(valor);
        txUserName2.setText(valor);

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

    //metodos de disparo do botao(onClick)
    public void selecionadoPedra(View view) {
        //System.out.println("Pedra foi selecionado");
        this.opcaoSelecionada("pedra");
    }

    public void selecionadoPapel(View view) {
        //System.out.println("Papel foi selecionado");
        this.opcaoSelecionada("papel");
    }

    public void selecionadoTesoura(View view) {
        //System.out.println("Tesoura foi selecionado");
        this.opcaoSelecionada("tesoura");
    }

    public void opcaoSelecionada(String opcaoSelecionada) {
        ImageView imgResultadoCpu = findViewById(R.id.imgResultadoCpu); //variavel que representa resultado do CPU
        ImageView imgOpcaoSelecionada = findViewById(R.id.imgOpcaoSelecionada); //variavel que representa a opcao selecionada
        TextView tvResultado = findViewById(R.id.tvResultado);
        TextView tvScoreCpu = findViewById(R.id.tvScoreCpu);
        TextView tvScoreUser = findViewById(R.id.tvScoreUser);
        TextView tvRoundNumber = findViewById(R.id.tvRoundNumber);

        int numero = new Random().nextInt(3); //0 1 2
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoCpu = opcoes[numero];

        switch (opcaoCpu) {
            case "pedra":
                imgResultadoCpu.setImageResource(R.drawable.pedraleft);
                break;

            case "papel":
                imgResultadoCpu.setImageResource(R.drawable.papelleft);
                break;

            case "tesoura":
                imgResultadoCpu.setImageResource(R.drawable.tesouraleft);
                break;

        }

        switch (opcaoSelecionada) {
            case "pedra":
                imgOpcaoSelecionada.setImageResource(R.drawable.pedraright);
                break;

            case "papel":
                imgOpcaoSelecionada.setImageResource(R.drawable.papelright);
                break;

            case "tesoura":
                imgOpcaoSelecionada.setImageResource(R.drawable.tesouraright);
                break;

        }

        if ((opcaoCpu == "pedra" && opcaoSelecionada == "tesoura") ||
                (opcaoCpu == "papel" && opcaoSelecionada == "pedra") ||
                (opcaoCpu == "tesoura" && opcaoSelecionada == "papel")) {
            tvResultado.setText(getResources().getString(R.string.tvLost));
            pontuacaoCpu++;
            tvScoreCpu.setText(String.valueOf(pontuacaoCpu));
        }else if ((opcaoSelecionada == "pedra" && opcaoCpu == "tesoura") ||
                (opcaoSelecionada == "papel" && opcaoCpu == "pedra") ||
                (opcaoSelecionada == "tesoura" && opcaoCpu == "papel") ) {
            tvResultado.setText(getResources().getString(R.string.tvWon));
            pontuacaoUser++;
            tvScoreUser.setText(String.valueOf(pontuacaoUser));
        }else{
            tvResultado.setText(getResources().getString(R.string.tvDraw));
        }

        numeroRound++;
        tvRoundNumber.setText(String.valueOf(numeroRound));
    }
}