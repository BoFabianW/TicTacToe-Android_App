package com.example.tictactoe.controller;

import android.os.Handler;
import android.os.Looper;
import com.example.tictactoe.gui.MainActivity;
import java.util.Random;

public class Computer extends Thread implements Runnable {

    MainActivity mainActivity;
    Handler handler;

    public Computer(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {

        mainActivity.aktuellerSpieler = "O";

        Random random = new Random();
        int feld = random.nextInt(9);

        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {}

        for (int i = 0; i <= mainActivity.buttons.size(); i++) {

            if (i == feld) {
                if (mainActivity.buttons.get(i).getText().equals("")) {

                    int finalI = i;

                    handler = new Handler(Looper.getMainLooper());

                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                mainActivity.buttons.get(finalI).setText("O");

                                if (!mainActivity.check(mainActivity.aktuellerSpieler)) {
                                    mainActivity.refresh();
                                    mainActivity.aktuellerSpieler = "X";
                                    mainActivity.runde++;
                                    mainActivity.aktivieren();
                                } else {
                                    mainActivity.deaktivieren();
                                }
                            }
                        });
                    } else {
                        run();
                    }
            }
        }
    }
}