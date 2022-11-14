package fr.fredray21.myapplication.tp5_BDD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class helperBDD extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BDD.db";
    public static final String TABLE_NAME = "repertoire";
    public static final String COL_ID = "ID";
    public static final String COL_PAYS = "PAYS";
    public static final String COL_DEVISE = "DEVISE";

    public helperBDD(Context ctx) {
        super(ctx, "BDD", null, 1);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PAYS + " TEXT, " + COL_DEVISE + " TEXT)");
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertPays(String pays, String devise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PAYS, pays);
        contentValues.put(COL_DEVISE, devise);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> getAllPays() {
        ArrayList<String> res = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor q = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        q.moveToFirst();
        while (!q.isAfterLast()) {
            @SuppressLint("Range") String id = q.getString(q.getColumnIndex(COL_ID));
            @SuppressLint("Range") String nom = q.getString(q.getColumnIndex(COL_PAYS));
            @SuppressLint("Range") String tel = q.getString(q.getColumnIndex(COL_DEVISE));
            res.add(id + " - " + nom + " - " + tel);
            q.moveToNext();
        }
        return res;
    }

    public void clearBDD() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
