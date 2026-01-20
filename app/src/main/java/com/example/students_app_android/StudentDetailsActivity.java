package com.example.students_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.students_app_android.model.Model;
import com.example.students_app_android.model.Student;

public class StudentDetailsActivity extends AppCompatActivity {
    TextView headerText;
    ImageView backButton;
    ImageView imageView;
    TextView nameView;
    TextView idView;
    TextView phoneView;
    TextView addressView;
    CheckBox checkedView;
    Button editStudentButton;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> {
            this.finish();
        });

        headerText = findViewById(R.id.headerText);
        headerText.setText("Student Details");

        imageView = findViewById(R.id.studentDetailsImage);
        nameView = findViewById(R.id.nameValue);
        idView = findViewById(R.id.idValue);
        phoneView = findViewById(R.id.phoneValue);
        addressView = findViewById(R.id.addressValue);
        checkedView = findViewById(R.id.studentDetailsChecked);
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos");
        setViews();
        editStudentButton = findViewById(R.id.editStudentButton);
        editStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditStudentActivity.class);
                intent.putExtra("pos", pos);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setViews();

    }

    private void setViews(){
        Student st = Model.getInstance().getAllStudents().get(pos);

        imageView.setImageResource(st.getImageId());
        nameView.setText(st.getName());
        idView.setText(st.getId());
        phoneView.setText(st.getPhoneNumber());
        addressView.setText(st.getAddress());
        checkedView.setChecked(st.isChecked());
        checkedView.setText(st.isChecked()? "checked": "unchecked");
    }
}
