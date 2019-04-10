package com.example.hospitoolsv2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;

public class DatabaseManager extends SQLiteOpenHelper {
    private String ACTIVITY_TAG = "MainActivity";
    private static final String DATABASE_NAME = "paitientDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PATIENT = "patient";
    private static final String ID = "id";
    private static final String FIRST = "firstName";
    private static final String LAST = "lastName";
    private static final String GENDER = "gender";
    private static final String NUMBER = "number";
    private static final String DOB = "dateOfBirth";
    private static final String EMAIL = "email";

    public DatabaseManager( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateStr = "create table " + TABLE_PATIENT + "( " + ID;
        sqlCreateStr += " integer primary key autoincrement, " + FIRST;
        sqlCreateStr += " text, " + LAST;
        sqlCreateStr += " text, " + NUMBER;
        sqlCreateStr += " text, " + GENDER;
        sqlCreateStr += " text, " + DOB;
        sqlCreateStr += " text, " + EMAIL + " text )";
        Log.w(ACTIVITY_TAG, "making database, cmd is "+ sqlCreateStr);
        sqLiteDatabase.execSQL(sqlCreateStr);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_PATIENT);

        onCreate(sqLiteDatabase);
    }


    public void insert(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_PATIENT;
        sqlInsert += " values( null, '" + patient.getFirstName( );
        sqlInsert += "', '" + patient.getLastName( );
        sqlInsert += "', '" + patient.getNumber( );
        sqlInsert += "', '" + patient.getGender( );
        sqlInsert += "', '" + patient.getDateOfBirth( );
        sqlInsert += "', '" + patient.getEmail( ) + "' )";

        Log.w(ACTIVITY_TAG,"command is: "+sqlInsert);
        db.execSQL( sqlInsert );
        db.close();


    }

    public void deleteByID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_PATIENT;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int id, String firstName, String lastName, String number,String gender, String datOfBirth, String email) {
        SQLiteDatabase database = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_PATIENT;
        sqlUpdate += " set " + FIRST + " = '" + firstName + "' ";
        sqlUpdate += LAST + " = '" + lastName + "' ";
        sqlUpdate += NUMBER + " = '" + number + "' ";
        sqlUpdate += GENDER + " = '" + gender + "' ";
        sqlUpdate += DOB + " = '" + datOfBirth + "' ";
        sqlUpdate += EMAIL + " = '" + email + "' ";
        sqlUpdate += " where " + ID + " = " + id;

        database.execSQL(sqlUpdate);
        database.close();
    }

    public ArrayList<Patient> selectAll() {
        String sqlQuery = String.format(Locale.US,
                "select * from %s", TABLE_PATIENT);

        Log.w(ACTIVITY_TAG, "query string created");

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(sqlQuery, null);
        Log.w(ACTIVITY_TAG, "ran query");

        ArrayList<Patient> patients = new ArrayList<>();
        while (cursor.moveToNext()) {
            Patient currentPatient = new Patient(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6));
            patients.add(currentPatient);
        }
        database.close();

        Log.w(ACTIVITY_TAG, "database closed");

        return patients;
    }

    public Patient selectByID(int id) {
        String sqlQuery = "select * from " + TABLE_PATIENT;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(sqlQuery, null);

        Patient patient = null;
        if (cursor.moveToFirst())
            patient = new Patient(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));

        return patient;


    }



}