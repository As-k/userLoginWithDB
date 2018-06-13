package com.aks.userdatabase;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aks.db.MyDatabase;

public class ViewProfileActivity extends Activity
{
    EditText et1, et2, et3;
    Long cno;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        Bundle b = getIntent().getExtras();
        String name = b.getString("k1");
        cno = b.getLong("k2");
        String username = b.getString("k3");
        String pass = b.getString("k4");

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);

        et1.setText(String.valueOf(name));
        et2.setText(String.valueOf(username));
        et3.setText(String.valueOf(pass));
    }

    public void update(View v)
    {
        String nm = et1.getText().toString().trim();
        String unm = et2.getText().toString().trim();
        String password = et3.getText().toString().trim();

        String qry = "update user_details set name ='"+nm+"', username ='"+unm+"', password ='"+password+"' where contactno ="+cno;
        MyDatabase md = new MyDatabase(this);
        SQLiteDatabase db = md.getWritableDatabase();
        db.execSQL(qry);
        Toast.makeText(this, "ok"+cno, Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, WelcomeActivity.class));

    }

    public void close(View v)
    {
        finish();
        startActivity(new Intent(this, WelcomeActivity.class));
    }
}
