package com.app4.hamdi.prodectsbrowse;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ItemAdapter extends CursorAdapter {

    public ItemAdapter(Context context, Cursor c) {
        super(context, c, 1);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_display , parent , false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameTv = view.findViewById(R.id.textView10);
        TextView priceTv = view.findViewById(R.id.textView12);

        String name = cursor.getString(cursor.getColumnIndex("name"));
        String price = cursor.getString(cursor.getColumnIndex("price"));

        nameTv.setText(name);
        priceTv.setText(price);

    }
}
