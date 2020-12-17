package com.example.eva2_14_document_provider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editTextDatos;
    final int ABRIR_ARCHIVO =100;
    final int GUARDAR_ARCHIVO=200;

    Uri uriResu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextDatos =findViewById(R.id.edtxtDatos);




     }

     public void abrir(View v){
         Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
         intent.addCategory(Intent.CATEGORY_OPENABLE);
         intent.setType("text/plain");//MIME Type
         intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI,uriResu);//OPCIONAL
         startActivityForResult(intent,ABRIR_ARCHIVO);
     }

     public void cerrar(View v){


         Intent inGuardad = new Intent(Intent.ACTION_CREATE_DOCUMENT);
         inGuardad.addCategory(Intent.CATEGORY_OPENABLE);
         inGuardad.setType("text/plain");//MIME Type
         inGuardad.putExtra(Intent.EXTRA_TITLE, "pruebaintermediario.txt");
         inGuardad.putExtra(DocumentsContract.EXTRA_INITIAL_URI,uriResu);//OPCIONAL

         startActivityForResult(inGuardad,GUARDAR_ARCHIVO);

     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 100:
                if (requestCode == 100) {
                    uriResu = data.getData();
                    Log.wtf("URI",data.getData().toString());
                    String sCade;
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uriResu);
                        InputStreamReader isr = new InputStreamReader(inputStream);

                        BufferedReader br = new BufferedReader(isr);

                        while ((sCade = br.readLine())!=null){
                            editTextDatos.append(sCade);
                            editTextDatos.append("\n");

                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 200:

                if (requestCode== 200){
                    uriResu = data.getData();
                    Log.wtf("URI",uriResu.toString());

                    try {
                        String[] aCade = editTextDatos.getText().toString().split("\n");
                        OutputStream outputStream = getContentResolver().openOutputStream(uriResu);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                        BufferedWriter  bw = new BufferedWriter(outputStreamWriter);
                        for (int i=0;i<aCade.length;i++){
                            bw.append(aCade[i]);
                            bw.append("\n");
                        }
                        bw.close();

                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}