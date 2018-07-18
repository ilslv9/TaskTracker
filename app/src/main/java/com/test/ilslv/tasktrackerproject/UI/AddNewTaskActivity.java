package com.test.ilslv.tasktrackerproject.UI;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.test.ilslv.tasktrackerproject.Domain.Task;
import com.test.ilslv.tasktrackerproject.Domain.TaskCreateContract;
import com.test.ilslv.tasktrackerproject.Domain.TaskStatus;
import com.test.ilslv.tasktrackerproject.R;
import com.test.ilslv.tasktrackerproject.TasksTrackerApp;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNewTaskActivity extends AppCompatActivity implements TaskCreateContract.TaskCreateView,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.titleControl)
    EditText titleControl;
    @BindView(R.id.dateControl)
    Button dateControl;
    @BindView(R.id.statusControl)
    Spinner statusControl;
    @BindView(R.id.desriptionControl)
    EditText descriptionControl;
    @BindView(R.id.addOrEditTask)
    Button addTask;
    @Inject
    TaskCreateContract.TaskCreatePresenter taskCreatePresenter;

    Date taskDate;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    private int taskYear;
    private int taskMonth;
    private int taskDay;
    private int taskHour;
    private int taskMinuets;
    LinkedHashMap<String, TaskStatus> statusMap;
    String[] spinnerData = new String[3];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_task);
        ButterKnife.bind(this);
        TasksTrackerApp.getTasksTrackerComponent().inject(this);
        taskCreatePresenter.onCreate(this);


        statusMap = new LinkedHashMap<>();
        statusMap.put("Новая", TaskStatus.NEW);
        statusMap.put("В процессе", TaskStatus.INPROGRESS);
        statusMap.put("Выполнена", TaskStatus.DONE);
        spinnerData = statusMap.keySet().toArray(spinnerData);

        taskDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        Locale loc = new Locale("ru");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", loc);
        format.setTimeZone(TimeZone.getDefault());
        dateControl.setText(format.format(taskDate).toString());

        datePickerDialog = new DatePickerDialog(
                this,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        timePickerDialog = new TimePickerDialog(
                this,
                this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);

        dateControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.create();
                datePickerDialog.show();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusControl.setAdapter(adapter);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = (String) statusControl.getSelectedItem();
                taskCreatePresenter.createTask(
                        titleControl.getText().toString(),
                        taskDate,
                        statusMap.get(position),
                        descriptionControl.getText().toString());
            }
        });
    }

    @Override
    public void showResult() {
        this.finish();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        taskYear = year - 1900;
        taskMonth = month;
        taskDay = day;
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minuets) {
        taskHour = hour;
        taskMinuets = minuets;
        taskDate = new Date(taskYear, taskMonth, taskDay, hour, minuets);
        Locale loc = new Locale("ru");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", loc);
        format.setTimeZone(TimeZone.getDefault());
        dateControl.setText(format.format(taskDate).toString());
    }
}
