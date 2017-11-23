package com.example.roman.roman_pset4;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private TodoDatabase db;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button onclick listener
        Button button = (Button) findViewById(R.id.addButton);
        button.setOnClickListener(new AddButtonClickListener());

        // List onclick listener
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new OnItemCLickListener());
        listView.setOnItemLongClickListener(new OnItemLongClickListener());

        // instantiate database , add 1 thing and get a cursor
        db = TodoDatabase.getInstance(this);
        Cursor c = db.selectAll();

        // instantiate adapter
        adapter = new TodoAdapter(this, c);
        listView.setAdapter(adapter);
    }
    private void updateData() {
        Cursor cursor = db.selectAll();
        adapter.swapCursor(cursor);
    }

    private class AddButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText editText = findViewById(R.id.titleText);
            db.insert(editText.getText().toString(), 0);
            editText.setText("");
            updateData();
        }
    }

    private class OnItemCLickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CheckBox checkBox = view.findViewById(R.id.checkBox2);
            int checked = 1;
            if (checkBox.isChecked()) {
                checked = 0;
            }
            Log.d("CREATION", "onItemClick: " + checked);
            db.update(l, checked);
            updateData();
        }
    }

    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            db.delete(l);
            updateData();
            return true;

        }
    }
}
