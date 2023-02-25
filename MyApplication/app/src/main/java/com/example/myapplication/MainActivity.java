package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private EditText e1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText)findViewById(R.id.editTextTextPersonName);
    }

    public void send_data(View v){
        String message = e1.getText().toString();
        BackgroundTask b1 = new BackgroundTask();
        b1.execute(message);
    }

    class BackgroundTask extends AsyncTask<String,Void,Void>{
        Socket s;
        PrintWriter writer;

        @Override
        protected Void doInBackground(String... voids) {
            try{
            String message = voids[0];
            s = new Socket("192.168.0.5",6000);
            writer = new PrintWriter(s.getOutputStream());
            writer.write(message);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

            return null;
        }
    }
}