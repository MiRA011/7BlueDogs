package com.sevenbluedogs.asteroides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ejecutarInfo(View view){
        Intent i = new Intent(this, InfoActivity.class);
        startActivity(i);
    }

    public void salirApp(View view){
        finish();
    }
}