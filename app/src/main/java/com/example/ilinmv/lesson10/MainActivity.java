package com.example.ilinmv.lesson10;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText textPref, textFile;
    Button savePref, saveFile, loadPref, loadFile;
    TextView textRead;

    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";
    final String FILENAME = "file";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textPref = (EditText) findViewById(R.id.TextPref);
        textFile = (EditText) findViewById(R.id.TextFile);

        textRead = (TextView) findViewById(R.id.rt);

    }

    public void  doSavePref(View v) {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, textPref.getText().toString());
        ed.apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    public void  doReadPref(View v) {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        textRead.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();

    }

    public void doWriteFile(View v) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            bw.write(String.valueOf(textFile.getText()));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doReadFile(View v) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            String str = "";
            str = br.readLine();
            /*
            while ((str = br.readLine()) != null) {
               Log.d("TAG", str);
            }
            */
            textRead.setText(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
