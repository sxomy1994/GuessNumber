package com.example.guessnumber;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
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
        final TextView rezultatPogadjanja = new TextView(this);

        String sBroj =poljeZaBroj.getText().toString();
        if(sBroj.matches("")){
            Toast.makeText(this,"Please enter a number",Toast.LENGTH_LONG).show();
        }else{
            int iBroj = Integer.parseInt(sBroj);
            if(iBroj > randomNumber){
                if(pokusaji < 10) {
                    pokusaji++;
                    displayResult("The number is lower" + "\nBroj pokusaja: " + pokusaji + "\n" +
                            "Entered numbers: ");
                    TextView rez = (TextView) findViewById(R.id.rezultat);
                    rez.setTextColor(Color.parseColor("#FF0000"));
                    unesiBrojIIspisi(iBroj);
                } else{
                    final TextView rez = (TextView)findViewById(R.id.rezultat);
                    rez.setText("Sorry, you lost. Try Again!");
                    rez.setTextColor(Color.parseColor("#FF0000"));

                   reset();
                }

            } else if(iBroj < randomNumber){
                if(pokusaji < 10){
                pokusaji++;
                displayResult("The number is higher" +"\nBroj pokusaja:" +  pokusaji+"\n" +
                        "Entered numbers: " );
                TextView rez = (TextView)findViewById(R.id.rezultat);
                rez.setTextColor(Color.parseColor("#FF0000"));
                unesiBrojIIspisi(iBroj);}
                else{
                    TextView rez = (TextView)findViewById(R.id.rezultat);
                    rez.setText("Sorry, you lost. Try Again!");
                    rez.setTextColor(Color.parseColor("#FF0000"));

                    reset();
                }

            } else{
                pokusaji++;
                displayResult("CORRECT!" + "\nBroj pokusaja: " + pokusaji +"\n" +
                        "Entered numbers: " );
                unesiBrojIIspisi(iBroj);
                TextView rez = (TextView)findViewById(R.id.rezultat);
                rez.setTextColor(Color.parseColor("#00FF00"));
               reset();
            }
        }
    }
    public void displayResult(String s){
        TextView rez = (TextView)findViewById(R.id.rezultat);
        rez.setText(s + "");
        rez.setVisibility(View.VISIBLE);
    }
    public void unesiBrojIIspisi(int br){
        if(brojac < pokusajiNiz.length){
            pokusajiNiz[brojac] = br;
            brojac++;
            TextView tv = (TextView)findViewById(R.id.rezultat);
            for(int i = 0; i  < brojac; i++){
                tv.append(pokusajiNiz[i] + " ");
            }
        }else{
            TextView tv = (TextView)findViewById(R.id.rezultat);
            tv.append("\nSorry, you didn't guess. Try Again!");
        }

    }
    public void reset(){
        final TextView rez = (TextView)findViewById(R.id.rezultat);
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        final Button b = (Button)findViewById(R.id.button_try);
        b.setClickable(false);
        b.setBackgroundColor(Color.parseColor("#C0C0C0"));
        final Button reset = new Button(this);
        reset.setText("Reset");
        reset.setGravity(Gravity.CENTER);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                randomNumber = r.nextInt(20)+1;
                pokusaji = 0;
                brojac = 0;
                rez.setText("");
                b.setClickable(true);
                b.setBackgroundColor(Color.parseColor("#7CFC00"));
                reset.setVisibility(View.INVISIBLE);

            }
        });
        layout.addView(reset);
    }

}

