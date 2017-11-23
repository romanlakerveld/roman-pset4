package com.example.roman.roman_pset4;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.security.KeyStore;

/**
 * Created by roman on 23/11/2017.
 */

public class TodoAdapter extends ResourceCursorAdapter {
    public TodoAdapter(Context context, Cursor cursor) {
        super(context, R.layout.row_todo, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int completed = cursor.getInt(cursor.getColumnIndex("completed"));
        String title = cursor.getString(cursor.getColumnIndex("title"));
        TextView textView = view.findViewById(R.id.textView);
        CheckBox checkBox = view.findViewById(R.id.checkBox2);
        textView.setText(title);
        if (completed == 1) {
            checkBox.setChecked(true);
        }
        else if (completed == 0) {
            checkBox.setChecked(false);
        }
    }
}
