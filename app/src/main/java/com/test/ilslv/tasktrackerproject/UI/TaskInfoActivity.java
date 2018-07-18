package com.test.ilslv.tasktrackerproject.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.test.ilslv.tasktrackerproject.Domain.Task;
import com.test.ilslv.tasktrackerproject.Domain.TaskInfoContract;
import com.test.ilslv.tasktrackerproject.R;
import com.test.ilslv.tasktrackerproject.TasksTrackerApp;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskInfoActivity extends AppCompatActivity implements TaskInfoContract.TaskInfoView{

    @BindView(R.id.taskTitleInfo)
    TextView taskTitle;
    @BindView(R.id.taskStatusInfo)
    TextView taskStatus;
    @BindView(R.id.taskDateInfo)
    TextView taskDate;
    @BindView(R.id.taskDescriptionInfo)
    TextView taskDescription;
    @BindView(R.id.taskEdit)
    Button editTask;
    @BindView(R.id.taskDelete)
    Button deleteTask;
    UUID taskId;

    @Inject
    TaskInfoContract.TaskInfoPresenter taskInfoPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);
        ButterKnife.bind(this);
        TasksTrackerApp.getTasksTrackerComponent().inject(this);
        taskInfoPresenter.onCreate(this);
        Intent intent = getIntent();
        taskId = UUID.fromString(intent.getStringExtra("taskId"));
        deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskInfoPresenter.deleteTask(taskId);
            }
        });

        editTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TaskEditActivity.class);
                intent.putExtra("taskId", taskId.toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        taskInfoPresenter.loadTaskInfo(taskId);
    }

    @Override
    public void showTaskInfo(Task task) {
        taskTitle.setText(task.getTaskTitle());
        taskStatus.setText(task.getTaskStatus().getName());

        Locale loc = new Locale("ru");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", loc);
        format.setTimeZone(TimeZone.getDefault());
        taskDate.setText(format.format(task.getTaskDate()));

        taskDescription.setText(task.getTaskDescription());
    }

    @Override
    public void showDeleteResult() {
        Toast.makeText(getApplicationContext(), "Задача удалена", Toast.LENGTH_SHORT).show();
        this.finish();
    }

}
