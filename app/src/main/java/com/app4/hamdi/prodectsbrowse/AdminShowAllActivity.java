package com.app4.hamdi.prodectsbrowse;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminShowAllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_all);

        String type = getIntent().getStringExtra("type");
        final TextView typeTv = findViewById(R.id.textView);
        typeTv.setText("The Type : " + type);

        // must convert to singelton
        DbHelper dbHelper = new DbHelper(getApplicationContext(),"ProdectsBrowse",1);
        final SQLiteDatabase db =  dbHelper.getWritableDatabase();

        final Cursor cursor = db.rawQuery("select * from prodects where type = ? " , new String[]{type});

        final ListView listView = findViewById(R.id.listview);
        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext() , cursor);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor1 = (Cursor) listView.getItemAtPosition(position);
                int prodId = cursor1.getInt(cursor1.getColumnIndex("_id"));

                Intent intent = new Intent(getApplicationContext() , AdminProdectDetailesActivity.class);
                intent.putExtra("id" , prodId);
                startActivity(intent);

            }
        });

    }
}
