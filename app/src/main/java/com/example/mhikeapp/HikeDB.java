package com.example.mhikeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class HikeDB extends SQLiteOpenHelper {



    private static final String DB_NAME = "hikeDetails";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "hikedetails";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our fullname column
    private static final String HIKE_COL = "hikeName";

    // below variable id for our email column.
    private static final String HIKE_LOC_COL = "hikeLocation";

    // below variable for our password column.
    private static final String HIKE_DATE_COL = "hikeDate";
    private static final String HIKE_PARKING = "parkingAvailability";
    private static final String HIKE_LENGTH = "hikeLength";
    private static final String HIKE_LEVEL = "hikeLevel";
    private static final String HIKE_DESC = "hikeDescription";



//    private static final String KEY_NAME = "image_name";
//    private static final String KEY_IMAGE = "image_data";


    // creating a constructor for our database handler.
    public HikeDB(Context context) {
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
                + HIKE_COL + " TEXT,"
                + HIKE_LOC_COL + " TEXT,"
                + HIKE_DATE_COL + " TEXT,"
                +
                HIKE_PARKING + " TEXT,"+HIKE_LENGTH+" TEXT,"+HIKE_LEVEL+
                " TEXT,"+HIKE_DESC+" TEXT)";

        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addUsers(String name, String location, String date,String parking, String length, String level, String desc) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();


        values.put(HIKE_COL, name);
        values.put(HIKE_LOC_COL, location);
        values.put(HIKE_DATE_COL, date);
        values.put(HIKE_PARKING, parking);
        values.put(HIKE_LENGTH,length);
        values.put(HIKE_LEVEL, level);
        values.put(HIKE_DESC,desc);


        db.insert(TABLE_NAME, null, values);


        db.close();
    }
    public Cursor readalldate(){
        SQLiteDatabase db= this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME ;

        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
     void updatedata(String id,String name,String location, String date, String parking, String length, String level, String desc){

        SQLiteDatabase sdb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HIKE_COL,name);
         cv.put(HIKE_LOC_COL,location);
         cv.put(HIKE_DATE_COL,date);
         cv.put(HIKE_PARKING,parking);
         cv.put(HIKE_LENGTH,length);
         cv.put(HIKE_LEVEL,level);
         cv.put(HIKE_DESC,desc);
         System.out.println(cv);
         System.out.println(id);
        int check = sdb.update(TABLE_NAME,cv,"id=?",new String[]{id});
        if(check==-1)
            System.out.println("Not updated");
        else
            System.out.println("Updated");
     }
     void deleteHike(String id){
         SQLiteDatabase db = this.getWritableDatabase();
         db.delete(TABLE_NAME,"id=?",new String[]{id});


     }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);

    }
}

