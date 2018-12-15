package com.example.mef1sto.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Weather w;
    TextView weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weather = (TextView) findViewById(R.id.weather);
        w = new Weather(weather);
    }

    public void onClick(View view) throws IOException {
        w.updateWeather();
    }
}
