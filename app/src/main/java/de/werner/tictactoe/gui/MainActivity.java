package de.werner.tictactoe.gui;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import de.werner.tictactoe.R;
import de.werner.tictactoe.controller.MainActivityListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainActivityListener mainActivityListener;

    public Button btnNeu, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    public ArrayList<Button> buttons = new ArrayList<>();

    public TextView txtPunkte, txtSpieler;
    public String aktuellerSpieler = "X";

    public int punkte = 0;
    public int runde = 0;

    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNeu = findViewById(R.id.btnNeu);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        // Buttons der ArrayList hinzufügen.
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);

        txtPunkte = findViewById(R.id.txtPunkte);
        txtSpieler = findViewById(R.id.txtSpieler);

        mainActivityListener = new MainActivityListener(this);

        // Schleife durchlaufen und Listener für setzen.
        for (Button b : buttons) {
            b.setOnClickListener(mainActivityListener);
        }

        btnNeu.setOnClickListener(mainActivityListener);
    }

    public void refresh() {

        runOnUiThread(() -> {

            // Schleife durchlaufen und Button neu rendern.
            for (Button b : buttons) {
                b.refreshDrawableState();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public boolean check(String player) {

        boolean win = false;

        // Gewinner ermitteln.
        if (btn1.getText().toString().equals("O") && btn2.getText().toString().equals("O") && btn3.getText().toString().equals("O"))
            win = true;
        if (btn4.getText().toString().equals("O") && btn5.getText().toString().equals("O") && btn6.getText().toString().equals("O"))
            win = true;
        if (btn7.getText().toString().equals("O") && btn8.getText().toString().equals("O") && btn9.getText().toString().equals("O"))
            win = true;
        if (btn1.getText().toString().equals("O") && btn4.getText().toString().equals("O") && btn7.getText().toString().equals("O"))
            win = true;
        if (btn1.getText().toString().equals("O") && btn5.getText().toString().equals("O") && btn9.getText().toString().equals("O"))
            win = true;
        if (btn2.getText().toString().equals("O") && btn5.getText().toString().equals("O") && btn8.getText().toString().equals("O"))
            win = true;
        if (btn3.getText().toString().equals("O") && btn6.getText().toString().equals("O") && btn9.getText().toString().equals("O"))
            win = true;
        if (btn3.getText().toString().equals("O") && btn5.getText().toString().equals("O") && btn7.getText().toString().equals("O"))
            win = true;

        if (btn1.getText().toString().equals("X") && btn2.getText().toString().equals("X") && btn3.getText().toString().equals("X"))
            win = true;
        if (btn4.getText().toString().equals("X") && btn5.getText().toString().equals("X") && btn6.getText().toString().equals("X"))
            win = true;
        if (btn7.getText().toString().equals("X") && btn8.getText().toString().equals("X") && btn9.getText().toString().equals("X"))
            win = true;
        if (btn1.getText().toString().equals("X") && btn4.getText().toString().equals("X") && btn7.getText().toString().equals("X"))
            win = true;
        if (btn1.getText().toString().equals("X") && btn5.getText().toString().equals("X") && btn9.getText().toString().equals("X"))
            win = true;
        if (btn2.getText().toString().equals("X") && btn5.getText().toString().equals("X") && btn8.getText().toString().equals("X"))
            win = true;
        if (btn3.getText().toString().equals("X") && btn6.getText().toString().equals("X") && btn9.getText().toString().equals("X"))
            win = true;
        if (btn3.getText().toString().equals("X") && btn5.getText().toString().equals("X") && btn7.getText().toString().equals("X"))
            win = true;

        if (win) {

            handler = new Handler(Looper.getMainLooper());

            handler.post(() -> {
                if (player.equals("O")) {
                    txtSpieler.setText("Du hast verloren!");
                    aktuellerSpieler = "X";
                } else {
                    txtSpieler.setText("Du hast gewonnen!");
                    punkte++;
                    txtPunkte.setText("Punktzahl : " + punkte);
                    aktuellerSpieler = "O";
                }
            });
        }

        Log.d("xxxxxxxxxxxxxxxxxxxxxxx", String.valueOf(runde));
        // Unentschieden ermitteln.
        if (!win && runde >= 9) txtSpieler.setText("Spiel beendet!");

        return win;
    }

    // Alle Spiel-Buttons aktivieren
    public void aktivieren() {
        for (Button b : buttons) {
            b.setClickable(true);
        }
    }

    // Alle Spiel-Buttons deaktivieren
    public void deaktivieren() {
        for (Button b : buttons) {
            b.setClickable(false);
        }
    }

    @SuppressLint("SetTextI18n")
    public String player() {
        if (aktuellerSpieler.equals("X")) {
            return "Du bist am Zug!";
        } else {
            return "Dein Handy ist am Zug!";
        }
    }
}