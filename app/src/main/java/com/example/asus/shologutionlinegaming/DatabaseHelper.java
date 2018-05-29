package com.example.asus.shologutionlinegaming;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME="highestScore.db";
        public static final String TABLE_NAME="azaz";
        public static final String COL2="ITEM";
        //public static final String[][] ID={{"1"},{"2"},{"3"},{"4"},{"5"},{"6"},{"7"},{"8"},{"9"},{"10"},{"11"},{"12"},{"13"},{"14"},{"15"},{"16"},{"17"},{"18"},{"19"},{"20"},{"21"},{"22"},
                //{"23"},{"24"},{"25"},{"26"},{"27"},{"28"},{"29"},{"30"},{"31"},{"32"},{"33"},{"34"},{"35"},{"36"},{"37"},{"38"},{"39"},{"40"},{"41"},{"42"},{"43"},{"44" }};

        SQLiteDatabase db;

        public DatabaseHelper(Context context){


                super(context,DATABASE_NAME,null,1);


        }

        @Override
        public void onCreate(SQLiteDatabase db){




                String createTable="CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+"ITEM TEXT)";
                db.execSQL(createTable);



        }

        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){




                 db.execSQL("DROP  TABLE IF  EXISTS " +TABLE_NAME);



        }

        public boolean addData(String item) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL2, item);


            long result = db.insert(TABLE_NAME, null, contentValues);

            if (result == -1) {

                return false;
            } else return true;

        }


        public Cursor getListContents() {


                 db = this.getWritableDatabase();
                Cursor data = db.rawQuery("SELECT *FROM " + TABLE_NAME, null);


                return data;

        }

        public void deleteData(){

            for(int i=0;i<100;i++) {
                db = this.getWritableDatabase();


                String[] ID = {"" + i};

                int a = db.delete(TABLE_NAME, "ID = ?", ID);
            }






        }





}
