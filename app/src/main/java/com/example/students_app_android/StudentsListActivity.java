package com.example.students_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students_app_android.model.Model;
import com.example.students_app_android.model.Student;

import java.util.List;

public class StudentsListActivity extends AppCompatActivity {
    RecyclerView studentsListRecyclerView;
    List<Student> studentList;
    TextView headerText;
    ImageView backButton;
    ImageView addStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        headerText = findViewById(R.id.headerText);
        headerText.setText("Students");
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> {
            this.finish();
        });

        studentList = Model.getInstance().getAllStudents();
        studentsListRecyclerView = findViewById(R.id.studentRecyclerList);
        studentsListRecyclerView.setHasFixedSize(true);

        studentsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(getApplicationContext(), StudentDetailsActivity.class);
                intent.putExtra("pos", pos);
                startActivity(intent);
            }
        });
        studentsListRecyclerView.setAdapter(adapter);

        addStudentButton = findViewById(R.id.addStudentButton);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView studentName;
        TextView studentId;
        CheckBox studentIsChecked;
        ImageView studentImage;

        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentListRowName);
            studentId = itemView.findViewById(R.id.studentListRowId);
            studentIsChecked = itemView.findViewById(R.id.studentListRowCheckbox);
            studentImage = itemView.findViewById(R.id.studentListRowImage);
            studentIsChecked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) studentIsChecked.getTag();
                    Student st = studentList.get(pos);
                    st.setChecked(studentIsChecked.isChecked());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            listener.onItemClick(pos);
                        }
                    }
                }
            });
        }

        public void bind(Student st, int position) {
            studentName.setText(st.getName());
            studentId.setText(st.getId());
            studentIsChecked.setChecked(st.isChecked());
            studentIsChecked.setTag(position);
            studentImage.setImageResource(st.getImageId());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
        OnItemClickListener listener;

        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);

            return new StudentViewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = studentList.get(position);
            holder.bind(st, position);
        }

        @Override
        public int getItemCount() {
            return studentList.size();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentsListRecyclerView.getAdapter().notifyDataSetChanged();
    }
}