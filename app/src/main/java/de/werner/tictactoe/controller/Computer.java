package de.werner.tictactoe.controller;

import android.os.Handler;
import android.os.Looper;
import de.werner.tictactoe.gui.MainActivity;
import java.util.Random;

/**
 * Klasse wird als eigenständiger Thread ausgeführt.
 * Computer macht seinen Zug.
 */
public class Computer extends Thread implements Runnable {

    MainActivity mainActivity;
    Handler handler;

    public static int threadPause = 400;

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
            Thread.sleep(threadPause);
        } catch (InterruptedException ignored) {}

        for (int i = 0; i <= mainActivity.buttons.size(); i++) {

            if (i == feld) {
                if (mainActivity.buttons.get(i).getText().equals("")) {

                    int finalI = i;

                    handler = new Handler(Looper.getMainLooper());

                        handler.post(() -> {

                            mainActivity.buttons.get(finalI).setText("O");
                            mainActivity.runde++;

                            if (!mainActivity.check(mainActivity.aktuellerSpieler)) {
                                mainActivity.aktuellerSpieler = "X";
                                mainActivity.refresh();
                                mainActivity.aktivieren();

                                if (mainActivity.runde < 9) {
                                    mainActivity.txtSpieler.setText(mainActivity.player());
                                }
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