package com.example.ant3384.mycontactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText editName;
    EditText editPhone;
    EditText editAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editPhone = findViewById(R.id.editText_phone);
        editAddress = findViewById(R.id.editText_address);


        mydb = new DatabaseHelper(this);
        Log.d("MyContactApp", "Main Activity: instantiated myDB");

    }
    public void addData(View view){
        Log.d("MyContactApp", "Main Activity: Add contact button pressed");

        boolean isInserted = mydb.insertData(editName.getText().toString());
        if(isInserted == true){
            makeText(MainActivity.this, "Success - Contact inserted", LENGTH_LONG).show();
        }

        else {
            makeText(MainActivity.this, "Failed - Contact not inserted", LENGTH_LONG).show();

        }


    }

    public void viewData(View view){
        Cursor res =  mydb.getAllData();
        Log.d("MyContactApp", "Main Activity: viewData: received cursor");

        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            return;
        }


        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()) {
            //Append res column 0,1,2,3 to the buffer - see stringBuffer and Cursor api's
            //Delimit each of the appends with line feed "/n"
            buffer.append(res + "\n");
            showMessage("Contacts", "Name: "+ editName.getText().toString() +"\nPhone: " + editPhone.getText().toString() + "\nAddress: " + editAddress.getText().toString());

        }

        showMessage("Data", buffer.toString());
    }




    private void showMessage(String title, String message) {
        Log.d("MyContactApp", "Main Activity: showMessage: assembling AlertDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public static final String EXTRA_MESSAGE = "com.example.ant3384.mycontactapp.MESSAGE";
    public void SearchRecord(View view){
        Log.d("MyContactApp", "Main Activity: Launching SearchActivity");
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(EXTRA_MESSAGE, editName.getText().toString());
        startActivity(intent);
    }

}
