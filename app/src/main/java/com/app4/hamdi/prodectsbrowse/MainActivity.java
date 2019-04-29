package com.app4.hamdi.prodectsbrowse;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usernameET = findViewById(R.id.editText);
        final EditText passwordET = findViewById(R.id.editText2);

        Button signinBtn = findViewById(R.id.button2);
        Button signupBtn = findViewById(R.id.button);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!usernameET.getText().toString().equals("") && !passwordET.getText().toString().equals("")){

                    // must convert to singleton
                        DbHelper dbHelper = new DbHelper(getApplicationContext(),"ProdectsBrowse",1);
                        SQLiteDatabase db =  dbHelper.getWritableDatabase();

                        String nameET = usernameET.getText().toString();
                        String passwordeET =  passwordET.getText().toString();

                        Cursor cursorLogin = db.rawQuery("select * from users where name = ? and password = ?" , new String[]{nameET ,passwordeET});
                        cursorLogin.moveToFirst();

                        if (cursorLogin.getCount() > 0 ){

                            String name = cursorLogin.getString(cursorLogin.getColumnIndex("name"));
                            String password = cursorLogin.getString(cursorLogin.getColumnIndex("password"));

                            if (name.equals(usernameET.getText().toString()) && password.equals(passwordET.getText().toString())) {

                                if (cursorLogin.getInt(cursorLogin.getColumnIndex("isAdmin")) == 1){

                                    Intent intent = new Intent(getApplicationContext() , AdminCrudUsersActivity.class);
                                    startActivity(intent);

                                }else{
                                    Intent intent = new Intent(getApplicationContext() , UserProdectTypeActivity.class);
                                    startActivity(intent);
                                }
                            }
                            db.close();
                        }
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext() , SignUpActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder adBuilderClose = new AlertDialog.Builder(this);

        adBuilderClose.setTitle("تأكيد الإغلاق");
        adBuilderClose.setMessage(R.string.exit_confirm);

        adBuilderClose.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
            }
        });
        adBuilderClose.setNegativeButton("لا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog d = adBuilderClose.create();
        d.setCanceledOnTouchOutside(false);
        d.show();
    }
}
