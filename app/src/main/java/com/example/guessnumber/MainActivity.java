package com.example.guessnumber;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int pokusaji = 0;
    int randomNumber;
    int brojac = 0;
    int[]pokusajiNiz = new int[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random r = new Random();
        randomNumber = r.nextInt(20)+1;
    }

    public void guessButton(View view){
        Random r = new Random();
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        EditText poljeZaBroj = (EditText)findViewById(R.id.enter_number);
        TextView rezultatPogadjanja = new TextView(this);

        String sBroj =poljeZaBroj.getText().toString();
        if(sBroj.matches("")){
            Toast.makeText(this,"Please enter a number",Toast.LENGTH_LONG).show();
        }else{
            int iBroj = Integer.parseInt(sBroj);
            if(iBroj > randomNumber){
                pokusaji++;
                displayResult("The number is lower" +"\nBroj pokusaja: " + pokusaji +"\n" +
                        "Entered numbers: " );
                TextView rez = (TextView)findViewById(R.id.rezultat);
                rez.setTextColor(Color.parseColor("#FF0000"));
                unesiBroj(iBroj);
                ispisi();

            } else if(iBroj < randomNumber){
                pokusaji++;
                displayResult("The number is higher" +"\nBroj pokusaja:" +  pokusaji+"\n" +
                        "Entered numbers: " );
                TextView rez = (TextView)findViewById(R.id.rezultat);
                rez.setTextColor(Color.parseColor("#FF0000"));
                unesiBroj(iBroj);
                ispisi();
            } else{
                pokusaji++;
                displayResult("CORRECT!" + "\nBroj pokusaja: " + pokusaji +"\n" +
                        "Entered numbers: " );
                unesiBroj(iBroj);
                ispisi();
                TextView rez = (TextView)findViewById(R.id.rezultat);
                rez.setTextColor(Color.parseColor("#00FF00"));
                Button b = (Button)findViewById(R.id.button_try);
                b.setClickable(false);
                b.setBackgroundColor(Color.parseColor("#C0C0C0"));
            }
        }
    }
    public void displayResult(String s){
        TextView rez = (TextView)findViewById(R.id.rezultat);
        rez.setText(s + "");
        rez.setVisibility(View.VISIBLE);
    }
    public void unesiBroj(int br){
        pokusajiNiz[brojac] = br;
        brojac++;
    }
    public void ispisi(){
        TextView tv = (TextView)findViewById(R.id.rezultat);
        for(int i = 0; i  < brojac; i++){
            tv.append(pokusajiNiz[i] + " ");
        }
    }
}

