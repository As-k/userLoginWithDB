package com.aks.userdatabase;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aks.db.MyDatabase;

public class MainActivity extends Activity
{
    EditText et1, et2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
    }
    public void userLogin(View v)
    {
        String cno = et1.getText().toString().trim();
        String pass = et2.getText().toString().trim();
        if(cno.isEmpty())
        {
            et1.setError("Empty");
            et1.requestFocus();
        }
        else {
            if(pass.isEmpty())
            {
                et2.setError("Empty");
                et2.requestFocus();
            }
            else {
                if (cno.length() == 10) {
                    if (cno.charAt(0)=='9'||cno.charAt(0)=='8'||cno.charAt(0)=='7')
                    {
                        long contact = Long.parseLong(cno);

                        String qry = "select password from user_details where contactno ="+contact;
                        MyDatabase md = new MyDatabase(this);
                        SQLiteDatabase db = md.getWritableDatabase();
                        Cursor c = db.rawQuery(qry,null);
                        if (c.moveToFirst()){
                            String password = c.getString(0);
                            if (pass.equals(password))
                            {
                                Intent i = new Intent(this,WelcomeActivity.class);
                                i.putExtra("k1",contact);
                                startActivity(i);
                                finish();
                            }
                            else {
                                et2.setError("Invalid");
                                et2.requestFocus();
                            }
                        }
                        else {
                            et1.setError("Invalid");
                            et1.requestFocus();
                        }
                    }
                    else {
                        et1.setError("Invalid Contact No");
                        et1.requestFocus();
                    }
                }
                else {
                    et1.setError("Must be 10 digits");
                    et1.requestFocus();
                }
            }
        }

    }

    public void registration(View v)
    {
        startActivity(new Intent(this,RegistrationActivity.class));
    }
}
