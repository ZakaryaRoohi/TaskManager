package com.example.taskmanager.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.taskmanager.R;
import com.example.taskmanager.repository.IRepository;
import com.example.taskmanager.repository.TaskDBRepository;
import com.example.taskmanager.repository.TasksRepository;
import com.example.taskmanager.utils.TaskState;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Random;

public class TaskPagerFragment extends Fragment {

    TaskPagerAdapter mTaskPagerAdapter;
    ViewPager2 mViewPager2;

    private FloatingActionButton mFloatingActionButtonAdd;
//    private TasksRepository mTasksRepository;
    private IRepository mTasksRepository;

    public TaskPagerFragment() {
        // Required empty public constructor
    }

    public static TaskPagerFragment newInstance() {
        TaskPagerFragment fragment = new TaskPagerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mTasksRepository = TasksRepository.getInstance();
        mTasksRepository = TaskDBRepository.getInstance(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_pager, container, false);
    }

    String[] title = {"Done", "Doing", "Todo"};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTaskPagerAdapter = new TaskPagerAdapter(this);
        mViewPager2 = view.findViewById(R.id.pager);
        mViewPager2.setAdapter(mTaskPagerAdapter);
//
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, mViewPager2,
                (tab, position) -> tab.setText(title[position])
        ).attach();

        mFloatingActionButtonAdd = (FloatingActionButton) view.findViewById(R.id.floating_action_button_add);
        setListeners();
    }

    private void setListeners() {
        mFloatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskState taskState = randomTaskState();
                mTasksRepository.addTask(taskState);
            }
        });
    }

    public class TaskPagerAdapter extends FragmentStateAdapter {
        public TaskPagerAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override

        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return TasksFragment.newInstance(TaskState.DONE);
                case 1:
                    return TasksFragment.newInstance(TaskState.DOING);

                case 2:
                    return TasksFragment.newInstance(TaskState.TODO);

            }
            return null;
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

    private  static TaskState randomTaskState() {
        Random random = new Random();
        int rand = random.nextInt(3);
        if (rand == 0)
            return TaskState.DONE;
        if (rand == 1)
            return TaskState.DOING;
        else
            return TaskState.TODO;
    }

}