package com.example.eva2_9_transiciones;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.ivSpider);
        intent = new Intent(this, MainActivity2.class);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View v){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                imageView,
                "mi_transicion"
                );

        startActivity(intent,options.toBundle());
    }
}