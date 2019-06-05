package vn.poly.demosqlitee.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vn.poly.demosqlitee.model.Student;

public class StudentReaderHelper extends SQLiteOpenHelper {


    final String CREATE_TABLE = "CREATE TABLE Student (id NVARCHAR PRIMARY KEY,name NVARCHAR)";
    public static final String TABLE_NAME = "Student";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";


    public StudentReaderHelper(Context context) {
        super(context, "students.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
