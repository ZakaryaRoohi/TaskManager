package com.example.taskmanager.controller.fragment;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.taskmanager.R;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TasksRepository;
import com.example.taskmanager.utils.TaskState;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;


 class TasksFragment<EndlessRecyclerViewScrollListener> extends Fragment {
    /**
     * get username and number of Tasks from Task activity
     */

    public static final String ARG_TASK_STATE = "ARG_taskState";

    public static  final  int TASK_HOLDER_1=0;
    public static  final  int TASK_HOLDER_2=1;


    private String mTaskState;
    private TasksRepository mTasksRepository;

    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private FloatingActionButton mButtonAdd;
    public TasksFragment() {
        // Required empty public constructor
    }


    public static TasksFragment newInstance(TaskState taskState) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TASK_STATE, String.valueOf(taskState));
        fragment.setArguments(args);
        return fragment;
    }
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskState = getArguments().getString(ARG_TASK_STATE);
//        mNumberOfTasks = getArguments().getInt(ARG_NUMB_AER_OF_TASKS);
        mTasksRepository = TasksRepository.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        findViews(view);
        setClickListener();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        switch (getResources().getConfiguration().orientation) {
            case 1:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
            case 2:
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                break;
        }


        updateUI();

        return view;
    }


    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_tasks);
        mButtonAdd = (FloatingActionButton) view.findViewById(R.id.floating_action_button_add);
    }
    private void setClickListener(){
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTasksRepository.addTask();
                Toast.makeText(getActivity(),"repository size updated to " + mTasksRepository.getList().size(),Toast.LENGTH_SHORT).show();
                updateUI();
            }
        });
    }

    private class TaskHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTaskTittle;
        private TextView mTextViewTaskState;
        private Task mTask;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTaskTittle = itemView.findViewById(R.id.list_row_task_title);
            mTextViewTaskState = itemView.findViewById(R.id.list_row_Task_state);
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

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // create a view of CrimeHolder for Adapter

//            if (viewType == 0)
//                return new TaskHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_task, parent, false));
//            else
//                return new TaskHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_task_gray, parent, false));
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
        List<Task> tasks = mTasksRepository.getList();
        if (mAdapter == null) {
            mAdapter = new TaskAdapter(tasks);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTasks(tasks);
            mAdapter.notifyDataSetChanged();

        }

    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


}