package com.aks.userdatabase;

import android.app.Activity;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aks.db.MyDatabase;

public class RegistrationActivity extends Activity
{
    EditText et[] = new EditText[4];
    int ids[] = {R.id.name, R.id.cno,R.id.uname,R.id.pass};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        for (int i=0; i<et.length; i++)
        {
            et[i] = findViewById(ids[i]);
        }
    }

    public void registerUser(View v)
    {
        String values[] = new String[et.length];
        int i;
        for (i=0; i<et.length; i++)
        {
            values[i] = et[i].getText().toString().trim();
            if (values[i].isEmpty())
            {
                et[i].setError("Empty");
                et[i].requestFocus();
                break;
            }
        }

        if (i == et.length)
        {
            if (values[1].length() == 10)
            {
                if (values[1].charAt(0)=='9'||values[1].charAt(0)=='8'||values[1].charAt(0)=='7')
                {
                    long cno = Long.parseLong(values[1]);
                    MyDatabase md = new MyDatabase(this);
                    SQLiteDatabase db = md.getWritableDatabase();
                    try
                    {
                        String qry = "insert into user_details values('"+values[0]+"',"+cno+",'"+values[2]+"','"+values[3]+"')";
                        db.execSQL(qry);
                        Toast.makeText(this, "Registration done", Toast.LENGTH_SHORT).show();
                        finish();
                    }catch (SQLiteConstraintException e)
                    {
                        Log.e("Insertion",""+e);
                        Toast.makeText(this, "Number is used", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Log.e("Insertion",""+e);
                    }

                }
                else {
                    et[1].setError("Invalid Contact No.");
                    et[1].requestFocus();
                }
            }
            else {
                et[1].setError("must be 10 digits");
                et[1].requestFocus();
            }
        }
    }
}
