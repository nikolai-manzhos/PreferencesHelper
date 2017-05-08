package com.defaultapps.preferenceshelperexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);

        CustomPreferencesManager preferencesManager = new CustomPreferencesManager();
        if (preferencesManager.getFirstTimeUser()) {
            textView.setText("first Time");
            preferencesManager.setFirstTimeUser(false);
        }
        textView.setText(preferencesManager.getStringProperty());
    }
}
