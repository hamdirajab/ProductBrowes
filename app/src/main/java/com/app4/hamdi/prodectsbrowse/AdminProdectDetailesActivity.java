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

public class AdminProdectDetailesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_prodect_detailes);

        final int prodId = getIntent().getIntExtra("id" , -1);

        final EditText nameET = findViewById(R.id.editText3);
        final EditText priceET = findViewById(R.id.editText4);

        final Spinner typeSp = findViewById(R.id.spinner);

        Button editBtn = findViewById(R.id.button7);
        Button cancelBtn = findViewById(R.id.button8);
        Button deleteBtn = findViewById(R.id.button9);

        DbHelper dbHelper = new DbHelper(getApplicationContext(),"ProdectsBrowse",1);
        SQLiteDatabase db =  dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from prodects where _id=?" , new String[]{String.valueOf(prodId)});
        cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndex("name"));
        String price = cursor.getString(cursor.getColumnIndex("price"));

        nameET.setText(name);
        priceET.setText(price);


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameET.getText().toString();
                float price = Float.parseFloat(priceET.getText().toString());

                // must convert to singleton
                DbHelper dbHelper = new DbHelper(getApplicationContext(),"ProdectsBrowse",1);
                SQLiteDatabase db =  dbHelper.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put("name" , name);
                cv.put("price" , price);

                long out = db.update("prodects" ,  cv , "_id=?" , new String[]{String.valueOf(prodId)});

                if (out != -1){

                    Toast.makeText(getApplicationContext() , "Edit Successfuly" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext() , AdminCrudUsersActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext() , "Do not Edit" , Toast.LENGTH_SHORT).show();
                }

                db.close();

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // must convert to singelton
                DbHelper dbHelper = new DbHelper(getApplicationContext(),"ProdectsBrowse",1);
                SQLiteDatabase db =  dbHelper.getWritableDatabase();

                int out = db.delete("prodects" , "_id=?" , new String[]{ String.valueOf(prodId)});

                if (out != -1){

                    Toast.makeText(getApplicationContext() , "delete Successfuly" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext() , AdminCrudUsersActivity.class);
                    startActivity(intent);

                }else{

                    Toast.makeText(getApplicationContext() , "It not deleted" , Toast.LENGTH_SHORT).show();
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
