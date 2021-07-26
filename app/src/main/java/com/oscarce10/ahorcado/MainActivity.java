package com.oscarce10.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button[] letras;
    private GridLayout contLetras;
    private String lista[];
    private char[] lines;
    private int choice;
    private GridLayout contName;
    private TextView name;
    private String  textDisplay;
    private int contador;
    private int limit;
    private String notAllowed;
    private String clear;
    private ImageView[] parts;
    private ImageView[] intento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parts = new ImageView[7];
        parts[0] = findViewById(R.id.imgMain);
        parts[1] = findViewById(R.id.imgHead);
        parts[2] = findViewById(R.id.imgArms1);
        parts[3] = findViewById(R.id.imgArms2);
        parts[4] = findViewById(R.id.imgArms3);
        parts[5] = findViewById(R.id.imgLegs1);
        parts[6] = findViewById(R.id.imgLegs2);
        intento = new ImageView[4];
        parts[0].setVisibility(View.VISIBLE);

        contador = 0;
        contName = (GridLayout) findViewById(R.id.contName);
        lista = new String[]{"Abogado", "Actor", "Sastre", "Bombero", "Chofer", "Mesero", "Panadero", "Piloto", "Cajero", "Policia"};
        //lista = new String[]{"aaaaaa", "Actobbbr", "Sassssstre", "Bombnnnnnnero", "Chofggggger", "yyyyyy", "Panaduuuuuero", "Piiiiiiiloto", "Caooooojero", "Pooooolicia"};
        choice = new Random().nextInt(lista.length);
        lines = new char[lista[choice].length()];
        textDisplay = "";
        clear = "";
        limit = 3;
        name = findViewById(R.id.name);
        notAllowed = lista[choice];
        for (int i = 0; i < lista[choice].length(); i++){
            lines[i] = '_';
            textDisplay += lines[i] + " ";
            if(!clear.contains(lista[choice].toUpperCase().charAt(i) + "")){
                clear += lista[choice].toUpperCase().charAt(i);
            }
        }
        name.setText(textDisplay);
        letras = new Button[20];
        contLetras = (GridLayout) findViewById(R.id.contLetras);
        for (int i = 0; i < clear.length(); i++){
            letras[i] = new Button(getApplicationContext());
            letras[i].setText("" + (char) (clear.toUpperCase().charAt(i)));
//            if(!notAllowed.contains(lista[choice].toUpperCase().charAt(i)+"")){
//                letras[i].setText("-" + (char) (lista[choice].toUpperCase().charAt(i)));
//                notAllowed += lista[choice].toUpperCase().charAt(i);
//            }
            letras[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < lista[choice].length(); j++){
                        Button btn = (Button) v;
                        if(btn.getText().charAt(0) == lista[choice].toUpperCase().charAt(j)){
                            lines[j] = btn.getText().charAt(0);
                            textDisplay = "";
                            for (int k = 0; k < lista[choice].length(); k++){
                                textDisplay += lines[k] + " ";
                            }
                            name.setText(textDisplay);
                            if(!textDisplay.contains("_")){
                                Toast.makeText(MainActivity.this, "Felicidades, ha ganado el juego", Toast.LENGTH_SHORT).show();
                                for(int a = 0; a < letras.length; a++){
                                    letras[a].setEnabled(false);
                                }
                            }

                        }
                        btn.setEnabled(false);
                    }

                }
            });
        }
        for (int i = clear.length(); i < 20; i++){
            letras[i] = new Button(getApplicationContext());
            do{
                letras[i].setText("" + (char) (new Random().nextInt((90 - 65) + 1) + 65) );
            }while(lista[choice].toUpperCase().contains(letras[i].getText()+"") || notAllowed.toUpperCase().contains(letras[i].getText()+""));
            notAllowed += letras[i].getText();
            letras[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button) v;
                    btn.setEnabled(false);
                    contador++;

                    switch (contador){
                        case 1:
                         parts[1].setVisibility(View.VISIBLE);
                         break;

                        case 2:
                        parts[2].setVisibility(View.VISIBLE);

                        break;

                        case 3:
                            parts[3].setVisibility(View.VISIBLE);
                            parts[4].setVisibility(View.VISIBLE);
                        break;

                        case 4:
                            parts[5].setVisibility(View.VISIBLE);
                            parts[6].setVisibility(View.VISIBLE);

                        default:

                    }

                    if(contador > limit){
                        for(int a = 0; a < letras.length; a++){
                            letras[a].setEnabled(false);
                        }
                        Toast.makeText(MainActivity.this, "Ha perdido el juego\nLa palabra era " + lista[choice].toUpperCase(), Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MainActivity.this, "Error, quedan " + (limit - contador + 1) + " intentos", Toast.LENGTH_SHORT).show();
                }

            });
        }

        for (int i=0; i<letras.length; i++) {
            int randomPosition = new Random().nextInt(letras.length);
            Button temp = letras[i];
            letras[i] = letras[randomPosition];
            letras[randomPosition] = temp;
        }

        for (int i = 0; i < 20; i++){
            contLetras.addView(letras[i]);
        }


    }
}