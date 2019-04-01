package com.example.hospitools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Locale;

public class ProfileManager extends SQLiteOpenHelper {

    private final static String ACTIVITY_TAG = "ProfileActivity";
    public static final String DB_NAME = "Profiles Database";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Register Profile";
    public static final String ID = "Profile ID";
    public static final String USERNAME = "Username";
    public static final String PASSWORD = "Password";
    public static final String First_Name = "First Name";
    public static final String Last_Name = "Last Name";

    public static final String PHONE = "Phone Number";


    public ProfileManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE patientData (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }



    public long addProfile(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", user);
        contentValues.put("Password", password);
        long res = db.insert("Register Profile", null, contentValues);
        Log.w(ACTIVITY_TAG,"Res output" + res);
        db.close();

        return res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = {ID};
        Log.w(ACTIVITY_TAG,"Columns");
        SQLiteDatabase db =  getReadableDatabase();
        Log.w(ACTIVITY_TAG,"get readable database");
        String selection = USERNAME + "=?" + " and " + PASSWORD + " +?";
        Log.w(ACTIVITY_TAG,"selection");
        String[] selectionArgs = { username,password};
        StringBuilder sb = new StringBuilder();
        sb.append('(' + selectionArgs[0]);
        for (int i = 1; i < selectionArgs.length; i++) {
            sb.append(',');
            sb.append(selectionArgs[i]);
        }
        sb.append(')');
        String sel_arg_str = sb.toString();

        Log.w(ACTIVITY_TAG,sel_arg_str);
        Log.w(ACTIVITY_TAG,"selection args, selection is: " + selection + "select_args: " + selectionArgs);
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        Log.w(ACTIVITY_TAG,"cursor");
        int count = cursor.getCount();
        Log.w(ACTIVITY_TAG,"Count");
        cursor.close();
        Log.w(ACTIVITY_TAG,"Cursor close");
        db.close();

        if (count >0)
            return true;

        else
        Log.w(ACTIVITY_TAG,"else if");
            return false;

    }
}
