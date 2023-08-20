package com.example.mhikeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "usersdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "users";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our fullname column
    private static final String NAME_COL = "name";

    // below variable id for our email column.
    private static final String EMAIL_COL = "email";

    // below variable for our password column.
    private static final String PASSWORD_COL = "password";



    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";


        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addUsers(String fullName, String emailID, String password) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();


        values.put(NAME_COL, fullName);
        values.put(EMAIL_COL, emailID);
        values.put(PASSWORD_COL, password);


        db.insert(TABLE_NAME, null, values);


        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void deleteuser(String id) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "id=?", new String[]{id});
        db.close();
    }
}

