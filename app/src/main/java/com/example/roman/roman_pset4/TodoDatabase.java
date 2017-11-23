package com.example.roman.roman_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.ResourceCursorAdapter;
import android.util.Log;
import android.view.View;

/**
 * Created by roman on 22/11/2017.
 */

public class TodoDatabase extends SQLiteOpenHelper {
    private static TodoDatabase instance;

    public static TodoDatabase getInstance(Context context) {
        if(instance == null) {
            instance = new TodoDatabase(context, "todo", null, 2);
        }
        return instance;
    }
    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, completed bit)");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('1', 0)");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('G2y', 0)");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('S3e', 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS todos");
        onCreate(sqLiteDatabase);
    }

    public Cursor selectAll() {
        SQLiteDatabase writer = getWritableDatabase();
        Cursor c = writer.rawQuery("SELECT * FROM todos", null);
        return c;

    }

    public void insert(String title, int completed) {
        SQLiteDatabase writer = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("completed", completed);
        writer.insert("todos", null, contentValues);
    }

    public void update(long id, int completed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("completed", completed);
        db.update("todos", contentValues, "_id = " + id, null);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("todos", "_id = " + id, null);
    }
}
