package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void basic(View view) {
        startActivity(new Intent(this, ObserverObservable.class));
    }

    public void list(View view) {
        startActivity(new Intent(this, ListActivity.class));
    }
}