/**package com.example.android.snake;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Timer;
import java.util.TimerTask;

/*
 * Created by chris on 3/1/2017.
 *

public class ListOfScoresActivity extends Activity {

    ListView mListView;
    SQLiteDatabase mSQLiteDatabase;
    UserDbHelper mUserDbHelper;
    Cursor mCursor;
    CustomListAdapter mCustomListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        mListView = (ListView)findViewById(R.id.list_view);
        mCustomListAdapter = new CustomListAdapter(getApplicationContext(),R.layout.row_layout);
        mListView.setAdapter(mCustomListAdapter);
        getInformation();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(ListOfScoresActivity.this,ListOfScoresFromCloud.class));
            }
        };

        Timer opening = new Timer();
        opening.schedule(timerTask,10000);
    }


    public void getInformation() {

        mUserDbHelper = new UserDbHelper(getApplicationContext());
        mSQLiteDatabase = mUserDbHelper.getReadableDatabase();
        mCursor = mUserDbHelper.getInformation(mSQLiteDatabase);
        if (mCursor.moveToFirst()) {// will return true if information is available false if not

            do {
                String username,date,score;
                username = mCursor.getString(0);
                score = mCursor.getString(1);
                date = mCursor.getString(2);
                DataProvider dataProvider = new DataProvider(username,score,date);
                mCustomListAdapter.add(dataProvider);

            }while (mCursor.moveToNext()); // all information is stored in mCursor
        }
    }

}
*/