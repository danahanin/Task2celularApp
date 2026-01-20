package com.example.students_app_android.model;

import com.example.students_app_android.R;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    public static Model getInstance(){
        return _instance;
    }

    private Model(){
        for (int i = 0; i < 5; i++) {
            studentList.add(new Student( Integer.toString(i),"name: " + i, R.drawable.avatar,"","",false ));
        }
    }

    public void addStudent (Student newStudent) {
        studentList.add(newStudent);
    }

    public void deleteStudent(int studentPos) {
        studentList.remove(studentPos);
    }

    List<Student> studentList = new LinkedList<>();

    public List<Student> getAllStudents(){
        return studentList;
    }

}
