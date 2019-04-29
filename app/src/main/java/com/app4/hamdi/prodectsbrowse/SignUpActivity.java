package com.app4.hamdi.prodectsbrowse;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText usernameET = findViewById(R.id.editText);
        final EditText passwordET = findViewById(R.id.editText2);

        Button cancelBtn = findViewById(R.id.button2);
        Button signupBtn = findViewById(R.id.button);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!usernameET.getText().toString().equals("") && !passwordET.getText().toString().equals("")){

                    // must convert to singleton
                    DbHelper dbHelper = new DbHelper(getApplicationContext(),"ProdectsBrowse",1);
                    SQLiteDatabase db =  dbHelper.getWritableDatabase();

                    ContentValues cv = new ContentValues();
                    cv.put("name" , usernameET.getText().toString());
                    cv.put("password" , passwordET.getText().toString());

                    long out = db.insert("users" , null , cv);

                    if (out != -1){

                        Toast.makeText(getApplicationContext() , "Add Successfuly" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                        startActivity(intent);

                    }else{

                        Toast.makeText(getApplicationContext() , "Do not Add" , Toast.LENGTH_SHORT).show();
                    }

                    db.close();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
