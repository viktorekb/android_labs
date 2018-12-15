package com.example.mef1sto.weather;

import android.os.Handler;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * .
 */

public class Weather {

    private URL url;
    private InputStream is;
    private BufferedReader br;
    private String line;
    private String weather;
    private Runnable updater;
    private Handler h;

    public Weather(final TextView out) {

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                out.setText("Тмепература в Омске: " + weather);
            }
        };

        updater = new Runnable() {
            @Override
            public void run() {
                try {
                    url = new URL("https://yandex.ru/pogoda/omsk");
                    is = url.openStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    String buf = sb.toString();
                    Pattern p = Pattern.compile("&temp=(.+?)&");
                    Matcher m = p.matcher(buf);
                    if (m.find()) {
                        weather = m.group(1);
                        h.sendEmptyMessage(0);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void updateWeather() {
        new Thread(updater).start();
    }
}
