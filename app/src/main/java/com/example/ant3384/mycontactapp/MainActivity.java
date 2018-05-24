package com.example.ant3384.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);


        myDB = new DatabaseHelper(this);
        Log.d("MyContactApp", "Main Activity: instantiated myDB");

    }
    public void addData(View view){
        Log.d("MyContactApp", "Main Activity: Add contact button pressed");

        boolean isInserted = myDB.insertData(editName.getText().toString());
        if (isInserted == true){
            Toast.makeText(MainActivity.this, "Success - Contact inserted", Toast.LENGTH_LONG).show();
        }

        else {
            Toast.makeText(MainActivity.this, "Failed - Contact not inserted", Toast.LENGTH_LONG).show();

        }


    }
}
