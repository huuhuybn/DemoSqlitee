package vn.poly.demosqlitee.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.poly.demosqlitee.model.Student;

import static vn.poly.demosqlitee.sqlite.StudentReaderHelper.COL_ID;
import static vn.poly.demosqlitee.sqlite.StudentReaderHelper.COL_NAME;
import static vn.poly.demosqlitee.sqlite.StudentReaderHelper.TABLE_NAME;

public class StudentDAO {

    private StudentReaderHelper studentReaderHelper;


    public StudentDAO(Context context) {
        studentReaderHelper = new StudentReaderHelper(context);
    }


    public long insertStudent(Student student) {
        //b1-xin quyen
        SQLiteDatabase sqLiteDatabase = studentReaderHelper.getWritableDatabase();
        //b2-ghep cap du lieu voi ten cot tuong ung
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, student.id);
        contentValues.put(COL_NAME, student.name);
        //b3- goi cau lenh insert
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        //b4- close ket noi
        sqLiteDatabase.close();
        return result;

    }

    public long updateStudent(Student student) {
        //b1-xin quyen
        SQLiteDatabase sqLiteDatabase = studentReaderHelper.getWritableDatabase();
        //b2-ghep cap du lieu voi ten cot tuong ung
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, student.id);
        contentValues.put(COL_NAME, student.name);
        //b3- goi cau lenh insert
        long result = sqLiteDatabase.
                update(TABLE_NAME, contentValues, COL_ID + "=?", new String[]{student.id});
        //b4- close ket noi
        sqLiteDatabase.close();

        return result;

    }

    public int deleteStudent(String id) {

        //b1-xin quyen
        SQLiteDatabase sqLiteDatabase = studentReaderHelper.getWritableDatabase();

        int result = sqLiteDatabase.
                delete(TABLE_NAME, COL_ID + "=?", new String[]{id});

        sqLiteDatabase.close();

        return result;


    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        // b1 - xin quyen
        SQLiteDatabase sqLiteDatabase = studentReaderHelper.getReadableDatabase();
        // b2 : viet cau lenh truy van
        String SELECT = "SELECT * FROM " + TABLE_NAME;
        //b3: goi cau lenh truy van
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);

        // b4 : lay du lieu tu cursor cho vao List Student

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.id = cursor.getString(cursor.getColumnIndex(COL_ID));
                student.name = cursor.getString(cursor.getColumnIndex(COL_NAME));

                studentList.add(student);

            } while (cursor.moveToNext());

            cursor.close();
        }

        sqLiteDatabase.close();

        return studentList;

    }


}
