package com.aks.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ashish on 12/26/2017.
 */

public class MyDatabase extends SQLiteOpenHelper
{
    public static final String MY_DB = "myDB";
    public static final int VERSION = 1;
    Context context;
    public MyDatabase(Context c)
    {
        super(c,MY_DB,null,VERSION);
        context = c;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        try
        {
            String qry = "create table user_details (name text, contactno number primary key, username text, password text)";
            sqLiteDatabase.execSQL(qry);
            Toast.makeText(context, "table created", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Log.e("table creation",""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {

    }
}
