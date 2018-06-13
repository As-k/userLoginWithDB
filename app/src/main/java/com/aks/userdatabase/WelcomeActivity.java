package com.aks.userdatabase;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aks.db.MyDatabase;

public class WelcomeActivity extends Activity
{
    long cno;
    String name,username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        cno = getIntent().getExtras().getLong("k1");
    }

    public void viewProfile(View v)
    {

        String qry = "select name, username, password from user_details where contactno ="+cno;
        MyDatabase md = new MyDatabase(this);
        SQLiteDatabase db = md.getWritableDatabase();
        Cursor c = db.rawQuery(qry,null);
        if (c.moveToFirst())
        {
            name = c.getString(0);
            username = c.getString(1);
            pass = c.getString(2);
        }
        Intent i = new Intent(this,ViewProfileActivity.class);
        i.putExtra("k1",name);
        i.putExtra("k2",cno);
        i.putExtra("k3",username);
        i.putExtra("k4",pass);
        startActivity(i);
    }

    public void deleteProfile(View v)
    {
        try {
            String qry = "delete from user_details where contactno ="+cno;
            MyDatabase md = new MyDatabase(this);
            SQLiteDatabase db = md.getWritableDatabase();
            db.execSQL(qry);
            finish();
            Toast.makeText(this, "Profile deleted successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Log.e("deleting: ",""+e);
        }
    }

    public void logout(View v)
    {
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
