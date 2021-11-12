package com.example.tictactoe.controller;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import com.example.tictactoe.R;
import com.example.tictactoe.gui.MainActivity;

public class MainActivityListener implements View.OnClickListener {

    MainActivity mainActivity;

    public MainActivityListener(MainActivity mainActivity) {

        this.mainActivity = mainActivity;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        Computer com = new Computer(mainActivity);

        if (v.getId() == R.id.btn1) {

            if (mainActivity.btn1.getText().equals("")) {
                mainActivity.btn1.setText("X");
                mainActivity.runde++;
                nextRound(com);
            }

        } else if (v.getId() == R.id.btn2) {

            if (mainActivity.btn2.getText().equals("")) {
                mainActivity.btn2.setText("X");
                mainActivity.runde++;
                nextRound(com);
            }

        } else if (v.getId() == R.id.btn3) {

            if (mainActivity.btn3.getText().equals("")) {
                mainActivity.btn3.setText("X");
                mainActivity.runde++;
                nextRound(com);
            }

        } else if (v.getId() == R.id.btn4) {

            if (mainActivity.btn4.getText().equals("")) {
                mainActivity.btn4.setText("X");
                mainActivity.runde++;
                nextRound(com);
            }

        } else if (v.getId() == R.id.btn5) {

            if (mainActivity.btn5.getText().equals("")) {
                mainActivity.btn5.setText("X");
                mainActivity.runde++;
                nextRound(com);
            }

        } else if (v.getId() == R.id.btn6) {

            if (mainActivity.btn6.getText().equals("")) {
                mainActivity.btn6.setText("X");
                mainActivity.runde++;
                nextRound(com);
            }

        } else if (v.getId() == R.id.btn7) {

            if (mainActivity.btn7.getText().equals("")) {
                mainActivity.btn7.setText("X");
                mainActivity.runde++;
                nextRound(com);
            }

        } else if (v.getId() == R.id.btn8) {

            if (mainActivity.btn8.getText().equals("")) {
                mainActivity.btn8.setText("X");
                mainActivity.runde++;
                nextRound(com);
            }

        } else if (v.getId() == R.id.btn9) {

            if (mainActivity.btn9.getText().equals("")) {
                mainActivity.btn9.setText("X");
                mainActivity.runde++;
                nextRound(com);
            }

        } else if (v.getId() == R.id.btnNeu) {

            for (Button b : mainActivity.buttons) {
                b.setText("");
            }

            mainActivity.runde = 0;
            mainActivity.handler = null;
            com.handler = null;

            String selectPlayer = mainActivity.player();
            if (selectPlayer.equals("Du bist am Zug!")) {
                mainActivity.aktivieren();
            } else {
                com.start();
            }
            mainActivity.txtSpieler.setText(selectPlayer);
        }
    }

    void nextRound(Computer com) {
        mainActivity.deaktivieren();
        if (!mainActivity.check(mainActivity.aktuellerSpieler)) {
            if (mainActivity.runde < 9) com.start();
        }
    }
}