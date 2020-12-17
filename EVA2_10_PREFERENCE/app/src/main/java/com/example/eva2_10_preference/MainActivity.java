package com.example.eva2_10_preference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.clySettings, new SettingsFragment());
        ft.commit();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Toast.makeText(this, sharedPreferences.getString("dia_semana","Nada"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this,""+ sharedPreferences.getBoolean("dia_laboral",false), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, sharedPreferences.getString("nombre","Juan perez jolote"), Toast.LENGTH_SHORT).show();

    }
}