package fr.fredray21.myapplication.tp5_BDD_2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;

import java.text.MessageFormat;
import java.util.ArrayList;

public class helperBDD_2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BDD.dbChuckNorris";
    public static final String TABLE_NAME = "repertoire";
    public static final String COL_ID = "ID";
    public static final String COL_BLAGUE = "BLAGUE";
    public static final String COL_DATEUPDATE = "DATEUPDATE";
    private static helperBDD_2 instance;

    helperBDD_2(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static synchronized helperBDD_2 getInstance(Context context) {
        if (instance == null) {
            instance = new helperBDD_2(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MessageFormat.format("CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} TEXT, {3} TEXT)", TABLE_NAME, COL_ID, COL_BLAGUE, COL_DATEUPDATE)
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MessageFormat.format("DROP TABLE IF EXISTS {0}", TABLE_NAME));
        onCreate(db);
    }


    public boolean insertBlague(String blague, String dateUpdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BLAGUE, blague);
        contentValues.put(COL_DATEUPDATE, dateUpdate);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            Log.d("UWU", "BLAGUE AJOUTER IN BDD");
            return true;
        }
    }

    public ArrayList<String> getAllBlagues() {
        ArrayList<String> res = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor q = db.rawQuery(MessageFormat.format("SELECT * FROM {0}", TABLE_NAME), null);
        q.moveToFirst();
        while (!q.isAfterLast()) {
            @SuppressLint("Range") String id = q.getString(q.getColumnIndex(COL_ID));
            @SuppressLint("Range") String blague = q.getString(q.getColumnIndex(COL_BLAGUE));
            @SuppressLint("Range") String dateUpdate = q.getString(q.getColumnIndex(COL_DATEUPDATE));
            res.add(id +" - " + dateUpdate +" - " + blague);
            q.moveToNext();
        }
        return res;
    }

}
