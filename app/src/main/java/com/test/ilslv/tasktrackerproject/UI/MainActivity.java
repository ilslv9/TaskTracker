package com.test.ilslv.tasktrackerproject.UI;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.test.ilslv.tasktrackerproject.Domain.MainContract;
import com.test.ilslv.tasktrackerproject.Domain.Task;
import com.test.ilslv.tasktrackerproject.Domain.TaskStatus;
import com.test.ilslv.tasktrackerproject.R;
import com.test.ilslv.tasktrackerproject.TasksTrackerApp;
import com.test.ilslv.tasktrackerproject.UI.AddNewTaskActivity;
import com.test.ilslv.tasktrackerproject.UI.TasksAdapter;

import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    @BindView(R.id.tasksView)
    RecyclerView tasksView;
    @BindView(R.id.addNewTask)
    Button addNewTask;
    @BindView(R.id.recyclerHint)
    TextView recyclerHint;
    @BindView(R.id.statusFilter)
    Spinner statusFilter;
    TasksAdapter tasksAdapter;
    LinkedHashMap<String, TaskStatus> statusMap;
    String[] spinnerData = new String[4];

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

        statusMap = new LinkedHashMap<>();
        statusMap.put("Без фильтров", null);
        statusMap.put(TaskStatus.NEW.getName(), TaskStatus.NEW);
        statusMap.put(TaskStatus.INPROGRESS.getName(), TaskStatus.INPROGRESS);
        statusMap.put(TaskStatus.DONE.getName(), TaskStatus.DONE);
        spinnerData = statusMap.keySet().toArray(spinnerData);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, spinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusFilter.setAdapter(adapter);

        statusFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    mainPresenter.loadTasks();
                }else{
                    String item = (String) statusFilter.getSelectedItem();
                    TaskStatus filterStatus = statusMap.get(item);
                    mainPresenter.filterTasks(filterStatus);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
