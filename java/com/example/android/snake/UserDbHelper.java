/**package com.example.android.snake;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by chris on 2/28/2017.
 *
 * Methods to perform database operations. SqLiteOpenHelper
 *
 *

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DATABASE_OPERATION";

    private static final String DATABASE_NAME = "Score_Table";
    private static final int DATABASE_VERSION =1;
    private static final String CREATE_QUERY = "CREATE TABLE "+ TableColumns.Score.SCORE_TABLE_NAME+"("+ TableColumns.Score.USER_NAME+" Text,"
            + TableColumns.Score.SCORE+" Text,"+ TableColumns.Score.DATE+" Text);"; // pay attention to spacing carefully

    public UserDbHelper(Context context){//constructor
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e(TAG,"DATABASE CREATED/OPENED");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { // create table is only called the first time once the table is created it is not called

        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.e(TAG,"Table Created");
    }

    public void addInformation(String name, String score, String Date, SQLiteDatabase db ){

        ContentValues contentValues = new ContentValues();
        contentValues.put(TableColumns.Score.USER_NAME,name);
        contentValues.put(TableColumns.Score.SCORE,score);
        contentValues.put(TableColumns.Score.DATE,Date);
        db.insert(TableColumns.Score.SCORE_TABLE_NAME, null, contentValues);
        Log.e(TAG,"One row is inserted");
    }

    public Cursor getInformation(SQLiteDatabase db){

        Cursor cursor;// objects that retrieves information from the database
        String[] projections = {TableColumns.Score.USER_NAME,TableColumns.Score.DATE,TableColumns.Score.SCORE};
        String orderBy = TableColumns.Score.SCORE+" DESC";
        String limit = "5";

       cursor = db.query(TableColumns.Score.SCORE_TABLE_NAME,projections,null,null,null,null,orderBy,limit); // the nulls have to do with where clause info

        return cursor;
    }


    


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
*/