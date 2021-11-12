package com.example.tictactoe.controller;

import android.os.Handler;
import android.os.Looper;
import com.example.tictactoe.gui.MainActivity;
import java.util.Random;

/**
 * Klasse wird als eigenständiger Thread ausgeführt.
 * Computer macht seinen Zug.
 */
public class Computer extends Thread implements Runnable {

    MainActivity mainActivity;
    Handler handler;

    // Konstruktor
    public Computer(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {

        mainActivity.aktuellerSpieler = "O";

        handler = new Handler(Looper.getMainLooper());
        handler.post(() -> mainActivity.txtSpieler.setText(mainActivity.player()));

        // Zufallszahl zwischen 0 und 9 generieren.
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

                        handler.post(() -> {

                            mainActivity.buttons.get(finalI).setText("O");
                            mainActivity.aktuellerSpieler = "X";
                            mainActivity.runde++;
                            mainActivity.txtSpieler.setText(mainActivity.player());
                            mainActivity.refresh();

                            if (!mainActivity.check(mainActivity.aktuellerSpieler)) {
                                mainActivity.aktivieren();
                            } else {
                                mainActivity.deaktivieren();
                            }
                        });
                    } else {
                        run();
                    }
            }
        }
    }
}