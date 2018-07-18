package com.test.ilslv.tasktrackerproject.UI;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.test.ilslv.tasktrackerproject.Domain.MainContract;
import com.test.ilslv.tasktrackerproject.Domain.Task;
import com.test.ilslv.tasktrackerproject.R;
import com.test.ilslv.tasktrackerproject.TasksTrackerApp;
import com.test.ilslv.tasktrackerproject.UI.AddNewTaskActivity;
import com.test.ilslv.tasktrackerproject.UI.TasksAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    @BindView(R.id.tasksView)
    RecyclerView tasksView;
    @BindView(R.id.addNewTask)
    FloatingActionButton addNewTask;
    @BindView(R.id.recyclerHint)
    TextView recyclerHint;
    TasksAdapter tasksAdapter;

    @Inject
    MainContract.MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TasksTrackerApp.getTasksTrackerComponent().inject(this);
        mainPresenter.onCreate(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        tasksView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(tasksView.getContext(),
                linearLayoutManager.getOrientation());
        tasksView.addItemDecoration(dividerItemDecoration);

        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddNewTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.loadTasks();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }

    @Override
    public void showTasks(List<Task> tasks) {
        if (tasks.size() != 0)
            recyclerHint.setVisibility(View.GONE);
        else
            recyclerHint.setVisibility(View.VISIBLE);
        tasksAdapter = new TasksAdapter(getApplicationContext(), tasks);
        tasksView.setAdapter(tasksAdapter);
    }
}
