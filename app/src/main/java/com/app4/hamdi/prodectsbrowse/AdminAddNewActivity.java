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
import android.widget.Spinner;
import android.widget.Toast;

public class AdminAddNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new);

        final EditText nameET = findViewById(R.id.editText3);
        final EditText priceET = findViewById(R.id.editText4);

        final Spinner typeSp = findViewById(R.id.spinner);

        Button addBtn = findViewById(R.id.button7);
        Button cancelBtn = findViewById(R.id.button8);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameET.getText().toString();
                String type = typeSp.getSelectedItem().toString();
                float price = Float.parseFloat(priceET.getText().toString());

                // must convert to singelton
                DbHelper dbHelper = new DbHelper(getApplicationContext(),"ProdectsBrowse",1);
                SQLiteDatabase db =  dbHelper.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put("name" , name);
                cv.put("type" , type);
                cv.put("price" , price);

                long out = db.insert("prodects" , null , cv);

                if (out != -1){

                    Toast.makeText(getApplicationContext() , "Add Successfuly" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext() , AdminCrudUsersActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext() , "Do not Add" , Toast.LENGTH_SHORT).show();
                }

                db.close();

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
