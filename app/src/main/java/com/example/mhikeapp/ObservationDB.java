package com.example.mhikeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ObservationDB extends SQLiteOpenHelper {



    private static final String DB_NAME = "observations";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "observations";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our fullname column
    private static final String HIKE_id = "hikeID";


    private static final String HIKE_OBSERVATION = "hikeObservation";
    private static final String HIKE_DATEOBS = "dateRecorded";
    private static final String HIKE_TIMEOBS = "timeeRecorded";

    private static final String HIKE_COMMENTSOBD = "observationcomments";




//    private static final String KEY_NAME = "image_name";
//    private static final String KEY_IMAGE = "image_data";


    // creating a constructor for our database handler.
    public ObservationDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {

//        String query = "CREATE TABLE " + TABLE_NAME + " ("
//                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + HIKE_COL + " TEXT,"
//                + HIKE_LOC_COL + " TEXT,"
//                + HIKE_DATE_COL + " TEXT,"
//                 +
//                KEY_IMAGE + " BLOB)";
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HIKE_id + " TEXT,"
                + HIKE_OBSERVATION + " TEXT,"
                + HIKE_DATEOBS + " TEXT,"
                +HIKE_TIMEOBS + " TEXT,"+
                HIKE_COMMENTSOBD + " TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // this method is use to add new course to our sqlite database.
    public void addObservation(String id, String hikeobservation, String date,String time, String comments) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();


        values.put(HIKE_id, id);
        values.put(HIKE_OBSERVATION, hikeobservation);
        values.put(HIKE_DATEOBS, date);
        values.put(HIKE_TIMEOBS,time);
        values.put(HIKE_COMMENTSOBD, comments);



        db.insert(TABLE_NAME, null, values);


        db.close();
    }


    public Cursor readalldate(String id){
        SQLiteDatabase db= this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +" where "+ HIKE_id+ "= "+id;

        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }
}

