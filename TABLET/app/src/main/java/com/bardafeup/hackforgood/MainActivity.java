package com.bardafeup.hackforgood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] time = new String[] { "Time#1", "Time#2", "Time#3", "Time#4", "Time#5" };
        String[] quantity = new String[] { "Dosage#1", "Dosage#2", "Dosage#3", "Dosage#4", "Dosage#5" };
        String[] notifications = new String[] { "Medicine#1", "Medicine#2", "Medicine#3", "Medicine#4", "Medicine#5" };
        ArrayAdapter arrayAdapter = new NotificationAdapter(this, time, quantity, notifications);

        listView = (ListView) findViewById(R.id.feedView);
        listView.setAdapter(arrayAdapter);
    }
}
