package com.example.noteapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import  com.example.noteapp.model.File;

import androidx.annotation.Nullable;
import com.example.noteapp.util.Util;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_FILE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Util.BODY + " TEXT , " + Util.TITLE  + " TEXT);";
        db.execSQL(CREATE_FILE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS";
        db.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME});
    }

    public long insertFile(File file)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.BODY, file.getBody());
        contentValues.put(Util.TITLE, file.getTitle());
        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return  newRowId;
    }

//    public  String fetchFile()
//    {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.ID}, Util.TITLE);
//    }

    public ArrayList<File> fetchAllFiles (){

        ArrayList<File> fileList = new ArrayList<File>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst())
        {
            do {
                File file = new File();
                file.setID(cursor.getInt(0));
                file.setTitle(cursor.getString(2));
                file.setBody(cursor.getString(1));

                fileList.add(file);

            }while (cursor.moveToNext());
        }
        db.close();
        return  fileList;
    }

    public int updateTexts(File file)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.BODY, file.getBody());
        contentValues.put(Util.TITLE, file.getTitle());


        return db.update(Util.TABLE_NAME, contentValues, Util.ID + "=?", new String[]{String.valueOf(file.getID())});
    }

    public int deleteTexts(File file)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(Util.TABLE_NAME,  Util.ID + "=?", new String[]{String.valueOf(file.getID())});
    }

}