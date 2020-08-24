package com.example.taskmanager.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskmanager.R;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.IRepository;
import com.example.taskmanager.repository.TaskDBRepository;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetailFragment extends DialogFragment {

    public static final String ARG_TASK_ID = "ARGTaskId";
    private IRepository mTasksRepository;
    private Task mTask;

    private EditText mEditTextTaskTitle;
    private EditText mEditTextDescription;
    private Button mButtonStartDate;
    private Button mButtonStartTime;
    private Button mButtonEndDate;
    private Button mButtonEndTime;
    private Button mButtonSave;
    private Button mButtonDiscard;

    public TaskDetailFragment() {
        // Required empty public constructor
    }


    public static TaskDetailFragment newInstance(UUID taskId) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTasksRepository = TaskDBRepository.getInstance(getActivity());
        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        mTask = (Task) mTasksRepository.get(taskId);
//        Toast.makeText(getActivity(),"uuid"+mTask.getId(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_task_detail, container, false);

        findViews(view);
        return view;
    }
    private void findViews(View view){
        mEditTextTaskTitle=view.findViewById(R.id.task_title);
        mEditTextDescription=view.findViewById(R.id.text_view_description);
        mButtonStartDate=view.findViewById(R.id.start_task_date);
        mButtonStartTime=view.findViewById(R.id.start_task_time);
        mButtonEndDate=view.findViewById(R.id.end_task_date);
        mButtonEndTime=view.findViewById(R.id.end_task_time);
        mButtonSave=view.findViewById(R.id.button_save);
        mButtonDiscard=view.findViewById(R.id.button_discard);
    }
    private void  initViews(){

    }
}