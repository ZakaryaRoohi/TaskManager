package com.example.taskmanager.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskmanager.R;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.IRepository;
import com.example.taskmanager.repository.TaskDBRepository;
import com.example.taskmanager.repository.TasksRepository;
import com.example.taskmanager.utils.TaskState;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;


public class TasksFragment extends Fragment {
    /**
     * get username and number of Tasks from Task activity
     */

    public static final String ARG_TASK_STATE = "ARG_taskState";
    public static final String TASK_DETAIL_FRAGMENT_DIALOG_TAG = "TaskDetailFragmentDialogTag";
    public static final int TASK_DETAIL_REQUEST_CODE = 101;

    private IRepository mTasksRepository;
//    private TasksRepository mTasksRepository;
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private TaskState mTaskState;
    private LinearLayout mLinearLayout1;
    private LinearLayout mLinearLayout2;


    public TasksFragment() {
        // Required empty public constructor
    }
    public static TasksFragment newInstance(TaskState taskState) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_STATE, taskState);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskState = (TaskState) getArguments().getSerializable(ARG_TASK_STATE);
//        mTasksRepository = TasksRepository.getInstance();
        mTasksRepository = TaskDBRepository.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        findViews(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }



    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_tasks);
        mLinearLayout2 = view.findViewById(R.id.layout2);
        mLinearLayout1 = view.findViewById(R.id.layout1);
    }

    private class TaskHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTaskTittle;
        private TextView mTextViewTaskState;
        private Task mTask;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTaskTittle = itemView.findViewById(R.id.list_row_task_title);
            mTextViewTaskState = itemView.findViewById(R.id.list_row_Task_state);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mCrime.getDate());
//
//                    //create parent-child relations between CrimeDetailFragment-DatePickerFragment
//                    datePickerFragment.setTargetFragment(CrimeDetailFragment.this, DATE_PICKER_REQUEST_CODE);
//
//                    datePickerFragment.show(getFragmentManager(), DATE_DIALOG_FRAGMENT_TAG);
                    TaskDetailFragment taskDetailFragment =  TaskDetailFragment.newInstance();
                    taskDetailFragment.setTargetFragment(TasksFragment.this,TASK_DETAIL_REQUEST_CODE);
                    taskDetailFragment.show(getFragmentManager(), TASK_DETAIL_FRAGMENT_DIALOG_TAG);


                }
            });
        }

        public void bindTask(Task task) {
            mTask = task;
            if (getAdapterPosition()%2==1)
                itemView.setBackgroundColor(Color.GRAY);
            else
                itemView.setBackgroundColor(Color.WHITE);

            mTextViewTaskTittle.setText(task.getTitle());
            mTextViewTaskState.setText(task.getTaskState().toString());
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<Task> mTasks;
        private void setTasks(List<Task> tasks){mTasks=tasks;}
        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_row_task, parent, false);
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.bindTask(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }

    private void updateUI() {
        List<Task> tasks = mTasksRepository.getList(mTaskState);
//        if (mAdapter == null) {
        mAdapter = new TaskAdapter(tasks);
        mAdapter.setTasks(tasks);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

//        } else {mAdapter.setTasks(tasks);
//            mAdapter.notifyDataSetChanged();
//        }
        if (tasks.size() == 0) {
//            Toast.makeText(getActivity(), "repository is cleared "+mRepository.getList().size(), Toast.LENGTH_SHORT).show();
            mLinearLayout1.setVisibility(View.GONE);
            mLinearLayout2.setVisibility(View.VISIBLE);
        } else if (mTasksRepository.getList().size() != 0) {
            mLinearLayout1.setVisibility(View.VISIBLE);
            mLinearLayout2.setVisibility(View.GONE);
        }

    }


}