package vn.poly.demosqlitee.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.poly.demosqlitee.R;
import vn.poly.demosqlitee.sqlite.StudentDAO;
import vn.poly.demosqlitee.sqlite.StudentReaderHelper;
import vn.poly.demosqlitee.model.Student;

public class MainActivity extends AppCompatActivity {


    private TextView tvInfo;

    private StudentDAO studentDAO;


    private EditText edtId;
    private EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        tvInfo = findViewById(R.id.tvInfo);


        studentDAO = new StudentDAO(MainActivity.this);


    }

    public void addStudent(View view) {


        String id = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();

        if (id.equals("")) {

            Toast.makeText(this, "Vui long nhap ID", Toast.LENGTH_SHORT).show();
        } else if (name.equals("")) {

            Toast.makeText(this, "Vui long nhap Name", Toast.LENGTH_SHORT).show();
        }else {

            Student student = new Student();
            student.id = id;
            student.name = name;
            long result = studentDAO.insertStudent(student);

            if (result > 0){
                Toast.makeText(this, "Thanh Cong", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this, "Khong Thanh Cong!", Toast.LENGTH_SHORT).show();

            }

            List<Student> studentList = studentDAO.getAllStudents();

            Student student1 = studentList.get(0);

            tvInfo.setText(student1.id + "  :   " + student1.name);
        }


    }

    public void updateStudent(View view) {

        Student student = new Student();
        student.id = "6666";
        student.name = "Nguyen Dinh Dinh";
        studentDAO.updateStudent(student);

        List<Student> studentList = studentDAO.getAllStudents();

        Student student1 = studentList.get(0);

        tvInfo.setText(student1.id + "  :   " + student1.name);


    }

    public void deleteStudent(View view) {


    }
}
