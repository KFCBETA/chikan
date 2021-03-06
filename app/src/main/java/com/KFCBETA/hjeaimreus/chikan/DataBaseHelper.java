package com.KFCBETA.hjeaimreus.chikan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hrw on 14/8/25.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    private final Context context;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ChikanDataBase";
    private static final String TABLE_INTNEWS = "intNews";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ARTICLE = "article";
    private static final String KEY_IMAGE = "image";
    private static final String TAG = "DataBaseHelper";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS intNews " + "(title NVARCHAR(20)" + ","
                + "article NVARCHAR(15000))" + "," + "image BLOB";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public void addIntNews(ArrayList<ArrayList<String>> intNews) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        for(int j = intNews.get(0).size()-1 ; j >= 0; j --){
            cv.put(KEY_TITLE, intNews.get(0).get(j));
            cv.put(KEY_ARTICLE, intNews.get(1).get(j));
            db.insert(TABLE_INTNEWS,null,cv);
        }

    }

    public void addPicsNews(byte[] image) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IMAGE,image);
        db.insert(TABLE_INTNEWS,null,cv);
    }

    public ArrayList<ArrayList<String>> getIntNews(){
        ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
        ArrayList<String> temp1 = new ArrayList<String>();
        ArrayList<String> temp2 = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT title,article FROM intNews", null);
        if (c.moveToFirst()) {
            do {
                temp1.add(c.getString(0));
                temp2.add(c.getString(1));
            } while (c.moveToNext());
        }
        temp.add(temp1);
        temp.add(temp2);
        return temp;
    }

    public void deleteTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_INTNEWS,null,null);
    }

}
